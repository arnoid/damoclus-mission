package org.arnoid.damoclus.ui.scene.menu.builder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.ActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.RowHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.ScrollPaneHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.TableHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.WindowHolder;

import java.util.ArrayList;
import java.util.List;

public class MenuSceneBuilder extends AbstractMenuBuilder<MenuSceneBuilder, ActorHolder> {

    private static final String TAG = MenuSceneBuilder.class.getSimpleName();

    public enum ActorType {
        TextButton,
        Label,
        CheckBox,
        SelectBox,
        Scroll,
        Table,
        TableRow,
        Space,
        Window,
    }

    private List<RowHolder> rowHolders;

    private final AbstractMenuScene scene;
    private final WindowHolder windowHolder;

    private MenuSceneBuilder(AbstractMenuScene scene, WindowHolder windowHolder) {
        super();

        rowHolders = new ArrayList<>();

        this.scene = scene;
        this.windowHolder = windowHolder;
    }

    @Override
    public MenuSceneBuilder add(ActorHolder actorHolder) {
        switch (actorHolder.actorType) {
            case TableRow:
                rowHolders.add((RowHolder) actorHolder);
                break;
            case Table:
            case CheckBox:
            case Label:
            case Scroll:
            case SelectBox:
            case TextButton:
            default:
                add(RowHolder.row().add(actorHolder));
                break;
        }

        return this;
    }

    public static MenuSceneBuilder with(AbstractMenuScene scene, WindowHolder windowHolder) {
        return new MenuSceneBuilder(scene, windowHolder);
    }

    public void build() {
        Window window = scene.getWindow();

        window.setX(windowHolder.x);
        window.setY(windowHolder.y);

        window.setWidth(windowHolder.width);
        window.setHeight(windowHolder.height);

        window.pad(windowHolder.padTop, windowHolder.padLeft, windowHolder.padBottom, windowHolder.padRight);

        window.setMovable(windowHolder.movable);
        window.setModal(windowHolder.modal);

        for (RowHolder rowHolder : rowHolders) {
            handleTableRow(scene, rowHolder, window);
        }

        if (windowHolder.pack) {
            window.pack();
        }
    }

    private void handleTableRow(AbstractMenuScene scene, RowHolder actorHolder, Table table) {
        handleCell(table.row(), actorHolder);

        for (ActorHolder innerActorHolder : actorHolder.actorHolders) {

            Actor innerActor = produceAndRegisterActor(scene, innerActorHolder);

            scene.onActorProduced(innerActorHolder.name, innerActor);

            handleCell(table.add(innerActor), innerActorHolder);

            if (ActorType.Table.equals(innerActorHolder.actorType)) {
                handleTableActor(scene, (Table) innerActor, (TableHolder) innerActorHolder);
            } else if (ActorType.Scroll.equals(innerActorHolder.actorType)) {
                handleScrollPaneActor(scene, (ScrollPane) innerActor, (ScrollPaneHolder) innerActorHolder);
            }
        }
    }

    public void handleCell(Cell cell, ActorHolder actorHolder) {
        if (actorHolder.width > 0) {
            cell.width(actorHolder.width);
        }

        if (actorHolder.height > 0) {
            cell.height(actorHolder.height);
        }

        cell.align(actorHolder.align);

        cell.pad(actorHolder.padTop, actorHolder.padLeft, actorHolder.padBottom, actorHolder.padRight);

        switch (actorHolder.grow) {
            case Grow:
                cell.grow();
                break;
            case GrowX:
                cell.growX();
                break;
            case GrowY:
                cell.growY();
                break;
            case None:
            default:
                break;
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

    private void handleTableActor(AbstractMenuScene scene, Table parent, TableHolder actorHolder) {
        for (RowHolder innerActorHolder : actorHolder.actorHolders) {
            handleTableRow(scene, innerActorHolder, parent);
        }
    }

    private void registerLabeledActor(AbstractMenuScene scene, ActorHolder actorHolder, Actor producedActor) {
        switch (actorHolder.actorType) {
            case TextButton:
            case SelectBox:
            case Label:
                scene.registerLabeledActor(producedActor);
                break;
            case CheckBox:
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
            case Table:
                break;
        }
    }

    private Actor produceActor(AbstractMenuScene scene, ActorHolder actorHolder) {
        Actor producedActor = null;
        switch (actorHolder.actorType) {
            case CheckBox:
                producedActor = scene.produceCheckBox(actorHolder.name);
                break;
            case Label:
                producedActor = scene.produceLabel(actorHolder.name);
                break;
            case SelectBox:
                producedActor = scene.produceSelectBox(actorHolder.name);
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

}
