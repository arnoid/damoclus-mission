package org.arnoid.damoclus.component;

import com.badlogic.gdx.scenes.scene2d.Stage;
import dagger.Module;
import dagger.Provides;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;
import org.arnoid.damoclus.ui.scene.menu.LanguageMenuScene;
import org.arnoid.damoclus.ui.scene.menu.MainMenuScene;
import org.arnoid.damoclus.ui.scene.menu.OptionsMenuScene;

import javax.inject.Named;

@Module
public class SceneModule {

    public static final String SCENE_MENU_MAIN = "SCENE_MENU_MAIN";
    public static final String SCENE_MENU_OPTIONS = "SCENE_MENU_OPTIONS";
    public static final String SCENE_MENU_LANGUAGE = "SCENE_MENU_LANGUAGE";

    private final Stage stage;

    public SceneModule(Stage stage) {
        this.stage = stage;
    }

    @Named(value = SceneModule.SCENE_MENU_MAIN)
    @Provides
    public AbstractMenuScene produceMainMenuScene(MainComponent component) {
        return new MainMenuScene(component, stage);
    }

    @Named(value = SceneModule.SCENE_MENU_OPTIONS)
    @Provides
    public AbstractMenuScene produceOptionsMenuScene(MainComponent component) {
        return new OptionsMenuScene(component, stage);
    }

    @Named(value = SceneModule.SCENE_MENU_LANGUAGE)
    @Provides
    public AbstractMenuScene produceLanguageMenuScene(MainComponent component) {
        return new LanguageMenuScene(component, stage);
    }
}
