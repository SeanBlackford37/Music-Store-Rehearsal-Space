package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
        List<Room> rented= new ArrayList<Room>();
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
        assertEquals(80.0,a.getRepairPricing(RepairCategory.FIVETOSEVEN));
    }
    @Test
    void updateRepairPricingTest(){
        MusicStore ms = new MusicStore("ms");
        Admin a = new Admin(12345, "Sam", ms);
        assertEquals(80.0,a.getRepairPricing(RepairCategory.FIVETOSEVEN));
        a.updateRepairPricing(RepairCategory.FIVETOSEVEN, 120);
        assertEquals(120.0, a.getRepairPricing(RepairCategory.FIVETOSEVEN));
        a.updateRepairPricing(RepairCategory.FIVETOSEVEN, 120.75);
        assertEquals(120.75, a.getRepairPricing(RepairCategory.FIVETOSEVEN));
        assertThrows(IllegalArgumentException.class,()->a.updateRepairPricing(RepairCategory.FIVETOSEVEN, -120));
        assertThrows(IllegalArgumentException.class,()->a.updateRepairPricing(RepairCategory.FIVETOSEVEN, -120.34));
        assertThrows(IllegalArgumentException.class,()->a.updateRepairPricing(RepairCategory.FIVETOSEVEN, -120.345));
        assertThrows(IllegalArgumentException.class,()->a.updateRepairPricing(RepairCategory.FIVETOSEVEN, 20.394));
        
        
    }


    @Test
    void payEmployeeTest(){
        MusicStore store= new MusicStore("Fancy Store", 10000);
        double currentBalance= store.getStoreBalance();
        Admin admin1= new Admin(56789, "Jack", 20.00, store);
        Employee employee1= new Employee(12345, "Katherine", store);
        Admin employee2= new Admin(23456, "David", store);
        RepairTech employee3= new RepairTech(34567, "Joe", store);
        store.addEmployee(employee1);
        store.addAdmin(employee2);
        store.addToRepairTechList(employee3);
        employee1.addHours(30);
        employee2.addHours(40);
        employee3.addHours(23);
        double shouldBePayed1= employee1.getPayAmt()*employee1.getHoursWorked();
        double shouldBePayed2= employee2.getPayAmt()*employee2.getHoursWorked();
        double shouldBePayed3= employee3.getPayAmt()*employee3.getHoursWorked();
        admin1.payEmployee(12345, "Employee");
        assertEquals(store.getStoreBalance(), currentBalance-shouldBePayed1);
        currentBalance= store.getStoreBalance();
        admin1.payEmployee(23456, "Admin");
        assertEquals(store.getStoreBalance(), currentBalance-shouldBePayed2);
        currentBalance= store.getStoreBalance();
        admin1.payEmployee(34567, "RepairTech");
        assertEquals(store.getStoreBalance(), currentBalance-shouldBePayed3);
        assertEquals(employee1.seePayment(), shouldBePayed1);
        assertEquals(employee2.seePayment(), shouldBePayed2);
        //assertEquals(employee3.seePayment(), shouldBePayed3);
        employee1.addHours(20);
        shouldBePayed1= employee1.getPayAmt()*employee1.getHoursWorked();
        admin1.payEmployee(12345, "Employee");
        assertEquals(employee1.seePayment(), shouldBePayed1);
        assertThrows(IllegalArgumentException.class, ()-> admin1.payEmployee(55555, "RepairTech"));
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

   @Test
    void fireEmployeesTest(){
        MusicStore store= new MusicStore("Fancy Store", 10000);
        Admin admin1= new Admin(56789, "Jack", 20.00, store);
        Employee employee1= new Employee(12345, "Katherine", store);
        RepairTech employee2= new RepairTech(23456, "David", store);
        Admin employee3= new Admin(34567, "Joe", store);
        store.addEmployee(employee1);
        store.addToRepairTechList(employee2);
        store.addAdmin(employee3);
        admin1.fireEmployee(23456, "RepairTech");
        assertEquals(store.findEmployee(23456), -1);
        assertThrows(IllegalArgumentException.class, ()->admin1.fireEmployee(55555, ""));
        admin1.fireEmployee(12345, "Employee");
        assertEquals(store.findEmployee(12345), -1);
        assertEquals(store.getEmployeeList().size(), 0);
    }

    @Test
    void hireEmployeesTest(){
        MusicStore store= new MusicStore("Fancy Store", 10000);
        Admin admin1= new Admin(56789, "Jack", 20.00, store);
        admin1.hireEmployees(12345, "Katherine", store, "Employee");
        admin1.hireEmployees(23456, "David", store, "RepairTech");
        admin1.hireEmployees(34567, "Joe", store, "RepairTech");
        admin1.hireEmployees(45678, "Corey", store, "Employee");
        assertTrue(store.getEmployeeList().size()==2);
        assertTrue(store.getRepairTechList().size()==2);
        assertEquals(store.getEmployeeList().get(0).getName(), "Katherine");
        assertEquals(store.getEmployeeList().get(1).getName(), "Corey");
        assertEquals(store.getRepairTechList().get(0).getName(), "David");
        assertEquals(store.getRepairTechList().get(1).getName(), "Joe");
        admin1.hireEmployees(67890, "Jeremy", store, "Admin");
        assertTrue(store.getAdminList().size()==1);
        Admin admin2= store.getAdmin(0);
        admin2.hireEmployees(11234, "Lillian", store, "Employee");
        assertTrue(store.getEmployeeList().size()==3);

        
    }
}
