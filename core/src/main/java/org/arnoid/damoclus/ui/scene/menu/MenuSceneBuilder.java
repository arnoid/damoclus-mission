package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;

public class MenuSceneBuilder {

    private final float[] columnWidth;
    private final float prefWidth;

    public enum ActorType {
        TextButton,
        Label,
        CheckBox,
        SelectBox,
        Space,
        GrowVertical,
    }

    private ArrayList<ActorsRowHolder> actorsRowHolders = new ArrayList<>();

    public MenuSceneBuilder(float prefWidth, float... columnWidth) {
        this.prefWidth = prefWidth;
        this.columnWidth = columnWidth;
    }

    protected MenuSceneBuilder row(ActorsRowHolder actorsRowHolder) {
        actorsRowHolders.add(actorsRowHolder);
        return this;
    }

    protected MenuSceneBuilder singleRowActor(ActorType actorType, String name) {
        return new ActorsRowHolder(this)
                .add(new ActorHolder(actorType, name))
                .endRow();
    }

    public MenuSceneBuilder textButton(String name) {
        return singleRowActor(ActorType.TextButton, name);
    }

    public MenuSceneBuilder label(String name) {
        return singleRowActor(ActorType.Label, name);
    }

    public MenuSceneBuilder checkBox(String name) {
        return singleRowActor(ActorType.CheckBox, name);
    }

    public MenuSceneBuilder selectBox(String name) {
        return singleRowActor(ActorType.TextButton, name);
    }

    public MenuSceneBuilder space() {
        return singleRowActor(ActorType.Space, null);
    }

    public MenuSceneBuilder grow() {
        return singleRowActor(ActorType.GrowVertical, null);
    }

    public ActorsRowHolder row() {
        return new ActorsRowHolder(this);
    }

    public void build(AbstractMenuScene scene) {

        for (ActorsRowHolder rowHolder : actorsRowHolders) {
            Window window = scene.getWindow();
            Cell row = window.row();

            int column = 0;
            for (ActorHolder actorHolder : rowHolder.actorHolders) {
                Actor producedActor = null;

                switch (actorHolder.actorType) {
                    case GrowVertical:
                        row.growY();
                        break;
                    case CheckBox:
                        producedActor = scene.produceCheckBox(actorHolder.name);
                        break;
                    case Label:
                        producedActor = scene.produceLabel(actorHolder.name);
                        break;
                    case SelectBox:
                        producedActor = scene.produceSelectBox(actorHolder.name);
                        break;
                    case Space:
                        row.height(scene.getCellHeight(column));
                        break;
                    case TextButton:
                        producedActor = scene.produceButton(actorHolder.name);
                        break;
                    default:
                        break;
                }

                if (producedActor != null) {
                    window.add(producedActor).align(Align.left).prefWidth(getColumnWidth(column));
                    switch (actorHolder.actorType) {
                        case Label:
                        case GrowVertical:
                        case Space:
                            break;
                        case CheckBox:
                        case TextButton:
                        case SelectBox:
                            scene.registerMenuItemListeners(producedActor);
                            scene.registerInMenuItems(producedActor);
                            break;
                    }

                    switch (actorHolder.actorType) {
                        case GrowVertical:
                        case CheckBox:
                        case Space:
                            break;
                        case TextButton:
                        case SelectBox:
                        case Label:
                            scene.registerLabeledActor(producedActor);
                            break;
                    }

                    scene.onActorProduced(actorHolder.name, producedActor);
                }

                column++;
            }
        }
    }

    private float getColumnWidth(int columnIndex) {
        if (columnWidth == null || columnWidth.length == 0 || columnWidth.length <= columnIndex) {
            return prefWidth;
        } else {
            return columnWidth[columnIndex];
        }
    }

    private static class ActorHolder {
        ActorType actorType;
        String name;

        public ActorHolder(ActorType actorType, String name) {
            this.actorType = actorType;
            this.name = name;
        }
    }

    public static class ActorsRowHolder {

        private final MenuSceneBuilder menuSceneBuilder;

        ArrayList<ActorHolder> actorHolders = new ArrayList<>();

        public ActorsRowHolder(MenuSceneBuilder menuSceneBuilder) {
            this.menuSceneBuilder = menuSceneBuilder;
        }

        protected ActorsRowHolder add(ActorHolder actorHolder) {
            actorHolders.add(actorHolder);

            return this;
        }

        protected ActorsRowHolder add(ActorType actorType, String name) {
            actorHolders.add(new ActorHolder(actorType, name));

            return this;
        }

        public MenuSceneBuilder endRow() {
            return menuSceneBuilder.row(this);
        }

        public ActorsRowHolder label(String name) {
            return add(new ActorHolder(ActorType.Label, name));
        }

        public ActorsRowHolder checkBox(String name) {
            return add(new ActorHolder(ActorType.CheckBox, name));
        }

        public ActorsRowHolder textButton(String name) {
            return add(new ActorHolder(ActorType.TextButton, name));
        }

        public ActorsRowHolder selectBox(String name) {
            return add(new ActorHolder(ActorType.SelectBox, name));
        }

        public ActorsRowHolder space() {
            return add(new ActorHolder(ActorType.Space, null));
        }

        public ActorsRowHolder grow() {
            return add(new ActorHolder(ActorType.GrowVertical, null));
        }
    }
}
