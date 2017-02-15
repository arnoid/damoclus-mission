package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.strings.Strings;
import org.arnoid.damoclus.data.configuration.DisplayConfiguration;
import org.arnoid.damoclus.logic.delegate.menu.VideoMenuSceneDelegate;
import org.arnoid.damoclus.logic.input.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.menu.builder.MenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.RowHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.SingleActorHolder;
import org.arnoid.damoclus.ui.scene.menu.builder.holder.WindowHolder;

import javax.inject.Inject;

public class VideoMenuScene extends AbstractMenuScene<VideoMenuSceneDelegate> {

    @Inject
    MenuNavigationInputAdapter menuNavigationInputAdapter;

    private static final String TAG = VideoMenuScene.class.getSimpleName();
    private SelectBox<Graphics.DisplayMode> displayModeSelectBox;
    private ImageButton fullscreenCheckBox;

    public VideoMenuScene(MainComponent component, Stage stage) {
        super(stage);
        component.inject(this);
        init();
    }

    @Override
    protected String getWindowTitle() {
        return getStringsController().string(Strings.VideoMenuWindow.title);
    }

    @Override
    protected void produceMenuItems() {
        MenuSceneBuilder.with(this, new WindowHolder())
                .add(RowHolder.row()
                        .add(SingleActorHolder.label(Strings.VideoMenuWindow.lbl_fullscreen).align(Align.right))
                        .add(SingleActorHolder.checkBox(Strings.VideoMenuWindow.chk_fullscreen).align(Align.left))
                        .align(Align.center).pad(5).width(250)
                )
                .add(RowHolder.row()
                        .add(SingleActorHolder.label(Strings.VideoMenuWindow.lbl_resolution).align(Align.right))
                        .add(SingleActorHolder.selectBox(Strings.VideoMenuWindow.select_resolution).align(Align.left))
                        .align(Align.center).pad(5).width(250)
                )
                .add(SingleActorHolder.space().height(50))
                .add(RowHolder.row()
                        .add(SingleActorHolder.textButton(Strings.VideoMenuWindow.btn_back).width(250))
                        .add(SingleActorHolder.textButton(Strings.VideoMenuWindow.btn_apply).width(250))
                        .align(Align.center).pad(5).width(250)
                )
                .build();
    }

    @Override
    protected Object[] getSelectBoxArray(String name) {
        if (Strings.VideoMenuWindow.select_resolution.equals(name)) {
            displayModeSelectBox.setItems(getSceneDelegate().getDisplayModes());

            DisplayConfiguration displayConfiguration = getSceneDelegate().getDisplayConfiguration();

            displayModeSelectBox.setSelectedIndex(getSceneDelegate().getDisplayModeIndex(displayConfiguration.getDisplayMode()));
            return null;
        } else {
            return super.getSelectBoxArray(name);
        }
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case Strings.VideoMenuWindow.chk_fullscreen:
                fullscreenCheckBox.toggle();
                break;
            case Strings.VideoMenuWindow.btn_back:
                getSceneDelegate().onBack();
                break;
            case Strings.VideoMenuWindow.btn_apply:
                getSceneDelegate().apply(fullscreenCheckBox.isChecked(), displayModeSelectBox.getSelected());
                break;
            case Strings.VideoMenuWindow.select_resolution:
                displayModeSelectBox.showList();
                List<Graphics.DisplayMode> list = displayModeSelectBox.getList();
                list.setTouchable(Touchable.enabled);
                getStage().setKeyboardFocus(list);
                MenuNavigationInputAdapter.MenuNavigationListener resolutionsListNavigator = new MenuNavigationInputAdapter.MenuNavigationListener() {
                    @Override
                    public void onNext() {
                        int selectedIndex = displayModeSelectBox.getSelectedIndex();
                        if (selectedIndex >= displayModeSelectBox.getItems().size - 1) {
                            selectedIndex = 0;
                        } else {
                            selectedIndex++;
                        }

                        displayModeSelectBox.setSelectedIndex(selectedIndex);
                        list.setSelectedIndex(selectedIndex);
                    }

                    @Override
                    public void onPrev() {
                        int selectedIndex = displayModeSelectBox.getSelectedIndex();
                        if (selectedIndex == 0) {
                            selectedIndex = displayModeSelectBox.getItems().size - 1;
                        } else {
                            selectedIndex--;
                        }

                        displayModeSelectBox.setSelectedIndex(selectedIndex);
                        list.setSelectedIndex(selectedIndex);
                    }

                    @Override
                    public void onInteract() {
                        menuNavigationInputAdapter.removeListener(this);
                        displayModeSelectBox.hideList();
                        resume();
                    }
                };

                menuNavigationInputAdapter.addListener(resolutionsListNavigator);
                pause();
                break;
        }
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {
    }

    @Override
    public void onActorProduced(String name, Actor producedActor) {
        Gdx.app.log(TAG, name);
        if (Strings.VideoMenuWindow.chk_fullscreen.equals(name)) {
            fullscreenCheckBox = (ImageButton) producedActor;
        } else if (Strings.VideoMenuWindow.select_resolution.equals(name)) {
            displayModeSelectBox = (SelectBox<Graphics.DisplayMode>) producedActor;
        }
    }

    @Override
    public void onSceneDelegate() {
        super.onSceneDelegate();

        DisplayConfiguration displayConfiguration = getSceneDelegate().getDisplayConfiguration();

        fullscreenCheckBox.setChecked(displayConfiguration.isFullscreen());
    }
}
