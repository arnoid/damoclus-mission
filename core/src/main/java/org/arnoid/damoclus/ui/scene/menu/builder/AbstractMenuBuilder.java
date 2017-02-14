package org.arnoid.damoclus.ui.scene.menu.builder;

import org.arnoid.damoclus.ui.scene.menu.builder.holder.ActorHolder;

public abstract class AbstractMenuBuilder<T extends AbstractMenuBuilder, V extends ActorHolder> {

    protected abstract AbstractMenuBuilder<T, V> add(V actorHolder);

}
