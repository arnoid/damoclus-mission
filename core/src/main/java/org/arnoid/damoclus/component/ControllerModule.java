package org.arnoid.damoclus.component;

import com.badlogic.gdx.InputMultiplexer;
import dagger.Module;
import dagger.Provides;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.logic.command.CommandHandler;
import org.arnoid.damoclus.logic.input.MenuNavigationInputAdapter;

import javax.inject.Singleton;

@Module
public class ControllerModule {

    private final DamoclusGdxGame game;

    public ControllerModule(DamoclusGdxGame game) {
        this.game = game;
    }

    @Provides
    @Singleton
    public SkinController provideSkinController() {
        return new SkinController();
    }

    @Provides
    @Singleton
    public StringsController provideStringsController(ConfigurationController configurationController) {
        return new StringsController(configurationController.read().getLocale());
    }

    @Singleton
    @Provides
    public ConfigurationController provideControlMapConfigurationController() {
        return new ConfigurationController();
    }

    @Provides
    public MenuNavigationInputAdapter provideMenuNavigationInputAdapter(ConfigurationController configurationController) {
        return new MenuNavigationInputAdapter(configurationController);
    }

    @Singleton
    @Provides
    public InputMultiplexer provideInputMultiplexer() {
        return new InputMultiplexer();
    }

    @Singleton
    @Provides
    public CommandHandler provideCommandHandler() {
        CommandHandler commandHandler = new CommandHandler(game);

        commandHandler.registerDefaultCommands();

        return commandHandler;
    }

}
