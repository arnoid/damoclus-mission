package org.arnoid.damoclus.data.configuration;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;

public class UserControllerMap extends AbstractUserControllerMap {

    public static UserControllerMap defaultKeyboardInstance() {
        UserControllerMap controllerMap = new UserControllerMap();

        controllerMap.up = Input.Keys.UP;
        controllerMap.down = Input.Keys.DOWN;

        controllerMap.left = Input.Keys.LEFT;
        controllerMap.right = Input.Keys.RIGHT;

        controllerMap.interact = Input.Keys.E;
        controllerMap.inventory = Input.Keys.I;

        controllerMap.menu = Input.Keys.ESCAPE;
        controllerMap.back = Input.Keys.ESCAPE;

        return controllerMap;
    }

    public static UserControllerMap defaultControllerInstance() {
        UserControllerMap controllerMap = new UserControllerMap();

        controllerMap.up = Input.Keys.UP;
        controllerMap.down = Input.Keys.DOWN;

        controllerMap.left = Input.Keys.LEFT;
        controllerMap.right = Input.Keys.RIGHT;

        controllerMap.interact = Input.Keys.E;
        controllerMap.inventory = Input.Keys.I;

        controllerMap.menu = Input.Keys.ESCAPE;
        controllerMap.back = Input.Keys.ESCAPE;

        return controllerMap;
    }

    public UserControllerMap clone() {
        UserControllerMap clone = new UserControllerMap();
        clone.up = up;
        clone.down = down;

        clone.left = left;
        clone.right = right;

        clone.interact = interact;
        clone.inventory = inventory;

        clone.menu = menu;
        clone.back = back;

        return clone;
    }

}
