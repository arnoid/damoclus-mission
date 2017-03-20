package org.arnoid.damoclus.controller.skin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import org.arnoid.damoclus.DamoclusGdxGame;
import org.arnoid.damoclus.controller.event.Dispatcher;
import org.arnoid.damoclus.data.configuration.DisplayConfiguration;

public class AssetsController implements Disposable {

    private static final String TAG = AssetsController.class.getSimpleName();

    private static final String PATH_INTERNAL = "%s";

    private final AssetManager assetManager;
    private final ResolutionFileResolver resolutionFileResolver;

    public AssetsController() {
        Graphics.DisplayMode[] displayModes = Gdx.graphics.getDisplayModes();
        ResolutionFileResolver.Resolution[] resolutions = new ResolutionFileResolver.Resolution[displayModes.length];

        for (int i = 0; i < displayModes.length; i++) {
            DisplayConfiguration.TextureQuality textureQuality;

            Graphics.DisplayMode displayMode = displayModes[i];

            if (displayMode.width < 800) {
                textureQuality = DisplayConfiguration.TextureQuality.mdpi;
            } else if (displayMode.width < 1280) {
                textureQuality = DisplayConfiguration.TextureQuality.hdpi;
            } else if (displayMode.width < 1680) {
                textureQuality = DisplayConfiguration.TextureQuality.xhdpi;
            } else if (displayMode.width < 2560) {
                textureQuality = DisplayConfiguration.TextureQuality.xxhdpi;
            } else {
                textureQuality = DisplayConfiguration.TextureQuality.xxxhdpi;
            }

            ResolutionFileResolver.Resolution resolution = new ResolutionFileResolver.Resolution(displayMode.width, displayMode.height, String.format(PATH_INTERNAL, textureQuality.name()));

            resolutions[i] = resolution;
        }

        resolutionFileResolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions) {
            private static final String RESOLUTION_TAG = "\\{resolution\\}";

            @Override
            public FileHandle resolve(String fileName) {
                Resolution bestResolution = chooseResolution(descriptors);
                FileHandle originalHandle = new FileHandle(fileName);
                FileHandle handle = baseResolver.resolve(resolve(originalHandle, bestResolution.folder));
                if (!handle.exists()) handle = baseResolver.resolve(fileName);
                return handle;
            }

            @Override
            protected String resolve(FileHandle originalHandle, String suffix) {
                return originalHandle.file().getAbsolutePath().replaceAll(RESOLUTION_TAG, suffix);
            }

            public Resolution chooseResolution(Resolution... descriptors) {
                Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
                int w = displayMode.width, h = displayMode.height;

                // Prefer the shortest side.
                Resolution best = descriptors[0];
                if (w < h) {
                    for (int i = 0, n = descriptors.length; i < n; i++) {
                        Resolution other = descriptors[i];
                        if (w >= other.portraitWidth && other.portraitWidth >= best.portraitWidth && h >= other.portraitHeight
                                && other.portraitHeight >= best.portraitHeight) best = descriptors[i];
                    }
                } else {
                    for (int i = 0, n = descriptors.length; i < n; i++) {
                        Resolution other = descriptors[i];
                        if (w >= other.portraitHeight && other.portraitHeight >= best.portraitHeight && h >= other.portraitWidth
                                && other.portraitWidth >= best.portraitWidth) best = descriptors[i];
                    }
                }
                return best;
            }
        };

        assetManager = new AssetManager(resolutionFileResolver);
    }

    public <T> T loadSyncAndGet(String resource, Class<T> clazz) {
        loadSync(resource, clazz);
        return assetManager.get(resource, clazz);
    }

    public <T> void loadSync(String resource, Class<T> clazz) {
        DamoclusGdxGame.messageDispatcher().dispatchMessage(Dispatcher.MessageType.ASSETS_MANAGER_LOAD_RESOURCE_START, resource);
        try {
            assetManager.load(resource, clazz);
            assetManager.finishLoadingAsset(resource);
            DamoclusGdxGame.messageDispatcher().dispatchMessage(Dispatcher.MessageType.ASSETS_MANAGER_LOAD_RESOURCE_STOP, resource);
        } catch (GdxRuntimeException e) {
            Gdx.app.error(TAG, e.getMessage(), e);
            DamoclusGdxGame.messageDispatcher().dispatchMessage(Dispatcher.MessageType.ASSETS_MANAGER_LOAD_RESOURCE_ERROR, e);
        }
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

}
