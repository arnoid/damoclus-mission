package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.event.Dispatcher;
import org.arnoid.damoclus.logic.delegate.ConsoleSceneDelegate;
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
        Window consoleWindow = (Window) findActor(R.id.window_console);
        consoleWindow.addAction(Actions.alpha(0.85F));

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

        edtConsoleInput.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                String text = textField.getText();
                if (text.length() == 0) {

                } else if ((c == (int) '\r' || c == (int) '\n')) {
                    textField.setText("");
                    textField.invalidate();
                    onSubmitConsoleMessage(text);
                }
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

    @Override
    protected SequenceAction produceShowAction() {
        return Actions.sequence(
                Actions.alpha(0, 0),
                Actions.fadeIn(0.5F)
        );
    }

    @Override
    public void resume() {
        super.resume();
        DamoclusGdxGame.messageDispatcher().addListener(this, Dispatcher.MessageType.CONSOLE_OUTPUT);
    }

    @Override
    public void pause() {
        super.pause();
        DamoclusGdxGame.messageDispatcher().removeListener(this, Dispatcher.MessageType.CONSOLE_OUTPUT);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        Dispatcher.MessageType messageType = DamoclusGdxGame.messageDispatcher().fromTelegram(msg);
        switch (messageType) {
            case CONSOLE_OUTPUT:
                onConsoleOutputMessage((String) msg.extraInfo);
                return true;
            default:
                return super.handleMessage(msg);
        }
    }

    private void onConsoleOutputMessage(String message) {
        lblConsoleOutput.getText()
                .append("\n")
                .append(getSkinController().formatColoredString(message));

        lblConsoleOutput.invalidate();
    }

    public void onSubmitConsoleMessage(String message) {
        onConsoleOutputMessage(message);

        getSceneDelegate().onStringCommand(message);
    }
}
