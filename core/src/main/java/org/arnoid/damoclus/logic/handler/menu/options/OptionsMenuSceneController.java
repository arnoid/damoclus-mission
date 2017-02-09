package org.arnoid.damoclus.logic.handler.menu.options;

import com.badlogic.gdx.Gdx;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class OptionsMenuSceneController extends AbstractScene.SceneController {

    private static final String TAG = OptionsMenuSceneController.class.getSimpleName();

    public OptionsMenuSceneController(DamoclusGdxGame game) {
        super(game);
    }

    public void onAudio() {
        Gdx.app.log(TAG, "Audio clicked");
    }

    public void onVideo() {
        Gdx.app.log(TAG, "Video clicked");
    }

    public void onControllers() {
        Gdx.app.log(TAG, "Controllers clicked");
    }

    public void onBack() {
        Gdx.app.log(TAG, "Back clicked");
        getGame().popScene();
    }
}
