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

    }

    @Override
    protected Configuration generateDefaultInstance() {
        Configuration defaultConfiguration = new Configuration();
        defaultConfiguration.setKeyboardMap(UserControllerMap.defaultKeyboardInstance());
        defaultConfiguration.setLocale(Locale.ENGLISH);

        DisplayConfiguration displayConfiguration = new DisplayConfiguration();
        displayConfiguration.setFullscreen(Gdx.graphics.isFullscreen());
        displayConfiguration.setDisplayMode(Gdx.graphics.getDisplayMode());
        defaultConfiguration.setDisplayConfiguration(displayConfiguration);
        
        return defaultConfiguration;
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
