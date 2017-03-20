package org.arnoid.damoclus;

public interface SceneNavigator {

    enum SceneType {
        MENU_MAIN,
        MENU_OPTIONS,
        MENU_LANGUAGE,
        MENU_AUDIO,
        MENU_VIDEO,
        MENU_CONSOLE,
        MENU_TEAM_ASSEMBLY,
        MENU_CONTROLS,

        SCENE_ASSETS_LOADING
    }

    void loadScene(SceneType sceneType);

    void popScene();
}
