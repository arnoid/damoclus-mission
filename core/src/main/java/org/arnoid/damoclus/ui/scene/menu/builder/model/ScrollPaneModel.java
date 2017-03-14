package org.arnoid.damoclus.ui.scene.menu.builder.model;

public class ScrollPaneModel extends ContainerModel {

    public boolean verticalScrollBar;
    public boolean horizontalScrollBar;

    public boolean isVerticalScrollBar() {
        return verticalScrollBar;
    }

    public void setVerticalScrollBar(boolean verticalScrollBar) {
        this.verticalScrollBar = verticalScrollBar;
    }

    public boolean isHorizontalScrollBar() {
        return horizontalScrollBar;
    }

    public void setHorizontalScrollBar(boolean horizontalScrollBar) {
        this.horizontalScrollBar = horizontalScrollBar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ScrollPaneModel that = (ScrollPaneModel) o;

        if (verticalScrollBar != that.verticalScrollBar) return false;
        if (horizontalScrollBar != that.horizontalScrollBar) return false;
        return child != null ? child.equals(that.child) : that.child == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (verticalScrollBar ? 1 : 0);
        result = 31 * result + (horizontalScrollBar ? 1 : 0);
        result = 31 * result + (child != null ? child.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScrollPaneModel{" +
                "verticalScrollBar=" + verticalScrollBar +
                ", horizontalScrollBar=" + horizontalScrollBar +
                ", child=" + child +
                "; " + super.toString() +
                '}';
    }
}
