package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.OptionsMenuSceneDelegate;

public class OptionsMenuScene extends AbstractMenuScene<OptionsMenuSceneDelegate> {

    private static final String TAG = LanguageMenuScene.class.getSimpleName();

    public OptionsMenuScene(MainComponent component) {
        super();
        component.inject(this);
        init();
    }

    @Override
    protected void produceLayout() {
        setLayout(R.layout.menu_options);
    }

    @Override
    public void postProduceLayout() {
        registerMenuItem(findButton(R.id.btn_audio));
        registerMenuItem(findButton(R.id.btn_video));
        registerMenuItem(findButton(R.id.btn_language));
        registerMenuItem(findButton(R.id.btn_controllers));
        registerMenuItem(findButton(R.id.btn_back));
    }

    @Override
    public void onInteract(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case R.id.btn_audio:
                getSceneDelegate().onAudio();
                break;
            case R.id.btn_video:
                getSceneDelegate().onVideo();
                break;
            case R.id.btn_controllers:
                getSceneDelegate().onControllers();
                break;
            case R.id.btn_language:
                getSceneDelegate().onLanguage();
                break;
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

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

}
