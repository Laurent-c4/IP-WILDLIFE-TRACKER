package model;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Animal extends Fauna {
    public static final String ANIMAL_TYPE = "common species";
    private int animalId;
    private int sightingId;

    public Animal(String name, int animalId, int sightingId) {
        this.name = name;
        this.type = ANIMAL_TYPE;
        this.animalId = animalId;
        this.sightingId = sightingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Animal animal = (Animal) o;
        return animalId == animal.animalId &&
                sightingId == animal.sightingId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), animalId, sightingId);
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, type, animalId, sightingId) VALUES (:name, :type, :animalId, :sightingId);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .addParameter("animalId", this.animalId)
                    .addParameter("sightingId", this.sightingId)
                    .executeUpdate()
                    .getKey();

        }
    }

    public int getSightingId() {
        return sightingId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public static List<Animal> getAll() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE type='common species';";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }

    public static Animal findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id ;";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
            return animal;


        }
    }

    public static Animal findBySightingId(int sightingId) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE sightingId=:sightingId ;";
            Animal animal = con.createQuery(sql)
                    .addParameter("sightingId", sightingId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
            return animal;


        }
    }

    public static void deleteBySightingId(int sightingId) {
        String sql = "DELETE from animals WHERE sightingId=:sightingId";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("sightingId", sightingId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
