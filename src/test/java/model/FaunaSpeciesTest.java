package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class FaunaSpeciesTest {
    @Test
    public void save_SavesAnimalToDatabase_Animal() {
        FaunaSpecies faunaSpecies = new FaunaSpecies("Cheetah","common species");
        faunaSpecies.save();
        assertEquals(faunaSpecies,FaunaSpecies.getAll().get(0));
    }



}