package org.arnoid.damoclus.component;

import dagger.Module;
import dagger.Provides;
import org.arnoid.damoclus.controller.persistent.ConfigurationController;
import org.arnoid.damoclus.logic.input.MenuNavigationInputAdapter;

@Module
public class ViewModule {

    @Provides
    public MenuNavigationInputAdapter provideMenuNavigationInputAdapter(ConfigurationController configurationController) {
        return new MenuNavigationInputAdapter(configurationController);
    }

}
