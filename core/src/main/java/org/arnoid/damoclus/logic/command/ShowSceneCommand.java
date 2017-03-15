package org.arnoid.damoclus.logic.command;

import com.badlogic.gdx.Gdx;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.SceneNavigator;

public class ShowSceneCommand implements Command {
    private static final String TAG = ShowSceneCommand.class.getSimpleName();

    public static final String PATTERN = "^show scene ((.*))$";

    private static final int PARAM_INDEX_SCENE_TYPE_NAME = 1;

    private SceneNavigator.SceneType sceneType;

    @Override
    public void handle(DamoclusGdxGame game) {
        if (sceneType == null) {

        } else {
            game.loadScene(sceneType);
        }

    }

    @Override
    public boolean withParams(String[] params) {
        try {
            String sceneName = params[PARAM_INDEX_SCENE_TYPE_NAME];
            sceneType = SceneNavigator.SceneType.valueOf(sceneName);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            DamoclusGdxGame.messageDispatcher().dispatchConsoleMessage(">[#error][[No scene name was given][]");
            Gdx.app.error(TAG, e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            DamoclusGdxGame.messageDispatcher().dispatchConsoleMessage(">[#error]Unknow scene name[[" + params[PARAM_INDEX_SCENE_TYPE_NAME] + "][]");
            Gdx.app.error(TAG, e.getMessage(), e);
        }

        return false;
    }
}
