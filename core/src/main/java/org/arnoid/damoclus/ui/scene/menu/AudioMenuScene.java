package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.Ids;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.AudioMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.SingleActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.WindowHolder;

public class AudioMenuScene extends AbstractMenuScene<AudioMenuSceneDelegate> {
    private static final String TAG = AudioMenuScene.class.getSimpleName();

    public AudioMenuScene(MainComponent component, Stage stage) {
        super(stage);
        component.inject(this);
        init();
    }

    @Override
    protected String getWindowTitle() {
        return getStringsController().string(Ids.menu.audio.window_title);
    }

    @Override
    protected void produceMenuItems() {
        MenuSceneBuilder.with(this, new WindowHolder())
                .add(SingleActorHolder.textButton(Ids.menu.audio.btn_back).align(Align.center).pad(5).width(250))
                .build();
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Ids.menu.audio.btn_back:
                getSceneDelegate().onBack();
                break;
        }
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

}
