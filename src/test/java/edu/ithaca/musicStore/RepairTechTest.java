package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RepairTechTest {
    @Test
    void constructorTest(){
        RepairTech employee1= new RepairTech(12345, "Steve");
        assertTrue(employee1.getName().equals("Steve"));
        assertTrue(employee1.getID()== 12345);
        assertTrue(employee1.getPayAmt()==15.00);
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(0, "Steve"));
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, ""));
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "Steve", -50));
        assertThrows(IllegalArgumentException.class, ()-> new RepairTech(12345, "Steve", 25.555));
    }
    
    @Test
    void checkStockTest(){
        RepairTech employee1= new RepairTech(12345, "Steve");
        List<Item> stock= new ArrayList<Item>();
        stock.add(new Item("piano", 150, "Mary"));
        stock.add(new Item("piano", 150, "Joe"));
        stock.add(new Item("guitar", 100, "Dustin"));
        stock.add(new Item("piano", 150, "Mike"));
        stock.add(new Item("guitar", 50, "Lucas"));
        stock.add(new Item("piano", 150, "Max"));
        stock.add(new Item("guitar", 100, "John"));
        stock.add(new Item("trumpet", 100, "John"));
        assertTrue(employee1.checkStock("piano", stock));
        assertTrue(employee1.checkStock("trumpet", stock));
        assertTrue(employee1.checkStock("guitar", stock));
        assertFalse(employee1.checkStock("trombone", stock));

    }

    @Test
    void addToRepairListTest(){
        RepairTech employee1= new RepairTech(12345, "Steve");
        employee1.addToRepairList(new Item("guitar", 12, "John"));
        assertEquals("guitar", employee1.getRepairList().get(0).getName());
    }

    @Test
    void removeFromRepairListTest(){
        RepairTech employee1= new RepairTech(12345, "Steve");
        employee1.addToRepairList(new Item("guitar", 12, "John"));
        employee1.removeFromRepairList("guitar", "John");
        assertEquals(0, employee1.getRepairList().size());
        
    }

    @Test
    void findRepairTest(){
        RepairTech employee1= new RepairTech(12345, "Steve");
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
