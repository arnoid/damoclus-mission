package org.arnoid.damoclus.ui.scene.menu.builder.holder;

import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;

public class WindowHolder extends ActorHolder {
    public float x = 0;
    public float y = 0;

    public boolean pack = false;
    public boolean movable = false;
    public boolean modal = false;

    public WindowHolder() {
        super(MenuSceneBuilder.ActorType.Window, "");
    }

    public WindowHolder x(float x) {
        this.x = x;
        return this;
    }

    public WindowHolder y(float y) {
        this.y = y;
        return this;
    }

    public WindowHolder pack() {
        this.pack = true;
        return this;
    }

    public WindowHolder movable() {
        this.movable = true;
        return this;
    }

    public WindowHolder modal() {
        this.modal = true;
        return this;
    }
}
