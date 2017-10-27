package org.arnoid.damoclus.ui.scene.menu.builder.model;

import java.util.ArrayList;
import java.util.List;

public class StackModel extends BaseModel {

    public List<BaseModel> children;

    public StackModel() {
        children = new ArrayList<>();
    }

    public StackModel(List<BaseModel> children) {
        this.children = new ArrayList<>(children);
    }

    public List<BaseModel> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StackModel that = (StackModel) o;

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
        return "StackModel{" +
                "children=" + children +
                '}';
    }
}
