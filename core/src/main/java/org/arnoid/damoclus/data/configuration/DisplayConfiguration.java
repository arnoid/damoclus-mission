package org.arnoid.damoclus.data.configuration;


import com.badlogic.gdx.Graphics;

public class DisplayConfiguration {

    private boolean fullscreen;
    private Graphics.DisplayMode displayMode;

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public Graphics.DisplayMode getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(Graphics.DisplayMode displayMode) {
        this.displayMode = displayMode;
    }
}
