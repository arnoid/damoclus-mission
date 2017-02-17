package org.arnoid.damoclus.controller.persistent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import org.arnoid.damoclus.data.configuration.Configuration;
import org.arnoid.damoclus.data.configuration.DisplayConfiguration;
import org.arnoid.damoclus.data.configuration.UserControllerMap;

import java.util.Locale;

public class ConfigurationController extends JsonPersistingController<Configuration> {

    private static final String FILE_PATH = "data/config/configuration.json";

    @Override
    protected Class<Configuration> getContentClass() {
        return Configuration.class;
    }

    @Override
    protected void onPostWrite(boolean result, Configuration instance) {

    }

    @Override
    protected void onPostRead(Configuration instance) {
        if (instance.getKeyboardMapping() == null) {
            instance.setKeyboardMapping(UserControllerMap.defaultKeyboardInstance());
        }

        if (instance.getControllerMapping() == null) {
            instance.setControllerMapping(UserControllerMap.defaultControllerInstance());
        }

        if (instance.getDisplayConfiguration() == null) {
            instance.setDisplayConfiguration(generateDefaultDisplayConfiguration());
        }
    }

    @Override
    protected Configuration generateDefaultInstance() {
        Configuration defaultConfiguration = new Configuration();
        defaultConfiguration.setKeyboardMapping(UserControllerMap.defaultKeyboardInstance());
        defaultConfiguration.setControllerMapping(UserControllerMap.defaultControllerInstance());
        defaultConfiguration.setLocale(Locale.ENGLISH);
        defaultConfiguration.setDisplayConfiguration(generateDefaultDisplayConfiguration());

        return defaultConfiguration;
    }

    private DisplayConfiguration generateDefaultDisplayConfiguration() {
        DisplayConfiguration displayConfiguration = new DisplayConfiguration();
        displayConfiguration.setFullscreen(Gdx.graphics.isFullscreen());
        displayConfiguration.setDisplayMode(Gdx.graphics.getDisplayMode());
        return displayConfiguration;
    }

    @Override
    protected String getJsonFilePath() {
        return FILE_PATH;
    }

    public void applyDisplayMode(boolean fullscreen, Graphics.DisplayMode displayMode) {
        if (fullscreen) {
            Gdx.graphics.setFullscreenMode(displayMode);
        } else {
            Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
        }
    }
}
