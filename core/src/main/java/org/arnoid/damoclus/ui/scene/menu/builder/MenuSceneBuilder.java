package org.arnoid.damoclus.ui.scene.menu.builder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.ActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.RowHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.SingleActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.TableHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuSceneBuilder extends AbstractMenuBuilder<MenuSceneBuilder, RowHolder> {

    public enum ActorType {
        TextButton,
        Label,
        CheckBox,
        SelectBox,
        Space,
        GrowVertical,
        Table,
        TableRow,
    }

    private float prefWidth = -1;
    private Map<Integer, Float> columnWidthMap;
    private List<ActorHolder> rowHolders;

    private AbstractMenuScene scene;

    private MenuSceneBuilder(AbstractMenuScene scene) {
        super();

        rowHolders = new ArrayList<>();
        columnWidthMap = new HashMap<>();

        this.scene = scene;
    }

    @Override
    public MenuSceneBuilder add(RowHolder actorHolder) {
        rowHolders.add(actorHolder);
        return this;
    }

    public MenuSceneBuilder add(SingleActorHolder actorHolder) {
        return add(new RowHolder().add(actorHolder));
    }

    public static MenuSceneBuilder with(AbstractMenuScene scene) {
        return new MenuSceneBuilder(scene);
    }

    public MenuSceneBuilder prefWidth(float prefWidth) {
        this.prefWidth = prefWidth;
        return this;
    }

    public MenuSceneBuilder columnWidth(int column, float prefWidth) {
        this.columnWidthMap.put(column, prefWidth);
        return this;
    }

    public void build() {
        Window window = scene.getWindow();

        int column = 0;
        for (ActorHolder rowHolder : rowHolders) {
            buildRow(scene, window, rowHolder, column);
            column++;
        }
    }

    private void buildRow(AbstractMenuScene scene, Table table, ActorHolder actorHolder, int column) {
        Cell row = table.row();

        Actor producedActor = produceActor(scene, actorHolder);

        if (ActorType.TableRow.equals(actorHolder.actorType)) {
            for (ActorHolder innerActorHolder : ((RowHolder) actorHolder).actorHolders) {

                Actor innerActor = produceActor(scene, innerActorHolder);

                table.add(innerActor).align(Align.left).prefWidth(getColumnWidth(column));

                if (ActorType.Table.equals(innerActorHolder.actorType)) {
                    handleTableActor(scene, (Table) innerActor, (TableHolder) innerActorHolder);
                }

                registerLabeledActor(scene, innerActorHolder, innerActor);
                registerListeners(scene, innerActorHolder, innerActor);
            }
        } else if (ActorType.Table.equals(actorHolder.actorType)) {
            table.add(producedActor).align(Align.left).prefWidth(getColumnWidth(column));

            handleTableActor(scene, table, (TableHolder) actorHolder);
        }
    }

    private void handleTableActor(AbstractMenuScene scene, Table table, TableHolder actorHolder) {
        int column = 0;
        for (ActorHolder innerActorHolder : actorHolder.actorHolders) {
            buildRow(scene, table, innerActorHolder, column);
            column++;
        }
    }

    private void handleProducedActor(AbstractMenuScene scene, ActorHolder actorHolder, Actor producedActor) {

        registerListeners(scene, actorHolder, producedActor);
        registerLabeledActor(scene, actorHolder, producedActor);

        if (actorHolder.actorType == ActorType.Table) {

            handleTable(scene, (Table) producedActor, (TableHolder) actorHolder);
        }

        scene.onActorProduced(actorHolder.name, producedActor);
    }

    private void handleTable(AbstractMenuScene scene, Table tableActor, TableHolder tableHolder) {
        for (RowHolder rowHolder : tableHolder.actorHolders) {
            for (ActorHolder actorHolder : rowHolder.actorHolders) {
                Actor producedActor = produceActor(scene, actorHolder);

                tableActor.add(producedActor);

                handleProducedActor(scene, actorHolder, producedActor);
            }

            tableActor.row();
        }
    }

    private void registerLabeledActor(AbstractMenuScene scene, ActorHolder actorHolder, Actor producedActor) {
        switch (actorHolder.actorType) {
            case TextButton:
            case SelectBox:
            case Label:
                scene.registerLabeledActor(producedActor);
                break;
            case GrowVertical:
            case CheckBox:
            case Space:
            case Table:
            default:
                break;
        }
    }

    private void registerListeners(AbstractMenuScene scene, ActorHolder actorHolder, Actor producedActor) {
        switch (actorHolder.actorType) {
            case CheckBox:
            case TextButton:
            case SelectBox:
                scene.registerMenuItemListeners(producedActor);
                scene.registerInMenuItems(producedActor);
                break;
            case Label:
            case GrowVertical:
            case Space:
            case Table:
                break;
        }
    }

    private Actor produceActor(AbstractMenuScene scene, ActorHolder actorHolder) {
        Actor producedActor = null;
        switch (actorHolder.actorType) {
            case GrowVertical:
                scene.produceGrowYCell();
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
                scene.produceSpace();
                break;
            case TextButton:
                producedActor = scene.produceButton(actorHolder.name);
                break;
            case Table:
                producedActor = scene.produceGroupTable(actorHolder.name);
                break;
            default:
                break;
        }

        return producedActor;
    }

    private float getColumnWidth(int columnIndex) {
        if (columnWidthMap.containsKey(columnIndex)) {
            return columnWidthMap.get(columnIndex);
        } else {
            return prefWidth;
        }
    }

}
