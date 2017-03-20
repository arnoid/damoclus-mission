package org.arnoid.damoclus.logic.delegate.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.data.configuration.Configuration;
import org.arnoid.damoclus.data.configuration.DisplayConfiguration;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;
import java.util.Arrays;

public class VideoMenuSceneDelegate extends AbstractScene.SceneDelegate {

    private static final String TAG = VideoMenuSceneDelegate.class.getSimpleName();

    @Inject
    ConfigurationController configurationController;

    public VideoMenuSceneDelegate(DamoclusGdxGame game) {
        super(game);
        DamoclusGdxGame.mainComponent().inject(this);
    }

    public void onBack() {
        getGame().popScene();
    }

    public Graphics.DisplayMode[] getDisplayModes() {
        Graphics.DisplayMode[] displayModes = Gdx.graphics.getDisplayModes();

        Arrays.sort(displayModes, (dm1, dm2) -> {
            if (dm1.width > dm2.width) {
                return -1;
            } else if (dm1.width < dm2.width) {
                return 1;
            }

            if (dm1.bitsPerPixel > dm2.bitsPerPixel) {
                return -1;
            } else if (dm1.bitsPerPixel < dm2.bitsPerPixel) {
                return 1;
            }

            return 0;
        });

        return displayModes;
    }

    public Graphics.DisplayMode getDisplayMode() {
        return Gdx.graphics.getDisplayMode();
    }

    public int getDisplayModeIndex(Graphics.DisplayMode selectedDisplayMode) {
        Graphics.DisplayMode[] displayModes = getDisplayModes();

        for (int i = 0; i < displayModes.length; i++) {
            if (selectedDisplayMode.toString().equals(displayModes[i].toString())) {
                return i;
            }
        }

        return 0;
    }

    public void apply(boolean fullScreen, Graphics.DisplayMode selectedMode, DisplayConfiguration.TextureQuality textureQuality) {
        Configuration configuration = configurationController.read();
        DisplayConfiguration displayConfiguration = configuration.getDisplayConfiguration();

        displayConfiguration.setFullscreen(fullScreen);
        displayConfiguration.setDisplayMode(selectedMode);
        displayConfiguration.setTextureQuality(textureQuality);

        configurationController.write(configuration);
    }

    public DisplayConfiguration getDisplayConfiguration() {
        return configurationController.read().getDisplayConfiguration();
    }
}
