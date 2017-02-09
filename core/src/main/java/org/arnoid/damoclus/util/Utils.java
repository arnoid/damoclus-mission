package org.arnoid.damoclus.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Utils {

    public static void center(Actor actor) {
        actor.setPosition(Gdx.graphics.getWidth() / 2 - actor.getWidth() / 2, Gdx.graphics.getHeight() / 2 - actor.getHeight() / 2);
    }

    public static void centerAnchor(Actor actor, Actor anchor) {
        actor.setPosition(anchor.getWidth() / 2 - actor.getWidth() / 2, anchor.getHeight() / 2 - actor.getHeight() / 2);
    }
}
