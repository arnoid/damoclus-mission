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

import javax.inject.Inject;

public class ControlsMenuScene extends AbstractMenuScene<ControlsMenuSceneDelegate> {

    private static final String TAG = ControlsMenuScene.class.getSimpleName();

    @Inject
    ConfigurationController configurationControllerl;

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
                sceneDelegate.onChangeDownController();
                break;
            case Ids.menu.controls.btn_down_keyboard:
                sceneDelegate.onChangeDownKeyboard();
                break;
            case Ids.menu.controls.btn_interact_controller:
                sceneDelegate.onChangeInteractController();
                break;
            case Ids.menu.controls.btn_interact_keyboard:
                sceneDelegate.onChangeInteractKeyboard();
                break;
            case Ids.menu.controls.btn_inventory_controller:
                sceneDelegate.onChangeInventoryController();
                break;
            case Ids.menu.controls.btn_inventory_keyboard:
                sceneDelegate.onChangeInventoryKeyboard();
                break;
            case Ids.menu.controls.btn_left_controller:
                sceneDelegate.onChangeLeftController();
                break;
            case Ids.menu.controls.btn_left_keyboard:
                sceneDelegate.onChangeLeftKeyboard();
                break;
            case Ids.menu.controls.btn_menu_controller:
                sceneDelegate.onChangeMenuController();
                break;
            case Ids.menu.controls.btn_menu_keyboard:
                sceneDelegate.onChangeMenuKeyboard();
                break;
            case Ids.menu.controls.btn_right_controller:
                sceneDelegate.onChangeRightController();
                break;
            case Ids.menu.controls.btn_right_keyboard:
                sceneDelegate.onChangeRightKeyboard();
                break;
            case Ids.menu.controls.btn_up_controller:
                sceneDelegate.onChangeUpController();
                break;
            case Ids.menu.controls.btn_up_keyboard:
                sceneDelegate.onChangeUpKeyboard();
                break;
        }
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
        UserControllerMap keyboardMap = configurationControllerl.read().getKeyboardMapping();
        UserControllerMap controllerMap = configurationControllerl.read().getControllerMapping();

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
