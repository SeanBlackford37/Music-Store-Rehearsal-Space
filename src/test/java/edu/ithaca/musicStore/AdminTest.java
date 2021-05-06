package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class AdminTest {
    @Test
    void constructorTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        assertEquals("Sean", employeeOne.getName()); //Equivalence class
        assertEquals(56789, employeeOne.getID()); //Equivalence class
        assertEquals(25, employeeOne.getPayAmt()); //Equivalence class
        //No need to check for excpetions as they are handeled by the parent class
    }
    @Test
    void cancelSpaceRentalTest(){ //Integration Test
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        List<Room> rented= new ArrayList<Room>();
        rented.add(new Room(false, 1, true, "Sadie"));
        rented.add(new Room(false, 5, true, "Carolyn"));
        rented.add(new Room(false, 9, true, "Sophia"));
        rented = employeeOne.cancelSpaceRental(1, rented);
        assertEquals(true, rented.get(0).getIsEmptyRoom()); //Equivalence class
        assertEquals(3, rented.size()); //Equivalence class
    }
    @Test
    void addSpaceToRentalTest(){ //Integration Test
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        ArrayList<Room> rented= new ArrayList<Room>();
        rented.add(new Room(false, 5, true, "Carolyn"));
        rented.add(new Room(false, 9, true, "Sophia"));
        employeeOne.addSpaceToRental(1, rented);
        assertEquals(3, rented.size()); //Equivalence class
    }
    @Test
    void viewSpaceRentalTest(){ //Integration Test
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        ArrayList<Room> rented= new ArrayList<Room>();
        rented.add(new Room(false, 1, true, "Sadie"));
        rented.add(new Room(false, 5, true, "Carolyn"));
        rented.add(new Room(false, 9, true, "Sophia"));
        assertEquals(3,rented.size()); //Equivalence class
        String testView= employeeOne.viewSpaceSchedule(rented);
        System.out.println(testView);
    }
    @Test
    void getRepairPricingTest(){  //Unit Test
        MusicStore ms = new MusicStore("ms");
        Admin a = new Admin(12345, "Sam", ms);
        assertEquals(80.0,a.getRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN)); //Equivalence class
    }
    @Test
    void updateRepairPricingTest(){ //Integration Test
        MusicStore ms = new MusicStore("ms");
        Admin a = new Admin(12345, "Sam", ms);
        assertEquals(80.0,a.getRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN)); //Equivalence class
        a.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, 120);
        assertEquals(120.0, a.getRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN)); //Equivalence class
        a.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, 120.75);
        assertEquals(120.75, a.getRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN)); //Equivalence class
        assertThrows(IllegalArgumentException.class,()->a.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, -120)); //Border Case
        assertThrows(IllegalArgumentException.class,()->a.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, -120.34)); //Border Case
        assertThrows(IllegalArgumentException.class,()->a.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, -120.345)); //Border Case
        assertThrows(IllegalArgumentException.class,()->a.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, 20.394)); //Border Case
        
        
    }


    @Test
    void payEmployeeTest(){ //Integration Test
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
        assertEquals(store.getStoreBalance(), currentBalance-shouldBePayed1); //Equivalence class
        currentBalance= store.getStoreBalance();
        admin1.payEmployee(23456, "Admin");
        assertEquals(store.getStoreBalance(), currentBalance-shouldBePayed2); //Equivalence class
        currentBalance= store.getStoreBalance();
        admin1.payEmployee(34567, "RepairTech");
        assertEquals(store.getStoreBalance(), currentBalance-shouldBePayed3); //Equivalence class
        assertEquals(employee1.seePayment(), shouldBePayed1);
        assertEquals(employee2.seePayment(), shouldBePayed2);
        //assertEquals(employee3.seePayment(), shouldBePayed3);
        employee1.addHours(20);
        shouldBePayed1= employee1.getPayAmt()*employee1.getHoursWorked();
        admin1.payEmployee(12345, "Employee");
        assertEquals(employee1.seePayment(), shouldBePayed1); //Equivalence class
        assertThrows(IllegalArgumentException.class, ()-> admin1.payEmployee(55555, "RepairTech")); //Border Case
    }

    @Test
    void changeEmployeePayTest(){ //Integration Test
        MusicStore store= new MusicStore("Fancy Store");
        Admin admin1= new Admin(56789, "Jack", 20.00, store);
        Employee employee1= new Employee(12345, "Katherine", store);
        Employee employee2= new Employee(23456, "David", store);
        Employee employee3= new Employee(34567, "Joe", store);
        store.addEmployee(employee1);
        store.addEmployee(employee2);
        store.addEmployee(employee3);
        admin1.changeEmployeePay(12345, 16.50); //Equivalence class
        admin1.changeEmployeePay(23456, 20.00); //Equivalence class
        assertThrows(IllegalArgumentException.class, ()-> admin1.changeEmployeePay(34567, -16.59)); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> admin1.changeEmployeePay(55555, 16.59)); //Border Case
        assertEquals(16.50, employee1.getPayAmt()); //Equivalence class
        assertEquals(20.00, employee2.getPayAmt()); //Equivalence class
        assertEquals(15.00, employee3.getPayAmt()); //Equivalence class
        admin1.changeEmployeePay(34567, 16.50);
    }

    @Test 
    void raiseEmployeePay(){ //Integration Test
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
    void fireEmployeesTest(){ //Integration Test
        MusicStore store= new MusicStore("Fancy Store", 10000);
        Admin admin1= new Admin(56789, "Jack", 20.00, store);
        Employee employee1= new Employee(12345, "Katherine", store);
        RepairTech employee2= new RepairTech(23456, "David", store);
        Admin employee3= new Admin(34567, "Joe", store);
        store.addEmployee(employee1);
        store.addToRepairTechList(employee2);
        store.addAdmin(employee3);
        admin1.fireEmployee(23456, "RepairTech");
        assertEquals(-1, (store.findEmployee(23456))); //Equivalence class
        assertThrows(IllegalArgumentException.class, ()->admin1.fireEmployee(55555, "")); //Border Case
        admin1.fireEmployee(12345, "Employee"); //Equivalence class
        assertEquals(-1, store.findEmployee(12345));
        assertEquals(0, store.getEmployeeList().size());
    }

    @Test
    void hireEmployeesTest(){ //Integration Test
        MusicStore store= new MusicStore("Fancy Store", 10000);
        Admin admin1= new Admin(56789, "Jack", 20.00, store);
        admin1.hireEmployees(12345, "Katherine", store, "Employee");
        admin1.hireEmployees(23456, "David", store, "RepairTech");
        admin1.hireEmployees(34567, "Joe", store, "RepairTech");
        admin1.hireEmployees(45678, "Corey", store, "Employee");
        assertEquals(2, store.getEmployeeList().size()); //Equivalence class
        assertEquals(2, store.getRepairTechList().size()); //Equivalence class
        assertEquals("Katherine", store.getEmployeeList().get(0).getName()); //Equivalence class
        assertEquals("Corey",store.getEmployeeList().get(1).getName() ); //Equivalence class
        assertEquals("David", store.getRepairTechList().get(0).getName()); //Equivalence class
        assertEquals("Joe", store.getRepairTechList().get(1).getName()); //Equivalence class
        admin1.hireEmployees(67890, "Jeremy", store, "Admin");
        assertEquals(1, store.getAdminList().size()); //Equivalence class
        Admin admin2= store.getAdmin(0);
        admin2.hireEmployees(11234, "Lillian", store, "Employee"); //Equivalence class
        assertEquals(3, store.getEmployeeList().size()); //Equivalence class
        admin1.hireEmployees(12349, "Katherine", 15.5, store, "Employee"); //Equivalence class
        assertEquals(15.5, store.getEmployeeList().get(3).getPayAmt()); //Equivalence class
        assertThrows(IllegalArgumentException.class, ()->admin1.hireEmployees(12349, "Katherine", -15.5, store, "Employee")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->admin1.hireEmployees(12349, "Katherine", 15.555, store, "Employee")); //Border Case
    }
    @Test
    void addEquipmentToInventoryTest(){ //Unit Test
        MusicStore store= new MusicStore("Fancy Store", 10000);
        Admin admin1= new Admin(56789, "Jack", 20.00, store);
        store.addToInventory(new Item("Piano", 30, "none"));
        store.addToInventory(new Item("Saxophone", 15, "none"));
        store.addToInventory(new Item("Drums", 50, "none"));
        store.addToInventory(new Item("Guitar", 15, "none"));
        store.addToInventory(new Item("Guitar", 15, "none"));
        assertEquals(5, store.getInventoryList().size()); //Equivalence class
        admin1.addEquipmentToInventory(new Item("Guitar 2.0", 36), store);
        assertEquals(6, store.getInventoryList().size()); //Equivalence class
        assertEquals("Guitar 2.0", store.getInventoryItem(5).getName()); //Equivalence class
    }

    @Test
    void orderEquipmentTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        employeeOne.orderEquipment("guitar string", 12);
        employeeOne.orderEquipment("guitar s", 12.01);
        assertEquals(0, ms.findEquipment("guitar string")); //Equivalence class
        assertEquals(1, ms.findEquipment("guitar s")); //Equivalence class

        assertThrows(IllegalArgumentException.class, ()->employeeOne.orderEquipment("string", -5)); //Border Case  
        assertThrows(IllegalArgumentException.class, ()->employeeOne.orderEquipment("", 5)); //Border Case  
        assertThrows(IllegalArgumentException.class, ()->employeeOne.orderEquipment("string", 5.234)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->employeeOne.orderEquipment("string", -5.2)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->employeeOne.orderEquipment("string", -5.23)); //Border Case
    }

    @Test
    void orderItemTest(){
        MusicStore ms = new MusicStore("ms");
        Admin employeeOne= new Admin(56789, "Sean", 25.00,ms);
        employeeOne.orderItem("guitar string", 12);
        employeeOne.orderItem("guitar s", 12.01);
        assertEquals(0, ms.searchForInventoryItem("guitar string")); //Equivalence class
        assertEquals(1, ms.searchForInventoryItem("guitar s")); //Equivalence class

        assertThrows(IllegalArgumentException.class, ()->employeeOne.orderItem("string", -5)); //Border Case  
        assertThrows(IllegalArgumentException.class, ()->employeeOne.orderItem("", 5)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->employeeOne.orderItem("string", 5.234)); //Border Case  
        assertThrows(IllegalArgumentException.class, ()->employeeOne.orderItem("string", -5.2)); //Border Case 
        assertThrows(IllegalArgumentException.class, ()->employeeOne.orderItem("string", -5.23)); //Border Case 
    }
}
