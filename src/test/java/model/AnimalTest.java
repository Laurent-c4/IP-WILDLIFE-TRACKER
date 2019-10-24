package model;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_InstantiatesCorrectly_true() {
        Animal animal = new Animal("Cheetah",2);
        assertTrue(animal instanceof Animal);
    }

    @Test
    public void animal_InstantiatesWithName_true() {
        Animal animal = new Animal("Cheetah",2);
        assertEquals("Cheetah", animal.getName());
    }

    @Test
    public void animal_checksCorrectlyIfAnimalsAreTheSame_true() {
        Animal animal = new Animal("Cheetah",2);
        Animal otherAnimal = new Animal("Cheetah",2);
        assertTrue(animal.equals(otherAnimal));
    }

    @Test
    public void save_SavesAnimalToDatabase_Animal() {
        Animal animal = new Animal("Cheetah",2);
        animal.save();
        assertEquals(animal,Animal.getAll().get(0));
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal firstAnimal = new Animal("Cheetah",2);
        firstAnimal.save();
        Animal secondAnimal = new Animal("Lion",2);
        secondAnimal.save();
        assertEquals(Animal.findById(secondAnimal.getId()), secondAnimal);
    }

}