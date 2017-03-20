package org.arnoid.damoclus.data.configuration;


import com.badlogic.gdx.Graphics;

public class DisplayConfiguration {

    public enum TextureQuality {
        mdpi,
        hdpi,
        xhdpi,
        xxhdpi,
        xxxhdpi
    }

    private Graphics.DisplayMode displayMode;
    private boolean fullscreen;
    private TextureQuality textureQuality;

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

    public TextureQuality getTextureQuality() {
        return textureQuality;
    }

    public void setTextureQuality(TextureQuality activeTextureQuality) {
        this.textureQuality = activeTextureQuality;
    }
}
