package org.arnoid.damoclus.ui.scene.menu.builder.holder;

import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;

public class SimpleActorHolder extends ActorHolder {
    public SimpleActorHolder(MenuSceneBuilder.ActorType actorType, String name) {
        super(actorType, name);
    }

    public static SimpleActorHolder label(String name) {
        return new SimpleActorHolder(MenuSceneBuilder.ActorType.Label, name);
    }

    public static SimpleActorHolder checkBox(String name) {
        return new SimpleActorHolder(MenuSceneBuilder.ActorType.CheckBox, name);
    }

    public static SimpleActorHolder textButton(String name) {
        return new SimpleActorHolder(MenuSceneBuilder.ActorType.TextButton, name);
    }

    public static SimpleActorHolder selectBox(String name) {
        return new SimpleActorHolder(MenuSceneBuilder.ActorType.SelectBox, name);
    }

    public static SimpleActorHolder space() {
        return new SimpleActorHolder(MenuSceneBuilder.ActorType.Space, "");
    }

    public static ActorHolder growVertical() {
        return new SimpleActorHolder(MenuSceneBuilder.ActorType.GrowVertical, "");
    }
}
