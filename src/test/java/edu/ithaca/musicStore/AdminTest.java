package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class AdminTest {
    @Test
    void constructorTest(){
        Admin employeeOne= new Admin(56789, "Sean", 25.00);
        assertEquals("Sean", employeeOne.getName());
        assertEquals(56789, employeeOne.getID());
        assertEquals(25, employeeOne.getPayAmt());
        //No need to check for excpetions as they are handeled by the parent class
    }
    @Test
    void cancelSpaceRentalTest(){
        Admin employeeOne= new Admin(56789, "Sean", 25.00);
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
        Admin employeeOne= new Admin(56789, "Sean", 25.00);
        ArrayList<Room> rented= new ArrayList<Room>();
        rented.add(new Room(false, 5, true, "Carolyn"));
        rented.add(new Room(false, 9, true, "Sophia"));
        employeeOne.addSpaceToRental(1, rented);
        assertEquals(3, rented.size());
    }
    @Test
    void viewSpaceRentalTest(){
        Admin employeeOne= new Admin(56789, "Sean", 25.00);
        ArrayList<Room> rented= new ArrayList<Room>();
        rented.add(new Room(false, 1, true, "Sadie"));
        rented.add(new Room(false, 5, true, "Carolyn"));
        rented.add(new Room(false, 9, true, "Sophia"));
        String testView= employeeOne.viewSpaceSchedule(rented);
        System.out.println(testView);
    }
}
