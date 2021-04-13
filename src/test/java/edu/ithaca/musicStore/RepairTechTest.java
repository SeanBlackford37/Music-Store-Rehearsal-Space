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
    

    @Test
    void addToRepairListTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.addToRepairList(new Item("guitar", 12, "John"));
        assertEquals("guitar", employee1.getRepairList().get(0).getName());
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

    @Test
    void addHoursTest(){
        MusicStore store = new MusicStore("Place");
        RepairTech employee1= new RepairTech(12345, "Steve", store);
        employee1.addHours(20);
        assertTrue(employee1.getHoursWorked()== 20);
        employee1.addHours(5);
        assertTrue(employee1.getHoursWorked()==25);
        RepairTech employee2= new RepairTech(23456, "Dustin", store);
        employee2.addHours(0);
        assertThrows(IllegalArgumentException.class, ()-> employee2.addHours(-30));
        assertTrue(employee2.getHoursWorked()==0);
        RepairTech employee3= new RepairTech(34567, "Nancy", store);
        employee3.addHours(50.50);
        assertTrue(employee3.getHoursWorked()==50.50);


    }
    

    
}
