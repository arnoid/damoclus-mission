package org.arnoid.damoclus.ui.scene.menu.builder;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.ui.scene.menu.builder.model.BaseModel;
import org.arnoid.damoclus.ui.scene.menu.builder.xml.XmlParser;

import javax.inject.Inject;
import java.util.List;

public class XmlMenuSceneBuilder {

    private static final String TAG = XmlMenuSceneBuilder.class.getSimpleName();
    private final String layoutFileName;

    private XmlMenuSceneBuilderListener listener;

    @Inject
    SkinController skinController;
    @Inject
    StringsController stringsController;

    public enum ActorType {
        TextButton,
        Label,
        CheckBox,
        SelectList,
        Scroll,
        Table,
        TableRow,
        Space,
        Window,
        TextField,
        Container,
        List,
    }

    private XmlMenuSceneBuilder(String layoutFileName) {
        super();

        DamoclusGdxGame.mainComponent().inject(this);

        this.layoutFileName = layoutFileName;
    }

    public static XmlMenuSceneBuilder with(String layoutFile) {
        return new XmlMenuSceneBuilder(layoutFile);
    }

    public XmlMenuSceneBuilder listener(XmlMenuSceneBuilderListener listener) {
        this.listener = listener;
        return this;
    }

    public void build(Stage stage) {

        if (listener != null) {
            listener.onStart(layoutFileName);
        }

        XmlParser xmlParser = new XmlParser(listener);
        List<BaseModel> holders = xmlParser.parseLayout(layoutFileName);


        ActorProducer actorProducer = new ActorProducer(skinController, stringsController, listener);

        for (BaseModel baseModel : holders) {
            stage.addActor(actorProducer.produce(baseModel));
        }

        if (listener != null) {
            listener.onStop();
        }
    }

}
