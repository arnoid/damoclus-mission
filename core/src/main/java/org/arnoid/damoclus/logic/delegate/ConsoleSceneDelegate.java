package org.arnoid.damoclus.logic.delegate;

import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class ConsoleSceneDelegate extends AbstractScene.SceneDelegate {

    private static final String TAG = ConsoleSceneDelegate.class.getSimpleName();

    public ConsoleSceneDelegate(DamoclusGdxGame game) {
        super(game);
    }

    public void onHide() {
    }

}
