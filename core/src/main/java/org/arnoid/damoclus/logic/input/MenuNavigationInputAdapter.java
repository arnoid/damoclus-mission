package org.arnoid.damoclus.logic.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.data.configuration.UserControllerMap;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MenuNavigationInputAdapter extends InputAdapter implements ControllerListener {

    private static final String TAG = MenuNavigationInputAdapter.class.getSimpleName();

    @Inject
    ConfigurationController configurationController;
    private Set<MenuNavigationListener> listeners = new HashSet<>();

    public MenuNavigationInputAdapter(ConfigurationController configurationController) {
        this.configurationController = configurationController;

        Controllers.addListener(this);
        configurationController.read();
    }

    public void addListener(MenuNavigationListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MenuNavigationListener listener) {
        listeners.remove(listener);
    }

    public void registerMenuNavigation(MenuNavigationListener listener) {
        if (listener != null) {
            addListener(listener);
            Gdx.app.debug(TAG, "Registered listener [" + listener + "]");
        }
    }

    public void unregisterMenuNavigation(MenuNavigationListener listener) {
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
            interact();
        } else {

        }

        return true;
    }

    private UserControllerMap getKeyboardMap() {
        return configurationController.read().getKeyboardMapping();
    }

    private void previous() {
        for (MenuNavigationListener listener : new ArrayList<>(listeners)) {
            listener.onPrev();
        }
    }

    private void next() {
        for (MenuNavigationListener listener : new ArrayList<>(listeners)) {
            listener.onNext();
        }
    }

    private void interact() {
        for (MenuNavigationListener listener : new ArrayList<>(listeners)) {
            listener.onInteract();
        }
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

    public interface MenuNavigationListener {
        void onNext();

        void onPrev();

        void onInteract();

        MenuNavigationInputAdapter getInputAdapter();
    }

}
