package org.arnoid.damoclus.component;

import dagger.Component;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.logic.handler.menu.LanguageMenuSceneController;
import org.arnoid.damoclus.logic.handler.menu.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.AbstractScene;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;
import org.arnoid.damoclus.ui.scene.menu.LanguageMenuScene;
import org.arnoid.damoclus.ui.scene.menu.MainMenuScene;
import org.arnoid.damoclus.ui.scene.menu.OptionsMenuScene;

import javax.inject.Named;
import javax.inject.Singleton;

@Component(
        modules = {
                SceneModule.class,
                SceneControllerModule.class,
                ControllerModule.class,
        }
)
@Singleton
public interface MainComponent {

    @Named(value = SceneModule.SCENE_MENU_MAIN)
    AbstractMenuScene provideMainMenu();

    @Named(value = SceneModule.SCENE_MENU_OPTIONS)
    AbstractMenuScene provideOptionsMenu();

    @Named(value = SceneModule.SCENE_MENU_LANGUAGE)
    AbstractMenuScene provideLanguageMenu();

    @Named(value = SceneModule.SCENE_MENU_MAIN)
    AbstractScene.SceneController provideMainMenuController();

    @Named(value = SceneModule.SCENE_MENU_OPTIONS)
    AbstractScene.SceneController provideOptionsMenuController();

    @Named(value = SceneModule.SCENE_MENU_LANGUAGE)
    AbstractScene.SceneController provideLanguageMenuController();

    void inject(DamoclusGdxGame game);

    void inject(MainMenuScene scene);

    void inject(OptionsMenuScene scene);

    void inject(LanguageMenuScene languageMenuScene);

    void inject(LanguageMenuSceneController languageMenuSceneController);

    void inject(MenuNavigationInputAdapter menuNavigationInputAdapter);
}
