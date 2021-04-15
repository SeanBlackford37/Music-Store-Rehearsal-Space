package edu.ithaca.musicStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThingToBeRepairedTest {
    @Test
    void constructorTest(){
        ThingToBeRepaired item = new ThingToBeRepaired("Guitar", "Steph", "String broken");
        assertEquals("Guitar", item.getItemName());
        assertEquals("Steph", item.getClientName());
        assertEquals("String broken", item.getDamageDescription());
        assertEquals(true, item.getIsBroken());

        assertThrows(IllegalArgumentException.class, ()-> new ThingToBeRepaired(null, "Steve", "Bonk"));
        assertThrows(IllegalArgumentException.class, ()-> new ThingToBeRepaired("guitar", null, "Bonk"));
        assertThrows(IllegalArgumentException.class, ()-> new ThingToBeRepaired("guitar", "Steve", null));
    }
     
    @Test
    void isBrokenTest(){
        ThingToBeRepaired item = new ThingToBeRepaired("Guitar", "Steph", "String broken");
        assertEquals(true, item.getIsBroken());
        item.setisBroken(false);
        assertEquals(false, item.getIsBroken());
    }

    @Test 
    void displayDamageDescriptionTest(){
        ThingToBeRepaired item = new ThingToBeRepaired("Guitar", "Steph", "String broken");
        System.out.print("Expected: ");
        System.out.println("String broken");
        System.out.print("Actual: ");
        item.displayDamageDescription();

    }

   
}
