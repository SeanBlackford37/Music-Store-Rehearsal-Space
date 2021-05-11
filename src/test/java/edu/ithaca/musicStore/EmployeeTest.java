package edu.ithaca.musicStore;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EmployeeTest {
    
    @Test
    void constructorTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        Employee employee1= new Employee(12345, "Steve",ms);
        assertEquals("Steve", employee1.getName()); //Equivalence class
        assertEquals(12345, employee1.getID()); //Equivalence class
        assertEquals(15.00, employee1.getPayAmt()); //Equivalence class
        assertThrows(IllegalArgumentException.class, ()-> new Employee(0, "Steve",ms)); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> new Employee(12345, "",ms)); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> new Employee(12345, "Steve", -50,ms)); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> new Employee(12345, "Steve", 25.555,ms)); //Border Case
    }
    
    @Test
    void checkStockTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        Employee employee1= new Employee(12345, "Steve",ms);
        ArrayList<Item> stock= new ArrayList<Item>();
        stock.add(new Item("piano", 150, "Mary"));
        stock.add(new Item("piano", 150, "Joe"));
        stock.add(new Item("guitar", 100, "Dustin"));
        stock.add(new Item("piano", 150, "Mike"));
        stock.add(new Item("guitar", 50, "Lucas"));
        stock.add(new Item("piano", 150, "Max"));
        stock.add(new Item("guitar", 100, "John"));
        stock.add(new Item("trumpet", 100, "John"));
        assertTrue(employee1.checkStock("piano", stock)); //Equivalence class
        assertTrue(employee1.checkStock("trumpet", stock)); //Equivalence class
        assertTrue(employee1.checkStock("guitar", stock)); //Equivalence class
        assertFalse(employee1.checkStock("trombone", stock)); //Equivalence class

    }

    @Test
    void equipmentScheduleTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        Employee employee1= new Employee(12345, "Steve",ms);
        ArrayList<Item> out= new ArrayList<Item>();
        out.add(new Item("piano", 150, "Mary"));
        out.add(new Item("piano", 150, "Joe"));
        out.add(new Item("sheet music", 20, "Erica"));
        String testView= employee1.viewItemSchedule(out);
        System.out.println(testView);

    }

    @Test
    void viewSpaceScheduleTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        Employee employee1= new Employee(12345, "Steve",ms);
        ArrayList<Room> rented= new ArrayList<Room>();
        rented.add(new Room(false, 1, true, "Sadie"));
        rented.add(new Room(false, 5, true, "Carolyn"));
        rented.add(new Room(false, 9, true, "Sophia"));
        String testView= employee1.viewSpaceSchedule(rented);
        System.out.println(testView);

    }

    @Test
    void addHoursTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        Employee employee1= new Employee(12345, "Steve",ms);
        employee1.addHours(20);
        assertEquals(20, employee1.getHoursWorked()); //Equivalence class
        employee1.addHours(5);
        assertEquals(25, employee1.getHoursWorked()); //Equivalence class
        Employee employee2= new Employee(23456, "Dustin",ms);
        employee2.addHours(0);
        assertThrows(IllegalArgumentException.class, ()-> employee2.addHours(-30)); //Border Case
        assertEquals(0, employee2.getHoursWorked()); //Equivalence class
        Employee employee3= new Employee(34567, "Nancy",ms);
        employee3.addHours(50.50);
        assertEquals(50.50, employee3.getHoursWorked()); //Equivalence class


    }

    @Test
    void chargeCustomerForItemRentalTest(){ //Integration Test
        MusicStore ms = new MusicStore("ms");
        Employee e = new Employee(10000, "Kip",ms);
        Customer c = new Customer(ms,"Barb");
        Item i = new Item("djembe",45);

        //empty inventory
        assertThrows(IllegalArgumentException.class,()->e.chargeCustomerForItemRental(c, "djembe")); 
        assertEquals(0,ms.getStoreBalance());

        ms.addToInventory(i);

        //item in inventory with good amount
        e.chargeCustomerForItemRental(c, "djembe");
        assertEquals(45,ms.getStoreBalance());

        //item is already rented by customer trying to rent
        assertThrows(IllegalArgumentException.class,()->e.chargeCustomerForItemRental(c, "djembe"));
        assertEquals(45,ms.getStoreBalance());

        //item is already rented by another customer
        Customer d = new Customer(ms,"Dale");
        assertThrows(IllegalArgumentException.class, ()->e.chargeCustomerForItemRental(d, "djembe"));
        assertEquals(45,ms.getStoreBalance());

        //customer is charged for item made recently available
        c.returnItem("djembe");
        e.chargeCustomerForItemRental(d, "djembe");
        assertEquals(90,ms.getStoreBalance());
        
    }

    @Test
    void chargeCustomerForRoomRentalTest(){ //Integration Test
        MusicStore ms = new MusicStore("ms");
        Employee e = new Employee(10000, "Kip",ms);
        Customer c = new Customer(ms,"Barb");
        Room r = new Room(123);

        //empty room list
        assertThrows(IllegalArgumentException.class,()->e.chargeCustomerForRoomRental(c, 123));
        assertEquals(0,ms.getStoreBalance());

        ms.addToRoomList(r);

        //room in list
        e.chargeCustomerForRoomRental(c, 123);
        assertEquals(50,ms.getStoreBalance());

        //room is already rented by the customer trying to rent
        assertThrows(IllegalArgumentException.class,()->e.chargeCustomerForRoomRental(c, 123));
        assertEquals(50,ms.getStoreBalance());

        //room is already rented by another customer
        Customer d = new Customer(ms,"Dale");
        assertThrows(IllegalArgumentException.class, ()->e.chargeCustomerForRoomRental(d, 123));
        assertEquals(50,ms.getStoreBalance());

        //customer is charged for room made recently available
        c.returnRoom(123);
        e.chargeCustomerForRoomRental(d, 123);
        assertEquals(100,ms.getStoreBalance());
    }

    @Test
    void refundCustomerForItemRentalTest(){ //Integration Test
        MusicStore ms = new MusicStore("ms");
        Employee e = new Employee(10000, "Kip",ms);
        Customer c = new Customer(ms,"Barb");
        Item i = new Item("djembe",45);
        ms.addToInventory(i);

        //empty rented list
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForItemRental(c, "djembe"));
        assertEquals(0,ms.getStoreBalance());

        e.chargeCustomerForItemRental(c, "djembe");

        //refund customer for item being rented by another customer
        Customer d = new Customer(ms,"Dale");
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForItemRental(d, "djembe"));
        assertEquals(45,ms.getStoreBalance());

        //refund customer for item they've already returned
        c.returnItem("djembe");
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForItemRental(c, "djembe"));
        assertEquals(45,ms.getStoreBalance());

        //refund customer for item they were renting
        e.chargeCustomerForItemRental(c, "djembe");
        assertEquals(90,ms.getStoreBalance());
        e.refundCustomerForItemRental(c, "djembe");
        assertEquals(45,ms.getStoreBalance());
        
        
    }

    @Test
    void refundCustomerForRoomRentalTest(){ //Integration Test
        MusicStore ms = new MusicStore("ms");
        Employee e = new Employee(10000, "Kip",ms);
        Customer c = new Customer(ms,"Barb");
        Room r = new Room(123);

        //empty room list
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForRoomRental(c, 123));
        assertEquals(0,ms.getStoreBalance());

        ms.addToRoomList(r);
        e.chargeCustomerForRoomRental(c, 123);

        //refund customer for room being rented by another customer
        Customer d = new Customer(ms,"Dale");
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForRoomRental(d, 123));
        assertEquals(50,ms.getStoreBalance());

        //refund customer for item they've already returned
        c.returnRoom(123);
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForRoomRental(c, 123));
        assertEquals(50,ms.getStoreBalance());

        //refund customer for item they were renting
        e.chargeCustomerForRoomRental(c, 123);
        assertEquals(100,ms.getStoreBalance());
        e.refundCustomerForRoomRental(c, 123);
        assertEquals(50,ms.getStoreBalance());

    }



}
