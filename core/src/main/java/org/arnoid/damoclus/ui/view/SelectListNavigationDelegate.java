package org.arnoid.damoclus.ui.view;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import org.arnoid.damoclus.logic.input.NavigationInputAdapter;
import org.arnoid.damoclus.logic.input.NavigationListener;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class SelectListNavigationDelegate implements NavigationListener {

    private final AbstractScene scene;
    private final NavigationInputAdapter menuNavigationInputAdapter;
    private final InputMultiplexer inputMultiplexer;
    private final SelectList selectList;

    public SelectListNavigationDelegate(SelectList selectList, NavigationInputAdapter navigationInputAdapter, InputMultiplexer inputMultiplexer, AbstractScene scene) {
        this.selectList = selectList;
        this.menuNavigationInputAdapter = navigationInputAdapter;
        this.inputMultiplexer = inputMultiplexer;
        this.scene = scene;

        menuNavigationInputAdapter.registerMenuNavigation(this);
    }

    @Override
    public void onNext() {
        int selectedIndex = selectList.getSelectedIndex();
        if (selectedIndex >= selectList.getItems().size - 1) {
            selectedIndex = 0;
        } else {
            selectedIndex++;
        }

        selectList.setSelectedIndex(selectedIndex);
        selectList.getList().setSelectedIndex(selectedIndex);
    }

    @Override
    public void onPrevious() {
        int selectedIndex = selectList.getSelectedIndex();
        if (selectedIndex == 0) {
            selectedIndex = selectList.getItems().size - 1;
        } else {
            selectedIndex--;
        }

        selectList.setSelectedIndex(selectedIndex);
        selectList.getList().setSelectedIndex(selectedIndex);
    }

    @Override
    public void onInteract(Actor actor, InputEvent event) {
        onBack();
    }

    @Override
    public void onBack() {
        menuNavigationInputAdapter.removeListener(this);
        inputMultiplexer.removeProcessor(menuNavigationInputAdapter);
        menuNavigationInputAdapter.unregisterMenuNavigation(this);
        selectList.hideList();
        scene.resume();
    }

}