package org.arnoid.damoclus.ui.scene.menu.builder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.ActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.RowHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.ScrollPaneHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.SingleActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.TableHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuSceneBuilder extends AbstractMenuBuilder<MenuSceneBuilder, ActorHolder> {

    public enum ActorType {
        TextButton,
        Label,
        CheckBox,
        SelectBox,
        Scroll,
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
    public MenuSceneBuilder add(ActorHolder actorHolder) {
        switch (actorHolder.actorType) {
            case TableRow:
                rowHolders.add(actorHolder);
                break;
            case Table:
            case CheckBox:
            case GrowVertical:
            case Label:
            case Scroll:
            case SelectBox:
            case Space:
            case TextButton:
            default:
                add(new RowHolder().add(actorHolder));
                break;
        }

        return this;
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

    private void buildRow(AbstractMenuScene scene, WidgetGroup parent, ActorHolder actorHolder, int column) {
        if (ActorType.TableRow.equals(actorHolder.actorType)) {

            Table table = ((Table) parent);

            Cell row = table.row();

            for (ActorHolder innerActorHolder : ((RowHolder) actorHolder).actorHolders) {

                Actor innerActor = produceAndRegisterActor(scene, innerActorHolder);

                scene.onActorProduced(innerActor.getName(), innerActor);

                table.add(innerActor).align(Align.left).prefWidth(getColumnWidth(column));

                if (ActorType.Table.equals(innerActorHolder.actorType)) {
                    handleTableActor(scene, (Table) innerActor, (TableHolder) innerActorHolder);
                } else if (ActorType.Scroll.equals(innerActorHolder.actorType)) {
                    handleScrollPaneActor(scene, (ScrollPane) innerActor, (ScrollPaneHolder) innerActorHolder);
                }
            }
        } else if (ActorType.Table.equals(actorHolder.actorType)) {
            handleTableActor(scene, parent, (TableHolder) actorHolder);
        } else if (ActorType.Scroll.equals(actorHolder.actorType)) {
            ScrollPane scrollPane = (ScrollPane) produceAndRegisterActor(scene, actorHolder);
            handleScrollPaneActor(scene, scrollPane, (ScrollPaneHolder) actorHolder);
        }
    }

    private void handleScrollPaneActor(AbstractMenuScene scene, ScrollPane scrollPane, ScrollPaneHolder scrollPaneHolder) {
        Actor scrollPaneWidgetActor = produceAndRegisterActor(scene, scrollPaneHolder.actorHolder);

        if (ActorType.Table.equals(scrollPaneHolder.actorHolder.actorType)) {
            handleTableActor(scene, (Table) scrollPaneWidgetActor, (TableHolder) scrollPaneHolder.actorHolder);
        }

        scrollPane.setWidget(scrollPaneWidgetActor);

        scene.onActorProduced(scrollPaneWidgetActor.getName(), scrollPaneWidgetActor);
    }

    private Actor produceAndRegisterActor(AbstractMenuScene scene, ActorHolder innerActorHolder) {
        Actor innerActor = produceActor(scene, innerActorHolder);

        registerLabeledActor(scene, innerActorHolder, innerActor);
        registerListeners(scene, innerActorHolder, innerActor);
        return innerActor;
    }

    private void handleTableActor(AbstractMenuScene scene, WidgetGroup parent, TableHolder actorHolder) {
        int column = 0;
        for (ActorHolder innerActorHolder : actorHolder.actorHolders) {
            buildRow(scene, parent, innerActorHolder, column);
            column++;
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
            case Scroll:
                producedActor = scene.produceScroll(actorHolder.name);
                ScrollPane scrollPane = (ScrollPane) producedActor;
                ScrollPaneHolder scrollPaneHolder = (ScrollPaneHolder) actorHolder;
                scrollPane.setScrollBarPositions(scrollPaneHolder.horizontalScrollBar, scrollPaneHolder.verticalScrollBar);
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
