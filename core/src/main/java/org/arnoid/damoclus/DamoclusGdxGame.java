package org.arnoid.damoclus;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import org.arnoid.damoclus.component.ControllerModule;
import org.arnoid.damoclus.component.DaggerMainComponent;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.component.SceneDelegateModule;
import org.arnoid.damoclus.component.SceneModule;
import org.arnoid.damoclus.controller.event.Dispatcher;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.controller.skin.AssetsController;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.data.configuration.Configuration;
import org.arnoid.damoclus.data.configuration.DisplayConfiguration;
import org.arnoid.damoclus.logic.command.CommandHandler;
import org.arnoid.damoclus.logic.input.ConsoleInputAdapter;
import org.arnoid.damoclus.ui.SceneContainer;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;
import java.util.Map;

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
    AssetsController assetsController;

    @Inject
    CommandHandler commandHandler;

    @Inject
    InputMultiplexer inputMultiplexer;

    private static MainComponent mainComponent;

    private static Dispatcher dispatcher;

    @Override
    public void create() {
        Gdx.graphics.setContinuousRendering(true);

        dispatcher = new Dispatcher();

        mainComponent = DaggerMainComponent.builder()
                .controllerModule(new ControllerModule(this))
                .sceneModule(new SceneModule())
                .sceneDelegateModule(new SceneDelegateModule(this))
                .build();

        mainComponent.inject(this);

        Configuration configuration = configurationController.read();

        applyDisplayMode(configuration);

        Gdx.input.setInputProcessor(inputMultiplexer);

        if (configuration.isDebug()) {
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
        } else {
            Gdx.app.setLogLevel(Application.LOG_ERROR);
        }

        sceneContainer = new SceneContainer();

        inputMultiplexer.addProcessor(new ConsoleInputAdapter(sceneContainer, this));

        loadScene(SceneType.SCENE_ASSETS_LOADING);
    }

    public void applyDisplayMode(Configuration configuration) {
        DisplayConfiguration displayConfiguration = configuration.getDisplayConfiguration();
        configurationController.applyDisplayMode(displayConfiguration.isFullscreen(), displayConfiguration.getDisplayMode());
    }

    public static MainComponent mainComponent() {
        return mainComponent;
    }

    public static Dispatcher messageDispatcher() {
        return dispatcher;
    }


    public void loadScene(SceneType sceneType, Map<String, Object> arguments) {
        AbstractScene scene = produceScene(sceneType);

        scene.setArguments(arguments);
        scene.onArguments();

        AbstractScene topScene = sceneContainer.peek();
        if (topScene != null) {
            topScene.hide();
        }

        sceneContainer.push(scene);
        scene.show();

        Gdx.graphics.requestRendering();
    }

    public void loadScene(SceneType sceneType) {
        loadScene(sceneType, null);
    }

    public AbstractScene produceScene(SceneType sceneType) {
        //TODO: Move this into separate component

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
            case MENU_TEAM_ASSEMBLY:
                scene = mainComponent.provideTeamAssemblyMenu();
                sceneDelegate = mainComponent.provideTeamAssemblyMenuDelegate();
                break;
            case MENU_CONSOLE:
                scene = mainComponent.provideConsoleMenu();
                sceneDelegate = mainComponent.provideConsoleMenuDelegate();
                break;
            case SCENE_ASSETS_LOADING:
                scene = mainComponent.provideAssetsLoadingScene();
                sceneDelegate = mainComponent.provideAssetsLoadingSceneDelegate();
                break;
            default:
                String sceneName;
                if (sceneType == null) {
                    sceneName = "null";
                } else {
                    sceneName = sceneType.name();
                }
                String message = "Unable to loadSyncAndGet scene [" + sceneName + "]";
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
        assetsController.dispose();
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

        dispatcher.update();
        sceneContainer.act(deltaTime);
        sceneContainer.render(deltaTime);

    }

    @Override
    public void resize(int width, int height) {
        sceneContainer.resize(width, height);
    }

}
