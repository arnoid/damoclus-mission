package org.arnoid.damoclus.controller.persistent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.concurrent.Callable;

public abstract class JsonPersistingController<T> {

    private static final String TAG = JsonPersistingController.class.getSimpleName();
    private T cachedInstance;
    private Gson gson;

    public JsonPersistingController() {
        this.gson = new Gson();
    }

    protected abstract Class<T> getContentClass();

    public Callable<T> readCallable() {
        return new Callable<T>() {
            @Override
            public T call() throws Exception {
                return read(true);
            }
        };
    }

    public T read() {
        return read(false);
    }

    public T read(boolean forced) {
        T instance = null;
        if (cachedInstance == null || forced) {

            FileHandle fileHandle = Gdx.files.local(getJsonFilePath());
            if (fileHandle.exists()) {
                Reader fileReader = null;
                try {
                    fileReader = fileHandle.reader();
                    instance = gson.fromJson(fileReader, getContentClass());
                } finally {
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e) {
                            Gdx.app.error(TAG, "Unable to close file reader", e);
                        }
                    }
                }
            }

            if (instance == null) {
                Gdx.app.log(TAG, "Configuration file does not exist. New file will be generated");
                instance = generateDefaultInstance();
            }

            onPostRead(instance);

            cachedInstance = instance;
        }

        return cachedInstance;
    }

    public boolean write(T instance) {
        boolean result = true;
        FileHandle fileHandle = Gdx.files.local(getJsonFilePath());

        if (fileHandle.exists()) {
            //delete file
            fileHandle.delete();
        } else {
            FileHandle parentDir = fileHandle.parent();
            if (parentDir.exists()) {
                //ok
            } else {
                parentDir.mkdirs();
            }

            try {
                fileHandle.file().createNewFile();
            } catch (IOException e) {
                Gdx.app.error(TAG, e.getMessage(), e);
                Gdx.app.error(TAG, "Unable to create file");
                result = false;
            }
        }

        if (result) {
            Writer fileWriter = null;
            try {
                fileWriter = fileHandle.writer(false);
                gson.toJson(instance, fileWriter);
            } finally {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        Gdx.app.error(TAG, "Unable to close file writer", e);
                    }
                }
            }
        }

        onPostWrite(result, instance);

        return result;
    }

    protected abstract void onPostWrite(boolean result, T instance);

    protected abstract void onPostRead(T instance);

    protected abstract T generateDefaultInstance();

    protected abstract String getJsonFilePath();
}
