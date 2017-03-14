package org.arnoid.damoclus.ui.scene.menu.builder;

import org.arnoid.damoclus.ui.scene.menu.builder.model.BaseModel;

public abstract class AbstractMenuBuilder<T extends AbstractMenuBuilder, V extends BaseModel> {

    protected abstract AbstractMenuBuilder<T, V> add(V actorHolder);

}
