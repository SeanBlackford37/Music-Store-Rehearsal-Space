package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RepairTechTest {
    @Test
    void constructorTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        assertTrue(employee1.getName().equals("Steve"));
        assertTrue(employee1.getID()== 12345);
        assertTrue(employee1.getPayAmt()==15.00);
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(0, "Steve", store));
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "", store));
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "Steve", -50, store));
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "Steve", 25.555, store));
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "Steve", null));
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "Steve", 25, null));
    }
    

    

    //Integration Test
    @Test 
    void checkInventoryTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech tech= new RepairTech(12345, "Steve", store);
        store.addToInventory(new Item("guitar string", 12));
        assertTrue(tech.checkInventory("guitar string"));
        assertFalse(tech.checkInventory("guitar"));
    }
    
    //Integration Test
    @Test 
    void pullFromInventoryTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech tech= new RepairTech(12345, "Steve", store);
        store.addToInventory(new Item("guitar string", 12));
        tech.pullFromInventory("guitar string");
        assertEquals(0, store.getInventorySize());
        assertThrows(IllegalArgumentException.class, ()-> tech.pullFromInventory("guitar"));
    }


    
}
