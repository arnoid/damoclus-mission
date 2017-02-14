package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.strings.Strings;
import org.arnoid.damoclus.logic.delegate.menu.LanguageMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.SingleActorHolder;

public class LanguageMenuScene extends AbstractMenuScene<LanguageMenuSceneDelegate> {
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
    protected void produceMenuItems() {
        MenuSceneBuilder.with(this)
                .prefWidth(150)
                .add(SingleActorHolder.textButton(Strings.LanguageMenuWindow.en))
                .add(SingleActorHolder.textButton(Strings.LanguageMenuWindow.ru))
                .add(SingleActorHolder.textButton(Strings.LanguageMenuWindow.btn_back))
                .build();
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Strings.LanguageMenuWindow.btn_back:
                getSceneDelegate().onBack();
                break;
            case Strings.LanguageMenuWindow.en:
                getSceneDelegate().onEnglish();
                show();
                break;
            case Strings.LanguageMenuWindow.ru:
                getSceneDelegate().onRussian();
                show();
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
