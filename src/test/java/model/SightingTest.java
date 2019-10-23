package model;

import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

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
    public void getAllSightedAnimals_retrievesAllSightedAnimalsFromDatabase_animalList() {
        Animal firstAnimal = new Animal("Cheetah");
        firstAnimal.save();
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("White Rhino","Old", "Sick");
        firstEndangeredAnimal.save();
        Object[] faunaCatalogue = new Object[] { firstAnimal, firstEndangeredAnimal};
        Sighting firstSighting = new Sighting(firstAnimal.getId(),"Zone B", "c4thasavage");
        firstSighting.save();
        Sighting secondSighting = new Sighting(firstEndangeredAnimal.getId(),"Zone A", "c4thasavage");
        secondSighting.save();
        assertEquals(Arrays.asList(faunaCatalogue),Sighting.getAllSightedAnimals());
    }

}