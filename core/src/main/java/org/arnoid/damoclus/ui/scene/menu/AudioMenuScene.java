package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.strings.Strings;
import org.arnoid.damoclus.logic.delegate.menu.AudioMenuSceneDelegate;

public class AudioMenuScene extends AbstractMenuScene<AudioMenuSceneDelegate> {
    private static final String TAG = AudioMenuScene.class.getSimpleName();

    public AudioMenuScene(MainComponent component, Stage stage) {
        super(stage);
        component.inject(this);
        init();
    }

    @Override
    protected String getWindowTitle() {
        return getStringsController().string(Strings.AudioMenuWindow.title);
    }

    @Override
    protected void produceMenuItems() {
        new MenuSceneBuilder(150)
                .textButton(Strings.AudioMenuWindow.btn_back)
                .build(this);
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Strings.AudioMenuWindow.btn_back:
                getSceneDelegate().onBack();
                break;
        }
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

    @Override
    protected float getCellWidth(int column) {
        return 150;
    }

}
