package org.arnoid.damoclus.controller.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.ui.scene.AbstractScene;
import org.arnoid.damoclus.ui.scene.menu.main.MainMenuScene;
import org.arnoid.damoclus.ui.scene.menu.options.OptionsMenuScene;

public class SceneProducer implements Disposable {

    public static final String TAG = SceneProducer.class.getSimpleName();

    public enum SceneType {
        MAIN_MENU,
        OPTIONS_MENU
    }

    @Override
    public void dispose() {

    }

    public AbstractScene produce(SceneType sceneType, DamoclusGdxGame game, Stage stage) {
        AbstractScene scene;
        switch (sceneType) {
            case MAIN_MENU:
                scene = produceMainMenuScene(game, stage);
                break;
            case OPTIONS_MENU:
                scene = produceOptionsMenuScene(game, stage);
                break;
            default:
                Gdx.app.error(TAG, "Unable to produce scene for type [" + sceneType.name() + "]");
                scene = null;
        }
        return scene;
    }

    protected AbstractScene produceMainMenuScene(DamoclusGdxGame game, Stage stage) {
        return new MainMenuScene(game, stage);
    }

    protected AbstractScene produceOptionsMenuScene(DamoclusGdxGame game, Stage stage) {
        return new OptionsMenuScene(game, stage);
    }
}
