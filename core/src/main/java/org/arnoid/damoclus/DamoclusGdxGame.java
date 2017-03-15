package org.arnoid.damoclus;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import org.arnoid.damoclus.component.ControllerModule;
import org.arnoid.damoclus.component.DaggerMainComponent;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.component.SceneDelegateModule;
import org.arnoid.damoclus.component.SceneModule;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.ui.SceneContainer;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;

public class DamoclusGdxGame implements ApplicationListener, SceneNavigator {

    private static final String TAG = DamoclusGdxGame.class.getSimpleName();

    private SceneContainer sceneContainer;

    @Inject
    ConfigurationController configurationController;
    @Inject
    StringsController stringsController;
    @Inject
    SkinController skinController;

    @Inject
    InputMultiplexer inputMultiplexer;

    private static MainComponent mainComponent;

    @Override
    public void create() {
        Gdx.graphics.setContinuousRendering(true);

        mainComponent = DaggerMainComponent.builder()
                .controllerModule(new ControllerModule())
                .sceneModule(new SceneModule())
                .sceneDelegateModule(new SceneDelegateModule(this))
                .build();

        mainComponent.inject(this);

        Gdx.input.setInputProcessor(inputMultiplexer);

        if (configurationController.read().isDebug()) {
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
        } else {
            Gdx.app.setLogLevel(Application.LOG_ERROR);
        }

        sceneContainer = new SceneContainer();

        inputMultiplexer.addProcessor(new ConsoleInputAdapter(sceneContainer, this));

        loadScene(SceneType.MENU_MAIN);
    }

    public static MainComponent mainComponent() {
        return mainComponent;
    }

    public void loadScene(SceneType sceneType) {

        AbstractScene scene = produceScene(sceneType);

        AbstractScene topScene = sceneContainer.peek();
        if (topScene != null) {
            topScene.hide();
        }
        sceneContainer.push(scene);
        scene.show();

        Gdx.graphics.requestRendering();
    }

    public AbstractScene produceScene(SceneType sceneType) {
        AbstractScene scene;
        AbstractScene.SceneDelegate sceneDelegate;

        switch (sceneType) {
            case MENU_MAIN:
                scene = mainComponent.provideMainMenu();
                sceneDelegate = mainComponent.provideMainMenuDelegate();
                break;
            case MENU_OPTIONS:
                scene = mainComponent.provideOptionsMenu();
                sceneDelegate = mainComponent.provideOptionsMenuDelegate();
                break;
            case MENU_LANGUAGE:
                scene = mainComponent.provideLanguageMenu();
                sceneDelegate = mainComponent.provideLanguageMenuDelegate();
                break;
            case MENU_AUDIO:
                scene = mainComponent.provideAudioMenu();
                sceneDelegate = mainComponent.provideAudioMenuDelegate();
                break;
            case MENU_VIDEO:
                scene = mainComponent.provideVideoMenu();
                sceneDelegate = mainComponent.provideVideoMenuDelegate();
                break;
            case MENU_CONTROLS:
                scene = mainComponent.provideControlsMenu();
                sceneDelegate = mainComponent.provideControlsMenuDelegate();
                break;
            case MENU_CONSOLE:
                scene = mainComponent.provideConsoleMenu();
                sceneDelegate = mainComponent.provideConsoleMenuDelegate();
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

        scene.setSceneDelegate(sceneDelegate);
        scene.onSceneDelegate();
        return scene;
    }

    public void popScene() {
        AbstractScene scene = sceneContainer.pop();

        scene.dispose();

        Gdx.graphics.requestRendering();
    }

    @Override
    public void dispose() {
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

        sceneContainer.act(deltaTime);
        sceneContainer.render(deltaTime);

    }

    @Override
    public void resize(int width, int height) {
        sceneContainer.resize(width, height);
    }

}
