package org.arnoid.damoclus.ui.scene.menu.builder.holder;

import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;

import java.util.ArrayList;
import java.util.List;

public class RowHolder extends ActorHolder<RowHolder> {
    public List<ActorHolder> actorHolders = new ArrayList<>();

    /**
     * for extension
     */
    protected RowHolder(MenuSceneBuilder.ActorType actorType) {
        super(actorType, "");
    }

    public RowHolder() {
        super(MenuSceneBuilder.ActorType.TableRow, "");
    }

    public RowHolder add(ActorHolder actorHolder) {
        actorHolders.add(actorHolder);
        return this;
    }

}
