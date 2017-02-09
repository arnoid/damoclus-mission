package org.arnoid.damoclus;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.arnoid.damoclus.controller.persistent.ControlMapConfigurationController;
import org.arnoid.damoclus.controller.scene.SceneControllerProducer;
import org.arnoid.damoclus.controller.scene.SceneProducer;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.ui.SceneContainer;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class DamoclusGdxGame implements ApplicationListener {

    private SceneContainer sceneContainer;

    public static SceneProducer sceneProducer;
    public static SceneControllerProducer sceneControllerProducer;


    public static SkinController skinController;
    public static StringsController stringsController;

    private Stage stage;
    private ControlMapConfigurationController controllersConfigurationController;
    private MenuNavigationInputAdapter menuNavigationInputAdapter;

    @Override
    public void create() {
        Gdx.graphics.setContinuousRendering(true);

        sceneProducer = new SceneProducer();
        sceneControllerProducer = new SceneControllerProducer();

        controllersConfigurationController = new ControlMapConfigurationController();

        skinController = new SkinController();
        stringsController = new StringsController();

        menuNavigationInputAdapter = new MenuNavigationInputAdapter();

        sceneContainer = new SceneContainer();

        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(new InputMultiplexer(menuNavigationInputAdapter, stage));

        loadScene(SceneProducer.SceneType.MAIN_MENU);
    }

    public void loadScene(SceneProducer.SceneType sceneType) {

        AbstractScene scene = sceneProducer.produce(sceneType, this, stage);
        AbstractScene.SceneController sceneController = sceneControllerProducer.produce(sceneType, this);

        scene.setSceneController(sceneController);

        menuNavigationInputAdapter.unregisterMenuNavigation(sceneContainer.peek());
        menuNavigationInputAdapter.registerMenuNavigation(scene);

        sceneContainer.push(scene);

        scene.resize(stage.getViewport().getScreenWidth(), stage.getViewport().getScreenHeight());

        Gdx.graphics.requestRendering();
    }

    public void popScene() {
        AbstractScene scene = sceneContainer.pop();

        menuNavigationInputAdapter.unregisterMenuNavigation(scene);
        menuNavigationInputAdapter.registerMenuNavigation(sceneContainer.peek());

        Gdx.graphics.requestRendering();
    }

    @Override
    public void dispose() {
        stage.dispose();
        sceneContainer.dispose();

        skinController.dispose();
        sceneProducer.dispose();
    }

    @Override
    public void pause() {
        sceneContainer.pause();
    }

    @Override
    public void resume() {
        sceneContainer.resume();
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(deltaTime);
        sceneContainer.render(deltaTime);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        sceneContainer.resize(width, height);
    }

}
