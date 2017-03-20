package org.arnoid.damoclus.logic.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.data.configuration.UserControllerMap;
import org.arnoid.damoclus.ui.view.UiUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class NavigationInputAdapter extends InputAdapter implements ControllerListener {

    public static final String TAG = NavigationInputAdapter.class.getSimpleName();

    private final ClickListener clickListener;
    private LinkedList<Actor> menuItems = new LinkedList<>();
    private AtomicBoolean paused = new AtomicBoolean(false);
    private ConfigurationController configurationController;

    private Set<NavigationListener> listeners = new HashSet<>();
    private Stage stage;

    public NavigationInputAdapter(ConfigurationController configurationController) {
        Controllers.addListener(this);

        this.configurationController = configurationController;

        configurationController.read();

        this.clickListener = new ClickListener() {

            boolean mouseMode = false;

            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                if (mouseMode) {
                    return true;
                } else {
                    mouseMode = true;

                    for (Actor actor : menuItems) {
                        UiUtil.markNotSelected(actor);
                    }
                    return super.mouseMoved(event, x, y);
                }
            }

            public void clicked(InputEvent event, float x, float y) {
                Actor listenerActor = event.getListenerActor();
                if (!paused.get() && listenerActor != null && listenerActor.getName() != null) {
                    interact(listenerActor, event);
                }
            }

            @Override
            public boolean keyTyped(InputEvent event, char character) {
                mouseMode = false;
                return super.keyTyped(event, character);
            }

        };
    }

    public void registerStage(Stage stage) {
        stage.addListener(clickListener);
    }

    public void addListener(NavigationListener listener) {
        listeners.add(listener);
    }

    public void removeListener(NavigationListener listener) {
        listeners.remove(listener);
    }

    public void registerMenuNavigation(NavigationListener listener) {
        if (listener != null) {
            addListener(listener);
            Gdx.app.debug(TAG, "Registered listener [" + listener + "]");
        }
    }

    public void unregisterMenuNavigation(NavigationListener listener) {
        if (listener != null) {
            removeListener(listener);
            Gdx.app.debug(TAG, "Unregistered listener [" + listener + "]");
        }
    }

    @Override
    public boolean keyDown(int character) {
        UserControllerMap keyboardMap = getKeyboardMap();

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(keyboardMap.getUp())) {
            previous();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(keyboardMap.getDown())) {
            next();
        } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isKeyPressed(keyboardMap.getInteract())) {
            InputEvent inputEvent = new InputEvent();
            inputEvent.setType(InputEvent.Type.keyDown);

            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                inputEvent.setCharacter((char) Input.Keys.ENTER);
            } else {
                inputEvent.setCharacter((char) keyboardMap.getInteract());
            }

            interact(inputEvent);
        } else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyPressed(keyboardMap.getBack())) {
            back();
        } else {
        }

        return true;
    }

    private UserControllerMap getKeyboardMap() {
        return configurationController.read().getKeyboardMapping();
    }

    private void interact(InputEvent event) {
        Actor interactedActor = menuItems.peek();

        interact(interactedActor, event);
    }

    private void interact(Actor interactedActor, InputEvent event) {
        for (NavigationListener listener : new ArrayList<>(listeners)) {
            listener.onInteract(interactedActor, event);
        }
    }

    private void back() {
        for (NavigationListener listener : new ArrayList<>(listeners)) {
            listener.onBack();
        }
    }

    private void next() {
        if (paused.get()) {
            return;
        }

        for (NavigationListener listener : new ArrayList<>(listeners)) {
            listener.onNext();
        }

        if (menuItems.size() == 0) {
            return;
        }
        Actor actor = menuItems.removeFirst();

        menuItems.addLast(actor);

        UiUtil.markNotSelected(actor);

        UiUtil.markSelected(menuItems.peek());
    }

    private void previous() {
        if (paused.get()) {
            return;
        }

        for (NavigationListener listener : new ArrayList<>(listeners)) {
            listener.onPrevious();
        }

        if (menuItems.size() == 0) {
            return;
        }

        UiUtil.markNotSelected(menuItems.peek());

        Actor actor = menuItems.removeLast();

        UiUtil.markSelected(actor);

        menuItems.addFirst(actor);
    }

    public void unregisterMenuItems() {
        menuItems.clear();
    }

    public void registerMenuItem(Actor actor) {
        menuItems.add(actor);

        if (menuItems.size() == 1) {
            UiUtil.markSelected(menuItems.peek());
        }

        actor.addListener(clickListener);
    }

    public AtomicBoolean getPaused() {
        return paused;
    }

    public void setPaused(AtomicBoolean paused) {
        this.paused = paused;
    }

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        if (value < -0.9) {
            next();
        } else if (value > 0.9) {
            previous();
        }

        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

    public LinkedList<Actor> getMenuItems() {
        return menuItems;
    }
}
