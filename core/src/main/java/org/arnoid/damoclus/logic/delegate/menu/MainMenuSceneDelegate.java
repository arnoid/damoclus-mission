package org.arnoid.damoclus.logic.delegate.menu;

import com.badlogic.gdx.Gdx;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.SceneNavigator;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class MainMenuSceneDelegate extends AbstractScene.SceneDelegate {

    private static final String TAG = MainMenuSceneDelegate.class.getSimpleName();

    public MainMenuSceneDelegate(DamoclusGdxGame game) {
        super(game);
    }

    public void onNewGame() {
        Gdx.app.log(TAG, "New game clicked");
    }

    public void onOptions() {
        getGame().loadScene(SceneNavigator.SceneType.MENU_OPTIONS);
    }

    public void onQuit() {
        Gdx.app.exit();
    }
}
