package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.logic.input.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractMenuScene<M extends AbstractScene.SceneDelegate> extends AbstractScene<M> implements MenuNavigationInputAdapter.MenuNavigationListener {

    private static final String TAG = AbstractMenuScene.class.getSimpleName();

    @Inject
    StringsController stringsController;
    @Inject
    SkinController skinController;

    @Inject
    MenuNavigationInputAdapter menuNavigationInputAdapter;
    @Inject
    InputMultiplexer inputMultiplexer;

    private ClickListener clickListener;

    private LinkedList<Actor> menuItems = new LinkedList<>();
    private ChangeListener changeListener;

    private AtomicBoolean paused = new AtomicBoolean(false);

    public AbstractMenuScene() {
        super();
    }

    void init() {
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
                if (!paused.get() && listenerActor != null && listenerActor.getName() != null) {
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

        getStage().addListener(clickListener);
        getStage().addListener(changeListener);

        produceLayout();
        postProduceLayout();

        if (menuItems.size() > 0) {
            Actor firstButton = menuItems.get(0);
            getStage().setKeyboardFocus(firstButton);
        }
    }

    @Override
    public void pause() {
        super.pause();
        paused.set(true);
        inputMultiplexer.removeProcessor(menuNavigationInputAdapter);
    }

    @Override
    public void resume() {
        super.resume();
        paused.set(false);
        inputMultiplexer.addProcessor(menuNavigationInputAdapter);
    }

    @Override
    public void show() {
        super.show();

        menuNavigationInputAdapter.registerMenuNavigation(this);

        getStage().addAction(
                Actions.sequence(
                        Actions.alpha(0, 0),
                        Actions.fadeIn(0.5F)
                )
        );
    }

    @Override
    public void hide() {
        super.hide();

        menuNavigationInputAdapter.unregisterMenuNavigation(this);

        getStage().addAction(
                Actions.fadeOut(0.5F)
        );
    }

    protected abstract void clicked(Actor actor, InputEvent event);

    protected abstract void changed(Actor actor, ChangeListener.ChangeEvent event);

    protected abstract void produceLayout();

    public abstract void postProduceLayout();

    public void registerMenuItem(Actor actor) {
        menuItems.add(actor);

        if (menuItems.size() == 1) {
            markSelected(menuItems.peek());
        }

        actor.addListener(clickListener);
    }

    public StringsController getStringsController() {
        return stringsController;
    }

    public SkinController getSkinController() {
        return skinController;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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
        if (paused.get()) {
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
        if (paused.get()) {
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
        if (paused.get()) {
            return;
        }
        clicked(menuItems.peek(), new InputEvent());
    }

    @Override
    public MenuNavigationInputAdapter getInputAdapter() {
        return menuNavigationInputAdapter;
    }
}