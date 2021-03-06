package org.arnoid.damoclus.logic.handler.menu.main;

import com.badlogic.gdx.Gdx;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.scene.SceneProducer;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class MainMenuSceneController extends AbstractScene.SceneController {

    private static final String TAG = MainMenuSceneController.class.getSimpleName();

    public MainMenuSceneController(DamoclusGdxGame game) {
        super(game);
    }

    public void onNewGame() {
        Gdx.app.log(TAG, "New game clicked");
    }

    public void onOptions() {
        Gdx.app.log(TAG, "Options clicked");

        getGame().loadScene(SceneProducer.SceneType.OPTIONS_MENU);
    }

    public void onQuit() {
        Gdx.app.log(TAG, "Quit clicked");
        Gdx.app.exit();
    }
}
