package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.data.configuration.UserControllerMap;
import org.arnoid.damoclus.logic.delegate.menu.ControlsMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilderAdapter;
import org.arnoid.damoclus.ui.scene.menu.dialog.InputRecorderDialog;

import javax.inject.Inject;

public class ControlsMenuScene extends AbstractMenuScene<ControlsMenuSceneDelegate> {

    private static final String TAG = ControlsMenuScene.class.getSimpleName();

    @Inject
    ConfigurationController configurationController;

    public ControlsMenuScene(MainComponent component) {
        super();
        component.inject(this);
        init();
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {

        ControlsMenuSceneDelegate sceneDelegate = getSceneDelegate();

        switch (actor.getName()) {
            case R.id.btn_back:
                sceneDelegate.onBack();
                break;
            case R.id.btn_apply:
                sceneDelegate.onApply();
                break;
            case R.id.btn_down_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeDownController(key);
                    }
                });
                break;
            case R.id.btn_down_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeDownKeyboard(key);
                    }
                });
                break;
            case R.id.btn_interact_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeInteractController(key);
                    }
                });
                break;
            case R.id.btn_interact_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeInteractKeyboard(key);
                    }
                });
                break;
            case R.id.btn_inventory_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeInventoryController(key);
                    }
                });
                break;
            case R.id.btn_inventory_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeInventoryKeyboard(key);
                    }
                });
                break;
            case R.id.btn_left_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeLeftController(key);
                    }
                });
                break;
            case R.id.btn_left_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeLeftKeyboard(key);
                    }
                });
                break;
            case R.id.btn_menu_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeMenuController(key);
                    }
                });
                break;
            case R.id.btn_menu_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeMenuKeyboard(key);
                    }
                });
                break;
            case R.id.btn_right_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeRightController(key);
                    }
                });
                break;
            case R.id.btn_right_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeRightKeyboard(key);
                    }
                });
                break;
            case R.id.btn_up_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeUpController(key);
                    }
                });
                break;
            case R.id.btn_up_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeUpKeyboard(key);
                    }
                });
                break;
        }
    }

    private void showInputRecorderDialog(InputRecorderDialog.KeyListener listener) {
        new InputRecorderDialog(R.string.dialog_recorder_keyboard_window_title, skinController.getSkin())
                .setListener(listener)
                .show(this);
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

    @Override
    protected void produceLayout() {
        XmlMenuSceneBuilder
                .with(R.layout.menu_controls)
                .listener(new XmlMenuSceneBuilderAdapter())
                .build(getStage());
    }

    @Override
    public void postProduceLayout() {
        TextButton btnDownController = findButton(R.id.btn_down_controller);
        TextButton btnDownKeyboard = findButton(R.id.btn_down_keyboard);
        TextButton btnUpController = findButton(R.id.btn_up_controller);
        TextButton btnUpKeyboard = findButton(R.id.btn_up_keyboard);
        TextButton btnRightController = findButton(R.id.btn_right_controller);
        TextButton btnRightKeyboard = findButton(R.id.btn_right_keyboard);
        TextButton btnLeftController = findButton(R.id.btn_left_controller);
        TextButton btnLeftKeyboard = findButton(R.id.btn_left_keyboard);
        TextButton btnInteractController = findButton(R.id.btn_interact_controller);
        TextButton btnInteractKeyboard = findButton(R.id.btn_interact_keyboard);
        TextButton btnInventoryController = findButton(R.id.btn_inventory_controller);
        TextButton btnInventoryKeyboard = findButton(R.id.btn_inventory_keyboard);
        TextButton btnMenuController = findButton(R.id.btn_menu_controller);
        TextButton btnMenuKeyboard = findButton(R.id.btn_menu_keyboard);

        btnDownController.setText(getButtonLabel(R.id.btn_down_controller));
        btnDownKeyboard.setText(getButtonLabel(R.id.btn_down_keyboard));
        btnUpController.setText(getButtonLabel(R.id.btn_up_controller));
        btnUpKeyboard.setText(getButtonLabel(R.id.btn_up_keyboard));
        btnRightController.setText(getButtonLabel(R.id.btn_right_controller));
        btnRightKeyboard.setText(getButtonLabel(R.id.btn_right_keyboard));
        btnLeftController.setText(getButtonLabel(R.id.btn_left_controller));
        btnLeftKeyboard.setText(getButtonLabel(R.id.btn_left_keyboard));
        btnInteractController.setText(getButtonLabel(R.id.btn_interact_controller));
        btnInteractKeyboard.setText(getButtonLabel(R.id.btn_interact_keyboard));
        btnInventoryController.setText(getButtonLabel(R.id.btn_inventory_controller));
        btnInventoryKeyboard.setText(getButtonLabel(R.id.btn_inventory_keyboard));
        btnMenuController.setText(getButtonLabel(R.id.btn_menu_controller));
        btnMenuKeyboard.setText(getButtonLabel(R.id.btn_menu_keyboard));

        registerMenuItem(btnUpKeyboard);
        registerMenuItem(btnUpController);

        registerMenuItem(btnDownKeyboard);
        registerMenuItem(btnDownController);

        registerMenuItem(btnRightKeyboard);
        registerMenuItem(btnRightController);

        registerMenuItem(btnLeftKeyboard);
        registerMenuItem(btnLeftController);

        registerMenuItem(btnInteractKeyboard);
        registerMenuItem(btnInteractController);

        registerMenuItem(btnInventoryKeyboard);
        registerMenuItem(btnInventoryController);

        registerMenuItem(btnMenuKeyboard);
        registerMenuItem(btnMenuController);


        registerMenuItem(findButton(R.id.btn_back));
        registerMenuItem(findButton(R.id.btn_apply));
    }

    protected String getButtonLabel(String id) {
        UserControllerMap keyboardMap;
        UserControllerMap controllerMap;

        if (getSceneDelegate() == null) {
            keyboardMap = configurationController.read().getKeyboardMapping();
            controllerMap = configurationController.read().getControllerMapping();
        } else {
            keyboardMap = getSceneDelegate().getTempKeyboardMapping();
            controllerMap = getSceneDelegate().getTempControllerMap();
        }

        String value;
        switch (id) {
            case R.id.btn_down_controller:
                value = Input.Keys.toString(controllerMap.getDown());
                break;
            case R.id.btn_down_keyboard:
                value = Input.Keys.toString(keyboardMap.getDown());
                break;
            case R.id.btn_interact_controller:
                value = Input.Keys.toString(controllerMap.getInteract());
                break;
            case R.id.btn_interact_keyboard:
                value = Input.Keys.toString(keyboardMap.getInteract());
                break;
            case R.id.btn_inventory_controller:
                value = Input.Keys.toString(controllerMap.getInventory());
                break;
            case R.id.btn_inventory_keyboard:
                value = Input.Keys.toString(keyboardMap.getInventory());
                break;
            case R.id.btn_left_controller:
                value = Input.Keys.toString(controllerMap.getLeft());
                break;
            case R.id.btn_left_keyboard:
                value = Input.Keys.toString(keyboardMap.getLeft());
                break;
            case R.id.btn_menu_controller:
                value = Input.Keys.toString(controllerMap.getMenu());
                break;
            case R.id.btn_menu_keyboard:
                value = Input.Keys.toString(keyboardMap.getMenu());
                break;
            case R.id.btn_right_controller:
                value = Input.Keys.toString(controllerMap.getRight());
                break;
            case R.id.btn_right_keyboard:
                value = Input.Keys.toString(keyboardMap.getRight());
                break;
            case R.id.btn_up_controller:
                value = Input.Keys.toString(controllerMap.getUp());
                break;
            case R.id.btn_up_keyboard:
                value = Input.Keys.toString(keyboardMap.getUp());
                break;
            default:
                value = stringsController.string(id);
        }
        return value;
    }

}
