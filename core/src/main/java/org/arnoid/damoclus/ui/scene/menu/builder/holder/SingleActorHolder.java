package org.arnoid.damoclus.ui.scene.menu.builder.holder;

import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;

public class SingleActorHolder extends ActorHolder<SingleActorHolder> {
    public SingleActorHolder(MenuSceneBuilder.ActorType actorType, String name) {
        super(actorType, name);
    }

    public static SingleActorHolder label(String name) {
        return new SingleActorHolder(MenuSceneBuilder.ActorType.Label, name);
    }

    public static SingleActorHolder checkBox(String name) {
        return new SingleActorHolder(MenuSceneBuilder.ActorType.CheckBox, name);
    }

    public static SingleActorHolder textButton(String name) {
        return new SingleActorHolder(MenuSceneBuilder.ActorType.TextButton, name);
    }

    public static SingleActorHolder selectBox(String name) {
        return new SingleActorHolder(MenuSceneBuilder.ActorType.SelectBox, name);
    }

    public static SingleActorHolder textField(String name) {
        return new SingleActorHolder(MenuSceneBuilder.ActorType.TextField, name);
    }

    public static SingleActorHolder space() {
        return new SingleActorHolder(MenuSceneBuilder.ActorType.Space, "");
    }

}
