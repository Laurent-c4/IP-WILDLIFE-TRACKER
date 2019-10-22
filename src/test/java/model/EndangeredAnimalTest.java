package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Test
    public void endangeredAnimal_InstantiatesCorrectly_true() {
        EndangeredAnimal animal = new EndangeredAnimal("Cheetah","Young", "Okay");
        assertTrue(animal instanceof EndangeredAnimal);
    }

    @Test
    public void endangeredAnimal_InstantiatesWithName_true() {
        EndangeredAnimal animal = new EndangeredAnimal("Cheetah","Young", "Okay");
        assertEquals("Cheetah", animal.getName());
    }

    @Test
    public void animal_checksCorrectlyIfAnimalsAreTheSame_true() {
        EndangeredAnimal animal = new EndangeredAnimal("Cheetah","Young", "Okay");
        EndangeredAnimal otherAnimal = new EndangeredAnimal("Cheetah","Young", "Okay");
        assertTrue(animal.equals(otherAnimal));
    }

    @Test
    public void save_SavesAnimalToDatabase_Animal() {
        EndangeredAnimal animal = new EndangeredAnimal("Cheetah","Young","Okay");
        animal.save();
        assertEquals(animal,EndangeredAnimal.getAll().get(0));
    }
}