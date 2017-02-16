package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisWindow;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.logic.input.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;
import java.util.LinkedList;

public abstract class AbstractMenuScene<M extends AbstractScene.SceneDelegate> extends AbstractScene<M> implements MenuNavigationInputAdapter.MenuNavigationListener {

    private static final String TAG = AbstractMenuScene.class.getSimpleName();

    private static final float WINDOW_TITLE_PADDING = 5F;

    @Inject
    StringsController stringsController;
    @Inject
    SkinController skinController;

    private VisWindow window;
    private ClickListener clickListener;

    private LinkedList<Actor> menuItems = new LinkedList<>();
    private LinkedList<Actor> labelItems = new LinkedList<>();
    private ChangeListener changeListener;

    private boolean paused = false;

    public AbstractMenuScene(Stage stage) {
        super(stage);
    }

    void init() {
        Skin skin = skinController.getSkin();

        window = new VisWindow(getWindowTitle(), skin.get("default", Window.WindowStyle.class));

        window.getTitleTable().align(Align.topLeft).padLeft(15).padTop(WINDOW_TITLE_PADDING);

        window.add().height(WINDOW_TITLE_PADDING + window.getTitleLabel().getHeight() + WINDOW_TITLE_PADDING);
        window.row();

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

        window.pack();
        window.centerWindow();
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

    public TextButton produceButton(String name) {
        TextButton button = new TextButton(getText(name), getSkinController().getSkin());
        button.setName(name);
        return button;
    }

    public TextField produceTextField(String name) {
        TextField textField = new TextField(getText(name), getSkinController().getSkin());
        textField.setName(name);
        return textField;
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

        window.centerWindow();
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
        if (menuItems.size() == 0) {
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
        if (menuItems.size() == 0) {
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


}