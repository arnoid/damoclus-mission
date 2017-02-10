package org.arnoid.damoclus.component;

import dagger.Module;
import dagger.Provides;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.logic.handler.menu.LanguageMenuSceneController;
import org.arnoid.damoclus.logic.handler.menu.MainMenuSceneController;
import org.arnoid.damoclus.logic.handler.menu.OptionsMenuSceneController;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Named;

@Module
public class SceneControllerModule {

    private final DamoclusGdxGame game;

    public SceneControllerModule(DamoclusGdxGame game) {
        this.game = game;
    }


    @Named(value = SceneModule.SCENE_MENU_MAIN)
    @Provides
    public AbstractScene.SceneController produceMainMenuSceneController() {
        return new MainMenuSceneController(game);
    }

    @Named(value = SceneModule.SCENE_MENU_OPTIONS)
    @Provides
    public AbstractScene.SceneController produceOptionsMenuSceneController() {
        return new OptionsMenuSceneController(game);
    }

    @Named(value = SceneModule.SCENE_MENU_LANGUAGE)
    @Provides
    public AbstractScene.SceneController produceLanguageMenuSceneController() {
        return new LanguageMenuSceneController(game);
    }
}
