package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.Ids;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.MainMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.SingleActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.WindowHolder;

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
        return getStringsController().string(Ids.menu.main.window_title);
    }

    @Override
    protected void produceMenuItems() {
        MenuSceneBuilder.with(this, new WindowHolder().movable().modal())
                .add(SingleActorHolder.textButton(Ids.menu.main.btn_new_game).align(Align.center).pad(5).width(250))
                .add(SingleActorHolder.textButton(Ids.menu.main.btn_options).align(Align.center).pad(5).width(250))
                .add(SingleActorHolder.textButton(Ids.menu.main.btn_quit).align(Align.center).pad(5).width(250))
                .build();

    }

    @Override
    public void onActorProduced(String name, Actor producedActor) {

    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Ids.menu.main.btn_new_game:
                getSceneDelegate().onNewGame();
                break;
            case Ids.menu.main.btn_options:
                getSceneDelegate().onOptions();
                break;
            case Ids.menu.main.btn_quit:
                getSceneDelegate().onQuit();
                break;
        }
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

}
