package org.arnoid.damoclus.component;

import dagger.Component;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.logic.delegate.menu.LanguageMenuSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.VideoMenuSceneDelegate;
import org.arnoid.damoclus.logic.input.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.AbstractScene;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;
import org.arnoid.damoclus.ui.scene.menu.AudioMenuScene;
import org.arnoid.damoclus.ui.scene.menu.ControlsMenuScene;
import org.arnoid.damoclus.ui.scene.menu.LanguageMenuScene;
import org.arnoid.damoclus.ui.scene.menu.MainMenuScene;
import org.arnoid.damoclus.ui.scene.menu.OptionsMenuScene;
import org.arnoid.damoclus.ui.scene.menu.VideoMenuScene;

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
    AbstractScene.SceneDelegate provideMainMenuDelegate();

    @Named(value = SceneModule.SCENE_MENU_OPTIONS)
    AbstractMenuScene provideOptionsMenu();

    @Named(value = SceneModule.SCENE_MENU_OPTIONS)
    AbstractScene.SceneDelegate provideOptionsMenuDelegate();

    @Named(value = SceneModule.SCENE_MENU_LANGUAGE)
    AbstractMenuScene provideLanguageMenu();

    @Named(value = SceneModule.SCENE_MENU_LANGUAGE)
    AbstractScene.SceneDelegate provideLanguageMenuDelegate();

    @Named(value = SceneModule.SCENE_MENU_AUDIO)
    AbstractMenuScene provideAudioMenu();

    @Named(value = SceneModule.SCENE_MENU_AUDIO)
    AbstractScene.SceneDelegate provideAudioMenuDelegate();

    @Named(value = SceneModule.SCENE_MENU_VIDEO)
    AbstractMenuScene provideVideoMenu();

    @Named(value = SceneModule.SCENE_MENU_VIDEO)
    AbstractScene.SceneDelegate provideVideoMenuDelegate();

    @Named(value = SceneModule.SCENE_MENU_CONTROLS)
    AbstractMenuScene provideControlsMenu();

    @Named(value = SceneModule.SCENE_MENU_CONTROLS)
    AbstractScene.SceneDelegate provideControlsMenuDelegate();


    void inject(DamoclusGdxGame game);

    void inject(MenuNavigationInputAdapter menuNavigationInputAdapter);

    void inject(MainMenuScene scene);

    void inject(OptionsMenuScene scene);

    void inject(LanguageMenuScene languageMenuScene);

    void inject(LanguageMenuSceneDelegate languageMenuSceneDelegate);

    void inject(VideoMenuScene videoMenuScene);

    void inject(VideoMenuSceneDelegate videoMenuSceneDelegate);

    void inject(AudioMenuScene audioMenuScene);

    void inject(ControlsMenuScene controlsMenuScene);
}
