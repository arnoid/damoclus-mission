package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.Ids;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.OptionsMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.SingleActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.WindowHolder;

public class OptionsMenuScene extends AbstractMenuScene<OptionsMenuSceneDelegate> {

    private static final String TAG = LanguageMenuScene.class.getSimpleName();

    public OptionsMenuScene(MainComponent component, Stage stage) {
        super(stage);
        component.inject(this);
        init();
    }

    @Override
    protected String getWindowTitle() {
        return getStringsController().string(Ids.menu.options.window_title);
    }

    @Override
    protected void produceMenuItems() {
        MenuSceneBuilder.with(this, new WindowHolder())
                .add(SingleActorHolder.textButton(Ids.menu.options.btn_audio).align(Align.center).pad(5).width(250))
                .add(SingleActorHolder.textButton(Ids.menu.options.btn_video).align(Align.center).pad(5).width(250))
                .add(SingleActorHolder.textButton(Ids.menu.options.btn_language).align(Align.center).pad(5).width(250))
                .add(SingleActorHolder.textButton(Ids.menu.options.btn_controllers).align(Align.center).pad(5).width(250))
                .add(SingleActorHolder.space().height(50))
                .add(SingleActorHolder.textButton(Ids.menu.options.btn_back).align(Align.center).pad(5).width(250))
                .build();
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Ids.menu.options.btn_audio:
                getSceneDelegate().onAudio();
                break;
            case Ids.menu.options.btn_video:
                getSceneDelegate().onVideo();
                break;
            case Ids.menu.options.btn_controllers:
                getSceneDelegate().onControllers();
                break;
            case Ids.menu.options.btn_language:
                getSceneDelegate().onLanguage();
                break;
            case Ids.menu.options.btn_back:
                getSceneDelegate().onBack();
                break;
        }
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

}
