package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.MainMenuSceneDelegate;

public class MainMenuScene extends AbstractMenuScene<MainMenuSceneDelegate> {

    private static final String TAG = MainMenuScene.class.getSimpleName();

    public MainMenuScene(MainComponent component) {
        super();
        component.inject(this);
        init();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    protected void produceLayout() {
        setLayout(R.layout.menu_main);
    }

    @Override
    public void postProduceLayout() {
        registerMenuItem(findButton(R.id.btn_new_game));
        registerMenuItem(findButton(R.id.btn_options));
        registerMenuItem(findButton(R.id.btn_quit));
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

    @Override
    public void onInteract(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case R.id.btn_new_game:
                getSceneDelegate().onNewGame();
                break;
            case R.id.btn_options:
                getSceneDelegate().onOptions();
                break;
            case R.id.btn_quit:
                getSceneDelegate().onQuit();
                break;
        }
    }
}
