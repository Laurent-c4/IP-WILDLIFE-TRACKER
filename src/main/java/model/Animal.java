package model;

import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Animal extends Fauna {
    public static final String ANIMAL_TYPE = "common species";
    private int animalId;

    public Animal(String name, int animalId) {
        this.name = name;
        this.type = ANIMAL_TYPE;
        this.animalId=animalId;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, type, animalId) VALUES (:name, :type, :animalId);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .addParameter("animalId", this.animalId)
                    .executeUpdate()
                    .getKey();

        }
    }

    public int getAnimalId() {
        return animalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Animal animal = (Animal) o;
        return animalId == animal.animalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), animalId);
    }

    public static List<Animal> getAll() {
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals WHERE type='common species';";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }

    public static Animal findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id ;";
            Animal animal= con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
            return animal;


        }
    }
}
