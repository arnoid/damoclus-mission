package org.arnoid.damoclus.data;

import java.util.List;

public class Tool {
    String name;
    String typeId;
    String icon;
    String description;
    float weight;
    int min;
    int max;
    String type;
    List<String> mayUse;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tool tool = (Tool) o;

        if (Float.compare(tool.weight, weight) != 0) return false;
        if (min != tool.min) return false;
        if (max != tool.max) return false;
        if (name != null ? !name.equals(tool.name) : tool.name != null) return false;
        if (typeId != null ? !typeId.equals(tool.typeId) : tool.typeId != null) return false;
        if (icon != null ? !icon.equals(tool.icon) : tool.icon != null) return false;
        if (description != null ? !description.equals(tool.description) : tool.description != null) return false;
        if (type != null ? !type.equals(tool.type) : tool.type != null) return false;
        return mayUse != null ? mayUse.equals(tool.mayUse) : tool.mayUse == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        result = 31 * result + min;
        result = 31 * result + max;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (mayUse != null ? mayUse.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "name='" + name + '\'' +
                ", typeId='" + typeId + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", min=" + min +
                ", max=" + max +
                ", type='" + type + '\'' +
                ", mayUse=" + mayUse +
                '}';
    }
}
