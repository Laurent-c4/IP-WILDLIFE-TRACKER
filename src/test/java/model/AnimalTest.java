package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    @Test
    public void animal_InstantiatesCorrectly_true() {
        Animal animal = new Animal("Cheetah");
        assertTrue(animal instanceof Animal);
    }

    @Test
    public void animal_InstantiatesWithName_true() {
        Animal animal = new Animal("Cheetah");
        assertEquals("Cheetah", animal.getName());
    }

    @Test
    public void animal_checksCorrectlyIfAnimalsAreTheSame_true() {
        Animal animal = new Animal("Cheetah");
        Animal otherAnimal = new Animal("Cheetah");
        assertTrue(animal.equals(otherAnimal));
    }

}