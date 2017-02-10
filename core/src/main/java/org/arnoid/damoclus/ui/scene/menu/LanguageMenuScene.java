package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.strings.Strings;
import org.arnoid.damoclus.logic.handler.menu.LanguageMenuSceneController;

public class LanguageMenuScene extends AbstractMenuScene<LanguageMenuSceneController> {
    private static final String TAG = OptionsMenuScene.class.getSimpleName();

    public LanguageMenuScene(MainComponent component, Stage stage) {
        super(stage);
        component.inject(this);
        init();
    }

    @Override
    protected String getWindowTitle() {
        return getStringsController().string(Strings.LanguageMenuWindow.title);
    }

    @Override
    protected void produceMenuButtons() {
        final TextButton btnEn = produceButton(Strings.LanguageMenuWindow.en);
        final TextButton btnRu = produceButton(Strings.LanguageMenuWindow.ru);
        final TextButton btnBack = produceButton(Strings.LanguageMenuWindow.btn_back);

        appendMenuButton(btnEn);
        appendMenuButton(btnRu);
        appendMenuButton(btnBack);
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Strings.LanguageMenuWindow.btn_back:
                getSceneController().onBack();
                break;
            case Strings.LanguageMenuWindow.en:
                getSceneController().onEnglish();
                show();
                break;
            case Strings.LanguageMenuWindow.ru:
                getSceneController().onRussian();
                show();
                break;
        }
    }

    @Override
    protected float getButtonsWidth() {
        return 150;
    }
}
