package org.arnoid.damoclus.ui.scene.menu.builder.model;

public class WindowModel extends TableModel {

    public boolean pack = false;
    public boolean movable = false;
    public boolean modal = false;
    public boolean fillParent = false;
    public String title = "";

    public WindowModel x(float x) {
        this.x = x;
        return this;
    }

    public WindowModel y(float y) {
        this.y = y;
        return this;
    }

    public WindowModel pack() {
        this.pack = true;
        return this;
    }

    public WindowModel movable() {
        this.movable = true;
        return this;
    }

    public WindowModel modal() {
        this.modal = true;
        return this;
    }

    public WindowModel fillParent() {
        this.fillParent = true;
        return this;
    }

    public boolean isPack() {
        return pack;
    }

    public void setPack(boolean pack) {
        this.pack = pack;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public boolean isModal() {
        return modal;
    }

    public void setModal(boolean modal) {
        this.modal = modal;
    }

    @Override
    public boolean isFillParent() {
        return fillParent;
    }

    @Override
    public void setFillParent(boolean fillParent) {
        this.fillParent = fillParent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WindowModel that = (WindowModel) o;

        if (pack != that.pack) return false;
        if (movable != that.movable) return false;
        if (modal != that.modal) return false;
        if (fillParent != that.fillParent) return false;
        return title != null ? title.equals(that.title) : that.title == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (pack ? 1 : 0);
        result = 31 * result + (movable ? 1 : 0);
        result = 31 * result + (modal ? 1 : 0);
        result = 31 * result + (fillParent ? 1 : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WindowModel{" +
                "pack=" + pack +
                ", movable=" + movable +
                ", modal=" + modal +
                ", fillParent=" + fillParent +
                ", title='" + title + '\'' +
                "; " + super.toString() +
                '}';
    }
}
