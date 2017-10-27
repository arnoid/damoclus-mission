package org.arnoid.damoclus.ui.scene.menu.builder;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.controller.skin.AssetsController;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringRefUtil;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.ui.scene.menu.builder.model.BaseModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.CheckBoxModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.ContainerModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.ImageModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.LabelModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.ListModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.RowModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.ScrollPaneModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.StackModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.TableModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.TextButtonModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.TextFieldModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.WindowModel;
import org.arnoid.damoclus.ui.scene.menu.builder.xml.XmlParserException;
import org.arnoid.damoclus.ui.view.SelectList;

public class ActorProducer {

    private static final float WINDOW_TITLE_PADDING = 5F;

    private final SkinController skinController;
    private final StringsController stringsController;
    private final XmlMenuSceneBuilderListener listener;
    private final AssetsController assetsController;

    public ActorProducer(SkinController skinController, StringsController stringsController, AssetsController assetsController, XmlMenuSceneBuilderListener listener) {
        this.skinController = skinController;
        this.stringsController = stringsController;
        this.assetsController = assetsController;
        this.listener = listener;
    }

    public Actor produce(BaseModel model) {
        Actor actor;
        switch (model.actorType) {
            case TextButton:
                actor = produceTextButton((TextButtonModel) model);
                break;
            case Space:
                actor = produceSpace(model);
                break;
            case CheckBox:
                actor = produceCheckBox((CheckBoxModel) model);
                break;
            case Window:
                actor = produceWindow((WindowModel) model);
                break;
            case TextField:
                actor = produceTextField((TextFieldModel) model);
                break;
            case Table:
                actor = produceTable(model);
                break;
            case SelectList:
                actor = produceSelectList(model);
                break;
            case Scroll:
                actor = produceScroll((ScrollPaneModel) model);
                break;
            case Label:
                actor = produceLabel((LabelModel) model);
                break;
            case Container:
                actor = produceContainer((ContainerModel) model);
                break;
            case List:
                actor = produceList((ListModel) model);
                break;
            case Image:
                actor = produceImage((ImageModel) model);
                break;
            case Stack:
                actor = produceStack((StackModel) model);
                break;
            default:
                if (listener != null) {
                    listener.onError(new XmlParserException("Unknown actor type [" + model.actorType + "]"));
                }
                actor = null;
        }

        if (actor != null && listener != null) {
            listener.onActor(actor, model);
        }

        return actor;
    }

    private Image produceImage(ImageModel model) {
        String src = model.getSrc();
        if(src == null) {
            src = "";
        }
        String resource = StringRefUtil.imageByRef(R.image._all, src);
        Image image = new Image(assetsController.loadSyncAndGet(resource, Texture.class));
        applyBaseParameters(image, model);

        return image;
    }

    private List produceList(ListModel model) {
        List list = new List(skinController.getSkin());
        applyBaseParameters(list, model);

        return list;
    }

    private Container produceContainer(ContainerModel model) {
        Container container = new Container();
        applyBaseParameters(container, model);

        if (model.child != null) {
            Actor child = produce(model.child);

            container.setActor(child);

            container.align(model.child.align);

            container.padLeft(model.padLeft)
                    .padRight(model.padRight)
                    .padTop(model.padTop)
                    .padBottom(model.padBottom);
        }

        return container;
    }

    private Stack produceStack(StackModel model) {
        Stack stack = new Stack();

        for(BaseModel baseModel : model.getChildren()) {
            Actor actor = produce(baseModel);
            stack.add(actor);
        }

        return stack;
    }

    public Label produceLabel(LabelModel model) {
        Label label = new Label(model.name, skinController.getSkin());
        applyBaseParameters(label, model);
        label.setText(stringsController.string(model.text));
        return label;
    }

    public ScrollPane produceScroll(ScrollPaneModel model) {
        final ScrollPane scrollPane = new ScrollPane(null, skinController.getSkin());
        applyBaseParameters(scrollPane, model);

        scrollPane.setScrollBarPositions(model.horizontalScrollBar, model.verticalScrollBar);
        scrollPane.setFadeScrollBars(false);

        if (model.child != null) {
            Actor child = produce(model.child);

            scrollPane.setWidget(child);
        }

        return scrollPane;
    }

    public SelectList produceSelectList(BaseModel model) {
        final SelectList selectList = new SelectList(skinController.getSkin());
        applyBaseParameters(selectList, model);

        return selectList;
    }

    public Table produceTable(BaseModel model) {
        Table table = new Table(skinController.getSkin());
        applyBaseParameters(table, model);

        produceChildrenActors((TableModel) model, table);

        return table;
    }

    public TextField produceTextField(TextFieldModel model) {
        TextField textField = new TextField(model.name, skinController.getSkin());
        applyBaseParameters(textField, model);
        textField.setText(stringsController.string(model.text));
        return textField;
    }

    public Window produceWindow(WindowModel model) {
        Window window = new Window(stringsController.string(model.title), skinController.getSkin().get("default", Window.WindowStyle.class));

        window.getTitleTable().align(Align.topLeft).padLeft(15).padTop(WINDOW_TITLE_PADDING);

        window.getTitleLabel().setEllipsis(false);

        window.add().height(WINDOW_TITLE_PADDING + window.getTitleLabel().getHeight() + WINDOW_TITLE_PADDING);

        produceChildrenActors(model, window);

        window.pad(model.padTop, model.padLeft, model.padBottom, model.padRight);

        window.setMovable(model.movable);
        window.setModal(model.modal);

        if (model.pack) {
            window.pack();
        }

        applyBaseParameters(window, model);

        return window;
    }

    public ImageButton produceCheckBox(CheckBoxModel model) {
        final ImageButton checkBox = new ImageButton(skinController.getSkin(), skinController.getImgByttonCheckBoxStyle());

        applyBaseParameters(checkBox, model);

        checkBox.setChecked(model.checked);

        return checkBox;
    }

    public Actor produceSpace(BaseModel model) {
        Widget space = new Widget();
        applyBaseParameters(space, model);

        return space;
    }

    public TextButton produceTextButton(TextButtonModel model) {
        TextButton button = new TextButton(model.name, skinController.getSkin());
        applyBaseParameters(button, model);
        button.setText(stringsController.string(model.text));

        return button;
    }

    public <T extends Widget> void applyBaseParameters(T actor, BaseModel model) {
        actor.setDebug(model.debug);
        actor.setName(model.name);

        if (model.width > 0) {
            actor.setWidth(model.width);
        }
        if (model.height > 0) {
            actor.setHeight(model.height);
        }
        actor.setY(model.y);
        actor.setX(model.x);

        actor.setFillParent(model.fillParent);
    }

    public <T extends WidgetGroup> void applyBaseParameters(T actor, BaseModel model) {
        actor.setDebug(model.debug);
        actor.setName(model.name);
        if (model.width > 0) {
            actor.setWidth(model.width);
        }
        if (model.height > 0) {
            actor.setHeight(model.height);
        }
        actor.setY(model.y);
        actor.setX(model.x);

        actor.setFillParent(model.fillParent);
    }

    private void produceChildrenActors(TableModel model, Table table) {
        for (BaseModel childBaseModel : model.children) {
            Cell row = table.row();
            if (childBaseModel.actorType == XmlMenuSceneBuilder.ActorType.TableRow) {
                RowModel rowModel = (RowModel) childBaseModel;
                row = row.colspan(rowModel.colspan);
                applyTableCellParameters(row, rowModel);

                for (BaseModel rowChildModel : rowModel.children) {
                    Cell<Actor> actorCell = table.add(produce(rowChildModel));
                    applyTableCellParameters(actorCell, rowChildModel);
                }
            } else {
                Cell<Actor> actorCell = table.add(produce(childBaseModel));
                applyTableCellParameters(actorCell, childBaseModel);
            }
        }
    }

    private Cell applyTableCellParameters(Cell cell, BaseModel cellModel) {
        cell = cell.padLeft(cellModel.padLeft)
                .padRight(cellModel.padRight)
                .padTop(cellModel.padTop)
                .padBottom(cellModel.padBottom);

        cell.align(cellModel.align);

        switch (cellModel.grow) {
            case Grow:
                cell = cell.grow();
                break;
            case GrowY:
                cell = cell.growY();
                break;
            case GrowX:
                cell = cell.growX();
                break;
            case None:
            default:
        }

        if (cellModel.width > 0) {
            cell.minWidth(cellModel.width);
            cell.maxWidth(cellModel.width);
        }

        if (cellModel.height > 0) {
            cell.minHeight(cellModel.height);
            cell.maxHeight(cellModel.height);
        }

        if (cellModel.expandX) {
            cell = cell.expandX();
        }

        if (cellModel.expandY) {
            cell = cell.expandY();
        }

        if (cellModel.fillX) {
            cell = cell.fillX();
        }

        if (cellModel.fillY) {
            cell = cell.fillY();
        }

        return cell;
    }
}
