package org.arnoid.damoclus.component;

import dagger.Module;
import dagger.Provides;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.controller.persistent.CrewMemberDictionaryController;
import org.arnoid.damoclus.data.CrewMemberDictionary;
import org.arnoid.damoclus.data.configuration.Configuration;

import javax.inject.Singleton;

@Module
public class PersistingControllerModule {

    private final DamoclusGdxGame game;

    public PersistingControllerModule(DamoclusGdxGame game) {
        this.game = game;
    }

    @Singleton
    @Provides
    public ConfigurationController provideConfigurationController() {
        return new ConfigurationController();
    }

    @Provides
    public Configuration provideConfiguration() {
        return provideConfigurationController().read();
    }

    @Singleton
    @Provides
    public CrewMemberDictionaryController provideCrewMemberDictionaryController() {
        return new CrewMemberDictionaryController();
    }

    @Provides
    public CrewMemberDictionary provideCrewMemberDictionary() {
        return provideCrewMemberDictionaryController().read();
    }

}
