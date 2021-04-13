package edu.ithaca.musicStore;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void addHoursTest(){
        Employee employee1= new Employee(12345, "Steve");
        employee1.addHours(20);
        assertTrue(employee1.getHoursWorked()== 20);
        employee1.addHours(5);
        assertTrue(employee1.getHoursWorked()==25);
        Employee employee2= new Employee(23456, "Dustin");
        employee2.addHours(0);
        assertThrows(IllegalArgumentException.class, ()-> employee2.addHours(-30));
        assertTrue(employee2.getHoursWorked()==0);
        Employee employee3= new Employee(34567, "Nancy");
        employee3.addHours(50.50);
        assertTrue(employee3.getHoursWorked()==50.50);


    }

    @Test
    void chargeCustomerForItemRentalTest(){
        MusicStore ms = new MusicStore("ms");
        Employee e = new Employee(10000, "Kip");
        Customer c = new Customer(ms,"Barb");
        Item i = new Item("djembe",45);
        Item i2 = new Item ("guitar",45.456);

        //empty inventory
        assertThrows(IllegalArgumentException.class,()->e.chargeCustomerForItemRental(c, "djembe", ms));
        assertEquals(0,ms.getStoreBalance());

        ms.addToInventory(i);
        ms.addToInventory(i2);

        //item in inventory with bad amount
        assertThrows(RuntimeException.class,()->e.chargeCustomerForItemRental(c, "guitar", ms));
        assertEquals(0,ms.getStoreBalance());

        //item in inventory with good amount
        e.chargeCustomerForItemRental(c, "djembe", ms);
        assertEquals(45,ms.getStoreBalance());

        //item is already rented by customer trying to rent
        assertThrows(IllegalArgumentException.class,()->e.chargeCustomerForItemRental(c, "djembe", ms));
        assertEquals(45,ms.getStoreBalance());

        //item is already rented by another customer
        Customer d = new Customer(ms,"Dale");
        assertThrows(IllegalArgumentException.class, ()->e.chargeCustomerForItemRental(d, "djembe", ms));
        assertEquals(45,ms.getStoreBalance());

        //customer is charged for item made recently available
        c.returnItem("djembe");
        e.chargeCustomerForItemRental(d, "djembe", ms);
        assertEquals(90,ms.getStoreBalance());
        
    }

    @Test
    void chargeCustomerForRoomRentalTest(){
        MusicStore ms = new MusicStore("ms");
        Employee e = new Employee(10000, "Kip");
        Customer c = new Customer(ms,"Barb");
        Room r = new Room(123);

        //empty room list
        assertThrows(IllegalArgumentException.class,()->e.chargeCustomerForRoomRental(c, 123, ms));
        assertEquals(0,ms.getStoreBalance());

        ms.addToRoomList(r);

        //room in list
        e.chargeCustomerForRoomRental(c, 123, ms);
        assertEquals(50,ms.getStoreBalance());

        //room is already rented by the customer trying to rent
        assertThrows(IllegalArgumentException.class,()->e.chargeCustomerForRoomRental(c, 123, ms));
        assertEquals(50,ms.getStoreBalance());

        //room is already rented by another customer
        Customer d = new Customer(ms,"Dale");
        assertThrows(IllegalArgumentException.class, ()->e.chargeCustomerForRoomRental(d, 123, ms));
        assertEquals(50,ms.getStoreBalance());

        //customer is charged for room made recently available
        c.returnRoom(123);
        e.chargeCustomerForRoomRental(d, 123, ms);
        assertEquals(100,ms.getStoreBalance());
    }

    @Test
    void refundCustomerForItemRentalTest(){
        MusicStore ms = new MusicStore("ms");
        Employee e = new Employee(10000, "Kip");
        Customer c = new Customer(ms,"Barb");
        Item i = new Item("djembe",45);
        ms.addToInventory(i);

        //empty rented list
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForItemRental(c, "djembe", ms));
        assertEquals(0,ms.getStoreBalance());

        e.chargeCustomerForItemRental(c, "djembe", ms);

        //refund customer for item being rented by another customer
        Customer d = new Customer(ms,"Dale");
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForItemRental(d, "djembe", ms));
        assertEquals(45,ms.getStoreBalance());

        //refund customer for item they've already returned
        c.returnItem("djembe");
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForItemRental(c, "djembe", ms));
        assertEquals(45,ms.getStoreBalance());

        //refund customer for item they were renting
        e.chargeCustomerForItemRental(c, "djembe", ms);
        assertEquals(90,ms.getStoreBalance());
        e.refundCustomerForItemRental(c, "djembe", ms);
        assertEquals(45,ms.getStoreBalance());
        
        
    }

    @Test
    void refundCustomerForRoomRentalTest(){
        MusicStore ms = new MusicStore("ms");
        Employee e = new Employee(10000, "Kip");
        Customer c = new Customer(ms,"Barb");
        Room r = new Room(123);

        //empty room list
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForRoomRental(c, 123, ms));
        assertEquals(0,ms.getStoreBalance());

        e.chargeCustomerForRoomRental(c, 123, ms);

        //refund customer for room being rented by another customer
        Customer d = new Customer(ms,"Dale");
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForRoomRental(d, 123, ms));
        assertEquals(50,ms.getStoreBalance());

        //refund customer for item they've already returned
        c.returnRoom(123);
        assertThrows(IllegalArgumentException.class,()->e.refundCustomerForRoomRental(c, 123, ms));
        assertEquals(50,ms.getStoreBalance());

        //refund customer for item they were renting
        e.chargeCustomerForRoomRental(c, 123, ms);
        assertEquals(100,ms.getStoreBalance());
        e.refundCustomerForRoomRental(c, 123, ms);
        assertEquals(50,ms.getStoreBalance());

    }



}
