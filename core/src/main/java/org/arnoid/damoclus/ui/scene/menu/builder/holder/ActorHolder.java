package org.arnoid.damoclus.ui.scene.menu.builder.holder;

import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;

public abstract class ActorHolder<T extends ActorHolder> {
    public MenuSceneBuilder.ActorType actorType;
    public String name;

    public enum Grow {
        Grow,
        GrowY,
        GrowX,
        None
    }

    public int align = Align.center;
    public float width = 0;
    public float height = 0;
    public float padLeft = 0;
    public float padRight = 0;
    public float padBottom = 0;
    public float padTop = 0;
    public Grow grow = Grow.None;

    public ActorHolder(MenuSceneBuilder.ActorType actorType, String name) {
        this.actorType = actorType;
        this.name = name;
    }

    public T pad(float pad) {
        this.padLeft = pad;
        this.padRight = pad;
        this.padBottom = pad;
        this.padTop = pad;
        return (T) this;
    }

    public T padLeft(float pad) {
        this.padLeft = pad;
        return (T) this;
    }

    public T padRight(float pad) {
        this.padRight = pad;
        return (T) this;
    }

    public T padBottom(float pad) {
        this.padBottom = pad;
        return (T) this;
    }

    public T padTop(float pad) {
        this.padTop = pad;
        return (T) this;
    }

    public T align(int align) {
        this.align = align;
        return (T) this;
    }

    public T width(float prefWidth) {
        this.width = prefWidth;
        return (T) this;
    }

    public T height(float prefHeight) {
        this.height = prefHeight;
        return (T) this;
    }

    public T grow(Grow grow) {
        this.grow = grow;
        return (T) this;
    }
}
