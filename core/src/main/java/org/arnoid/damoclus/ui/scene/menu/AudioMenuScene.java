package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.AudioMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilderAdapter;

public class AudioMenuScene extends AbstractMenuScene<AudioMenuSceneDelegate> {
    private static final String TAG = AudioMenuScene.class.getSimpleName();

    public AudioMenuScene(MainComponent component) {
        super();
        component.inject(this);
        init();
    }

    @Override
    protected void produceLayout() {
        XmlMenuSceneBuilder
                .with(R.layout.menu_audio)
                .listener(new XmlMenuSceneBuilderAdapter())
                .build(getStage());
    }

    @Override
    public void postProduceLayout() {
        registerMenuItem(findButton(R.id.btn_back));
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case R.id.btn_back:
                getSceneDelegate().onBack();
                break;
        }
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

}
