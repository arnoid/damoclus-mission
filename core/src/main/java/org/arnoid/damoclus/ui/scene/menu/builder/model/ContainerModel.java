package org.arnoid.damoclus.ui.scene.menu.builder.model;

public class ContainerModel extends BaseModel {
    public BaseModel child;

    public BaseModel getChild() {
        return child;
    }

    public void setChild(BaseModel child) {
        this.child = child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ContainerModel that = (ContainerModel) o;

        return child != null ? child.equals(that.child) : that.child == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (child != null ? child.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContainerModel{" +
                "child=" + child +
                "; " + super.toString() +
                '}';
    }
}
