package org.arnoid.damoclus.logic.input;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public interface NavigationListener {
    void onInteract(Actor actor, InputEvent event);

    void onBack();

    void onNext();

    void onPrevious();
}
