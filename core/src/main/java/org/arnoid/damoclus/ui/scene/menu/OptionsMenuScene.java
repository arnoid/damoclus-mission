package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.strings.Strings;
import org.arnoid.damoclus.logic.delegate.menu.OptionsMenuSceneDelegate;

public class OptionsMenuScene extends AbstractMenuScene<OptionsMenuSceneDelegate> {

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
    protected void produceMenuItems() {
        new MenuSceneBuilder(150)
                .textButton(Strings.OptionsMenuWindow.btn_audio)
                .textButton(Strings.OptionsMenuWindow.btn_video)
                .textButton(Strings.OptionsMenuWindow.btn_language)
                .textButton(Strings.OptionsMenuWindow.btn_controllers)
                .textButton(Strings.OptionsMenuWindow.btn_back)
                .build(this);
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Strings.OptionsMenuWindow.btn_audio:
                getSceneDelegate().onAudio();
                break;
            case Strings.OptionsMenuWindow.btn_video:
                getSceneDelegate().onVideo();
                break;
            case Strings.OptionsMenuWindow.btn_controllers:
                getSceneDelegate().onControllers();
                break;
            case Strings.OptionsMenuWindow.btn_language:
                getSceneDelegate().onLanguage();
                break;
            case Strings.OptionsMenuWindow.btn_back:
                getSceneDelegate().onBack();
                break;
        }
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

    @Override
    protected float getCellWidth(int column) {
        return 150;
    }

}
