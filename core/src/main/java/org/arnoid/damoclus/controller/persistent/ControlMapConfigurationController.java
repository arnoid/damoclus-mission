package org.arnoid.damoclus.controller.persistent;

import org.arnoid.damoclus.data.configuration.ControlMapConfiguration;
import org.arnoid.damoclus.data.configuration.UserControllerMap;

public class ControlMapConfigurationController extends JsonPersistingController<ControlMapConfiguration> {

    private static final String FILE_PATH = "data/config/control-map.json";

    @Override
    protected Class<ControlMapConfiguration> getContentClass() {
        return ControlMapConfiguration.class;
    }

    @Override
    protected void onPostWrite(boolean result, ControlMapConfiguration instance) {

    }

    @Override
    protected void onPostRead(ControlMapConfiguration instance) {

    }

    @Override
    protected ControlMapConfiguration generateDefaultInstance() {
        ControlMapConfiguration defaultConfiguration = new ControlMapConfiguration();
        defaultConfiguration.setKeyboardMap(UserControllerMap.defaultKeyboardInstance());
        return defaultConfiguration;
    }

    @Override
    protected String getJsonFilePath() {
        return FILE_PATH;
    }
}
