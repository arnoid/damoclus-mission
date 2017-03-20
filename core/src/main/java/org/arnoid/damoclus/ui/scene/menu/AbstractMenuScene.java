package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.logic.input.NavigationInputAdapter;
import org.arnoid.damoclus.logic.input.NavigationListener;
import org.arnoid.damoclus.ui.scene.AbstractScene;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilderAdapter;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractMenuScene<T extends AbstractScene.SceneDelegate> extends AbstractScene<T> implements NavigationListener {

    private static final String TAG = AbstractMenuScene.class.getSimpleName();

    @Inject
    StringsController stringsController;
    @Inject
    SkinController skinController;

    @Inject
    NavigationInputAdapter navigationInputAdapter;
    @Inject
    InputMultiplexer inputMultiplexer;

    private ChangeListener changeListener;

    public AbstractMenuScene() {
        super();
    }

    void init() {
        changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Actor listenerActor = event.getListenerActor();
                if (listenerActor != null && listenerActor.getName() != null) {
                    AbstractMenuScene.this.changed(listenerActor, event);
                }
            }
        };

        getStage().addListener(changeListener);
        navigationInputAdapter.registerStage(getStage());

        produceLayout();
        postProduceLayout();

        LinkedList<Actor> menuItems = navigationInputAdapter.getMenuItems();
        if (menuItems.size() > 0) {
            Actor firstButton = menuItems.get(0);
            getStage().setKeyboardFocus(firstButton);
        }
    }

    @Override
    public void pause() {
        super.pause();
        navigationInputAdapter.getPaused().set(true);
        inputMultiplexer.removeProcessor(navigationInputAdapter);
        inputMultiplexer.removeProcessor(getStage());
    }

    @Override
    public void resume() {
        super.resume();
        navigationInputAdapter.getPaused().set(false);
        inputMultiplexer.addProcessor(navigationInputAdapter);
        inputMultiplexer.addProcessor(getStage());
    }

    @Override
    public void show() {
        super.show();

        navigationInputAdapter.registerMenuNavigation(this);

        getStage().addAction(
                produceShowAction()
        );
    }

    protected void registerMenuItem(Actor actor) {
        navigationInputAdapter.registerMenuItem(actor);
    }

    protected SequenceAction produceShowAction() {
        return Actions.sequence(
                Actions.alpha(0, 0),
                Actions.fadeIn(0.5F)
        );
    }

    @Override
    public void hide() {
        super.hide();

        navigationInputAdapter.unregisterMenuNavigation(this);

        getStage().addAction(
                produceHideAction()
        );

    }

    protected AlphaAction produceHideAction() {
        return Actions.fadeOut(0.5F);
    }

    public void hideAndDispose() {
        super.hideAndDispose();
        navigationInputAdapter.unregisterMenuNavigation(this);

        getStage().addAction(
                Actions.sequence(
                        produceHideAction(),
                        new Action() {
                            @Override
                            public boolean act(float delta) {
                                dispose();
                                return false;
                            }
                        }
                )
        );
    }

    protected abstract void produceLayout();

    public abstract void postProduceLayout();

    public StringsController getStringsController() {
        return stringsController;
    }

    public Skin getSkin() {
        return skinController.getSkin();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    public void setLayout(String layoutName) {
        List<Actor> actors = XmlMenuSceneBuilder
                .with(layoutName)
                .listener(new XmlMenuSceneBuilderAdapter())
                .build();

        Stage stage = getStage();
        for (Actor actor : actors) {
            stage.addActor(actor);
        }
    }

    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

    @Override
    public void onInteract(Actor actor, InputEvent event) {

    }

    @Override
    public void onBack() {

    }

    @Override
    public void onNext() {

    }

    @Override
    public void onPrevious() {

    }
}