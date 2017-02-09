package org.arnoid.damoclus.controller.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.logic.handler.menu.main.MainMenuSceneController;
import org.arnoid.damoclus.logic.handler.menu.options.OptionsMenuSceneController;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class SceneControllerProducer implements Disposable {

    public static final String TAG = SceneControllerProducer.class.getSimpleName();

    @Override
    public void dispose() {

    }

    public AbstractScene.SceneController produce(SceneProducer.SceneType sceneType, DamoclusGdxGame game) {
        AbstractScene.SceneController sceneController;
        switch (sceneType) {
            case MAIN_MENU:
                sceneController = produceMainMenuSceneController(game);
                break;
            case OPTIONS_MENU:
                sceneController = produceOptionsMenuSceneController(game);
                break;
            default:
                Gdx.app.error(TAG, "Unable to produce scene for type [" + sceneType.name() + "]");
                sceneController = null;
        }
        return sceneController;
    }

    protected MainMenuSceneController produceMainMenuSceneController(DamoclusGdxGame game) {
        return new MainMenuSceneController(game);
    }

    protected OptionsMenuSceneController produceOptionsMenuSceneController(DamoclusGdxGame game) {
        return new OptionsMenuSceneController(game);
    }

}
