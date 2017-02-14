package org.arnoid.damoclus;

public interface SceneNavigator {

    public enum SceneType {
        MENU_MAIN,
        MENU_OPTIONS,
        MENU_LANGUAGE,
        MENU_AUDIO,
        MENU_VIDEO,
        MENU_CONTROLS
    }

    void loadScene(SceneType sceneType);

    void popScene();
}
