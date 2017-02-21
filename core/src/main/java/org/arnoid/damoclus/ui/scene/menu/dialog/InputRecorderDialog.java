package org.arnoid.damoclus.ui.scene.menu.dialog;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.Ids;
import org.arnoid.damoclus.controller.strings.StringsController;

import javax.inject.Inject;

public class InputRecorderDialog extends Dialog {

    public interface KeyListener {
        void onKey(int key);
    }

    @Inject
    StringsController stringsController;


    public InputRecorderDialog(String title, Skin skin) {
        super(title, skin);

        DamoclusGdxGame.mainComponent.inject(this);

        getTitleTable().padLeft(5);
        text(stringsController.string(Ids.dialog.recorder.keyboard_label_message));
    }

    public InputRecorderDialog setListener(final KeyListener listener) {
        clearListeners();
        addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                hide();
                remove();
                event.cancel();

                listener.onKey(keycode);
                return false;
            }

        });

        return this;
    }

}
