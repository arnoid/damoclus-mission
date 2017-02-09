package org.arnoid.damoclus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.HoloSkin;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new DamoclusGdxGame(), config);
	}
}
