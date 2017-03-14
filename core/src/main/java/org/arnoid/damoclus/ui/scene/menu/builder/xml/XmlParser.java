package org.arnoid.damoclus.ui.scene.menu.builder.xml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilderListener;
import org.arnoid.damoclus.ui.scene.menu.builder.model.BaseModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.CheckBoxModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.ContainerModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.LabelModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.ListModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.RowModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.ScrollPaneModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.SelectBoxModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.SpaceModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.TableModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.TextButtonModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.TextFieldModel;
import org.arnoid.damoclus.ui.scene.menu.builder.model.WindowModel;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class XmlParser {
    private static final String TAG = XmlParser.class.getSimpleName();
    private static final String DEFAULT_LAYOUT_FOLDER = "layout";

    public static final String TABLE_TAG = "Table";
    public static final String ROW_TAG = "Row";
    public static final String WINDOW_TAG = "Window";
    public static final String TEXT_BUTTON_TAG = "TextButton";
    public static final String TEXT_FIELD_TAG = "TextField";
    public static final String LABEL_TAG = "Label";
    public static final String CHECKBOX_TAG = "CheckBox";
    public static final String CONTAINER_TAG = "Container";
    public static final String SCROLLPANE_TAG = "ScrollPane";
    public static final String SPACE_TAG = "Space";
    public static final String SELECTLIST_TAG = "SelectList";
    public static final String LISTBOX_TAG = "List";

    public static final List<String> PARENT_TAGS_LIST = Arrays.asList(
            TABLE_TAG.toLowerCase(),
            ROW_TAG.toLowerCase(),
            WINDOW_TAG.toLowerCase(),
            CONTAINER_TAG.toLowerCase(),
            SCROLLPANE_TAG.toLowerCase()
    );

    private final XmlMenuSceneBuilderListener listener;

    public XmlParser(XmlMenuSceneBuilderListener listener) {
        this.listener = listener;
    }

    public List<BaseModel> parseLayout(String layout) {
        List<BaseModel> models = new ArrayList<>();

        FileHandle layoutFileHandle = Gdx.files.internal(DEFAULT_LAYOUT_FOLDER + File.separator + layout);

        InputStreamReader inputStreamReader = null;
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();

            inputStreamReader = new FileReader(layoutFileHandle.file());

            xmlPullParser.setInput(inputStreamReader);

            models = buildModels(xmlPullParser);

        } catch (XmlPullParserException e) {
            if (listener != null) {
                listener.onError(new XmlParserException("Unable to parse layout file [" + layoutFileHandle.path() + "]", e));
            }
            Gdx.app.error(TAG, e.getMessage(), e);
        } catch (IOException e) {
            if (listener != null) {
                listener.onError(new XmlParserException("Problem with reading layout file [" + layoutFileHandle.path() + "]", e));
            }
            Gdx.app.error(TAG, e.getMessage(), e);
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return models;
    }

    public List<BaseModel> buildModels(XmlPullParser xmlParser) throws IOException, XmlPullParserException {
        List<BaseModel> modelsList = new LinkedList<>();
        int eventType = xmlParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    BaseModel model = processXmlStartTag(xmlParser);
                    if (model != null) {
                        if (listener != null) {
                            listener.onActorModel(model);
                        }
                        modelsList.add(model);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (canHaveChildren(xmlParser.getName())) {
                        eventType = XmlPullParser.END_DOCUMENT;
                    }
                    break;
                default:
                    break;
            }
            if (eventType != XmlPullParser.END_DOCUMENT) {
                eventType = xmlParser.next();
            }
        }
        return modelsList;
    }

    private BaseModel processXmlStartTag(XmlPullParser xmlParser) throws IOException, XmlPullParserException {
        String tagName = xmlParser.getName();
        BaseModel model;
        if (WINDOW_TAG.equalsIgnoreCase(tagName)) {
            model = buildWindowModel(xmlParser);
        } else if (TABLE_TAG.equalsIgnoreCase(tagName)) {
            model = buildTableModel(xmlParser);
        } else if (TEXT_BUTTON_TAG.equalsIgnoreCase(tagName)) {
            model = buildTextButtonModel(xmlParser);
        } else if (ROW_TAG.equalsIgnoreCase(tagName)) {
            model = buildRowModel(xmlParser);
        } else if (TEXT_FIELD_TAG.equalsIgnoreCase(tagName)) {
            model = buildTextFieldModel(xmlParser);
        } else if (LABEL_TAG.equalsIgnoreCase(tagName)) {
            model = buildLableModel(xmlParser);
        } else if (CHECKBOX_TAG.equalsIgnoreCase(tagName)) {
            model = buildCheckBoxModel(xmlParser);
        } else if (CONTAINER_TAG.equalsIgnoreCase(tagName)) {
            model = buildContainerModel(xmlParser);
        } else if (SPACE_TAG.equalsIgnoreCase(tagName)) {
            model = buildSpaceModel(xmlParser);
        } else if (SELECTLIST_TAG.equalsIgnoreCase(tagName)) {
            model = buildSelectBoxModel(xmlParser);
        } else if (SCROLLPANE_TAG.equalsIgnoreCase(tagName)) {
            model = buildScrollPaneModel(xmlParser);
        } else if (LISTBOX_TAG.equalsIgnoreCase(tagName)) {
            model = buildListModel(xmlParser);
        } else {
            String message = "Unknown tag [" + tagName + "]. It will be ignored";
            Gdx.app.error(TAG, message);
            if (listener != null) {
                listener.onError(new XmlParserException(message));
            }
            model = null;
        }
        return model;
    }

    private BaseModel buildScrollPaneModel(XmlPullParser xmlParser) {
        ScrollPaneModel model = new ScrollPaneModel();
        model.actorType = XmlMenuSceneBuilder.ActorType.Scroll;
        setBaseModelParameters(model, xmlParser);

        model.setHorizontalScrollBar(XmlHelper.readBooleanAttribute(xmlParser, "horizontal", false));
        model.setVerticalScrollBar(XmlHelper.readBooleanAttribute(xmlParser, "vertical", false));

        return model;
    }

    private BaseModel buildListModel(XmlPullParser xmlParser) {
        ListModel model = new ListModel();
        model.actorType = XmlMenuSceneBuilder.ActorType.List;
        setBaseModelParameters(model, xmlParser);

        return model;
    }

    private BaseModel buildSelectBoxModel(XmlPullParser xmlParser) {
        SelectBoxModel selectBoxModel = new SelectBoxModel();
        selectBoxModel.actorType = XmlMenuSceneBuilder.ActorType.SelectList;
        setBaseModelParameters(selectBoxModel, xmlParser);

        return selectBoxModel;
    }

    private BaseModel buildSpaceModel(XmlPullParser xmlParser) {
        SpaceModel spaceModel = new SpaceModel();
        spaceModel.actorType = XmlMenuSceneBuilder.ActorType.Space;
        setBaseModelParameters(spaceModel, xmlParser);

        return spaceModel;
    }

    private BaseModel buildContainerModel(XmlPullParser xmlParser) throws IOException, XmlPullParserException {
        ContainerModel containerModel = new ContainerModel();
        containerModel.actorType = XmlMenuSceneBuilder.ActorType.Container;
        setBaseModelParameters(containerModel, xmlParser);
        xmlParser.next();
        List<BaseModel> baseModels = buildModels(xmlParser);
        if (baseModels != null && baseModels.size() > 0) {
            containerModel.child = baseModels.get(0);
        }
        return containerModel;
    }

    private BaseModel buildCheckBoxModel(XmlPullParser xmlParser) {
        CheckBoxModel model = new CheckBoxModel();
        model.actorType = XmlMenuSceneBuilder.ActorType.CheckBox;
        setBaseModelParameters(model, xmlParser);
        model.checked = XmlHelper.readBooleanAttribute(xmlParser, "checked", false);

        return model;
    }

    private BaseModel buildTextFieldModel(XmlPullParser xmlParser) {
        TextFieldModel model = new TextFieldModel();
        model.actorType = XmlMenuSceneBuilder.ActorType.TextField;
        setBaseModelParameters(model, xmlParser);
        model.text = XmlHelper.readStringAttribute(xmlParser, "text");

        return model;
    }

    private BaseModel buildLableModel(XmlPullParser xmlParser) {
        LabelModel model = new LabelModel();
        model.actorType = XmlMenuSceneBuilder.ActorType.Label;
        setBaseModelParameters(model, xmlParser);
        model.text = XmlHelper.readStringAttribute(xmlParser, "text");

        return model;
    }

    private BaseModel buildRowModel(XmlPullParser xmlParser) throws IOException, XmlPullParserException {
        RowModel model = new RowModel();
        model.actorType = XmlMenuSceneBuilder.ActorType.TableRow;
        setBaseModelParameters(model, xmlParser);
        model.colspan = XmlHelper.readIntAttribute(xmlParser, "colspan", 1);

        xmlParser.next();
        model.children = buildModels(xmlParser);
        return model;
    }

    private BaseModel buildTextButtonModel(XmlPullParser xmlParser) {
        TextButtonModel model = new TextButtonModel();
        model.actorType = XmlMenuSceneBuilder.ActorType.TextButton;
        setBaseModelParameters(model, xmlParser);
        model.text = XmlHelper.readStringAttribute(xmlParser, "text");

        return model;
    }

    private BaseModel buildTableModel(XmlPullParser xmlParser) throws IOException, XmlPullParserException {
        TableModel model = new TableModel();
        model.actorType = XmlMenuSceneBuilder.ActorType.Table;
        setBaseModelParameters(model, xmlParser);

        xmlParser.next();
        model.children = buildModels(xmlParser);
        return model;
    }

    private BaseModel buildWindowModel(XmlPullParser xmlParser) throws IOException, XmlPullParserException {
        WindowModel model = new WindowModel();
        model.actorType = XmlMenuSceneBuilder.ActorType.Window;
        setBaseModelParameters(model, xmlParser);
        setWindowModelParameters(model, xmlParser);

        xmlParser.next();
        model.children = buildModels(xmlParser);

        return model;
    }

    private void setWindowModelParameters(WindowModel model, XmlPullParser xmlParser) {
        model.pack = XmlHelper.readBooleanAttribute(xmlParser, "pack", false);
        model.movable = XmlHelper.readBooleanAttribute(xmlParser, "movable", false);
        model.modal = XmlHelper.readBooleanAttribute(xmlParser, "modal", false);

        model.title = XmlHelper.readStringAttribute(xmlParser, "title");
    }

    private void setBaseModelParameters(BaseModel model, XmlPullParser xmlParser) {
        model.name = XmlHelper.readStringAttribute(xmlParser, "name");
        model.debug = XmlHelper.readBooleanAttribute(xmlParser, "debug", false);
        model.x = XmlHelper.readFloatAttribute(xmlParser, "x", 0F);
        model.y = XmlHelper.readFloatAttribute(xmlParser, "y", 0F);

        model.width = XmlHelper.readFloatAttribute(xmlParser, "width", 0F);
        model.height = XmlHelper.readFloatAttribute(xmlParser, "height", 0F);
        model.pad(XmlHelper.readFloatAttribute(xmlParser, "pad", 0F));

        float padLeft = XmlHelper.readFloatAttribute(xmlParser, "padLeft", 0F);
        if (padLeft != 0) {
            model.padLeft = padLeft;
        }

        float padRight = XmlHelper.readFloatAttribute(xmlParser, "padRight", 0F);
        if (padRight != 0) {
            model.padRight = padRight;
        }

        float padBottom = XmlHelper.readFloatAttribute(xmlParser, "padBottom", 0F);
        if (padBottom != 0) {
            model.padBottom = padBottom;
        }

        float padTop = XmlHelper.readFloatAttribute(xmlParser, "padTop", 0F);
        if (padTop != 0) {
            model.padTop = padTop;
        }

        model.expandX = XmlHelper.readBooleanAttribute(xmlParser, "expandX", false);
        model.expandY = XmlHelper.readBooleanAttribute(xmlParser, "expandY", false);
        model.fillX = XmlHelper.readBooleanAttribute(xmlParser, "fillX", false);
        model.fillY = XmlHelper.readBooleanAttribute(xmlParser, "fillY", false);
        model.fillParent = XmlHelper.readBooleanAttribute(xmlParser, "fillParent", false);
        model.align = XmlHelper.readAlignmentAttribute(xmlParser, "align", Align.center);
        model.grow = XmlHelper.calculateGrow(XmlHelper.readStringAttribute(xmlParser, "grow"));
    }

    private boolean canHaveChildren(String tag) {
        return PARENT_TAGS_LIST.contains(tag.toLowerCase());
    }

}
