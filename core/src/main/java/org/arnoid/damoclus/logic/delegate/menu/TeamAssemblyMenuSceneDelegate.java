package org.arnoid.damoclus.logic.delegate.menu;

import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class TeamAssemblyMenuSceneDelegate extends AbstractScene.SceneDelegate {

    private static final String TAG = TeamAssemblyMenuSceneDelegate.class.getSimpleName();

    public TeamAssemblyMenuSceneDelegate(DamoclusGdxGame game) {
        super(game);
    }

    public void onBack() {
        getGame().popScene();
    }

}
