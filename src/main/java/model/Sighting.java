package model;

import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.*;

public class Sighting {
    private int id;
    private String location;
    private String rangerName;
    private int animalId;
    private Timestamp sighted;

    public Timestamp getSighted() {
        return sighted;
    }

    public String getSightedString() {
        return String.format("%1$TD %1$TR",sighted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id &&
                animalId == sighting.animalId &&
                Objects.equals(location, sighting.location) &&
                Objects.equals(rangerName, sighting.rangerName) &&
                Objects.equals(sighted, sighting.sighted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, rangerName, animalId, sighted);
    }

    public Sighting(int animalId, String location, String rangerName) {
        this.location = location;
        this.rangerName = rangerName;
        this.animalId = animalId;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public int getAnimalId() {
        return animalId;
    }


    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalId, location, rangerName, sighted) VALUES (:animalId, :location, :rangerName, now());";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("rangerName", this.rangerName)
                    .executeUpdate()
                    .getKey();

        }

    }

    public static Sighting findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id=:id ;";
            Sighting sighting= con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;


        }
    }

    public static List<Sighting> getAll() {
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sightings;";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
        }
    }

    public static List<EndangeredAnimal>  getAllEndangeredAnimals() {
       return EndangeredAnimal.getAll();
    }

    public static List<Animal> getAllAnimals() {
        return Animal.getAll();
    }

    public static List<Fauna> getAllSightedAnimals() {
        List<Fauna> allSightedAnimals = new ArrayList<Fauna>();
        try{for (int i=0; i < getAllAnimals().size(); i++){
            allSightedAnimals.add(getAllAnimals().get(i));
        }
        for (int i=0; i < getAllEndangeredAnimals().size(); i++){
            allSightedAnimals.add(getAllEndangeredAnimals().get(i));
        }}catch (Exception e){}

        Collections.sort(allSightedAnimals, new SortById());

        return allSightedAnimals;
    }


}
