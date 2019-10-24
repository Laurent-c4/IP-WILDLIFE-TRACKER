import model.*;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        int port;

        // This tells our app that if Heroku sets a port for us, we need to use that port.
        // Otherwise, if they do not, continue using port 4567.

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);

        staticFileLocation("/public");

//        String connectionString = "jdbc:postgresql://localhost:5432/herosquad";
//        Sql2o sql2o = new Sql2o(connectionString, "laurent", "laurent");

//        System.out.println(Sighting.al().size());

        //get: show all species of animals present in the Forest
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<FaunaSpecies> faunaSpeciesList = FaunaSpecies.getAll();
            model.put("species", faunaSpeciesList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to add a new sighting
        get("/sightings/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sightings = Sighting.getAll();
            model.put("sightings", sightings);
            List<FaunaSpecies> faunaSpeciesList = FaunaSpecies.getAll();
            model.put("species", faunaSpeciesList);
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to create a new sighting
        post("/sightings", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            int animalId = Integer.parseInt(req.queryParams("animalId"));
            String age = req.queryParams("age");
            String health = req.queryParams("health");
            String location = req.queryParams("location");
            String rangerName = req.queryParams("rangerName");
            Sighting newSighting = new Sighting(animalId, location,rangerName);
            newSighting.save();
            System.out.println("THIssssssss" + newSighting.getId());
            List<FaunaSpecies> faunaSpeciesList = FaunaSpecies.getAll();
            if (FaunaSpecies.findById(animalId).getType().equals("endangered species")){
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(FaunaSpecies.findById(animalId).getName(),age,health, FaunaSpecies.findById(animalId).getId(), newSighting.getId());
                endangeredAnimal.save();
            }else {
                Animal animal = new Animal(FaunaSpecies.findById(animalId).getName(),FaunaSpecies.findById(animalId).getId(),newSighting.getId());
                animal.save();
            }

            res.redirect("/sightings/all");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show all sightings
        get("/sightings/all", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sightingList = Sighting.getAll();
            model.put("sightings", sightingList);
            List<Fauna> allAnimalsSighted=Sighting.getAllSightedAnimals();
            model.put("allAnimalsSighted",allAnimalsSighted);
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete a sighting by id
        get("/sightings/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSightingToDelete = Integer.parseInt(req.params("id"));
            Sighting sightingToDelete = Sighting.findById(idOfSightingToDelete);
            Sighting.deleteById(idOfSightingToDelete);
            EndangeredAnimal.deleteBySightingId(idOfSightingToDelete);
            Animal.deleteBySightingId(idOfSightingToDelete);
            res.redirect("/sightings/all");
            return null;
        }, new HandlebarsTemplateEngine());



    }
}
