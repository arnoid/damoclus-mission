package org.arnoid.damoclus.logic.delegate.menu;

import com.badlogic.gdx.Gdx;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.SceneNavigator;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class OptionsMenuSceneDelegate extends AbstractScene.SceneDelegate {

    private static final String TAG = OptionsMenuSceneDelegate.class.getSimpleName();

    public OptionsMenuSceneDelegate(DamoclusGdxGame game) {
        super(game);
    }

    public void onAudio() {
        getGame().loadScene(SceneNavigator.SceneType.MENU_AUDIO);
    }

    public void onVideo() {
        getGame().loadScene(SceneNavigator.SceneType.MENU_VIDEO);
    }

    public void onControllers() {
        getGame().loadScene(SceneNavigator.SceneType.MENU_CONTROLS);
    }

    public void onLanguage() {
        getGame().loadScene(SceneNavigator.SceneType.MENU_LANGUAGE);
    }

    public void onBack() {
        getGame().popScene();
    }
}
