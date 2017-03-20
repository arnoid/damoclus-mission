package org.arnoid.damoclus.ui.scene.menu.dialog;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.R;

public class InputRecorderDialog extends SceneDialog implements EventListener {

    public InputRecorderDialog(String title, Skin skin) {
        super(title, skin);

        DamoclusGdxGame.mainComponent().inject(this);

        addListener(this);

        text(stringsController.string(R.string.dialog_recorder_keyboard_label_message));
    }

    @Override
    public boolean handle(Event event) {
        if (event instanceof InputEvent) {
            InputEvent inputEvent = (InputEvent) event;
            if (inputEvent.getType().equals(InputEvent.Type.keyTyped)) {
                keyTyped(inputEvent, inputEvent.getKeyCode());
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean keyTyped(InputEvent event, int character) {
        return true;
    }

}
