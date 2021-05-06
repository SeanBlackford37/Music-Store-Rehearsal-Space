package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RepairTechTest {
    @Test
    void constructorTest(){ //Unit Test
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        assertEquals("Steve", employee1.getName()); //Equivalence class
        assertEquals(12345, employee1.getID()); //Equivalence class
        assertEquals(15.00, employee1.getPayAmt()); //Equivalence class
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(0, "Steve", store)); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "", store)); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "Steve", -50, store)); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "Steve", 25.555, store)); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "Steve", null)); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "Steve", 25, null)); //Border Case
    }
    

    

    
    @Test 
    void checkInventoryTest(){ //Integration Test
        MusicStore store = new MusicStore("Place");
        RepairTech tech= new RepairTech(12345, "Steve", store);
        store.addEquipment(new Equipment("guitar string", 12));
        assertTrue(tech.checkEquipInventory("guitar string")); //Equivalence class
        assertFalse(tech.checkEquipInventory("guitar")); //Equivalence class
    }
    
    
    @Test 
    void pullFromInventoryTest(){ //Integration Test
        MusicStore store = new MusicStore("Place");
        RepairTech tech= new RepairTech(12345, "Steve", store);
        store.addEquipment(new Equipment("guitar string", 12));
        tech.pullFromEquipInventory("guitar string");
        assertEquals(0, store.getInventorySize()); //Equivalence class
        assertThrows(IllegalArgumentException.class, ()-> tech.pullFromEquipInventory("guitar")); //Border Case
    }


    @Test
    void addActiveToRepairListTest(){ //Integration Test
        MusicStore store = new MusicStore("Place"); 
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("guitar", "Sam", "bonk"), employee1)); 
        assertEquals("Sam", employee1.getActiveRepairList().get(0).getItem().getClientName()); //Equivalence class
    }

    @Test
    void removeFromRepairListTest(){ //Integration Test
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("guitar", "Sam", "bonk"), employee1));
        employee1.removeFromActiveRepairList("guitar", "Sam");
        assertEquals(0, employee1.getActiveRepairList().size()); //Equivalence class
        assertThrows(IllegalArgumentException.class, ()-> employee1.removeFromActiveRepairList("guitar", "Janet")); //Border Case
        
    }

    @Test
    void findRepairTest(){ //Integration Test
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("guitar", "John", "bonk"), employee1)); //Border Case
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("piano", "Beth", "bonk"), employee1)); //Border Case
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("flute", "John", "bonk"), employee1)); //Border Case
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("guitar", "Sam", "bonk"), employee1)); //Border Case
        assertEquals(0, employee1.findRepair("guitar", "John")); //Equivalence class
        assertEquals(1, employee1.findRepair("piano", "Beth")); //Equivalence class
        assertEquals(2, employee1.findRepair("flute", "John")); //Equivalence class
        assertEquals(3, employee1.findRepair("guitar", "Sam")); //Equivalence class
        assertEquals(-1, employee1.findRepair("guitar", "April")); //Equivalence class
        
    }

    @Test
    void getPriceFromCategoryTest(){ //Unit Test
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store); 
        assertEquals(40, employee1.getPriceFromCategory(1)); //Equivalence class
        assertEquals(40, employee1.getPriceFromCategory(0.1)); //Equivalence class
        assertEquals(40, employee1.getPriceFromCategory(2.99)); //Equivalence class

        assertEquals(60, employee1.getPriceFromCategory(3)); //Equivalence class
        assertEquals(60, employee1.getPriceFromCategory(4)); //Equivalence class
        assertEquals(60, employee1.getPriceFromCategory(4.99)); //Equivalence class
 
        assertEquals(80, employee1.getPriceFromCategory(5)); //Equivalence class
        assertEquals(80, employee1.getPriceFromCategory(6)); //Equivalence class
        assertEquals(80, employee1.getPriceFromCategory(6.99)); //Equivalence class

        assertEquals(100, employee1.getPriceFromCategory(7)); //Equivalence class
        assertEquals(100, employee1.getPriceFromCategory(10)); //Equivalence class
        assertEquals(100, employee1.getPriceFromCategory(24)); //Equivalence class

        assertThrows(IllegalArgumentException.class, ()-> employee1.getPriceFromCategory(0)); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> employee1.getPriceFromCategory(-10)); //Border Case

    }

    //@Test
    void tunerTest(){ //Unit Test
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.tuner();
    }

    
}
