package org.arnoid.damoclus.data.configuration;

import java.util.Locale;

public class Configuration {

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
}
