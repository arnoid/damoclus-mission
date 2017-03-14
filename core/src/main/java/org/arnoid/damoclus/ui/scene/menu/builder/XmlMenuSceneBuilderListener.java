package org.arnoid.damoclus.ui.scene.menu.builder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import org.arnoid.damoclus.ui.scene.menu.builder.model.BaseModel;
import org.arnoid.damoclus.ui.scene.menu.builder.xml.XmlParserException;

public interface XmlMenuSceneBuilderListener {
    void onStart(String layoutName);

    void onStop();

    void onError(XmlParserException e);

    void onActorModel(BaseModel actorModel);

    void onActor(Actor actor, BaseModel actorModel);
}
