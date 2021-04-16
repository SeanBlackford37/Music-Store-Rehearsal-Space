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
    void getRepairPricingTest(){
        MusicStore ms = new MusicStore("ms");
        Admin a = new Admin(12345, "Sam", ms);
        assertEquals(80.0,a.getRepairPricing("5-7"));
        assertEquals(80.0,a.getRepairPricing("5-7 business days"));
        assertThrows(IllegalArgumentException.class,()->a.getRepairPricing("5"));
        assertThrows(IllegalArgumentException.class,()->a.getRepairPricing(""));
        assertThrows(IllegalArgumentException.class,()->a.getRepairPricing("57"));
        assertThrows(IllegalArgumentException.class,()->a.getRepairPricing("-"));
        assertThrows(IllegalArgumentException.class,()->a.getRepairPricing("5-"));
        assertThrows(IllegalArgumentException.class,()->a.getRepairPricing("-7"));
        assertThrows(IllegalArgumentException.class,()->a.getRepairPricing("5-7 bus d"));
        assertThrows(IllegalArgumentException.class,()->a.getRepairPricing("5-7 "));

    }
    @Test
    void updateRepairPricingTest(){
        MusicStore ms = new MusicStore("ms");
        Admin a = new Admin(12345, "Sam", ms);
    }


    @Test
    void payEmployeeTest(){
        MusicStore store= new MusicStore("Fancy Store", 10000);
        double currentBalance= store.getStoreBalance();
        Admin admin1= new Admin(56789, "Jack", 20.00, store);
        Employee employee1= new Employee(12345, "Katherine", store);
        Employee employee2= new Employee(23456, "David", store);
        Employee employee3= new Employee(34567, "Joe", store);
        store.addEmployee(employee1);
        store.addEmployee(employee2);
        store.addEmployee(employee3);
        employee1.addHours(30);
        employee2.addHours(40);
        employee3.addHours(23);
        double shouldBePayed1= employee1.getPayAmt()*employee1.getHoursWorked();
        double shouldBePayed2= employee2.getPayAmt()*employee2.getHoursWorked();
        double shouldBePayed3= employee3.getPayAmt()*employee3.getHoursWorked();
        admin1.payEmployee(12345);
        assertEquals(store.getStoreBalance(), currentBalance-shouldBePayed1);
        currentBalance= store.getStoreBalance();
        admin1.payEmployee(23456);
        assertEquals(store.getStoreBalance(), currentBalance-shouldBePayed2);
        currentBalance= store.getStoreBalance();
        admin1.payEmployee(34567);
        assertEquals(store.getStoreBalance(), currentBalance-shouldBePayed3);
        assertEquals(employee1.seePayment(), shouldBePayed1);
        assertEquals(employee2.seePayment(), shouldBePayed2);
        assertEquals(employee3.seePayment(), shouldBePayed3);
        employee1.addHours(20);
        shouldBePayed1= employee1.getPayAmt()*employee1.getHoursWorked();
        admin1.payEmployee(12345);
        assertEquals(employee1.seePayment(), shouldBePayed1);
        assertThrows(IllegalArgumentException.class, ()-> admin1.payEmployee(55555));
    }

    @Test
    void changeEmployeePayTest(){
        MusicStore store= new MusicStore("Fancy Store");
        Admin admin1= new Admin(56789, "Jack", 20.00, store);
        Employee employee1= new Employee(12345, "Katherine", store);
        Employee employee2= new Employee(23456, "David", store);
        Employee employee3= new Employee(34567, "Joe", store);
        store.addEmployee(employee1);
        store.addEmployee(employee2);
        store.addEmployee(employee3);
        admin1.changeEmployeePay(12345, 16.50);
        admin1.changeEmployeePay(23456, 20.00);
        assertThrows(IllegalArgumentException.class, ()-> admin1.changeEmployeePay(34567, -16.59));
        assertThrows(IllegalArgumentException.class, ()-> admin1.changeEmployeePay(55555, 16.59));
        assertEquals(employee1.getPayAmt(), 16.50);
        assertEquals(employee2.getPayAmt(), 20.00);
        assertEquals(employee3.getPayAmt(), 15.00);
        admin1.changeEmployeePay(34567, 16.50);
    }

    @Test 
    void raiseEmployeePay(){
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        Employee employeeTwo = new Employee(12345, "Toby", 15.00, ms);
        employeeOne.raisePay(employeeTwo, .10);
        assertEquals(16.50, employeeTwo.getPayAmt());
        assertThrows(IllegalArgumentException.class,  ()->employeeOne.raisePay(employeeTwo, 1.01)); //Has to be less than 1
        assertThrows(IllegalArgumentException.class,  ()->employeeOne.raisePay(employeeTwo, -.10)); //Can't be negative
        assertThrows(IllegalArgumentException.class,  ()->employeeOne.raisePay(employeeTwo, .105)); //Only two decimal places

    }
}
