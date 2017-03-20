package org.arnoid.damoclus.ui.scene.menu.dialog;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;

import javax.inject.Inject;

public class SceneDialog extends Dialog {

    private static final String TAG = SceneDialog.class.getSimpleName();

    protected AbstractMenuScene scene;

    ObjectMap<Actor, Object> values = new ObjectMap();

    private boolean cancellable = true;

    @Inject
    StringsController stringsController;

    public SceneDialog(String title, Skin skin) {
        super(title, skin);

        DamoclusGdxGame.mainComponent().inject(this);

        getTitleLabel().setText(stringsController.string(title));

        getTitleTable().padLeft(5);

        getTitleLabel().invalidate();
    }

    public void show(AbstractMenuScene scene) {
        this.scene = scene;
        scene.pause();

        Dialog dialog = this;

        scene.getStage().addAction(new Action() {

            @Override
            public boolean act(float delta) {
                show(scene.getStage());
                scene.getStage().setKeyboardFocus(dialog);
                return true;
            }
        });
    }

    public void hide() {
        hide(new Action() {
            @Override
            public boolean act(float delta) {
                scene.postProduceLayout();
                scene.resume();

                return true;
            }
        });
    }

    public SceneDialog cancellable(boolean cancellable) {
        this.cancellable = cancellable;
        return this;
    }

    @Override
    public void setObject(Actor actor, Object object) {
        values.put(actor, object);
    }

    public Object getObject(Actor actor) {
        return values.get(actor);
    }
}