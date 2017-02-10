package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.strings.Strings;
import org.arnoid.damoclus.logic.handler.menu.MainMenuSceneController;

public class MainMenuScene extends AbstractMenuScene<MainMenuSceneController> {

    private static final String TAG = MainMenuScene.class.getSimpleName();

    public MainMenuScene(MainComponent component, Stage stage) {
        super(stage);
        component.inject(this);
        init();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    protected String getWindowTitle() {
        return getStringsController().string(Strings.MainMenuWindow.title);
    }

    @Override
    protected void produceMenuButtons() {
        TextButton btnNewGame = produceButton(Strings.MainMenuWindow.btn_new_game);
        TextButton btnOptions = produceButton(Strings.MainMenuWindow.btn_options);
        TextButton btnQuit = produceButton(Strings.MainMenuWindow.btn_quit);

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
