package org.arnoid.damoclus.component;

import dagger.Module;
import dagger.Provides;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;
import org.arnoid.damoclus.ui.scene.menu.AudioMenuScene;
import org.arnoid.damoclus.ui.scene.menu.ConsoleMenuScene;
import org.arnoid.damoclus.ui.scene.menu.ControlsMenuScene;
import org.arnoid.damoclus.ui.scene.menu.LanguageMenuScene;
import org.arnoid.damoclus.ui.scene.menu.AssetsLoadingScene;
import org.arnoid.damoclus.ui.scene.menu.MainMenuScene;
import org.arnoid.damoclus.ui.scene.menu.OptionsMenuScene;
import org.arnoid.damoclus.ui.scene.menu.TeamAssemblyMenuScene;
import org.arnoid.damoclus.ui.scene.menu.VideoMenuScene;

import javax.inject.Named;

@Module
public class SceneModule {

    public static final String SCENE_CONSOLE = "SCENE_CONSOLE";

    public static final String SCENE_MENU_MAIN = "SCENE_MENU_MAIN";
    public static final String SCENE_MENU_OPTIONS = "SCENE_MENU_OPTIONS";
    public static final String SCENE_MENU_LANGUAGE = "SCENE_MENU_LANGUAGE";
    public static final String SCENE_MENU_VIDEO = "SCENE_MENU_VIDEO";
    public static final String SCENE_MENU_AUDIO = "SCENE_MENU_AUDIO";
    public static final String SCENE_MENU_CONTROLS = "SCENE_MENU_CONTROLS";
    public static final String SCENE_MENU_TEAM_ASSEMBLY = "SCENE_MENU_TEAM_ASSEMBLY";
    public static final String SCENE_ASSETS_LOADING = "SCENE_ASSETS_LOADING";

    public SceneModule() {
    }

    @Named(value = SceneModule.SCENE_CONSOLE)
    @Provides
    public AbstractMenuScene produceConsole(MainComponent component) {
        return new ConsoleMenuScene(component);
    }

    @Named(value = SceneModule.SCENE_MENU_MAIN)
    @Provides
    public AbstractMenuScene produceMainMenuScene(MainComponent component) {
        return new MainMenuScene(component);
    }

    @Named(value = SceneModule.SCENE_MENU_OPTIONS)
    @Provides
    public AbstractMenuScene produceOptionsMenuScene(MainComponent component) {
        return new OptionsMenuScene(component);
    }

    @Named(value = SceneModule.SCENE_MENU_LANGUAGE)
    @Provides
    public AbstractMenuScene produceLanguageMenuScene(MainComponent component) {
        return new LanguageMenuScene(component);
    }

    @Named(value = SceneModule.SCENE_MENU_AUDIO)
    @Provides
    public AbstractMenuScene produceAudioMenuScene(MainComponent component) {
        return new AudioMenuScene(component);
    }

    @Named(value = SceneModule.SCENE_MENU_VIDEO)
    @Provides
    public AbstractMenuScene produceVideoMenuScene(MainComponent component) {
        return new VideoMenuScene(component);
    }

    @Named(value = SceneModule.SCENE_MENU_CONTROLS)
    @Provides
    public AbstractMenuScene produceControlsMenuScene(MainComponent component) {
        return new ControlsMenuScene(component);
    }

    @Named(value = SceneModule.SCENE_MENU_TEAM_ASSEMBLY)
    @Provides
    public AbstractMenuScene produceTeamAssemblyMenuScene(MainComponent component) {
        return new TeamAssemblyMenuScene(component);
    }

    @Named(value = SceneModule.SCENE_ASSETS_LOADING)
    @Provides
    public AbstractMenuScene produceAssetsMenuScene(MainComponent component) {
        return new AssetsLoadingScene(component);
    }

}
