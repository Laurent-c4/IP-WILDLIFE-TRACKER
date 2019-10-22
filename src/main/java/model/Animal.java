package model;

import org.sql2o.Connection;

import java.util.List;

public class Animal extends Fauna {
    public static final String ANIMAL_TYPE = "common species";

    public Animal(String name) {
        this.name = name;
        this.type = ANIMAL_TYPE;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, type) VALUES (:name, :type);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();

        }
    }

    public static List<Animal> getAll() {
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals where type='common species';";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }
}
