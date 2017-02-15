package org.arnoid.damoclus.ui.scene.menu.builder.holder;

import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;

import java.util.ArrayList;
import java.util.List;

public class TableHolder extends ActorHolder<RowHolder> {
    public List<RowHolder> actorHolders = new ArrayList<>();

    public TableHolder() {
        super(MenuSceneBuilder.ActorType.Table, "");
    }

    public TableHolder add(RowHolder actorHolder) {
        actorHolders.add(actorHolder);
        return this;
    }

    public TableHolder add(RowHolder... actorHolder) {
        for (RowHolder rowHolder : actorHolder) {
            actorHolders.add(rowHolder);
        }
        return this;
    }
}
