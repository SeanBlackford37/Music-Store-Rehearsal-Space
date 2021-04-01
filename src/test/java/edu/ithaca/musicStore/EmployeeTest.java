package edu.ithaca.musicStore;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EmployeeTest {
    
    @Test
    void constructorTest(){
        Employee employee1= new Employee(12345, "Steve");
        assertTrue(employee1.getName().equals("Steve"));
        assertTrue(employee1.getID()== 12345);
        assertTrue(employee1.getPayAmt()==15.00);
        assertThrows(IllegalArgumentException.class, ()-> new Employee(0, "Steve"));
        assertThrows(IllegalArgumentException.class, ()-> new Employee(12345, ""));
        assertThrows(IllegalArgumentException.class, ()-> new Employee(12345, "Steve", -50));
        assertThrows(IllegalArgumentException.class, ()-> new Employee(12345, "Steve", 25.555));
    }
    
    @Test
    void checkStockTest(){
        Employee employee1= new Employee(12345, "Steve");
        ArrayList<Item> stock= new ArrayList<Item>();
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
    void chargeClientTest(){
        Employee employee1= new Employee(12345, "Steve");
        ArrayList<Item> stock= new ArrayList<Item>();
        stock.add(new Item("piano", 150, "Mary"));
        stock.add(new Item("piano", 150, "Joe"));
        stock.add(new Item("sheet music", 20, "Erica"));
        ArrayList<Item> purchases= new ArrayList<Item>();
        purchases.add(new Item("piano", 150, "Mary"));
        purchases.add(new Item("sheet music", 20, "Mary"));
        employee1.chargeClient(purchases, stock);
        assertTrue(employee1.checkStock("piano", stock));
        assertFalse(employee1.checkStock("sheet music", stock));
        //We should add field for total funds in music store
        //test to ensure that total funds has risen by total of purchase, in this case 170

    }

    @Test
    void equipmentScheduleTest(){
        Employee employee1= new Employee(12345, "Steve");
        ArrayList<Item> out= new ArrayList<Item>();
        out.add(new Item("piano", 150, "Mary"));
        out.add(new Item("piano", 150, "Joe"));
        out.add(new Item("sheet music", 20, "Erica"));
        String testView= employee1.viewEquipmentSchedule(out);
        System.out.println(testView);

    }

    @Test
    void viewSpaceScheduleTest(){
        Employee employee1= new Employee(12345, "Steve");
        ArrayList<Room> rented= new ArrayList<Room>();
        rented.add(new Room(false, 1, true, "Sadie"));
        rented.add(new Room(false, 5, true, "Carolyn"));
        rented.add(new Room(false, 9, true, "Sophia"));
        String testView= employee1.viewSpaceSchedule(rented);
        System.out.println(testView);

    }


}
