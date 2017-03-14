package org.arnoid.damoclus.ui.scene.menu.builder.model;

import java.util.ArrayList;
import java.util.List;

public class TableModel extends BaseModel<TableModel> {
    public List<BaseModel> children = new ArrayList<>();

    public List<BaseModel> getChildren() {
        return children;
    }

    public void setChildren(List<BaseModel> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TableModel that = (TableModel) o;

        return children != null ? children.equals(that.children) : that.children == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TableModel{" +
                "children=" + children +
                "; " + super.toString() +
                '}';
    }
}
