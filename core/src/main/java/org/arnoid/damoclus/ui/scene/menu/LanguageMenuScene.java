package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.LanguageMenuSceneDelegate;

public class LanguageMenuScene extends AbstractMenuScene<LanguageMenuSceneDelegate> {
    private static final String TAG = OptionsMenuScene.class.getSimpleName();

    public LanguageMenuScene(MainComponent component) {
        super();
        component.inject(this);
        init();
    }

    @Override
    protected void produceLayout() {
        setLayout(R.layout.menu_language);
    }

    @Override
    public void postProduceLayout() {
        registerMenuItem(findButton(R.id.btn_language_en));
        registerMenuItem(findButton(R.id.btn_language_ru));
        registerMenuItem(findButton(R.id.btn_back));
    }

    @Override
    public void onInteract(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case R.id.btn_back:
                getSceneDelegate().onBack();
                break;
            case R.id.btn_language_en:
                getSceneDelegate().onEnglish();
                show();
                break;
            case R.id.btn_language_ru:
                getSceneDelegate().onRussian();
                show();
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
