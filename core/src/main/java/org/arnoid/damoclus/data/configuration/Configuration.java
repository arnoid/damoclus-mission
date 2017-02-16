package org.arnoid.damoclus.data.configuration;

import java.util.Locale;

public class Configuration {

    boolean debug;
    Locale locale;
    UserControllerMap keyboardMap;
    DisplayConfiguration displayConfiguration;

    public UserControllerMap getKeyboardMap() {
        return keyboardMap;
    }

    public void setKeyboardMap(UserControllerMap keyboardMap) {
        this.keyboardMap = keyboardMap;
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
}
