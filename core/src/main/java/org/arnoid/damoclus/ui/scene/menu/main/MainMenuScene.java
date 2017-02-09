package org.arnoid.damoclus.ui.scene.menu.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.strings.Strings;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.logic.handler.menu.main.MainMenuSceneController;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;

public class MainMenuScene extends AbstractMenuScene<MainMenuSceneController> {

    private static final String TAG = MainMenuScene.class.getSimpleName();

    public MainMenuScene(DamoclusGdxGame game, Stage stage) {
        super(game, stage);
    }

    @Override
    protected String getWindowTitle(StringsController stringsController) {
        return stringsController.string(Strings.MainMenuWindow.title);
    }

    @Override
    protected void produceMenuButtons(Skin skin, StringsController stringsController) {
        final TextButton btnNewGame = produceButton(skin, stringsController, Strings.MainMenuWindow.btn_new_game);
        final TextButton btnOptions = produceButton(skin, stringsController, Strings.MainMenuWindow.btn_options);
        final TextButton btnQuit = produceButton(skin, stringsController, Strings.MainMenuWindow.btn_quit);

        appendMenuButton(btnNewGame);
        appendMenuButton(btnOptions);
        appendMenuButton(btnQuit);
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Strings.MainMenuWindow.btn_new_game:
                getSceneController().onNewGame();
                break;
            case Strings.MainMenuWindow.btn_options:
                getSceneController().onOptions();
                break;
            case Strings.MainMenuWindow.btn_quit:
                getSceneController().onQuit();
                break;
        }
    }

    @Override
    protected float getButtonsWidth() {
        return 150;
    }
}
