package model;

import org.sql2o.Connection;

import java.util.List;

public class FaunaSpecies extends Fauna {
    public FaunaSpecies(String name, String type) {
        this.name = name;
        this.type = type;
    }


    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO fauna_species (name, type) VALUES (:name, :type);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<FaunaSpecies> getAll() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM fauna_species;";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(FaunaSpecies.class);
        }
    }

    public static FaunaSpecies findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM fauna_species WHERE id=:id ;";
            FaunaSpecies faunaSpecies = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(FaunaSpecies.class);
            return faunaSpecies;


        }
    }


}
