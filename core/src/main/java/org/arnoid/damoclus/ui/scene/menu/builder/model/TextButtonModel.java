package org.arnoid.damoclus.ui.scene.menu.builder.model;

public class TextButtonModel extends BaseModel {

    public String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TextButtonModel that = (TextButtonModel) o;

        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TextButtonModel{" +
                "text='" + text + '\'' +
                "; " + super.toString() +
                '}';
    }
}
