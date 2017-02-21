package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.Ids;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.data.configuration.UserControllerMap;
import org.arnoid.damoclus.logic.delegate.menu.ControlsMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.RowHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.SingleActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.TableHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.WindowHolder;
import org.arnoid.damoclus.ui.scene.menu.dialog.InputRecorderDialog;

import javax.inject.Inject;

public class ControlsMenuScene extends AbstractMenuScene<ControlsMenuSceneDelegate> {

    private static final String TAG = ControlsMenuScene.class.getSimpleName();

    @Inject
    ConfigurationController configurationController;

    public ControlsMenuScene(MainComponent component, Stage stage) {
        super(stage);
        component.inject(this);
        init();
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        Gdx.app.log(TAG, actor.getName());

        ControlsMenuSceneDelegate sceneDelegate = getSceneDelegate();
        switch (actor.getName()) {
            case Ids.menu.controls.btn_back:
                sceneDelegate.onBack();
                break;
            case Ids.menu.controls.btn_apply:
                sceneDelegate.onApply();
                break;
            case Ids.menu.controls.btn_down_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeDownController(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_down_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeDownKeyboard(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_interact_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeInteractController(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_interact_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeInteractKeyboard(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_inventory_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeInventoryController(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_inventory_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeInventoryKeyboard(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_left_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeLeftController(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_left_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeLeftKeyboard(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_menu_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeMenuController(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_menu_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeMenuKeyboard(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_right_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeRightController(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_right_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeRightKeyboard(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_up_controller:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeUpController(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
            case Ids.menu.controls.btn_up_keyboard:
                showInputRecorderDialog(new InputRecorderDialog.KeyListener() {
                    @Override
                    public void onKey(int key) {
                        sceneDelegate.onChangeUpKeyboard(key);
                        updateTexts();
                        resume();
                        getStage().setKeyboardFocus(getWindow());
                    }
                });
                break;
        }
    }

    private void showInputRecorderDialog(InputRecorderDialog.KeyListener listener) {
        InputRecorderDialog dialog = new InputRecorderDialog(Ids.dialog.recorder.keyboard_window_title, skinController.getSkin()).setListener(listener);
        getStage().setKeyboardFocus(dialog);
        getStage().addAction(produceDialogDisplayAction(dialog));
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

    @Override
    protected String getWindowTitle() {
        return Ids.menu.controls.window_title;
    }

    @Override
    protected void produceMenuItems() {
        MenuSceneBuilder.with(this, new WindowHolder())
                .add(new TableHolder()
                        .add(
                                new RowHolder()
                                        .add(SingleActorHolder.space().width(150))
                                        .add(SingleActorHolder.label(Ids.menu.controls.label_keyboard).width(150).align(Align.center))
                                        .add(SingleActorHolder.label(Ids.menu.controls.label_controller).width(150).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label(Ids.menu.controls.label_up).width(150))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_up_keyboard).width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_up_controller).width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label(Ids.menu.controls.label_down).width(150))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_down_keyboard).width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_down_controller).width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label(Ids.menu.controls.label_right).width(150))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_right_keyboard).width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_right_controller).width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label(Ids.menu.controls.label_left).width(150))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_left_keyboard).width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_left_controller).width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label(Ids.menu.controls.label_interact).width(150))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_interact_keyboard).width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_interact_controller).width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label(Ids.menu.controls.label_inventory).width(150))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_inventory_keyboard).width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_inventory_controller).width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label(Ids.menu.controls.label_menu).width(150))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_menu_keyboard).width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton(Ids.menu.controls.btn_menu_controller).width(75).align(Align.center))
                        )
                )
                .add(new TableHolder()
                        .add(new RowHolder()
                                .add(SingleActorHolder.textButton(Ids.menu.controls.btn_back).width(150))
                                .add(SingleActorHolder.textButton(Ids.menu.controls.btn_apply).width(150))
                        )
                )
                .build();
    }

    @Override
    public void onActorProduced(String name, Actor producedActor) {
        super.onActorProduced(name, producedActor);

        Gdx.app.log(TAG, "Actor produced [" + name + "]");
    }

    @Override
    protected String getText(String name) {
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
        switch (name) {
            case Ids.menu.controls.btn_down_controller:
                value = Input.Keys.toString(controllerMap.getDown());
                break;
            case Ids.menu.controls.btn_down_keyboard:
                value = Input.Keys.toString(keyboardMap.getDown());
                break;
            case Ids.menu.controls.btn_interact_controller:
                value = Input.Keys.toString(controllerMap.getInteract());
                break;
            case Ids.menu.controls.btn_interact_keyboard:
                value = Input.Keys.toString(keyboardMap.getInteract());
                break;
            case Ids.menu.controls.btn_inventory_controller:
                value = Input.Keys.toString(controllerMap.getInventory());
                break;
            case Ids.menu.controls.btn_inventory_keyboard:
                value = Input.Keys.toString(keyboardMap.getInventory());
                break;
            case Ids.menu.controls.btn_left_controller:
                value = Input.Keys.toString(controllerMap.getLeft());
                break;
            case Ids.menu.controls.btn_left_keyboard:
                value = Input.Keys.toString(keyboardMap.getLeft());
                break;
            case Ids.menu.controls.btn_menu_controller:
                value = Input.Keys.toString(controllerMap.getMenu());
                break;
            case Ids.menu.controls.btn_menu_keyboard:
                value = Input.Keys.toString(keyboardMap.getMenu());
                break;
            case Ids.menu.controls.btn_right_controller:
                value = Input.Keys.toString(controllerMap.getRight());
                break;
            case Ids.menu.controls.btn_right_keyboard:
                value = Input.Keys.toString(keyboardMap.getRight());
                break;
            case Ids.menu.controls.btn_up_controller:
                value = Input.Keys.toString(controllerMap.getUp());
                break;
            case Ids.menu.controls.btn_up_keyboard:
                value = Input.Keys.toString(keyboardMap.getUp());
                break;
            default:
                value = super.getText(name);
        }
        return value;
    }

}
