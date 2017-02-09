package org.arnoid.damoclus.ui.scene.menu.options;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.strings.Strings;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.logic.handler.menu.options.OptionsMenuSceneController;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;

public class OptionsMenuScene extends AbstractMenuScene<OptionsMenuSceneController> {

    private static final String TAG = OptionsMenuScene.class.getSimpleName();

    public OptionsMenuScene(DamoclusGdxGame game, Stage stage) {
        super(game, stage);
    }

    @Override
    protected String getWindowTitle(StringsController stringsController) {
        return stringsController.string(Strings.OptionsMenuWindow.title);
    }

    @Override
    protected void produceMenuButtons(Skin skin, StringsController stringsController) {
        final TextButton btnAudio = produceButton(skin, stringsController, Strings.OptionsMenuWindow.btn_audio);
        final TextButton btnVideo = produceButton(skin, stringsController, Strings.OptionsMenuWindow.btn_video);
        final TextButton btnControllers = produceButton(skin, stringsController, Strings.OptionsMenuWindow.btn_controllers);
        final TextButton btnBack = produceButton(skin, stringsController, Strings.OptionsMenuWindow.btn_back);

        appendMenuButton(btnAudio);
        appendMenuButton(btnVideo);
        appendMenuButton(btnControllers);
        appendMenuButton(btnBack);
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Strings.OptionsMenuWindow.btn_audio:
                getSceneController().onAudio();
                break;
            case Strings.OptionsMenuWindow.btn_video:
                getSceneController().onVideo();
                break;
            case Strings.OptionsMenuWindow.btn_controllers:
                getSceneController().onControllers();
                break;
            case Strings.OptionsMenuWindow.btn_back:
                getSceneController().onBack();
                break;
        }
    }

    @Override
    protected float getButtonsWidth() {
        return 150;
    }
}
