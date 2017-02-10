package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.strings.Strings;
import org.arnoid.damoclus.logic.handler.menu.OptionsMenuSceneController;

public class OptionsMenuScene extends AbstractMenuScene<OptionsMenuSceneController> {


    private static final String TAG = LanguageMenuScene.class.getSimpleName();

    public OptionsMenuScene(MainComponent component, Stage stage) {
        super(stage);
        component.inject(this);
        init();
    }

    @Override
    protected String getWindowTitle() {
        return getStringsController().string(Strings.OptionsMenuWindow.title);
    }

    @Override
    protected void produceMenuButtons() {
        final TextButton btnAudio = produceButton(Strings.OptionsMenuWindow.btn_audio);
        final TextButton btnVideo = produceButton(Strings.OptionsMenuWindow.btn_video);
        final TextButton btnLanguage = produceButton(Strings.OptionsMenuWindow.btn_language);
        final TextButton btnControllers = produceButton(Strings.OptionsMenuWindow.btn_controllers);
        final TextButton btnBack = produceButton(Strings.OptionsMenuWindow.btn_back);

        appendMenuButton(btnAudio);
        appendMenuButton(btnVideo);
        appendMenuButton(btnLanguage);
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
            case Strings.OptionsMenuWindow.btn_language:
                getSceneController().onLanguage();
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
