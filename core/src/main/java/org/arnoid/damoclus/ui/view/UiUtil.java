package org.arnoid.damoclus.ui.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class UiUtil {

    public static void markNotSelected(Actor actor) {
        if (actor != null) {
            for (EventListener listener : actor.getListeners()) {
                if (listener instanceof InputListener) {
                    InputEvent inputEvent = new InputEvent();
                    inputEvent.setType(InputEvent.Type.exit);
                    inputEvent.setListenerActor(actor);
                    ((InputListener) listener).exit(inputEvent, 0, 0, -1, actor);
                }
            }
        }
    }

    public static void markSelected(Actor actor) {
        if (actor != null) {
            for (EventListener listener : actor.getListeners()) {
                if (listener instanceof InputListener) {
                    InputEvent inputEvent = new InputEvent();
                    inputEvent.setType(InputEvent.Type.enter);
                    inputEvent.setListenerActor(actor);
                    ((InputListener) listener).enter(inputEvent, 0, 0, -1, actor);
                }
            }
        }
    }
}
