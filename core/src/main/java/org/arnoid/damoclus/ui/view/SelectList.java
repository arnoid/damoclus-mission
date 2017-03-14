package org.arnoid.damoclus.ui.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.logic.input.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.menu.AbstractMenuScene;

import javax.inject.Inject;

public class SelectList extends SelectBox {

    @Inject
    MenuNavigationInputAdapter menuNavigationInputAdapter;
    @Inject
    InputMultiplexer inputMultiplexer;

    public SelectList(Skin skin) {
        super(skin);
        DamoclusGdxGame.mainComponent().inject(this);
    }

    public SelectList(Skin skin, String styleName) {
        super(skin, styleName);
        DamoclusGdxGame.mainComponent().inject(this);
    }

    public SelectList(SelectBoxStyle style) {
        super(style);
        DamoclusGdxGame.mainComponent().inject(this);
    }

    public void show(AbstractMenuScene scene) {
        showList();

        List list = getList();

        list.layout();
        getStage().setKeyboardFocus(list);

        scene.pause();

        SelectListNavigationDelegate navigationDelegate = new SelectListNavigationDelegate(this, menuNavigationInputAdapter, inputMultiplexer, scene);
        inputMultiplexer.removeProcessor(scene.getInputAdapter());
        inputMultiplexer.addProcessor(navigationDelegate.getInputAdapter());
    }

}
