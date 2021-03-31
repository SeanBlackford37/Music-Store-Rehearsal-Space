package edu.ithaca.musicStore;

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
        ArrayList<Item> findItems= new ArrayList<Item>();
        findItems= employee1.checkStock("piano");
        assertTrue(findItems.size()== 4);
        findItems.clear();
        findItems= employee1.checkStock("guitar");
        assertTrue(findItems.size()==3);
        findItems.clear();
        findItems= employee1.checkStock("trumpet");
        assertTrue(findItems.size()==0);
    }

    void chargeClientTest(){
        Employee employee1= new Employee(12345, "Steve");
        ArrayList<Item> stock= new ArrayList<Item>();
        stock.add(new Item("piano", 150, "Mary"));
        stock.add(new Item("piano", 150, "Joe"));
        stock.add(new Item("guitar", 100, "Dustin"));
        stock.add(new Item("piano", 150, "Mike"));
        stock.add(new Item("guitar", 50, "Lucas"));
        stock.add(new Item("piano", 150, "Max"));
        stock.add(new Item("guitar", 100, "John"));
        ArrayList<Item> purchases= new ArrayList<Item>();
        purchases.add(new Item("piano", 150, "Mary"));
        purchases.add(new Item("sheet music", 20, "Mary"));
        ArrayList<Item> findItems= new ArrayList<Item>();
        findItems= employee1.checkStock("piano");
        assertTrue(findItems.size()==4);
        findItems.clear();
        findItems= employee1.checkStock("sheet music");
        assertTrue(findItems.size()==2);
        employee1.chargeClient(purchases);
        findItems= employee1.checkStock("piano");
        assertTrue(findItems.size()==3);
        findItems.clear();
        findItems= employee1.checkStock("sheet music");
        assertTrue(findItems.size()==1);
        //We should add field for total funds in music store

    }

    void equipmentScheduleTest(){

    }


}
