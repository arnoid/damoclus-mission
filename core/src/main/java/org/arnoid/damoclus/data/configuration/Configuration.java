package org.arnoid.damoclus.data.configuration;

import java.util.Locale;

public class Configuration {

    Locale locale;

    UserControllerMap keyboardMap;

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
}
