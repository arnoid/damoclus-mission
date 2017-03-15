package org.arnoid.damoclus.ui.view;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.logic.input.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;

public class SelectList<T> extends SelectBox<T> {

    public SelectList(Skin skin) {
        super(skin);
    }

    public SelectList(Skin skin, String styleName) {
        super(skin, styleName);
    }

    public SelectList(SelectBoxStyle style) {
        super(style);
    }

    public void show(AbstractMenuScene scene) {
        showList();

        MenuNavigationInputAdapter menuNavigationInputAdapter = DamoclusGdxGame.mainComponent().provideMenuNavigationInputAdapter();
        InputMultiplexer inputMultiplexer = DamoclusGdxGame.mainComponent().provideInputMultiplexer();


        List list = getList();

        list.layout();
        getStage().setKeyboardFocus(list);

        scene.pause();

        SelectListNavigationDelegate navigationDelegate = new SelectListNavigationDelegate(this, menuNavigationInputAdapter, inputMultiplexer, scene);
        inputMultiplexer.removeProcessor(scene.getInputAdapter());
        inputMultiplexer.addProcessor(navigationDelegate.getInputAdapter());
    }

}
