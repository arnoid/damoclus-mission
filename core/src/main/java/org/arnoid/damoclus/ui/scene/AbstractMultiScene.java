package org.arnoid.damoclus.ui.scene;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMultiScene<T extends AbstractScene.SceneDelegate> extends AbstractScene<T> {

    private List<AbstractScene> nestedScenes = new ArrayList<>();

    public AbstractMultiScene(Stage stage) {
        super(stage);
    }

    public AbstractMultiScene(Stage stage, T sceneDelegate) {
        super(stage, sceneDelegate);
    }

    public void add(AbstractScene scene) {
        nestedScenes.add(scene);
    }

    public void remove(AbstractScene scene) {
        nestedScenes.remove(scene);
    }

    @Override
    public void render(float delta) {
        for (AbstractScene scene : nestedScenes) {
            scene.render(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        for (AbstractScene scene : nestedScenes) {
            scene.resize(width, height);
        }
    }

    @Override
    public void show() {
        for (AbstractScene scene : nestedScenes) {
            scene.show();
        }
    }

    @Override
    public void hide() {
        for (AbstractScene scene : nestedScenes) {
            scene.hide();
        }
    }

    @Override
    public void pause() {
        for (AbstractScene scene : nestedScenes) {
            scene.pause();
        }
    }

    @Override
    public void resume() {
        for (AbstractScene scene : nestedScenes) {
            scene.resume();
        }
    }

    @Override
    public void dispose() {
        for (AbstractScene scene : nestedScenes) {
            scene.dispose();
        }
    }
}
