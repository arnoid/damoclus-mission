package org.arnoid.damoclus.ui.scene.menu.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.SnapshotArray;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.data.configuration.Configuration;
import org.arnoid.damoclus.data.configuration.UserControllerMap;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;
import org.arnoid.damoclus.ui.view.UiUtil;

import javax.inject.Inject;

public class MessageDialog extends SceneDialog {

    public static final String TAG = MessageDialog.class.getSimpleName();

    @Inject
    ConfigurationController configurationController;

    public enum ButtonType {
        POSITIVE,
        NEUTRAL,
        NEGATIVE,

        NEXT,
        PREV,
        INTERACT,
        BACK
    }

    private int selectedButtonIndex;

    public MessageDialog(String title, Skin skin, String message) {
        super(title, skin);

        text(stringsController.string(message));

        DamoclusGdxGame.mainComponent().inject(this);
        Configuration configuration = configurationController.read();
        UserControllerMap keyboardMapping = configuration.getKeyboardMapping();

        key(keyboardMapping.getUp(), ButtonType.PREV);
        key(keyboardMapping.getLeft(), ButtonType.PREV);

        key(keyboardMapping.getDown(), ButtonType.NEXT);
        key(keyboardMapping.getRight(), ButtonType.NEXT);

        key(keyboardMapping.getInteract(), ButtonType.INTERACT);
        key(Input.Keys.ENTER, ButtonType.INTERACT);

        key(keyboardMapping.getBack(), ButtonType.BACK);
        key(Input.Keys.ESCAPE, ButtonType.BACK);
    }

    public MessageDialog positive(String button) {
        button(stringsController.string(button), ButtonType.POSITIVE);
        return this;
    }

    public MessageDialog neutral(String button) {
        button(stringsController.string(button), ButtonType.NEUTRAL);
        return this;
    }

    public MessageDialog negative(String button) {
        button(stringsController.string(button), ButtonType.NEGATIVE);
        return this;
    }

    @Override
    public void show(AbstractMenuScene scene) {
        super.show(scene);

        SnapshotArray<Actor> children = getButtonTable().getChildren();
        if (children.size > 0) {
            UiUtil.markSelected(children.get(0));
        }
    }

    @Override
    protected void result(Object object) {
        if (ButtonType.POSITIVE.equals(object)) {
            onPositive(this);
            super.result(object);
        } else if (ButtonType.NEUTRAL.equals(object)) {
            onNeutral(this);
            super.result(object);
        } else if (ButtonType.NEGATIVE.equals(object)) {
            onNegative(this);
            super.result(object);
        } else if (ButtonType.PREV.equals(object)) {
            Gdx.app.log(TAG, "PREV");
            cancel();
            SnapshotArray<Actor> children = getButtonTable().getChildren();

            if (selectedButtonIndex == 0) {
                selectedButtonIndex = children.size - 1;
            } else {
                selectedButtonIndex--;
            }

            for (int i = 0; i < children.size; i++) {
                Actor actor = children.get(i);
                if (i == selectedButtonIndex) {
                    UiUtil.markSelected(actor);
                } else {
                    UiUtil.markNotSelected(actor);
                }
            }
        } else if (ButtonType.NEXT.equals(object)) {
            Gdx.app.log(TAG, "NEXT");
            cancel();

            SnapshotArray<Actor> children = getButtonTable().getChildren();

            if (selectedButtonIndex == children.size - 1) {
                selectedButtonIndex = 0;
            } else {
                selectedButtonIndex++;
            }

            for (int i = 0; i < children.size; i++) {
                Actor actor = children.get(i);
                if (i == selectedButtonIndex) {
                    UiUtil.markSelected(actor);
                } else {
                    UiUtil.markNotSelected(actor);
                }
            }
        } else if (ButtonType.INTERACT.equals(object)) {
            Gdx.app.log(TAG, "INTERACT");
            cancel();

            onInteract(object);
        } else if (ButtonType.BACK.equals(object)) {
            Gdx.app.log(TAG, "BACK");
            cancel();

            hide();
        }
    }

    public void onInteract(Object result) {
        SnapshotArray<Actor> children = getButtonTable().getChildren();
        Object object = getObject(children.get(selectedButtonIndex));
        result(object);
    }

    public void onPositive(MessageDialog messageDialog) {

    }

    public void onNeutral(MessageDialog messageDialog) {

    }

    public void onNegative(MessageDialog messageDialog) {

    }
}
