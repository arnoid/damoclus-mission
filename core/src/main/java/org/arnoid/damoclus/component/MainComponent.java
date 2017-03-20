package org.arnoid.damoclus.component;

import com.badlogic.gdx.InputMultiplexer;
import dagger.Component;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.logic.delegate.ConsoleSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.ControlsMenuSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.LanguageMenuSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.VideoMenuSceneDelegate;
import org.arnoid.damoclus.logic.input.NavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;
import org.arnoid.damoclus.ui.scene.menu.AssetsLoadingScene;
import org.arnoid.damoclus.ui.scene.menu.AudioMenuScene;
import org.arnoid.damoclus.ui.scene.menu.ConsoleMenuScene;
import org.arnoid.damoclus.ui.scene.menu.ControlsMenuScene;
import org.arnoid.damoclus.ui.scene.menu.LanguageMenuScene;
import org.arnoid.damoclus.ui.scene.menu.MainMenuScene;
import org.arnoid.damoclus.ui.scene.menu.OptionsMenuScene;
import org.arnoid.damoclus.ui.scene.menu.TeamAssemblyMenuScene;
import org.arnoid.damoclus.ui.scene.menu.VideoMenuScene;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.dialog.MessageDialog;
import org.arnoid.damoclus.ui.scene.menu.dialog.SceneDialog;

import javax.inject.Named;
import javax.inject.Singleton;

@Component(
        modules = {
                SceneModule.class,
                SceneDelegateModule.class,
                ControllerModule.class,
        }
)
@Singleton
public interface MainComponent {

    @Named(value = SceneModule.SCENE_MENU_MAIN)
    AbstractMenuScene provideMainMenu();

    @Named(value = SceneModule.SCENE_MENU_MAIN)
    org.arnoid.damoclus.ui.scene.AbstractScene.SceneDelegate provideMainMenuDelegate();

    @Named(value = SceneModule.SCENE_MENU_OPTIONS)
    AbstractMenuScene provideOptionsMenu();

    @Named(value = SceneModule.SCENE_MENU_OPTIONS)
    org.arnoid.damoclus.ui.scene.AbstractScene.SceneDelegate provideOptionsMenuDelegate();

    @Named(value = SceneModule.SCENE_MENU_LANGUAGE)
    AbstractMenuScene provideLanguageMenu();

    @Named(value = SceneModule.SCENE_MENU_LANGUAGE)
    org.arnoid.damoclus.ui.scene.AbstractScene.SceneDelegate provideLanguageMenuDelegate();

    @Named(value = SceneModule.SCENE_MENU_AUDIO)
    AbstractMenuScene provideAudioMenu();

    @Named(value = SceneModule.SCENE_MENU_AUDIO)
    org.arnoid.damoclus.ui.scene.AbstractScene.SceneDelegate provideAudioMenuDelegate();

    @Named(value = SceneModule.SCENE_MENU_VIDEO)
    AbstractMenuScene provideVideoMenu();

    @Named(value = SceneModule.SCENE_MENU_VIDEO)
    org.arnoid.damoclus.ui.scene.AbstractScene.SceneDelegate provideVideoMenuDelegate();

    @Named(value = SceneModule.SCENE_MENU_TEAM_ASSEMBLY)
    AbstractMenuScene provideTeamAssemblyMenu();

    @Named(value = SceneModule.SCENE_MENU_TEAM_ASSEMBLY)
    org.arnoid.damoclus.ui.scene.AbstractScene.SceneDelegate provideTeamAssemblyMenuDelegate();

    @Named(value = SceneModule.SCENE_MENU_CONTROLS)
    AbstractMenuScene provideControlsMenu();

    @Named(value = SceneModule.SCENE_MENU_CONTROLS)
    org.arnoid.damoclus.ui.scene.AbstractScene.SceneDelegate provideControlsMenuDelegate();

    @Named(value = SceneModule.SCENE_CONSOLE)
    AbstractMenuScene provideConsoleMenu();

    @Named(value = SceneModule.SCENE_CONSOLE)
    org.arnoid.damoclus.ui.scene.AbstractScene.SceneDelegate provideConsoleMenuDelegate();

    @Named(value = SceneModule.SCENE_ASSETS_LOADING)
    AbstractMenuScene provideAssetsLoadingScene();

    @Named(value = SceneModule.SCENE_ASSETS_LOADING)
    org.arnoid.damoclus.ui.scene.AbstractScene.SceneDelegate provideAssetsLoadingSceneDelegate();

    void inject(DamoclusGdxGame game);

    void inject(MainMenuScene scene);

    void inject(OptionsMenuScene scene);

    void inject(LanguageMenuScene languageMenuScene);

    void inject(LanguageMenuSceneDelegate languageMenuSceneDelegate);

    void inject(VideoMenuScene videoMenuScene);

    void inject(VideoMenuSceneDelegate videoMenuSceneDelegate);

    void inject(AudioMenuScene audioMenuScene);

    void inject(ControlsMenuScene controlsMenuScene);

    void inject(ConsoleMenuScene consoleScene);

    void inject(SceneDialog menuSceneDialog);

    void inject(MessageDialog menuSceneDialog);

    void inject(ControlsMenuSceneDelegate controlsMenuSceneDelegate);

    void inject(XmlMenuSceneBuilder xmlMenuSceneBuilder);

    NavigationInputAdapter provideMenuNavigationInputAdapter();

    InputMultiplexer provideInputMultiplexer();

    void inject(ConsoleSceneDelegate consoleSceneDelegate);

    void inject(TeamAssemblyMenuScene teamAssemblyMenuScene);

    void inject(AssetsLoadingScene assetsLoadingScene);
}
