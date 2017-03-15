package org.arnoid.damoclus.logic.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.SceneNavigator;
import org.arnoid.damoclus.ui.SceneContainer;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class ConsoleInputAdapter extends InputAdapter {

    private final SceneContainer sceneContainer;
    private final DamoclusGdxGame game;

    boolean consoleIsVisible = false;

    public ConsoleInputAdapter(SceneContainer sceneContainer, DamoclusGdxGame game) {
        this.sceneContainer = sceneContainer;
        this.game = game;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.GRAVE) {
            if (consoleIsVisible) {
                sceneContainer.removeOverlay();
                sceneContainer.peek().resume();
                consoleIsVisible = false;
            } else {
                AbstractScene consoleScene = game.produceScene(SceneNavigator.SceneType.MENU_CONSOLE);
                sceneContainer.setOverlay(consoleScene);
                sceneContainer.peek().pause();
                consoleIsVisible = true;
            }
            return true;
        }
        return false;
    }
}
