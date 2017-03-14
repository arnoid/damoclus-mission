package org.arnoid.damoclus.ui.view;

import com.badlogic.gdx.InputMultiplexer;
import org.arnoid.damoclus.logic.input.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.AbstractScene;

public class SelectListNavigationDelegate implements MenuNavigationInputAdapter.MenuNavigationListener {

    private final AbstractScene scene;
    private final MenuNavigationInputAdapter menuNavigationInputAdapter;
    private final InputMultiplexer inputMultiplexer;
    private final SelectList selectList;

    public SelectListNavigationDelegate(SelectList selectList, MenuNavigationInputAdapter menuNavigationInputAdapter, InputMultiplexer inputMultiplexer, AbstractScene scene) {
        this.selectList = selectList;
        this.menuNavigationInputAdapter = menuNavigationInputAdapter;
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
    public void onPrev() {
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
    public void onInteract() {
        menuNavigationInputAdapter.removeListener(this);
        inputMultiplexer.removeProcessor(getInputAdapter());
        menuNavigationInputAdapter.unregisterMenuNavigation(this);
        selectList.hideList();
        scene.resume();
    }

    @Override
    public MenuNavigationInputAdapter getInputAdapter() {
        return menuNavigationInputAdapter;
    }
}