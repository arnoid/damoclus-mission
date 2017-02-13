package org.arnoid.damoclus.ui.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.arnoid.damoclus.DamoclusGdxGame;

public abstract class AbstractScene<T extends AbstractScene.SceneDelegate> extends ScreenAdapter {

    private static final String TAG = AbstractScene.class.getSimpleName();

    private T sceneDelegate;
    private Stage stage;

    public AbstractScene(Stage stage) {
        this(stage, null);
    }

    public AbstractScene(Stage stage, T sceneDelegate) {
        this.sceneDelegate = sceneDelegate;
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setSceneDelegate(T sceneDelegate) {
        this.sceneDelegate = sceneDelegate;
    }

    public T getSceneDelegate() {
        return sceneDelegate;
    }

    public void onSceneDelegate() {

    }

    public static class SceneDelegate {

        private final DamoclusGdxGame game;

        public SceneDelegate(DamoclusGdxGame game) {
            this.game = game;
        }

        public DamoclusGdxGame getGame() {
            return game;
        }
    }
}
