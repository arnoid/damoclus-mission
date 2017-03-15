package org.arnoid.damoclus.logic.delegate;

import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.logic.command.Command;
import org.arnoid.damoclus.logic.command.CommandHandler;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import javax.inject.Inject;

public class ConsoleSceneDelegate extends AbstractScene.SceneDelegate {

    private static final String TAG = ConsoleSceneDelegate.class.getSimpleName();

    @Inject
    CommandHandler handler;

    public ConsoleSceneDelegate(DamoclusGdxGame game) {
        super(game);
        game.mainComponent().inject(this);
    }

    public void onStringCommand(String commandText) {
        Command command = handler.fromText(commandText);
        if (command == null) {

        } else {
            command.handle(getGame());
        }
    }

}
