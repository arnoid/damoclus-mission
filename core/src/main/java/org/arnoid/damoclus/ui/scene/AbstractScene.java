package org.arnoid.damoclus.ui.scene;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.arnoid.damoclus.DamoclusGdxGame;

import javax.inject.Inject;

public abstract class AbstractScene<T extends AbstractScene.SceneDelegate> extends ScreenAdapter {

    private static final String TAG = AbstractScene.class.getSimpleName();

    private T sceneDelegate;
    private Stage stage;

    private boolean visible = false;

    @Inject
    InputMultiplexer inputMultiplexer;

    public AbstractScene() {
        this(null);
    }

    public AbstractScene(T sceneDelegate) {
        this.sceneDelegate = sceneDelegate;
        this.stage = new Stage(new ScreenViewport());
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

    @Override
    public void show() {
        super.show();
        inputMultiplexer.addProcessor(stage);
        visible = true;

        resume();
    }

    @Override
    public void hide() {
        pause();

        super.hide();
        inputMultiplexer.removeProcessor(stage);
        visible = false;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().setScreenSize(width, height);
    }

    @Override
    public void render(float delta) {
        if (visible) {
            super.render(delta);
            stage.draw();
        }
    }

    public void act(float delta) {
        stage.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }

    public Image findImage(String name) {
        return (Image) findActor(name);
    }

    public TextButton findButton(String name) {
        return (TextButton) findActor(name);
    }

    public Label findLabel(String name) {
        return (Label) findActor(name);
    }

    public Actor findActor(String name) {
        return stage.getRoot().findActor(name);
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
