package org.arnoid.damoclus.component;

import dagger.Module;
import dagger.Provides;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.logic.delegate.ConsoleSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.AudioMenuSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.ControlsMenuSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.LanguageMenuSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.AssetsLoadingSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.MainMenuSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.OptionsMenuSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.TeamAssemblyMenuSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.VideoMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Named;

@Module
public class SceneDelegateModule {

    private final DamoclusGdxGame game;

    public SceneDelegateModule(DamoclusGdxGame game) {
        this.game = game;
    }

    @Named(value = SceneModule.SCENE_CONSOLE)
    @Provides
    public AbstractScene.SceneDelegate produceConsoleSceneDelegate() {
        return new ConsoleSceneDelegate(game);
    }

    @Named(value = SceneModule.SCENE_MENU_MAIN)
    @Provides
    public AbstractScene.SceneDelegate produceMainMenuSceneDelegate() {
        return new MainMenuSceneDelegate(game);
    }

    @Named(value = SceneModule.SCENE_MENU_OPTIONS)
    @Provides
    public AbstractScene.SceneDelegate produceOptionsMenuSceneDelegate() {
        return new OptionsMenuSceneDelegate(game);
    }

    @Named(value = SceneModule.SCENE_MENU_LANGUAGE)
    @Provides
    public AbstractScene.SceneDelegate produceLanguageMenuSceneDelegate() {
        return new LanguageMenuSceneDelegate(game);
    }

    @Named(value = SceneModule.SCENE_MENU_VIDEO)
    @Provides
    public AbstractScene.SceneDelegate produceVideoMenuSceneDelegate() {
        return new VideoMenuSceneDelegate(game);
    }

    @Named(value = SceneModule.SCENE_MENU_AUDIO)
    @Provides
    public AbstractScene.SceneDelegate produceAudioMenuSceneDelegate() {
        return new AudioMenuSceneDelegate(game);
    }

    @Named(value = SceneModule.SCENE_MENU_CONTROLS)
    @Provides
    public AbstractScene.SceneDelegate produceControlsMenuSceneDelegate() {
        return new ControlsMenuSceneDelegate(game);
    }

    @Named(value = SceneModule.SCENE_MENU_TEAM_ASSEMBLY)
    @Provides
    public AbstractScene.SceneDelegate produceTeamAssemblyMenuSceneDelegate() {
        return new TeamAssemblyMenuSceneDelegate(game);
    }

    @Named(value = SceneModule.SCENE_ASSETS_LOADING)
    @Provides
    public AbstractScene.SceneDelegate produceAssetsLoadingSceneDelegate() {
        return new AssetsLoadingSceneDelegate(game);
    }
}
