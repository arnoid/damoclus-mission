package org.arnoid.damoclus.logic.delegate.menu;

import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class AudioMenuSceneDelegate extends AbstractScene.SceneDelegate {

    private static final String TAG = AudioMenuSceneDelegate.class.getSimpleName();

    public AudioMenuSceneDelegate(DamoclusGdxGame game) {
        super(game);
    }

    public void onBack() {
        getGame().popScene();
    }

}
