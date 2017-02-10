package org.arnoid.damoclus;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.arnoid.damoclus.component.ControllerModule;
import org.arnoid.damoclus.component.DaggerMainComponent;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.component.SceneControllerModule;
import org.arnoid.damoclus.component.SceneModule;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.logic.handler.menu.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.SceneContainer;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;

public class DamoclusGdxGame implements ApplicationListener, SceneNavigator {

    private static final String TAG = DamoclusGdxGame.class.getSimpleName();

    private Stage stage;
    private SceneContainer sceneContainer;

    @Inject
    ConfigurationController configurationController;
    @Inject
    StringsController stringsController;
    @Inject
    SkinController skinController;

    private MenuNavigationInputAdapter menuNavigationInputAdapter;

    public static MainComponent mainComponent;

    @Override
    public void create() {
        Gdx.graphics.setContinuousRendering(true);

        stage = new Stage(new ScreenViewport());

        mainComponent = DaggerMainComponent.builder()
                .controllerModule(new ControllerModule())
                .sceneModule(new SceneModule(stage))
                .sceneControllerModule(new SceneControllerModule(this))
                .build();

        mainComponent.inject(this);

        menuNavigationInputAdapter = new MenuNavigationInputAdapter(mainComponent);

        sceneContainer = new SceneContainer();

        Gdx.input.setInputProcessor(new InputMultiplexer(menuNavigationInputAdapter, stage));

        loadScene(SceneType.MENU_MAIN);
    }

    public void loadScene(SceneType sceneType) {

        AbstractScene scene;
        AbstractScene.SceneController sceneController;

        switch (sceneType) {
            case MENU_MAIN:
                scene = mainComponent.provideMainMenu();
                sceneController = mainComponent.provideMainMenuController();
                break;
            case MENU_OPTIONS:
                scene = mainComponent.provideOptionsMenu();
                sceneController = mainComponent.provideOptionsMenuController();
                break;
            case MENU_LANGUAGE:
                scene = mainComponent.provideLanguageMenu();
                sceneController = mainComponent.provideLanguageMenuController();
                break;
            default:
                String sceneName;
                if (sceneType == null) {
                    sceneName = "null";
                } else {
                    sceneName = sceneType.name();
                }
                String message = "Unable to load scene [" + sceneName + "]";
                Gdx.app.error(TAG, message);
                throw new RuntimeException(message);
        }

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
        stringsController.dispose();
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
