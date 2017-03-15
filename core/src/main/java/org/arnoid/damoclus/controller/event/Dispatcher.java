package org.arnoid.damoclus.controller.event;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import org.arnoid.damoclus.DamoclusGdxGame;

public class Dispatcher extends MessageDispatcher {

    public enum MessageType {
        CONSOLE_OUTPUT
    }

    public void addListener(Telegraph telegraph, MessageType messageType) {
        addListener(telegraph, messageType.ordinal());
    }

    public void removeListener(Telegraph listener, MessageType messageType) {
        removeListener(listener, messageType.ordinal());
    }

    public MessageType fromTelegram(Telegram telegram) {
        return MessageType.values()[telegram.message];
    }

    public void dispatchMessage(MessageType messageType, Object extraInfo) {
        dispatchMessage(messageType.ordinal(), extraInfo);
    }

    public void dispatchConsoleMessage(String message) {
        dispatchMessage(Dispatcher.MessageType.CONSOLE_OUTPUT, message);
    }

}
