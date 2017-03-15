package org.arnoid.damoclus.logic.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.event.Dispatcher;
import org.arnoid.damoclus.logic.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandHandler implements Telegraph {

    private static final String TAG = CommandHandler.class.getSimpleName();

    private final DamoclusGdxGame game;

    private Map<Pattern, Class<? extends Command>> commandList = new HashMap<>();

    public CommandHandler(DamoclusGdxGame game) {
        this.game = game;
    }

    public void registerDefaultCommands() {
        registerCommand(ShowSceneCommand.PATTERN, ShowSceneCommand.class);
    }

    public void registerCommand(String regexPattern, Class<? extends Command> commandClazz) {
        commandList.put(Pattern.compile(regexPattern), commandClazz);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        Gdx.app.debug(TAG, msg.toString());
        return false;
    }

    @Nullable
    public Command fromText(String commandText) {
        for (Pattern pattern : commandList.keySet()) {
            Matcher matcher = pattern.matcher(commandText);
            if (matcher.matches()) {
                Class<? extends Command> commandClazz = commandList.get(pattern);
                Command command = instantiateCommand(commandClazz);
                if (command.withParams(paramsFromMatcher(matcher))) {
                    return command;
                } else {
                    return null;
                }
            }
        }

        DamoclusGdxGame.messageDispatcher().dispatchConsoleMessage("[#error][[" + commandText + "][] no such command registered");

        return null;
    }

    public String[] paramsFromMatcher(Matcher matcher) {
        String[] params = new String[matcher.groupCount()];
        for (int i = 0; i < matcher.groupCount(); i++) {
            params[i] = matcher.group(i);
        }
        return params;
    }

    @Nullable
    public Command instantiateCommand(Class<? extends Command> commandClazz) {
        Command command = null;
        try {
            command = commandClazz.newInstance();
        } catch (InstantiationException e) {
            Gdx.app.error(TAG, e.getMessage(), e);
        } catch (IllegalAccessException e) {
            Gdx.app.error(TAG, e.getMessage(), e);
        }

        return command;
    }


}
