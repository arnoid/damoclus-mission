package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.AudioMenuSceneDelegate;

public class AudioMenuScene extends AbstractMenuScene<AudioMenuSceneDelegate> {
    private static final String TAG = AudioMenuScene.class.getSimpleName();

    public AudioMenuScene(MainComponent component) {
        super();
        component.inject(this);
        init();
    }

    @Override
    protected void produceLayout() {
        setLayout(R.layout.menu_audio);
    }

    @Override
    public void postProduceLayout() {
        registerMenuItem(findButton(R.id.btn_back));
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

    @Override
    public void onInteract(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case R.id.btn_back:
                getSceneDelegate().onBack();
                break;
        }
    }

    @Override
    public void onBack() {
        super.onBack();
        getSceneDelegate().onBack();
    }
}
