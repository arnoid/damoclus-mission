package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.EventAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.MenuNavigationInputAdapter;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import java.util.LinkedList;

public abstract class AbstractMenuScene<M extends AbstractScene.SceneController> extends AbstractScene<M> implements MenuNavigationInputAdapter.MenuNavigationListener {

    private static final String TAG = AbstractMenuScene.class.getSimpleName();

    private final SkinController skinController;
    private final StringsController stringsController;

    private final Window window;
    private final SpriteBatch spriteBatch;
    private final ClickListener clickListener;

    private LinkedList<TextButton> menuButtons = new LinkedList<>();

    public AbstractMenuScene(DamoclusGdxGame game, Stage stage) {
        super(game, stage);

        skinController = game.skinController;
        stringsController = game.stringsController;

        spriteBatch = new SpriteBatch();

        Skin skin = skinController.getSkin();

        window = new Window(getWindowTitle(stringsController), skin);
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

        produceMenuButtons(skin, stringsController);

        window.setModal(true);
        window.setMovable(true);

        if (menuButtons.size() > 0) {
            Button firstButton = menuButtons.get(0);
            stage.setKeyboardFocus(firstButton);
        }
    }

    @Override
    public void show() {
        window.setTouchable(Touchable.enabled);
        getStage().addActor(window);
    }

    @Override
    public void hide() {
        window.setTouchable(Touchable.disabled);
        window.remove();
    }

    protected abstract void clicked(Actor actor, InputEvent event);

    protected abstract String getWindowTitle(StringsController stringsController);

    protected abstract void produceMenuButtons(Skin skin, StringsController stringsController);

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
        Cell<TextButton> cell = window.align(align).add(button);

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

        menuButtons.add(button);

        if (menuButtons.size() == 1) {
            markSelected(menuButtons.peek());
        }

        button.addListener(clickListener);

        return button;
    }

    protected TextButton produceButton(Skin skin, StringsController stringsController, String name) {
        TextButton textButton = new TextButton(stringsController.string(name), skin);
        textButton.setName(name);
        return textButton;
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
        TextButton button = menuButtons.removeFirst();

        menuButtons.addLast(button);

        markNotSelected(button);

        markSelected(menuButtons.peek());
    }

    public void selectPreviousButton() {
        markNotSelected(menuButtons.peek());

        TextButton button = menuButtons.removeLast();

        markSelected(button);

        menuButtons.addFirst(button);
    }

    private void markNotSelected(TextButton button) {
        InputEvent inputEvent = new InputEvent();
        inputEvent.setListenerActor(button);
        button.getClickListener().exit(inputEvent, 0, 0, -1, button);
    }

    private void markSelected(TextButton button) {
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
        clicked(menuButtons.peek(), new InputEvent());
    }
}
