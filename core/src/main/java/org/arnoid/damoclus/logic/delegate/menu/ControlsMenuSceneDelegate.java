package org.arnoid.damoclus.logic.delegate.menu;

import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.data.configuration.Configuration;
import org.arnoid.damoclus.data.configuration.UserControllerMap;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;

public class ControlsMenuSceneDelegate extends AbstractScene.SceneDelegate {
    private static final String TAG = ControlsMenuSceneDelegate.class.getSimpleName();

    @Inject
    ConfigurationController configurationController;
    private final UserControllerMap keyboardMapping;
    private final UserControllerMap controllerMap;

    public ControlsMenuSceneDelegate(DamoclusGdxGame game) {
        super(game);

        game.mainComponent().inject(this);

        keyboardMapping = configurationController.read().getKeyboardMapping().clone();
        controllerMap = configurationController.read().getControllerMapping().clone();
    }

    public void onBack() {
        getGame().popScene();
    }

    public void onApply() {
        Configuration configuration = configurationController.read();

        configuration.setControllerMapping(controllerMap);
        configuration.setKeyboardMapping(keyboardMapping);

        configurationController.write(configuration);

        onBack();
    }

    public UserControllerMap getTempKeyboardMapping() {
        return keyboardMapping;
    }

    public UserControllerMap getTempControllerMap() {
        return controllerMap;
    }

    public void onChangeDownController(int keyCode) {
        controllerMap.down = keyCode;
    }

    public void onChangeDownKeyboard(int keyCode) {
        keyboardMapping.down = keyCode;
    }

    public void onChangeInteractController(int keyCode) {
        controllerMap.interact = keyCode;
    }

    public void onChangeInteractKeyboard(int keyCode) {
        keyboardMapping.interact = keyCode;
    }

    public void onChangeInventoryController(int keyCode) {
        controllerMap.inventory = keyCode;
    }

    public void onChangeInventoryKeyboard(int keyCode) {
        keyboardMapping.inventory = keyCode;
    }

    public void onChangeLeftController(int keyCode) {
        controllerMap.left = keyCode;
    }

    public void onChangeLeftKeyboard(int keyCode) {
        keyboardMapping.left = keyCode;
    }

    public void onChangeMenuController(int keyCode) {
        controllerMap.menu = keyCode;
    }

    public void onChangeMenuKeyboard(int keyCode) {
        keyboardMapping.menu = keyCode;
    }

    public void onChangeRightController(int keyCode) {
        controllerMap.right = keyCode;
    }

    public void onChangeRightKeyboard(int keyCode) {
        keyboardMapping.right = keyCode;
    }

    public void onChangeUpController(int keyCode) {
        controllerMap.up = keyCode;
    }

    public void onChangeUpKeyboard(int keyCode) {
        keyboardMapping.up = keyCode;
    }
}
