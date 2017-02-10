package org.arnoid.damoclus;

public interface SceneNavigator {

    public enum SceneType {
        MENU_MAIN,
        MENU_OPTIONS,
        MENU_LANGUAGE
    }

    void loadScene(SceneType sceneType);

    void popScene();
}
