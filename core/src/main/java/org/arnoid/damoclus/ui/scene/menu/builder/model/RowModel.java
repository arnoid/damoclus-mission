package org.arnoid.damoclus.ui.scene.menu.builder.model;

public class RowModel extends TableModel {
    public int colspan;

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RowModel rowModel = (RowModel) o;

        return colspan == rowModel.colspan;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + colspan;
        return result;
    }

    @Override
    public String toString() {
        return "RowModel{" +
                "colspan=" + colspan +
                "; " + super.toString() +
                '}';
    }
}
