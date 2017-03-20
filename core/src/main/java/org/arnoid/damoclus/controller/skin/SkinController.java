package org.arnoid.damoclus.controller.skin;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import org.arnoid.damoclus.R;

public class SkinController implements Disposable {

    private final AssetsController assetsController;
    private Skin skin;

    private static final String IMG_BYTTON_CHECK_BOX_STYLE = "checkbox";

    public SkinController(AssetsController assetsController) {

        this.assetsController = assetsController;

        skin = assetsController.loadSyncAndGet(R.skin.skin, Skin.class);

        ObjectMap<String, BitmapFont> allFonts = skin.getAll(BitmapFont.class);

        for (BitmapFont font : allFonts.values()) {
            font.getData().markupEnabled = true;
        }
    }

    @Override
    public void dispose() {
        skin.dispose();
    }

    public Skin getSkin() {
        return skin;
    }

    public String getImgByttonCheckBoxStyle() {
        return IMG_BYTTON_CHECK_BOX_STYLE;
    }

    public String formatColoredString(String stringToFormat) {
        String result = stringToFormat;
        ObjectMap<String, Color> colors = getSkin().getAll(Color.class);
        for (String colorName : colors.keys()) {
            Color color = colors.get(colorName);
            String stringColor = new StringBuilder("[#")
                    .append(color.toString())
                    .append("]")
                    .toString();

            result = result.replaceAll("\\[#" + colorName + "\\]", stringColor);
        }

        return result;

    }

}
