package edu.ithaca.musicStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThingToBeRepairedTest {
    @Test
    void constructorTest(){ //Unit Test
        ThingToBeRepaired item = new ThingToBeRepaired("Guitar", "Steph", "String broken");
        assertEquals("Guitar", item.getItemName()); //Equivalence class
        assertEquals("Steph", item.getClientName()); //Equivalence class
        assertEquals("String broken", item.getDamageDescription()); //Equivalence class
        assertEquals(true, item.getIsBroken()); //Equivalence class

        assertThrows(IllegalArgumentException.class, ()-> new ThingToBeRepaired(null, "Steve", "Bonk")); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> new ThingToBeRepaired("guitar", null, "Bonk")); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> new ThingToBeRepaired("guitar", "Steve", null)); //Border Case
    }
     
    @Test
    void isBrokenTest(){ //Unit Test
        ThingToBeRepaired item = new ThingToBeRepaired("Guitar", "Steph", "String broken");
        assertEquals(true, item.getIsBroken()); //Equivalence class
        item.setisBroken(false);
        assertEquals(false, item.getIsBroken()); //Equivalence class
    }

    @Test 
    void displayDamageDescriptionTest(){ //Unit Test
        ThingToBeRepaired item = new ThingToBeRepaired("Guitar", "Steph", "String broken");
        System.out.print("Expected: ");
        System.out.println("String broken");
        System.out.print("Actual: ");
        item.displayDamageDescription();

    }

   
}
