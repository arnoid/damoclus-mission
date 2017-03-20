package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.data.configuration.DisplayConfiguration;
import org.arnoid.damoclus.logic.delegate.menu.VideoMenuSceneDelegate;
import org.arnoid.damoclus.ui.scene.menu.dialog.MessageDialog;
import org.arnoid.damoclus.ui.view.SelectList;

public class VideoMenuScene extends AbstractMenuScene<VideoMenuSceneDelegate> {

    private static final String TAG = VideoMenuScene.class.getSimpleName();
    private ImageButton chkFullscreen;
    private SelectList<Graphics.DisplayMode> slctDisplayMode;
    private SelectList<String> slctTextureQuality;

    public VideoMenuScene(MainComponent component) {
        super();
        component.inject(this);
        init();
    }

    @Override
    protected void produceLayout() {
        setLayout(R.layout.menu_video);
    }

    @Override
    public void postProduceLayout() {
        chkFullscreen = (ImageButton) findActor(R.id.menu_video_chk_fullscreen);
        slctDisplayMode = (SelectList<Graphics.DisplayMode>) findActor(R.id.menu_video_selectbox_resolution);
        slctTextureQuality = (SelectList<String>) findActor(R.id.menu_video_selectbox_texture_quality);

        registerMenuItem(slctTextureQuality);
        registerMenuItem(chkFullscreen);
        registerMenuItem(slctDisplayMode);
        registerMenuItem(findButton(R.id.btn_back));
        registerMenuItem(findButton(R.id.btn_apply));
    }

    @Override
    public void onInteract(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case R.id.menu_video_chk_fullscreen:
                chkFullscreen.toggle();
                break;
            case R.id.btn_back:
                getSceneDelegate().onBack();
                break;
            case R.id.btn_apply:
                applyChanges();
                break;
            case R.id.menu_video_selectbox_resolution:
                slctDisplayMode.show(this);
                break;
            case R.id.menu_video_selectbox_texture_quality:
                slctTextureQuality.show(this);
                break;
        }
    }

    @Override
    public void onBack() {
        super.onBack();
        getSceneDelegate().onBack();
    }

    private void applyChanges() {
        getSceneDelegate().apply(chkFullscreen.isChecked(), slctDisplayMode.getSelected(), getSelectedTextureQuality());
        new MessageDialog(R.string.dialog_title_warning, getSkin(), R.string.dialog_texture_quality_change_message) {
            @Override
            public void onPositive(MessageDialog messageDialog) {
                messageDialog.hide();
            }
        }
                .positive(R.string.dialog_texture_quality_change_btn_ok)
                .show(this);
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

        String[] textureQualityLabels = produceTextureQualityLabels();

        slctTextureQuality.setItems(textureQualityLabels);

        DisplayConfiguration.TextureQuality textureQuality = displayConfiguration.getTextureQuality();
        slctTextureQuality.setSelected(getLabelForTextureQuality(textureQuality));

    }

    private String[] produceTextureQualityLabels() {
        DisplayConfiguration.TextureQuality[] textureQualities = DisplayConfiguration.TextureQuality.values();
        String[] labels = new String[textureQualities.length];

        for (int i = 0; i < textureQualities.length; i++) {
            labels[i] = getLabelForTextureQuality(textureQualities[i]);
        }

        return labels;
    }

    private String getLabelForTextureQuality(DisplayConfiguration.TextureQuality textureQuality) {
        String label;
        switch (textureQuality) {
            case mdpi:
                label = stringsController.string(R.string.texture_quality_mdpi);
                break;
            case hdpi:
                label = stringsController.string(R.string.texture_quality_hdpi);
                break;
            case xhdpi:
                label = stringsController.string(R.string.texture_quality_xhdpi);
                break;
            case xxhdpi:
                label = stringsController.string(R.string.texture_quality_xxhdpi);
                break;
            case xxxhdpi:
                label = stringsController.string(R.string.texture_quality_xxxhdpi);
                break;
            default:
                label = textureQuality.name();
        }
        return label;
    }

    public DisplayConfiguration.TextureQuality getSelectedTextureQuality() {
        String selected = slctTextureQuality.getSelected();

        String[] labels = produceTextureQualityLabels();

        for (int i = 0; i < labels.length; i++) {

            if (labels[i].equals(selected)) {
                return DisplayConfiguration.TextureQuality.values()[i];
            }
        }

        return DisplayConfiguration.TextureQuality.mdpi;

    }
}
