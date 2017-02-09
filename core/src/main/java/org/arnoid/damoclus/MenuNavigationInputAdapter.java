package org.arnoid.damoclus;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import org.arnoid.damoclus.ui.scene.AbstractScene;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;

import java.util.ArrayList;

public class MenuNavigationInputAdapter extends InputAdapter implements ControllerListener {

    public ArrayList<AbstractMenuScene> listeners = new ArrayList<>();

    public MenuNavigationInputAdapter() {
        Controllers.addListener(this);
    }

    public void addListner(AbstractMenuScene listener) {
        listeners.add(listener);
    }

    public void removeListener(AbstractMenuScene listener) {
        listeners.remove(listener);
    }

    public void registerMenuNavigation(AbstractScene scene) {
        if (scene != null && scene instanceof AbstractMenuScene) {
            addListner((AbstractMenuScene) scene);
        }
    }

    public void unregisterMenuNavigation(AbstractScene scene) {
        if (scene != null && scene instanceof AbstractMenuScene) {
            removeListener((AbstractMenuScene) scene);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                previous();
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                next();
                break;
            case Input.Keys.ENTER:
            case Input.Keys.BUTTON_A:
                interact();
                break;
        }
        return true;
    }

    private void previous() {
        for (AbstractMenuScene listener : new ArrayList<>(listeners)) {
            listener.onPrev();
        }
    }

    private void next() {
        for (AbstractMenuScene listener : new ArrayList<>(listeners)) {
            listener.onNext();
        }
    }

    private void interact() {
        for (AbstractMenuScene listener : new ArrayList<>(listeners)) {
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
    }
}
