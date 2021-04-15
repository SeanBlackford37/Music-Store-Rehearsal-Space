package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class AdminTest {
    @Test
    void constructorTest(){
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        assertEquals("Sean", employeeOne.getName());
        assertEquals(56789, employeeOne.getID());
        assertEquals(25, employeeOne.getPayAmt());
        //No need to check for excpetions as they are handeled by the parent class
    }
    @Test
    void cancelSpaceRentalTest(){
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        ArrayList<Room> rented= new ArrayList<Room>();
        rented.add(new Room(false, 1, true, "Sadie"));
        rented.add(new Room(false, 5, true, "Carolyn"));
        rented.add(new Room(false, 9, true, "Sophia"));
        rented = employeeOne.cancelSpaceRental(1, rented);
        assertEquals(true, rented.get(0).getIsEmptyRoom());
        assertEquals(3, rented.size());
    }
    @Test
    void addSpaceToRentalTest(){
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        ArrayList<Room> rented= new ArrayList<Room>();
        rented.add(new Room(false, 5, true, "Carolyn"));
        rented.add(new Room(false, 9, true, "Sophia"));
        employeeOne.addSpaceToRental(1, rented);
        assertEquals(3, rented.size());
    }
    @Test
    void viewSpaceRentalTest(){
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        ArrayList<Room> rented= new ArrayList<Room>();
        rented.add(new Room(false, 1, true, "Sadie"));
        rented.add(new Room(false, 5, true, "Carolyn"));
        rented.add(new Room(false, 9, true, "Sophia"));
        String testView= employeeOne.viewSpaceSchedule(rented);
        System.out.println(testView);
    }
    @Test 
    void raiseEmployeePay(){
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        Employee employeeTwo = new Employee(1234, "Toby", 15.00, ms);
        employeeOne.raisePay(employeeTwo, .10);
        assertEquals(16.50, employeeTwo.getPayAmt());
        assertThrows(IllegalArgumentException.class,  ()->employeeOne.raisePay(employeeTwo, 10)); //Has to be less than 1
        assertThrows(IllegalArgumentException.class,  ()->employeeOne.raisePay(employeeTwo, -.10)); //Can't be negative
        assertThrows(IllegalArgumentException.class,  ()->employeeOne.raisePay(employeeTwo, .105)); //Only two decimal places
    }
}
