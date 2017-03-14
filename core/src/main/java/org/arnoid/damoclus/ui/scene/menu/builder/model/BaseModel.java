package org.arnoid.damoclus.ui.scene.menu.builder.model;

import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.ui.scene.menu.builder.XmlMenuSceneBuilder;

public abstract class BaseModel<T extends BaseModel> {

    public enum Grow {
        Grow,
        GrowY,
        GrowX,
        None
    }

    public XmlMenuSceneBuilder.ActorType actorType;
    public String name;
    public boolean debug;
    public int align = Align.center;
    public float width = 0;
    public float height = 0;
    public float padLeft = 0;
    public float padRight = 0;
    public float padBottom = 0;
    public float padTop = 0;

    public float x = 0;
    public float y = 0;

    public boolean expandX = false;
    public boolean expandY = false;

    public boolean fillX;
    public boolean fillY;

    public boolean fillParent;

    public Grow grow = Grow.None;

    public T pad(float pad) {
        this.padLeft = pad;
        this.padRight = pad;
        this.padBottom = pad;
        this.padTop = pad;
        return (T) this;
    }

    public T padLeft(float pad) {
        this.padLeft = pad;
        return (T) this;
    }

    public T padRight(float pad) {
        this.padRight = pad;
        return (T) this;
    }

    public T padBottom(float pad) {
        this.padBottom = pad;
        return (T) this;
    }

    public T padTop(float pad) {
        this.padTop = pad;
        return (T) this;
    }

    public T align(int align) {
        this.align = align;
        return (T) this;
    }

    public T width(float prefWidth) {
        this.width = prefWidth;
        return (T) this;
    }

    public T height(float prefHeight) {
        this.height = prefHeight;
        return (T) this;
    }

    public T grow(Grow grow) {
        this.grow = grow;
        return (T) this;
    }

    public T expandX() {
        this.expandX = true;
        return (T) this;
    }

    public T expandY() {
        this.expandY = true;
        return (T) this;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public XmlMenuSceneBuilder.ActorType getActorType() {
        return actorType;
    }

    public void setActorType(XmlMenuSceneBuilder.ActorType actorType) {
        this.actorType = actorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public int getAlign() {
        return align;
    }

    public void setAlign(int align) {
        this.align = align;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getPadLeft() {
        return padLeft;
    }

    public void setPadLeft(float padLeft) {
        this.padLeft = padLeft;
    }

    public float getPadRight() {
        return padRight;
    }

    public void setPadRight(float padRight) {
        this.padRight = padRight;
    }

    public float getPadBottom() {
        return padBottom;
    }

    public void setPadBottom(float padBottom) {
        this.padBottom = padBottom;
    }

    public float getPadTop() {
        return padTop;
    }

    public void setPadTop(float padTop) {
        this.padTop = padTop;
    }

    public boolean isExpandX() {
        return expandX;
    }

    public void setExpandX(boolean expandX) {
        this.expandX = expandX;
    }

    public boolean isExpandY() {
        return expandY;
    }

    public void setExpandY(boolean expandY) {
        this.expandY = expandY;
    }

    public boolean isFillX() {
        return fillX;
    }

    public void setFillX(boolean fillX) {
        this.fillX = fillX;
    }

    public boolean isFillY() {
        return fillY;
    }

    public void setFillY(boolean fillY) {
        this.fillY = fillY;
    }

    public boolean isFillParent() {
        return fillParent;
    }

    public void setFillParent(boolean fillParent) {
        this.fillParent = fillParent;
    }

    public Grow getGrow() {
        return grow;
    }

    public void setGrow(Grow grow) {
        this.grow = grow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseModel<?> baseModel = (BaseModel<?>) o;

        if (debug != baseModel.debug) return false;
        if (align != baseModel.align) return false;
        if (Float.compare(baseModel.width, width) != 0) return false;
        if (Float.compare(baseModel.height, height) != 0) return false;
        if (Float.compare(baseModel.padLeft, padLeft) != 0) return false;
        if (Float.compare(baseModel.padRight, padRight) != 0) return false;
        if (Float.compare(baseModel.padBottom, padBottom) != 0) return false;
        if (Float.compare(baseModel.padTop, padTop) != 0) return false;
        if (Float.compare(baseModel.x, x) != 0) return false;
        if (Float.compare(baseModel.y, y) != 0) return false;
        if (expandX != baseModel.expandX) return false;
        if (expandY != baseModel.expandY) return false;
        if (fillX != baseModel.fillX) return false;
        if (fillY != baseModel.fillY) return false;
        if (fillParent != baseModel.fillParent) return false;
        if (actorType != baseModel.actorType) return false;
        if (name != null ? !name.equals(baseModel.name) : baseModel.name != null) return false;
        return grow == baseModel.grow;
    }

    @Override
    public int hashCode() {
        int result = actorType != null ? actorType.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (debug ? 1 : 0);
        result = 31 * result + align;
        result = 31 * result + (width != +0.0f ? Float.floatToIntBits(width) : 0);
        result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
        result = 31 * result + (padLeft != +0.0f ? Float.floatToIntBits(padLeft) : 0);
        result = 31 * result + (padRight != +0.0f ? Float.floatToIntBits(padRight) : 0);
        result = 31 * result + (padBottom != +0.0f ? Float.floatToIntBits(padBottom) : 0);
        result = 31 * result + (padTop != +0.0f ? Float.floatToIntBits(padTop) : 0);
        result = 31 * result + (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (expandX ? 1 : 0);
        result = 31 * result + (expandY ? 1 : 0);
        result = 31 * result + (fillX ? 1 : 0);
        result = 31 * result + (fillY ? 1 : 0);
        result = 31 * result + (fillParent ? 1 : 0);
        result = 31 * result + (grow != null ? grow.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "actorType=" + actorType +
                ", name='" + name + '\'' +
                ", debug=" + debug +
                ", align=" + align +
                ", width=" + width +
                ", height=" + height +
                ", padLeft=" + padLeft +
                ", padRight=" + padRight +
                ", padBottom=" + padBottom +
                ", padTop=" + padTop +
                ", x=" + x +
                ", y=" + y +
                ", expandX=" + expandX +
                ", expandY=" + expandY +
                ", fillX=" + fillX +
                ", fillY=" + fillY +
                ", fillParent=" + fillParent +
                ", grow=" + grow +
                '}';
    }
}
