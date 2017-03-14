package org.arnoid.damoclus.ui.scene.menu.dialog;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;
import org.arnoid.damoclus.ui.scene.menu.ControlsMenuScene;

import javax.inject.Inject;

public class InputRecorderDialog extends Dialog {

    private AbstractMenuScene scene;

    public void show(AbstractMenuScene scene) {
        this.scene = scene;
        scene.pause();

        scene.getStage().addAction(produceDialogDisplayAction(scene));
    }

    public Action produceDialogDisplayAction(final AbstractMenuScene scene) {
        final Dialog dialog = this;
        DelayAction action = Actions.delay(0.3F, new Action() {

            @Override
            public boolean act(float delta) {
                show(scene.getStage());
                scene.getStage().setKeyboardFocus(dialog);
                return true;
            }
        });

        action.setActor(this);

        return action;
    }

    public interface KeyListener {
        void onKey(int key);
    }

    @Inject
    StringsController stringsController;

    public InputRecorderDialog(String title, Skin skin) {
        super(title, skin);

        DamoclusGdxGame.mainComponent().inject(this);

        getTitleTable().padLeft(5);
        text(stringsController.string(R.string.dialog_recorder_keyboard_label_message));
    }

    public InputRecorderDialog setListener(final KeyListener listener) {
        clearListeners();
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                hide();
                event.cancel();

                listener.onKey(keycode);
                scene.postProduceLayout();
                scene.resume();
//                getStage().setKeyboardFocus(scene.getStage().getRoot());
                return true;
            }

        });

        return this;
    }

}
