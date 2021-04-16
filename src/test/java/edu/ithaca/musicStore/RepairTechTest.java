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

    Test
    void addActiveToRepairListTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("guitar", "Sam", "bonk"), employee1));
        assertEquals("Sam", employee1.getActiveRepairList().get(0).getClientName());
    }

    @Test
    void removeFromRepairListTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.addToRepairList(new Item("guitar", 12, "John"));
        employee1.removeFromRepairList("guitar", "John");
        assertEquals(0, employee1.getRepairList().size());
        
    }

    @Test
    void findRepairTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.addToRepairList(new Item("guitar", 12, "John"));
        employee1.addToRepairList(new Item("piano", 12, "Beth"));
        employee1.addToRepairList(new Item("flute", 12, "John"));
        employee1.addToRepairList(new Item("guitar", 12, "Sam"));
        assertEquals(0, employee1.findRepair("guitar", "John"));
        assertEquals(1, employee1.findRepair("piano", "Beth"));
        assertEquals(2, employee1.findRepair("flute", "John"));
        assertEquals(3, employee1.findRepair("guitar", "Sam"));
        assertEquals(-1, employee1.findRepair("guitar", "April"));
        
    }

    
}
