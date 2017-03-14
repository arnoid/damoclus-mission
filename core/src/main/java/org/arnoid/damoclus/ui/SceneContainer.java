package org.arnoid.damoclus.ui;

import com.badlogic.gdx.Screen;
import org.arnoid.damoclus.ui.scene.AbstractScene;

import java.util.ArrayDeque;
import java.util.Iterator;

public class SceneContainer implements Screen {

    private ArrayDeque<AbstractScene> scenes = new ArrayDeque<>();

    @Override
    public void show() {
        for (AbstractScene scene : scenes) {
            scene.show();
        }
    }

    @Override
    public void render(float delta) {
        Iterator<AbstractScene> sceneIterator = scenes.descendingIterator();

        while (sceneIterator.hasNext()) {
            sceneIterator.next().render(delta);
        }
    }

    public void act(float delta) {
        for (AbstractScene scene : scenes) {
            scene.act(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        for (AbstractScene scene : scenes) {
            scene.resize(width, height);
        }
    }

    @Override
    public void pause() {
        for (AbstractScene scene : scenes) {
            scene.pause();
        }
    }

    @Override
    public void resume() {
        for (AbstractScene scene : scenes) {
            scene.resume();
        }
    }

    @Override
    public void hide() {
        for (AbstractScene scene : scenes) {
            scene.hide();
        }
    }

    @Override
    public void dispose() {
        for (AbstractScene scene : scenes) {
            scene.dispose();
        }
    }

    public AbstractScene addFirstScene(AbstractScene scene) {
        AbstractScene peekedScene = scenes.peek();

        if (peekedScene != null) {
            peekedScene.hide();
        }

        scenes.push(scene);

        scene.show();

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

    public AbstractScene remove() {
        return removeFirst();
    }

    public AbstractScene removeFirst() {
        AbstractScene removedScene = scenes.removeFirst();

        if (removedScene != null) {
            removedScene.hide();
        }

        AbstractScene peekedScene = scenes.peekFirst();

        if (peekedScene != null) {
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
