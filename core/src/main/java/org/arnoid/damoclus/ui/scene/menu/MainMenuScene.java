package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.MainMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilderAdapter;

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
        XmlMenuSceneBuilder
                .with(R.layout.menu_main)
                .listener(new XmlMenuSceneBuilderAdapter())
                .build(getStage());
    }

    @Override
    public void postProduceLayout() {
        registerMenuItem(findButton(R.id.btn_new_game));
        registerMenuItem(findButton(R.id.btn_options));
        registerMenuItem(findButton(R.id.btn_quit));
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
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

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

}
