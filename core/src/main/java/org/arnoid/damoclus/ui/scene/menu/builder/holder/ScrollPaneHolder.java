package org.arnoid.damoclus.ui.scene.menu.builder.holder;

import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;

import java.util.ArrayList;
import java.util.List;

public class ScrollPaneHolder extends ActorHolder<ScrollPaneHolder> {

    public final boolean verticalScrollBar;
    public final boolean horizontalScrollBar;

    public ActorHolder actorHolder;

    public ScrollPaneHolder() {
        this(false, false);
    }

    public ScrollPaneHolder(boolean horizontalScrollBar, boolean verticalScrollBar) {
        super(MenuSceneBuilder.ActorType.Scroll, null);
        this.verticalScrollBar = verticalScrollBar;
        this.horizontalScrollBar = horizontalScrollBar;
    }

    public ScrollPaneHolder set(ActorHolder actorHolder) {
        this.actorHolder = actorHolder;
        return this;
    }

}
