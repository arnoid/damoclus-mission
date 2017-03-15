package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.ConsoleSceneDelegate;
import org.arnoid.damoclus.logic.delegate.menu.AudioMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilderAdapter;

public class ConsoleScene extends AbstractMenuScene<ConsoleSceneDelegate> {

    private static final String TAG = ConsoleScene.class.getSimpleName();

    private Label lblConsoleOutput;
    private TextField edtConsoleInput;

    public ConsoleScene(MainComponent component) {
        super();
        component.inject(this);
        init();

        setOverlay(true);
    }

    @Override
    protected void produceLayout() {
        XmlMenuSceneBuilder
                .with(R.layout.window_console)
                .listener(new XmlMenuSceneBuilderAdapter())
                .build(getStage());
    }

    @Override
    public void postProduceLayout() {
        lblConsoleOutput = (Label) findActor(R.id.lbl_console_output);
        lblConsoleOutput.setWrap(true);
        lblConsoleOutput.setAlignment(Align.bottomLeft);
        edtConsoleInput = (TextField) findActor(R.id.edt_console_input);

        edtConsoleInput.setTextFieldFilter(new TextField.TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return c != Input.Keys.GRAVE && c != '`';
            }
        });

        getStage().setKeyboardFocus(edtConsoleInput);
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {

        if (event.getCharacter() == Input.Keys.GRAVE) {
            Gdx.app.log(TAG, "GRAVE is typed");
        }
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

}
