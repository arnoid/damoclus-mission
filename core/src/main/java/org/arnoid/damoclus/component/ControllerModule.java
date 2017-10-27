package org.arnoid.damoclus.component;

import com.badlogic.gdx.InputMultiplexer;
import dagger.Module;
import dagger.Provides;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.controller.skin.AssetsController;
import org.arnoid.damoclus.controller.skin.SkinController;
import org.arnoid.damoclus.controller.strings.StringsController;
import org.arnoid.damoclus.logic.command.CommandHandler;
import org.arnoid.damoclus.logic.input.NavigationInputAdapter;

import javax.inject.Singleton;

@Module
public class ControllerModule {

    private final DamoclusGdxGame game;

    public ControllerModule(DamoclusGdxGame game) {
        this.game = game;
    }

    @Provides
    @Singleton
    public SkinController provideSkinController(AssetsController assetsController) {
        return new SkinController(assetsController);
    }

    @Provides
    @Singleton
    public StringsController provideStringsController(ConfigurationController configurationController) {
        return new StringsController(configurationController.read().getLocale());
    }

    @Singleton
    @Provides
    public AssetsController provideAssetsController() {
        return new AssetsController();
    }

    @Provides
    public NavigationInputAdapter provideNavigationInputAdapter(ConfigurationController configurationController) {
        return new NavigationInputAdapter(configurationController);
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
