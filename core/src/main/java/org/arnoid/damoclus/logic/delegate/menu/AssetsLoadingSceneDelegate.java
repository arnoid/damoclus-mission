package org.arnoid.damoclus.logic.delegate.menu;

import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.SceneNavigator;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class AssetsLoadingSceneDelegate extends AbstractScene.SceneDelegate {

    private static final String TAG = AssetsLoadingSceneDelegate.class.getSimpleName();

    public AssetsLoadingSceneDelegate(DamoclusGdxGame game) {
        super(game);
    }

    public void onLoadingComplete() {
        getGame().popScene();
        getGame().loadScene(SceneNavigator.SceneType.MENU_MAIN);
    }

}
