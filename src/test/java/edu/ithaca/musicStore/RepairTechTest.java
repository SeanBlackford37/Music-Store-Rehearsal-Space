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
        store.addEquipment(new Equipment("guitar string", 12));
        assertTrue(tech.checkEquipInventory("guitar string"));
        assertFalse(tech.checkEquipInventory("guitar"));
    }
    
    //Integration Test
    @Test 
    void pullFromInventoryTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech tech= new RepairTech(12345, "Steve", store);
        store.addEquipment(new Equipment("guitar string", 12));
        tech.pullFromEquipInventory("guitar string");
        assertEquals(0, store.getInventorySize());
        assertThrows(IllegalArgumentException.class, ()-> tech.pullFromEquipInventory("guitar"));
    }


    @Test
    void addActiveToRepairListTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("guitar", "Sam", "bonk"), employee1));
        assertEquals("Sam", employee1.getActiveRepairList().get(0).getItem().getClientName());
    }

    @Test
    void removeFromRepairListTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("guitar", "Sam", "bonk"), employee1));
        employee1.removeFromActiveRepairList("guitar", "Sam");
        assertEquals(0, employee1.getActiveRepairList().size());
        assertThrows(IllegalArgumentException.class, ()-> employee1.removeFromActiveRepairList("guitar", "Janet"));
        
    }

    @Test
    void findRepairTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("guitar", "John", "bonk"), employee1));
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("piano", "Beth", "bonk"), employee1));
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("flute", "John", "bonk"), employee1));
        employee1.addToActiveRepairList(new Repair(new ThingToBeRepaired("guitar", "Sam", "bonk"), employee1));
        assertEquals(0, employee1.findRepair("guitar", "John"));
        assertEquals(1, employee1.findRepair("piano", "Beth"));
        assertEquals(2, employee1.findRepair("flute", "John"));
        assertEquals(3, employee1.findRepair("guitar", "Sam"));
        assertEquals(-1, employee1.findRepair("guitar", "April"));
        
    }

    @Test
    void getPriceFromCategoryTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        assertEquals(40, employee1.getPriceFromCategory(1));
        assertEquals(40, employee1.getPriceFromCategory(0.1));
        assertEquals(40, employee1.getPriceFromCategory(2.99));

        assertEquals(60, employee1.getPriceFromCategory(3));
        assertEquals(60, employee1.getPriceFromCategory(4));
        assertEquals(60, employee1.getPriceFromCategory(4.99));

        assertEquals(80, employee1.getPriceFromCategory(5));
        assertEquals(80, employee1.getPriceFromCategory(6));
        assertEquals(80, employee1.getPriceFromCategory(6.99));

        assertEquals(100, employee1.getPriceFromCategory(7));
        assertEquals(100, employee1.getPriceFromCategory(10));
        assertEquals(100, employee1.getPriceFromCategory(24));

        assertThrows(IllegalArgumentException.class, ()-> employee1.getPriceFromCategory(0));
        assertThrows(IllegalArgumentException.class, ()-> employee1.getPriceFromCategory(-10));

    }


    
}
