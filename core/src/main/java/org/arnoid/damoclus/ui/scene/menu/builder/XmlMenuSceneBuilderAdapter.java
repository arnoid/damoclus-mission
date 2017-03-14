package org.arnoid.damoclus.ui.scene.menu.builder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.arnoid.damoclus.ui.scene.menu.builder.model.BaseModel;
import org.arnoid.damoclus.ui.scene.menu.builder.xml.XmlParserException;

public class XmlMenuSceneBuilderAdapter implements XmlMenuSceneBuilderListener {

    private static final String TAG = XmlMenuSceneBuilderAdapter.class.getSimpleName();

    @Override
    public void onStart(String layoutName) {
        Gdx.app.debug(TAG, "Started parsing of layout [" + layoutName + "]");
    }

    @Override
    public void onStop() {
        Gdx.app.debug(TAG, "Stopped parsing of layout");
    }

    @Override
    public void onError(XmlParserException e) {
        Gdx.app.error(TAG, e.getMessage(), e);
    }

    @Override
    public void onActorModel(BaseModel actorModel) {
        Gdx.app.debug(TAG, "Parsed actor model [" + actorModel + "]");
    }

    @Override
    public void onActor(Actor actor, BaseModel actorModel) {
        Gdx.app.debug(TAG, "Parsed actor [name=" + actor.getName() + "; actor=" + actor + "]");
    }
}
