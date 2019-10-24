package model;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void endangeredAnimal_InstantiatesCorrectly_true() {
        EndangeredAnimal animal = new EndangeredAnimal("Cheetah","Young", "Okay",2);
        assertTrue(animal instanceof EndangeredAnimal);
    }

    @Test
    public void endangeredAnimal_InstantiatesWithName_true() {
        EndangeredAnimal animal = new EndangeredAnimal("Cheetah","Young", "Okay",2);
        assertEquals("Cheetah", animal.getName());
    }

    @Test
    public void animal_checksCorrectlyIfAnimalsAreTheSame_true() {
        EndangeredAnimal animal = new EndangeredAnimal("Cheetah","Young", "Okay",2);
        EndangeredAnimal otherAnimal = new EndangeredAnimal("Cheetah","Young", "Okay",2);
        assertTrue(animal.equals(otherAnimal));
    }

    @Test
    public void save_SavesAnimalToDatabase_Animal() {
        EndangeredAnimal animal = new EndangeredAnimal("Cheetah","Young","Okay",2);
        animal.save();
        assertEquals(animal,EndangeredAnimal.getAll().get(0));
    }

    @Test
    public void find_returnsEndangeredAnimalWithSameId_secondAnimal() {
        EndangeredAnimal firstAnimal = new EndangeredAnimal("Cheetah","Young","Okay",2);
        firstAnimal.save();
        EndangeredAnimal secondAnimal = new EndangeredAnimal("Cheetah","Young","Okay",2);
        secondAnimal.save();
        assertEquals(EndangeredAnimal.findById(secondAnimal.getId()), secondAnimal);
    }
}