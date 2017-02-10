package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.logic.handler.menu.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;
import java.util.LinkedList;

public abstract class AbstractMenuScene<M extends AbstractScene.SceneController> extends AbstractScene<M> implements MenuNavigationInputAdapter.MenuNavigationListener {

    private static final String TAG = AbstractMenuScene.class.getSimpleName();

    @Inject
    StringsController stringsController;
    @Inject
    SkinController skinController;

    private Window window;
    private SpriteBatch spriteBatch;
    private ClickListener clickListener;

    private LinkedList<TextButton> menuItems = new LinkedList<>();

    public AbstractMenuScene(Stage stage) {
        super(stage);
    }

    protected void init() {
        spriteBatch = new SpriteBatch();

        Skin skin = skinController.getSkin();

        window = new Window(getWindowTitle(), skin);
        window.getTitleTable().padLeft(5);

        window.setPosition(0, 0);

        clickListener = new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Actor listenerActor = event.getListenerActor();
                if (listenerActor != null && listenerActor.getName() != null) {
                    AbstractMenuScene.this.clicked(listenerActor, event);
                }
            }
        };

        produceMenuContent(window, skin);

        if (menuItems.size() > 0) {
            Actor firstButton = menuItems.get(0);
            getStage().setKeyboardFocus(firstButton);
        }
    }

    public Window getWindow() {
        return window;
    }

    protected void produceMenuContent(Window window, Skin skin) {
        produceMenuButtons();
    }

    @Override
    public void show() {
        window.setTouchable(Touchable.enabled);

        window.getTitleLabel().setText(getWindowTitle());

        for (TextButton button : menuItems) {
            button.setText(getButtonLabel(button.getName()));
        }

        getStage().addActor(window);
    }

    protected String getButtonLabel(String name) {
        return getStringsController().string(name);
    }

    @Override
    public void hide() {
        window.setTouchable(Touchable.disabled);
        window.remove();
    }

    protected abstract void clicked(Actor actor, InputEvent event);

    protected abstract String getWindowTitle();

    protected abstract void produceMenuButtons();

    protected float getButtonsWidth() {
        return 0;
    }

    protected float getButtonsHeight() {
        return 0;
    }

    protected float getButtonsSpacing() {
        return 0;
    }

    protected TextButton appendMenuButton(TextButton button) {
        return appendMenuButton(button, Align.topLeft);
    }

    protected TextButton appendMenuButton(TextButton button, int align) {
        Cell<Button> cell = window.align(align).add(button);

        float buttonsWidth = getButtonsWidth();

        if (buttonsWidth > 0) {
            cell.width(buttonsWidth);
        }

        float buttonsHeight = getButtonsHeight();

        if (buttonsHeight > 0) {
            cell.height(buttonsHeight);
        }

        window.row();
        window.add().height(getButtonsSpacing()).row();

        menuItems.add(button);

        if (menuItems.size() == 1) {
            markSelected(menuItems.peek());
        }

        button.addListener(clickListener);

        return button;
    }

    public StringsController getStringsController() {
        return stringsController;
    }

    public SkinController getSkinController() {
        return skinController;
    }

    protected TextButton produceButton(String name) {
        TextButton button = new TextButton(getButtonLabel(name), getSkinController().getSkin());
        button.setName(name);
        return button;
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

    public void selectNextButton() {
        TextButton button = menuItems.removeFirst();

        menuItems.addLast(button);

        markNotSelected(button);

        markSelected(menuItems.peek());
    }

    public void selectPreviousButton() {
        markNotSelected(menuItems.peek());

        TextButton button = menuItems.removeLast();

        markSelected(button);

        menuItems.addFirst(button);
    }

    private void markNotSelected(Button button) {
        InputEvent inputEvent = new InputEvent();
        inputEvent.setListenerActor(button);
        button.getClickListener().exit(inputEvent, 0, 0, -1, button);
    }

    private void markSelected(Button button) {
        button.getClickListener().enter(null, 0, 0, -1, button);
    }

    @Override
    public void onNext() {
        selectNextButton();
    }

    @Override
    public void onPrev() {
        selectPreviousButton();
    }

    @Override
    public void onInteract() {
        clicked(menuItems.peek(), new InputEvent());
    }
}
