package org.arnoid.damoclus.logic.handler.menu;

import com.badlogic.gdx.Gdx;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.data.configuration.Configuration;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;
import java.util.Locale;

public class LanguageMenuSceneController extends AbstractScene.SceneController {

    private static final String TAG = LanguageMenuSceneController.class.getSimpleName();

    @Inject
    ConfigurationController configurationController;
    @Inject
    StringsController stringsController;

    public LanguageMenuSceneController(DamoclusGdxGame game) {
        super(game);
        game.mainComponent.inject(this);
    }

    public void onEnglish() {
        setLocale(Locale.ENGLISH);
    }

    public void onRussian() {
        setLocale(new Locale("ru", "ru"));
    }

    public void onBack() {
        Gdx.app.log(TAG, "Back clicked");
        getGame().popScene();
    }

    private void setLocale(Locale locale) {
        Configuration configuration = configurationController.read();
        configuration.setLocale(locale);
        configurationController.write(configuration);
        stringsController.loadLocale(locale);
    }
}
