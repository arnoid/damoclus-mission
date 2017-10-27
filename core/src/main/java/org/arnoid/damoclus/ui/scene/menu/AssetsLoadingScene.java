package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StringBuilder;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.event.Dispatcher;
import org.arnoid.damoclus.controller.skin.AssetsController;
import org.arnoid.damoclus.logic.delegate.menu.AssetsLoadingSceneDelegate;

import javax.inject.Inject;

public class AssetsLoadingScene extends AbstractMenuScene<AssetsLoadingSceneDelegate> {

    private Label lblConsoleOuput;

    @Inject
    AssetsController assetsController;

    public AssetsLoadingScene(MainComponent component) {
        super();
        component.inject(this);
        init();
    }

    @Override
    protected void produceLayout() {
        setLayout(R.layout.scene_resouce_loading);
    }

    @Override
    public void postProduceLayout() {
        lblConsoleOuput = findLabel(R.id.lbl_console_output);
        lblConsoleOuput.setAlignment(Align.bottomLeft);
        lblConsoleOuput.setWrap(true);
        registerMenuItem(lblConsoleOuput);
    }

    @Override
    public void resume() {
        super.resume();

        DamoclusGdxGame.messageDispatcher().addListener(this, Dispatcher.MessageType.ASSETS_MANAGER_LOAD_RESOURCE_START);
        DamoclusGdxGame.messageDispatcher().addListener(this, Dispatcher.MessageType.ASSETS_MANAGER_LOAD_RESOURCE_STOP);
        DamoclusGdxGame.messageDispatcher().addListener(this, Dispatcher.MessageType.ASSETS_MANAGER_LOAD_RESOURCE_ERROR);

        getStage().addAction(
                Actions.sequence(
                        new AssetLoadingAction(assetsController, R.image.helmet_astronaut_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.helmet_astronomer_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.helmet_biologist_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.helmet_engineer_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.helmet_physicist_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.helmet_semanticist_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.tool_geiger_counter_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.tool_infrared_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.tool_magnetometer, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.tool_ohmmeter_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.tool_radio_receiver_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.tool_recording_equipment_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.tool_simple_tools_logo, Texture.class),
                        new AssetLoadingAction(assetsController, R.image.tool_vision_equipment_logo, Texture.class),
                        Actions.delay(3),
                        new Action() {

                            @Override
                            public boolean act(float delta) {
                                getSceneDelegate().onLoadingComplete();
                                return true;
                            }
                        }
                )
        );
    }

    @Override
    public void pause() {
        super.pause();

        DamoclusGdxGame.messageDispatcher().removeListener(this, Dispatcher.MessageType.ASSETS_MANAGER_LOAD_RESOURCE_START);
        DamoclusGdxGame.messageDispatcher().removeListener(this, Dispatcher.MessageType.ASSETS_MANAGER_LOAD_RESOURCE_STOP);
        DamoclusGdxGame.messageDispatcher().removeListener(this, Dispatcher.MessageType.ASSETS_MANAGER_LOAD_RESOURCE_ERROR);
    }

    public boolean handleMessage(Telegram msg) {
        Dispatcher.MessageType messageType = Dispatcher.MessageType.values()[msg.message];

        StringBuilder stringBuilder = new StringBuilder(lblConsoleOuput.getText());

        switch (messageType) {
            case ASSETS_MANAGER_LOAD_RESOURCE_START:
                //do nothing
                break;
            case ASSETS_MANAGER_LOAD_RESOURCE_ERROR:
                GdxRuntimeException exception = (GdxRuntimeException) msg.extraInfo;
                stringBuilder
                        .append("\nAsset ")
                        .append("[#error]")
                        .append(msg.extraInfo)
                        .append("[]")
                        .append(" ")
                        .append(" was not loaded. Message ")
                        .append(exception.getMessage());
                break;
            case ASSETS_MANAGER_LOAD_RESOURCE_STOP:
                stringBuilder
                        .append("\nAsset ")
                        .append("[#primary]")
                        .append(msg.extraInfo)
                        .append("[]")
                        .append(" ")
                        .append("loaded");
                break;
        }

        lblConsoleOuput.setText(skinController.formatColoredString(stringBuilder.toString()));
        lblConsoleOuput.invalidate();

        return true;
    }

    private static class AssetLoadingAction extends Action {

        private final AssetsController assetsController;
        private final String resource;
        private final Class clazz;

        public AssetLoadingAction(AssetsController assetsController, String resource, Class clazz) {
            this.assetsController = assetsController;
            this.resource = resource;
            this.clazz = clazz;
        }

        @Override
        public boolean act(float delta) {
            assetsController.loadSync(resource, clazz);
            return true;
        }
    }

}
