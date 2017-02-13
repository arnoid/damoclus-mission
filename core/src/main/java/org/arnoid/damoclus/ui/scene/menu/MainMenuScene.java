package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.strings.Strings;
import org.arnoid.damoclus.logic.delegate.menu.MainMenuSceneDelegate;

public class MainMenuScene extends AbstractMenuScene<MainMenuSceneDelegate> {

    private static final String TAG = MainMenuScene.class.getSimpleName();

    public MainMenuScene(MainComponent component, Stage stage) {
        super(stage);
        component.inject(this);
        init();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    protected String getWindowTitle() {
        return getStringsController().string(Strings.MainMenuWindow.title);
    }

    @Override
    protected void produceMenuItems() {
        new MenuSceneBuilder(150)
                .textButton(Strings.MainMenuWindow.btn_new_game)
                .textButton(Strings.MainMenuWindow.btn_options)
                .textButton(Strings.MainMenuWindow.btn_quit)
                .build(this);
    }

    @Override
    public void onActorProduced(String name, Actor producedActor) {

    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Strings.MainMenuWindow.btn_new_game:
                getSceneDelegate().onNewGame();
                break;
            case Strings.MainMenuWindow.btn_options:
                getSceneDelegate().onOptions();
                break;
            case Strings.MainMenuWindow.btn_quit:
                getSceneDelegate().onQuit();
                break;
        }
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

}
