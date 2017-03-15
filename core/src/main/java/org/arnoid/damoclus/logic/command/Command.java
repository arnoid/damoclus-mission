package org.arnoid.damoclus.logic.command;

import org.arnoid.damoclus.DamoclusGdxGame;

public interface Command {

    void handle(DamoclusGdxGame game);

    boolean withParams(String[] params);
}
