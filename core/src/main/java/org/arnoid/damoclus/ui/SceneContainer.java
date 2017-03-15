package org.arnoid.damoclus.ui;

import com.badlogic.gdx.Screen;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import java.util.ArrayDeque;
import java.util.Iterator;

public class SceneContainer implements Screen {

    private ArrayDeque<AbstractScene> scenes = new ArrayDeque<>();
    private AbstractScene overlay;

    @Override
    public void show() {
        for (AbstractScene scene : scenes) {
            scene.show();
        }

        if (overlay != null) {
            overlay.show();
        }
    }

    @Override
    public void render(float delta) {
        Iterator<AbstractScene> sceneIterator = scenes.descendingIterator();

        while (sceneIterator.hasNext()) {
            sceneIterator.next().render(delta);
        }

        if (overlay != null) {
            overlay.render(delta);
        }
    }

    public void act(float delta) {
        for (AbstractScene scene : scenes) {
            scene.act(delta);
        }

        if (overlay != null) {
            overlay.act(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        for (AbstractScene scene : scenes) {
            scene.resize(width, height);
        }

        if (overlay != null) {
            overlay.resize(width, height);
        }
    }

    @Override
    public void pause() {
        for (AbstractScene scene : scenes) {
            scene.pause();
        }

        if (overlay != null) {
            overlay.pause();
        }
    }

    @Override
    public void resume() {
        for (AbstractScene scene : scenes) {
            scene.resume();
        }

        if (overlay != null) {
            overlay.resume();
        }
    }

    @Override
    public void hide() {
        for (AbstractScene scene : scenes) {
            scene.hide();
        }

        if (overlay != null) {
            overlay.hide();
        }
    }

    @Override
    public void dispose() {
        for (AbstractScene scene : scenes) {
            scene.dispose();
        }

        if (overlay != null) {
            overlay.dispose();
        }
    }

    public AbstractScene addFirstScene(AbstractScene scene) {
        AbstractScene peekedScene = scenes.peek();

        if (peekedScene != null) {
            peekedScene.pause();
        }

        scenes.push(scene);

        if (overlay == null) {
            scene.resume();
        }

        return scene;
    }

    public void addLastScene(AbstractScene scene) {
        scenes.addLast(scene);
    }

    public void clear() {
        scenes.clear();
    }

    public AbstractScene peek() {
        return scenes.peek();
    }

    public AbstractScene pop() {
        return remove();
    }

    public AbstractScene push(AbstractScene scene) {
        return addFirstScene(scene);
    }

    public void setOverlay(AbstractScene scene) {
        removeOverlay();

        overlay = scene;
        scene.resume();
        scene.show();
    }

    public void removeOverlay() {
        if (overlay != null) {
            overlay.pause();
            overlay.hideAndDispose();

            overlay = null;
        }
    }

    public AbstractScene getOverlay() {
        return overlay;
    }

    public AbstractScene remove() {
        return removeFirst();
    }

    public AbstractScene removeFirst() {
        AbstractScene removedScene = scenes.removeFirst();

        if (removedScene != null) {
            removedScene.pause();
            removedScene.hideAndDispose();
        }

        AbstractScene peekedScene = scenes.peekFirst();

        if (peekedScene != null) {
            peekedScene.resume();
            peekedScene.show();
        }

        return removedScene;
    }

    public AbstractScene removeLast() {
        return scenes.removeLast();
    }

    public void isEmpty() {
        scenes.isEmpty();
    }

    public void size() {
        scenes.size();
    }

    public void iterator() {
        scenes.iterator();
    }


}
