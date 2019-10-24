package model;

import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_InstantiatesCorrectly_true() {
        Sighting sighting = new Sighting(1,"Zone A","c4thasavage" );
        assertTrue(sighting instanceof Sighting);
    }

    @Test
    public void sighting_InstantiatesWithAnimalId_true() {
        Sighting sighting = new Sighting(1,"Zone A","c4thasavage" );
        assertEquals(1, sighting.getAnimalId());
    }

    @Test
    public void sighting_checksCorrectlyIfSightingsAreTheSame_true() {
        Sighting sighting = new Sighting(1,"Zone A","c4thasavage" );
        Sighting otherSighting = new Sighting(1,"Zone A","c4thasavage" );
        assertTrue(sighting.equals(otherSighting));
    }

    @Test
    public void save_SavesSightingToDatabase_Sighting() {
        Sighting sighting = new Sighting(1,"Zone A","c4thasavage" );
        sighting.save();
        assertEquals(sighting,Sighting.getAll().get(0));
    }

    @Test
    public void save_recordsTimeOfCreationInDatabase() {
        Sighting sighting = new Sighting(1,"Zone A","c4thasavage" );
        sighting.save();
        Timestamp sighted = Sighting.findById(sighting.getId()).getSighted();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(rightNow.getDay(), sighted.getDay());
    }



    @Test
    public void getAllSightedAnimals_retrievesAllSightedAnimalsFromDatabase_animalList() {
        FaunaSpecies faunaSpecies = new FaunaSpecies("White Rhino", "endangered species"); //Endangered Animal
        faunaSpecies.save();
        FaunaSpecies secondFaunaSpecies = new FaunaSpecies("Lion", "common species"); //Animal
        secondFaunaSpecies.save();
        FaunaSpecies thirdFaunaSpecies = new FaunaSpecies("Cheetah", "endangered species"); //Endangered Animal
        thirdFaunaSpecies.save();
        Animal firstAnimal = new Animal(FaunaSpecies.getAll().get(1).getName(),FaunaSpecies.getAll().get(1).getId());
        firstAnimal.save();
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal(FaunaSpecies.getAll().get(0).getName(),"Old", "Sick",FaunaSpecies.getAll().get(0).getId());
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal(FaunaSpecies.getAll().get(2).getName(),"Young", "Good",FaunaSpecies.getAll().get(2).getId());
        secondEndangeredAnimal.save();
        Object[] faunaCatalogue = new Object[] { firstAnimal, firstEndangeredAnimal, secondEndangeredAnimal};
        Sighting firstSighting = new Sighting(firstAnimal.getAnimalId(),"Zone B", "c4thasavage");
        firstSighting.save();
        Sighting secondSighting = new Sighting(firstEndangeredAnimal.getAnimalId(),"Zone A", "c4thasavage");
        secondSighting.save();
        Sighting thirdSighting = new Sighting(secondEndangeredAnimal.getAnimalId(),"Zone A", "c4thasavage");
        thirdSighting.save();
        assertEquals(Arrays.asList(faunaCatalogue),Sighting.getAllSightedAnimals());
    }

}