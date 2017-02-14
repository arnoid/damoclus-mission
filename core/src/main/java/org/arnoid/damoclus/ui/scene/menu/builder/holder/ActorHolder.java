package org.arnoid.damoclus.ui.scene.menu.builder.holder;

import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;

public abstract class ActorHolder {
    public MenuSceneBuilder.ActorType actorType;
    public String name;

    public ActorHolder(MenuSceneBuilder.ActorType actorType, String name) {
        this.actorType = actorType;
        this.name = name;
    }
}
