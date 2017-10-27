package org.arnoid.damoclus.ui.scene.menu.builder.model;

public class ImageModel extends BaseModel {
    String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ImageModel that = (ImageModel) o;

        return src != null ? src.equals(that.src) : that.src == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (src != null ? src.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "src='" + src + '\'' +
                "; " + super.toString() +
                '}';
    }
}
