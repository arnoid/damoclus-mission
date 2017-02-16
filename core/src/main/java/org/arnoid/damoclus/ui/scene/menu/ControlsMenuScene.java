package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
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
        return "Controls";
    }

    @Override
    protected void produceMenuItems() {
        MenuSceneBuilder.with(this, new WindowHolder())
                .add(new TableHolder()
                        .add(
                                new RowHolder()
                                        .add(SingleActorHolder.space().width(150))
                                        .add(SingleActorHolder.label("Keyboard").width(150).align(Align.center))
                                        .add(SingleActorHolder.label("Controller").width(150).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label("Up").width(150))
                                        .add(SingleActorHolder.textButton("W").width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton("W").width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label("Down").width(150))
                                        .add(SingleActorHolder.textButton("S").width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton("S").width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label("Right").width(150))
                                        .add(SingleActorHolder.textButton("D").width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton("D").width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label("Left").width(150))
                                        .add(SingleActorHolder.textButton("A").width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton("A").width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label("Interact").width(150))
                                        .add(SingleActorHolder.textButton("Enter").width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton("Enter").width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label("Inventory").width(150))
                                        .add(SingleActorHolder.textButton("I").width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton("I").width(75).align(Align.center)),
                                new RowHolder()
                                        .add(SingleActorHolder.label("Menu").width(150))
                                        .add(SingleActorHolder.textButton("Esc").width(75).align(Align.center))
                                        .add(SingleActorHolder.textButton("Esc").width(75).align(Align.center))
                        )
                )
                .add(new TableHolder()
                        .add(new RowHolder()
                                .add(SingleActorHolder.textButton("Back").width(150))
                                .add(SingleActorHolder.textButton("Apply").width(150))
                        )
                )
                .build();
    }

    @Override
    public void onActorProduced(String name, Actor producedActor) {
        super.onActorProduced(name, producedActor);

        Gdx.app.log(TAG, "Actor produced [" + name + "]");
    }
}
