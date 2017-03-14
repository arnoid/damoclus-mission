package org.arnoid.damoclus.ui.scene.menu.builder.model;

public class CheckBoxModel extends BaseModel {

    public boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CheckBoxModel that = (CheckBoxModel) o;

        return checked == that.checked;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (checked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CheckBoxModel{" +
                "checked=" + checked +
                "; " + super.toString() +
                '}';
    }
}
