package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.ControlsMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.RowHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.ScrollPaneHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.SingleActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.TableHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.WindowHolder;

public class ControlsMenuScene extends AbstractMenuScene<ControlsMenuSceneDelegate> {

    private static final String TAG = ControlsMenuScene.class.getSimpleName();

    public ControlsMenuScene(MainComponent component, Stage stage) {
        super(stage);
        component.inject(this);
        init();
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        Gdx.app.log(TAG, actor.getName());
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

    @Override
    protected String getWindowTitle() {
        return "";
    }

    @Override
    protected void produceMenuItems() {
        MenuSceneBuilder.with(this, new WindowHolder())
                .add(
                        new ScrollPaneHolder(true, false)
                                .set(new TableHolder()
                                        .add(
                                                RowHolder.row()
                                                        .add(SingleActorHolder.textButton("1"))
                                                        .add(SingleActorHolder.textButton("2")),
                                                RowHolder.row()
                                                        .add(SingleActorHolder.textButton("2"))
                                                        .add(SingleActorHolder.textButton("3")),
                                                RowHolder.row()
                                                        .add(SingleActorHolder.textButton("4"))
                                                        .add(SingleActorHolder.textButton("5")),
                                                RowHolder.row()
                                                        .add(SingleActorHolder.textButton("6"))
                                                        .add(SingleActorHolder.textButton("7"))
                                                        .add(SingleActorHolder.textButton("18"))
                                        )
                                )
                )
                .add(
                        new TableHolder().add(
                                RowHolder.row()
                                        .add(SingleActorHolder.textButton("1"))
                                        .add(SingleActorHolder.textButton("2")),
                                RowHolder.row()
                                        .add(SingleActorHolder.textButton("2"))
                                        .add(SingleActorHolder.textButton("3")),
                                RowHolder.row()
                                        .add(SingleActorHolder.textButton("4"))
                                        .add(SingleActorHolder.textButton("5")),
                                RowHolder.row()
                                        .add(SingleActorHolder.textButton("6"))
                                        .add(SingleActorHolder.textButton("7"))
                                        .add(SingleActorHolder.textButton("18"))
                        )
                )
                .build();
    }

    @Override
    public void onActorProduced(String name, Actor producedActor) {
        super.onActorProduced(name, producedActor);

        Gdx.app.debug(TAG, "Actor produced [" + name + "]");
    }
}
