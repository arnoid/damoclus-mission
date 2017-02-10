package org.arnoid.damoclus.ui.scene;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.arnoid.damoclus.DamoclusGdxGame;

public abstract class AbstractScene<T extends AbstractScene.SceneController> extends ScreenAdapter {

    private T sceneController;
    private Stage stage;

    public AbstractScene(Stage stage) {
        this(stage, null);
    }

    public AbstractScene(Stage stage, T sceneController) {
        this.sceneController = sceneController;
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setSceneController(T sceneController) {
        this.sceneController = sceneController;
    }

    public T getSceneController() {
        return sceneController;
    }

    public static class SceneController {

        private final DamoclusGdxGame game;

        public SceneController(DamoclusGdxGame game) {
            this.game = game;
        }

        public DamoclusGdxGame getGame() {
            return game;
        }
    }
}
