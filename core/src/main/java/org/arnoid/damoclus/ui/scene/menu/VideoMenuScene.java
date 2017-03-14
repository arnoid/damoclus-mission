package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.data.configuration.DisplayConfiguration;
import org.arnoid.damoclus.logic.delegate.menu.VideoMenuSceneDelegate;
import org.arnoid.damoclus.logic.input.MenuNavigationInputAdapter;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilder;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilderAdapter;
import org.arnoid.damoclus.ui.view.SelectList;

import javax.inject.Inject;

public class VideoMenuScene extends AbstractMenuScene<VideoMenuSceneDelegate> {

    @Inject
    MenuNavigationInputAdapter menuNavigationInputAdapter;

    private static final String TAG = VideoMenuScene.class.getSimpleName();
    private ImageButton chkFullscreen;
    private SelectList slctDisplayMode;

    public VideoMenuScene(MainComponent component) {
        super();
        component.inject(this);
        init();
    }

    @Override
    protected void produceLayout() {
        XmlMenuSceneBuilder
                .with(R.layout.menu_video)
                .listener(new XmlMenuSceneBuilderAdapter())
                .build(getStage());
    }

    @Override
    public void postProduceLayout() {
        chkFullscreen = (ImageButton) findActor(R.id.menu_video_chk_fullscreen);
        slctDisplayMode = (SelectList) findActor(R.id.menu_video_selectbox_resolution);

        registerMenuItem(chkFullscreen);
        registerMenuItem(slctDisplayMode);
        registerMenuItem(findButton(R.id.btn_back));
        registerMenuItem(findButton(R.id.btn_apply));
    }

    @Override
    protected void clicked(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case R.id.menu_video_chk_fullscreen:
                chkFullscreen.toggle();
                break;
            case R.id.btn_back:
                getSceneDelegate().onBack();
                break;
            case R.id.btn_apply:
                getSceneDelegate().apply(chkFullscreen.isChecked(), (Graphics.DisplayMode) slctDisplayMode.getSelected());
                break;
            case R.id.menu_video_selectbox_resolution:
                slctDisplayMode.show(this);
                break;
        }
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {
    }

    @Override
    public void onSceneDelegate() {
        super.onSceneDelegate();

        DisplayConfiguration displayConfiguration = getSceneDelegate().getDisplayConfiguration();

        chkFullscreen.setChecked(displayConfiguration.isFullscreen());

        slctDisplayMode.setItems(getSceneDelegate().getDisplayModes());
        slctDisplayMode.setSelectedIndex(getSceneDelegate().getDisplayModeIndex(displayConfiguration.getDisplayMode()));
    }
}
