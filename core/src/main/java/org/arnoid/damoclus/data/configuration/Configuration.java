package org.arnoid.damoclus.data.configuration;

import java.util.Locale;

public class Configuration {

    boolean debug;
    Locale locale;
    UserControllerMap keyboardMapping;
    UserControllerMap controllerMapping;
    DisplayConfiguration displayConfiguration;

    public UserControllerMap getKeyboardMapping() {
        return keyboardMapping;
    }

    public void setKeyboardMapping(UserControllerMap keyboardMapping) {
        this.keyboardMapping = keyboardMapping;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public DisplayConfiguration getDisplayConfiguration() {
        return displayConfiguration;
    }

    public void setDisplayConfiguration(DisplayConfiguration displayConfiguration) {
        this.displayConfiguration = displayConfiguration;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isDebug() {
        return debug;
    }

    public UserControllerMap getControllerMapping() {
        return controllerMapping;
    }

    public void setControllerMapping(UserControllerMap controllerMapping) {
        this.controllerMapping = controllerMapping;
    }
}
