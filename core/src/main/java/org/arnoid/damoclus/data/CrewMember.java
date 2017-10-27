package org.arnoid.damoclus.data;

public class CrewMember {
    String name;
    String typeId;
    String icon;
    String abbreviation;
    String description;
    int min;
    int max;
    float strength;
    boolean scientist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrewMember that = (CrewMember) o;

        if (min != that.min) return false;
        if (max != that.max) return false;
        if (Float.compare(that.strength, strength) != 0) return false;
        if (scientist != that.scientist) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (abbreviation != null ? !abbreviation.equals(that.abbreviation) : that.abbreviation != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (abbreviation != null ? abbreviation.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + min;
        result = 31 * result + max;
        result = 31 * result + (strength != +0.0f ? Float.floatToIntBits(strength) : 0);
        result = 31 * result + (scientist ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "name='" + name + '\'' +
                ", typeId='" + typeId + '\'' +
                ", icon='" + icon + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", description='" + description + '\'' +
                ", min=" + min +
                ", max=" + max +
                ", strength=" + strength +
                ", scientist=" + scientist +
                '}';
    }
}
