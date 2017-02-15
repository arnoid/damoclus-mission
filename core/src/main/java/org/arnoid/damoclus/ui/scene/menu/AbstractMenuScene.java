package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.logic.input.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;
import java.util.LinkedList;

public abstract class AbstractMenuScene<M extends AbstractScene.SceneDelegate> extends AbstractScene<M> implements MenuNavigationInputAdapter.MenuNavigationListener {

    private static final String TAG = AbstractMenuScene.class.getSimpleName();

    @Inject
    StringsController stringsController;
    @Inject
    SkinController skinController;

    private Window window;
    private SpriteBatch spriteBatch;
    private ClickListener clickListener;

    private LinkedList<Actor> menuItems = new LinkedList<>();
    private LinkedList<Actor> labelItems = new LinkedList<>();
    private ChangeListener changeListener;

    private boolean paused = false;

    public AbstractMenuScene(Stage stage) {
        super(stage);
    }

    void init() {
        spriteBatch = new SpriteBatch();

        Skin skin = skinController.getSkin();

        window = new Window(getWindowTitle(), skin);
        window.setDebug(true);
        window.getTitleTable().padLeft(5).align(Align.left);
        window.align(Align.topLeft);

        window.setPosition(0, 0);

        clickListener = new ClickListener() {

            boolean mouseMode = false;

            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                if (mouseMode) {
                    return true;
                } else {
                    mouseMode = true;

                    for (Actor actor : menuItems) {
                        markNotSelected(actor);
                    }
                    return super.mouseMoved(event, x, y);
                }
            }

            public void clicked(InputEvent event, float x, float y) {
                Actor listenerActor = event.getListenerActor();
                if (listenerActor != null && listenerActor.getName() != null) {
                    AbstractMenuScene.this.clicked(listenerActor, event);
                }
            }

            @Override
            public boolean keyTyped(InputEvent event, char character) {
                mouseMode = false;
                return super.keyTyped(event, character);
            }

        };

        changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Actor listenerActor = event.getListenerActor();
                if (listenerActor != null && listenerActor.getName() != null) {
                    AbstractMenuScene.this.changed(listenerActor, event);
                }
            }
        };

        window.addListener(clickListener);

        produceMenuContent(window, skin);

        if (menuItems.size() > 0) {
            Actor firstButton = menuItems.get(0);
            getStage().setKeyboardFocus(firstButton);
        }
    }

    @Override
    public void pause() {
        super.pause();
        paused = true;
    }

    @Override
    public void resume() {
        super.resume();
        paused = false;
    }

    public boolean isPaused() {
        return paused;
    }

    public Window getWindow() {
        return window;
    }

    protected void produceMenuContent(Window window, Skin skin) {
        produceMenuItems();
    }

    @Override
    public void show() {
        window.setTouchable(Touchable.enabled);

        window.getTitleLabel().setText(getWindowTitle());

        for (Actor actor : menuItems) {
            if (actor instanceof TextButton) {
                TextButton button = (TextButton) actor;
                button.setText(getText(button.getName()));
            }
        }

        for (Actor actor : labelItems) {
            if (actor instanceof TextButton) {
                ((TextButton) actor).setText(getText(actor.getName()));
            } else if (actor instanceof Label) {
                ((Label) actor).setText(getText(actor.getName()));
            } else if (actor instanceof SelectBox) {
                Object[] selectBoxArray = getSelectBoxArray(actor.getName());
                if (selectBoxArray != null) {
                    SelectBox selectBox = (SelectBox) actor;
                    selectBox.setItems(selectBoxArray);
                    if (selectBoxArray.length > 0) {
                        selectBox.setSelectedIndex(0);
                    }
                }
            }
        }

        getStage().addActor(window);
    }

    protected String getText(String name) {
        return getStringsController().string(name);
    }

    protected Object[] getSelectBoxArray(String name) {
        return new String[]{};
    }

    @Override
    public void hide() {
        window.setTouchable(Touchable.disabled);
        window.remove();
    }

    protected abstract void clicked(Actor actor, InputEvent event);

    protected abstract void changed(Actor actor, ChangeListener.ChangeEvent event);

    protected abstract String getWindowTitle();

    protected abstract void produceMenuItems();

    protected float getCellWidth(int column) {
        return 0;
    }

    public float getCellHeight(int column) {
        return 0;
    }

    public void registerMenuItemListeners(Actor actor) {
        actor.addListener(clickListener);
        actor.addListener(changeListener);
    }

    public void registerInMenuItems(Actor actor) {
        menuItems.add(actor);

        if (menuItems.size() == 1) {
            markSelected(menuItems.peek());
        }
    }

    public void registerLabeledActor(Actor producedActor) {
        labelItems.add(producedActor);
    }

    public StringsController getStringsController() {
        return stringsController;
    }

    public SkinController getSkinController() {
        return skinController;
    }

    public Table produceGroupTable(String name) {
        Table table = new Table();
        table.setName(name);
        return table;
    }

    public VerticalGroup produceGroupVertical(String name) {
        VerticalGroup verticalGroup = new VerticalGroup();
        verticalGroup.setName(name);
        return verticalGroup;
    }

    public HorizontalGroup produceGroupHorizontal(String name) {
        HorizontalGroup horizontalGroup = new HorizontalGroup();
        horizontalGroup.setName(name);
        return horizontalGroup;
    }

    public TextButton produceButton(String name) {
        TextButton button = new TextButton(getText(name), getSkinController().getSkin());
        button.setName(name);
        return button;
    }

    public Label produceLabel(String name) {
        Label label = new Label(getText(name), getSkinController().getSkin());
        label.setName(name);
        return label;
    }

    public <T> SelectBox<T> produceSelectBox(String name) {
        final SelectBox<T> selectBox = new SelectBox<>(getSkinController().getSkin());

        selectBox.setName(name);

        selectBox.addListener(changeListener);
        return selectBox;
    }

    public ScrollPane produceScroll(String name) {
        final ScrollPane scrollPane = new ScrollPane(null, getSkinController().getSkin());
        scrollPane.setName(name);

        return scrollPane;
    }

    public ImageButton produceCheckBox(String name) {
        final ImageButton checkBox = new ImageButton(getSkinController().getSkin(), getSkinController().getImgByttonCheckBoxStyle());

        checkBox.setName(name);

        checkBox.addListener(changeListener);
        return checkBox;
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        window.setSize(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        if (window.hasParent()) {
            window.draw(spriteBatch, 1);
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        super.dispose();

        spriteBatch.dispose();
    }

    private void markNotSelected(Actor actor) {
        InputEvent inputEvent = new InputEvent();
        inputEvent.setListenerActor(actor);

        for (EventListener listener : actor.getListeners()) {
            if (listener instanceof InputListener) {
                ((InputListener) listener).exit(null, 0, 0, -1, actor);
            }
        }
    }

    private void markSelected(Actor actor) {
        for (EventListener listener : actor.getListeners()) {
            if (listener instanceof InputListener) {
                ((InputListener) listener).enter(null, 0, 0, -1, actor);
            }
        }
    }

    @Override
    public void onNext() {
        if (isPaused()) {
            return;
        }
        Actor actor = menuItems.removeFirst();

        menuItems.addLast(actor);

        markNotSelected(actor);

        markSelected(menuItems.peek());
    }

    @Override
    public void onPrev() {
        if (isPaused()) {
            return;
        }
        markNotSelected(menuItems.peek());

        Actor actor = menuItems.removeLast();

        markSelected(actor);

        menuItems.addFirst(actor);
    }

    @Override
    public void onInteract() {
        if (isPaused()) {
            return;
        }
        clicked(menuItems.peek(), new InputEvent());
    }

    public void onActorProduced(String name, Actor producedActor) {

    }

    public void produceGrowYCell() {
        window.add().growY();
    }

    public void produceSpace() {
        window.add().height(getCellHeight(0));
    }

}
