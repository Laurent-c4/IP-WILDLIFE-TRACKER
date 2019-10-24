package model;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class EndangeredAnimal extends Fauna {
    private String health;
    private String age;
    private int animalId;
    private int sightingId;
    public static final String ANIMAL_TYPE = "endangered species";

    public EndangeredAnimal(String name, String age, String health, int animalId, int sightingId) {
        this.name = name;
        this.age = age;
        this.health = health;
        this.animalId = animalId;
        this.sightingId = sightingId;
        this.type = ANIMAL_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EndangeredAnimal that = (EndangeredAnimal) o;
        return animalId == that.animalId &&
                Objects.equals(health, that.health) &&
                Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), health, age, animalId);
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, age, type, health, animalId, sightingId) VALUES (:name, :age, :type, :health, :animalId, :sightingId);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("type", this.type)
                    .addParameter("health", this.health)
                    .addParameter("animalId", this.animalId)
                    .addParameter("sightingId", this.sightingId)

                    .executeUpdate()
                    .getKey();

        }
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public int getAnimalId() {
        return animalId;
    }

    public static List<EndangeredAnimal> getAll() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where type='endangered species';";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }

    public static EndangeredAnimal findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id ;";
            EndangeredAnimal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return animal;


        }
    }

    public static EndangeredAnimal findBySightingId(int sightingId) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE sightingId=:sightingId ;";
            EndangeredAnimal endangeredAnimal = con.createQuery(sql)
                    .addParameter("sightingId", sightingId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return endangeredAnimal;


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
