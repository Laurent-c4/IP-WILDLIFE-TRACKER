package model;

import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimal extends Fauna {
    private String health;
    private String age;
    public static final String ANIMAL_TYPE = "endangered species";

    public EndangeredAnimal(String name, String age, String health){
        this.name=name;
        this.age=age;
        this.health=health;
        this.type=ANIMAL_TYPE;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, age, type, health) VALUES (:name, :age, :type, :health);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("type", this.type)
                    .addParameter("health", this.health)
                    .executeUpdate()
                    .getKey();

        }
    }

    public static List<EndangeredAnimal> getAll() {
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals where type='endangered species';";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }
}
