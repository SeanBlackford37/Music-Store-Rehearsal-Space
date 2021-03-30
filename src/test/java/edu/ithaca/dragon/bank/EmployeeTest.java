package edu.ithaca.dragon.bank;

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
        stock.add(new Item("piano"));
        stock.add(new Item("piano"));
        stock.add(new Item("guitar"));
        stock.add(new Item("piano"));
        stock.add(new Item("guitar"));
        stock.add(new Item("piano"));
        stock.add(new Item("guitar"));
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
        ArrayList<Item> purchases= new ArrayList<Item>();
        purchases.add(new Item("piano"));
        purchases.add(new Item("sheet music"));
        employee1.chargeClient(purchases);
    }

    void equipmentScheduleTest(){

    }


}
