package org.arnoid.damoclus.controller.skin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class SkinController implements Disposable {

    Skin skin;

    public SkinController() {
        skin = new Skin(Gdx.files.internal("data/skin/skin.json"));
    }

    @Override
    public void dispose() {
        skin.dispose();
    }

    public Skin getSkin() {
        return skin;
    }
}
