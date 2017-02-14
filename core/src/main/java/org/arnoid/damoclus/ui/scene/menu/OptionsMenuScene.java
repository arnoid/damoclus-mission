package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.strings.Strings;
import org.arnoid.damoclus.logic.delegate.menu.OptionsMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.ActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.SimpleActorHolder;

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
        MenuSceneBuilder.with(this)
                .prefWidth(150)
                .add(SimpleActorHolder.textButton(Strings.OptionsMenuWindow.btn_audio))
                .add(SimpleActorHolder.textButton(Strings.OptionsMenuWindow.btn_video))
                .add(SimpleActorHolder.textButton(Strings.OptionsMenuWindow.btn_language))
                .add(SimpleActorHolder.textButton(Strings.OptionsMenuWindow.btn_controllers))
                .add(SimpleActorHolder.textButton(Strings.OptionsMenuWindow.btn_back))
                .build();
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
