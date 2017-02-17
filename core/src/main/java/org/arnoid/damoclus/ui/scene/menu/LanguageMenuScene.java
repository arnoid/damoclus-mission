package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.Ids;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.LanguageMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.SingleActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.WindowHolder;

public class LanguageMenuScene extends AbstractMenuScene<LanguageMenuSceneDelegate> {
    private static final String TAG = OptionsMenuScene.class.getSimpleName();

    public LanguageMenuScene(MainComponent component, Stage stage) {
        super(stage);
        component.inject(this);
        init();
    }

    @Override
    protected String getWindowTitle() {
        return getStringsController().string(Ids.menu.language.window_title);
    }

    @Override
    protected void produceMenuItems() {
        MenuSceneBuilder.with(this, new WindowHolder())
                .add(SingleActorHolder.textButton(Ids.menu.language.en).align(Align.center).pad(5).width(250))
                .add(SingleActorHolder.textButton(Ids.menu.language.ru).align(Align.center).pad(5).width(250))
                .add(SingleActorHolder.space().height(50))
                .add(SingleActorHolder.textButton(Ids.menu.language.btn_back).align(Align.center).pad(5).width(250))
                .build();
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Ids.menu.language.btn_back:
                getSceneDelegate().onBack();
                break;
            case Ids.menu.language.en:
                getSceneDelegate().onEnglish();
                show();
                break;
            case Ids.menu.language.ru:
                getSceneDelegate().onRussian();
                show();
                break;
        }
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

}
