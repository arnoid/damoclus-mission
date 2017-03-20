package org.arnoid.damoclus.ui.view;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.logic.input.NavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;

public class SelectList<T> extends SelectBox<T> {

    public SelectList(Skin skin) {
        super(skin);
    }

    public void show(AbstractMenuScene scene) {
        showList();

        NavigationInputAdapter navigationInputAdapter = DamoclusGdxGame.mainComponent().provideMenuNavigationInputAdapter();
        InputMultiplexer inputMultiplexer = DamoclusGdxGame.mainComponent().provideInputMultiplexer();

        List list = getList();

        list.layout();
        getStage().setKeyboardFocus(list);

        scene.pause();

        SelectListNavigationDelegate navigationDelegate = new SelectListNavigationDelegate(this, navigationInputAdapter, inputMultiplexer, scene);

        inputMultiplexer.addProcessor(navigationInputAdapter);
    }

}
