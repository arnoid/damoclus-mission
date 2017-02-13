package org.arnoid.damoclus.logic.delegate.menu;

import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.data.configuration.Configuration;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;
import java.util.Locale;

public class LanguageMenuSceneDelegate extends AbstractScene.SceneDelegate {

    private static final String TAG = LanguageMenuSceneDelegate.class.getSimpleName();

    @Inject
    ConfigurationController configurationController;
    @Inject
    StringsController stringsController;

    public LanguageMenuSceneDelegate(DamoclusGdxGame game) {
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
        getGame().popScene();
    }

    private void setLocale(Locale locale) {
        Configuration configuration = configurationController.read();
        configuration.setLocale(locale);
        configurationController.write(configuration);
        stringsController.loadLocale(locale);
    }
}
