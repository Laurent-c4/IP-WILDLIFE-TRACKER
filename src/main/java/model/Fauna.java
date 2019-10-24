package model;

import java.util.Objects;

public abstract class Fauna {
    public String name;
    public int id;
    public String type;

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fauna fauna = (Fauna) o;
        return Objects.equals(name, fauna.name) &&
                Objects.equals(type, fauna.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
