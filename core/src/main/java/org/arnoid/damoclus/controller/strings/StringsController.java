package org.arnoid.damoclus.controller.strings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.arnoid.damoclus.R;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

public class StringsController implements Disposable {

    private static final String TAG = StringsController.class.getSimpleName();

    private final Gson gson;
    private Map<String, String> stringsMap;

    public StringsController(Locale locale) {
        gson = new Gson();
        loadLocale(locale);
    }

    public void loadLocale(Locale locale) {
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Reader reader = Gdx.files.internal(getLocalePath(locale)).reader(Charset.defaultCharset().displayName());
        stringsMap = gson.fromJson(reader, type);

        try {
            reader.close();
        } catch (IOException e) {
            Gdx.app.error(TAG, e.getMessage(), e);
        }
    }

    private String getLocalePath(Locale locale) {
        return "locale/" + locale.getLanguage() + "/strings.json";
    }

    @Override
    public void dispose() {
    }

    public String string(String key) {
        String value = StringRefUtil.stringByRef(stringsMap, key);
        if (value == null) {
            return key;
        } else {
            return value;
        }
    }

}
