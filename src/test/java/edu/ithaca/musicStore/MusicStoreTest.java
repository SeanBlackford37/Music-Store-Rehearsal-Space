package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MusicStoreTest {
    
    @Test
    void createStoreTest() { //Unit Test
        //Create store
        MusicStore store = new MusicStore("Fancy Store");
        assertEquals("Fancy Store", store.getStoreName()); //Equivalence class
    }

    @Test
    void addItemTest() { //Unit Test
        MusicStore store = new MusicStore("Fancy Store");
        //Add valid item
        store.addToInventory(new Item("Guitar", 94, "none"));
        assertEquals(1, store.getInventorySize()); //Equivalence class
        assertEquals("Guitar", store.getInventoryItem(0).getName()); //Equivalence class

        //Add same item
        store.addToInventory(new Item("Guitar", 94, "none"));
        assertEquals(2, store.getInventorySize()); //Equivalence class
        assertEquals("Guitar", store.getInventoryItem(1).getName()); //Equivalence class

    }

    @Test
    void removeItemTest() { //Unit Test
        MusicStore store = new MusicStore("Fancy Store");
        store.addToInventory(new Item("Guitar", 94, "none")); 
        //remove valid item
        store.removeFromInventory("Guitar", 94); 
        assertEquals(0, store.getInventorySize()); //Equivalence class

        //Remove when items have same name but different price
        store.addToInventory(new Item("Guitar", 94, "none"));
        store.addToInventory(new Item("Guitar", 93, "none"));
        store.removeFromInventory("Guitar", 94);
        assertEquals(1, store.getInventorySize()); //Equivalence class
        assertEquals(93, store.getInventoryItem(0).getPrice()); //Equivalence class

        //Remove an item that doesnt exist
        assertThrows(IllegalArgumentException.class, ()->store.removeFromInventory("Bongos", 50));  //Border Case
    }

    @Test
    void searchForItemTest() { //Unit Test
        MusicStore store = new MusicStore("Fancy Store");
        store.addToInventory(new Item("Flute", 23, "none"));
        store.addToInventory(new Item("Guitar", 94, "none"));
        store.addToInventory(new Item("Guitar", 94, "none"));
        store.addToInventory(new Item("Keyboard", 130, "none"));

        //Search for valid item at front
        assertEquals(0, store.searchForInventoryItem("Flute"));

        //Search for item when there are two of the same
        assertEquals(1, store.searchForInventoryItem("Guitar"));

        //Search for item at the end
        assertEquals(3, store.searchForInventoryItem("Keyboard"));

        //Search for item that doesnt exist
        assertEquals(-1, store.searchForInventoryItem("Bongos"));
    }

    @Test
    void moveToRentedTest() { //Unit Test
        MusicStore store = new MusicStore("Fancy Store");
        store.addToInventory(new Item("Flute", 23, "none"));
        store.addToInventory(new Item("Guitar", 94, "none"));
        store.addToInventory(new Item("Guitar", 94, "none"));
        store.addToInventory(new Item("Keyboard", 130, "none"));
        
        //Move Valid Item
        store.moveToRented("Flute");
        assertEquals(-1, store.searchForInventoryItem("Flute"));
        assertEquals(0, store.searchForRentedItem("Flute"));

        //Move when two identical items exist
        store.moveToRented("Guitar");
        assertEquals(2, store.getInventorySize());
        assertEquals(0, store.searchForInventoryItem("Guitar"));
        assertEquals(1, store.searchForRentedItem("Guitar"));

        //Move when an item doesnt exist
        assertThrows(IllegalArgumentException.class, ()->store.moveToRented("Bongos")); 

    }

    @Test
    void moveToInventoryTest() { //Unit Test
        MusicStore store = new MusicStore("Fancy Store");
        store.addToRented(new Item("Flute", 23, "none"));
        store.addToRented(new Item("Guitar", 94, "none"));
        store.addToRented(new Item("Guitar", 94, "none"));
        store.addToRented(new Item("Keyboard", 130, "none"));
        
        //Move Valid Item
        store.moveToInventory("Flute");
        assertEquals(-1, store.searchForRentedItem("Flute"));
        assertEquals(0, store.searchForInventoryItem("Flute"));

        //Move when two identical items exist
        store.moveToInventory("Guitar");
        assertEquals(2, store.getRentedSize());
        assertEquals(0, store.searchForRentedItem("Guitar"));
        assertEquals(1, store.searchForInventoryItem("Guitar"));

        //Move when an item doesnt exist
        assertThrows(IllegalArgumentException.class, ()->store.moveToInventory("Bongos")); 

    }

    @Test
    void addRoomTest() { //Unit Test
        MusicStore store = new MusicStore("Fancy Store");
        //Add valid room
        store.addToRoomList(new Room(true, 1, false, "none"));
        assertEquals(1, store.getRoomListSize());
        assertEquals(1, store.getRoom(0).getRoomNumber());

        //Add same room
        store.addToRoomList(new Room(true, 1, false, "none"));
        assertEquals(2, store.getRoomListSize());
        assertEquals(1, store.getRoom(1).getRoomNumber());

    }

    @Test
    void removeRoomTest() { //Unit Test
        MusicStore store = new MusicStore("Fancy Store");
        store.addToRoomList(new Room(true, 1, false, "none"));
       
        //remove valid item
        store.removeFromRoomList(1);
        assertEquals(0, store.getRoomListSize());

        //Remove when items have same room number
        store.addToRoomList(new Room(true, 1, false, "none"));
        store.addToRoomList(new Room(true, 1, false, "none"));
        store.removeFromRoomList(1);
        assertEquals(1, store.getRoomListSize());
        assertEquals(1, store.getRoom(0).getRoomNumber());

        //Remove an room that doesnt exist
        assertThrows(IllegalArgumentException.class, ()->store.removeFromRoomList(50)); 
    }

    @Test
    void searchForRoomTest() { //Unit Test
        MusicStore store = new MusicStore("Fancy Store");
        store.addToRoomList(new Room(true, 1, false, "none"));
        store.addToRoomList(new Room(true, 2, false, "none"));
        store.addToRoomList(new Room(true, 3, false, "none"));
        store.addToRoomList(new Room(true, 4, false, "none"));

        //Search for valid room at front
        assertEquals(0, store.findRoom(1));

        //Search for room when there are two of the same
        assertEquals(1, store.findRoom(2));

        //Search for room at the end
        assertEquals(3, store.findRoom(4));

        //Search for room that doesnt exist
        assertEquals(-1, store.findRoom(30));
    }
    @Test
    void avaibleRoomListTest() { //Unit Test
        MusicStore store = new MusicStore("Fancy Store");
        store.addToRoomList(new Room(true,1,false,"none"));
        store.addToRoomList(new Room(false,2,true,"Joe Smith"));
        store.addToRoomList(new Room(true,3,false,"none"));
        store.addToRoomList(new Room(false,4,false,"Bob"));
        store.addToRoomList(new Room(true,5,false,"none"));

        //Search for valid room at front
        assertEquals(3, store.availableRoomList().size());

    }

    @Test
    void addEmployeeTest(){ //Unit Test
        MusicStore store = new MusicStore("Fancy Store");
        store.addEmployee(new Employee(12345, "Steve",store));
        store.addEmployee(new Employee(23456, "Linda",store));
        store.addEmployee(new Employee(34567, "John",store));
        assertEquals(3, store.getEmployeeList().size());
        assertEquals("Steve", store.getEmployeeList().get(0).getName());
        assertEquals(23456, store.getEmployeeList().get(1).getID());
        assertEquals("John", store.getEmployeeList().get(2).getName());
        assertThrows(IllegalArgumentException.class, ()-> store.addEmployee(new Employee(12345, "",store)));
        assertThrows(IllegalArgumentException.class, ()-> store.addEmployee(new Employee(0, "Sarah",store)));
    }

    @Test
    void createStoreTestWithBalance(){ //Unit Test
        //create valid store with no decimals
        MusicStore store = new MusicStore("Cool", 10);
        assertEquals(10, store.getStoreBalance());

        //create valid store with 1 decimal
        store = new MusicStore("Cool", 10.1);
        assertEquals(10.1, store.getStoreBalance());

        //create valid store with 2 decimals
        store = new MusicStore("Cool", 10.13);
        assertEquals(10.13, store.getStoreBalance());

        //create store with 3 decimals
        assertThrows(IllegalArgumentException.class, ()->new MusicStore("Cool", 10.123));
        //create store with negative 3 decimals
        assertThrows(IllegalArgumentException.class, ()->new MusicStore("Cool", -10.123));
        //create store with negative 2 decimals
        assertThrows(IllegalArgumentException.class, ()->new MusicStore("Cool", -10.13));
        //create store with negative and 1 decimal
        assertThrows(IllegalArgumentException.class, ()->new MusicStore("Cool", -10.3));
        //create store with negative
        assertThrows(IllegalArgumentException.class, ()->new MusicStore("Cool", -10));

    }

    @Test
    void addToStoreBalanceTest(){ //Unit Test
        //create valid store 
        MusicStore store = new MusicStore("Cool", 10);
        assertEquals(10, store.getStoreBalance());

        //add an int
        store.addToStoreBalance(10);
        assertEquals(20, store.getStoreBalance());

        //add a num with 1 decimal
        store.addToStoreBalance(10.1);
        assertEquals(30.1, store.getStoreBalance());

        //add a num with 2 decimals
        store.addToStoreBalance(10.12);
        assertEquals(40.22, store.getStoreBalance());

        //add with 3 decimals
        assertThrows(IllegalArgumentException.class, ()-> store.addToStoreBalance(10.123));
        //add with negative 3 decimals
        assertThrows(IllegalArgumentException.class, ()->store.addToStoreBalance( -10.123));
        //add with negative 2 decimals
        assertThrows(IllegalArgumentException.class, ()->store.addToStoreBalance( -10.13));
        //add with negative and 1 decimal
        assertThrows(IllegalArgumentException.class, ()->store.addToStoreBalance( -10.3));
        //add with negative
        assertThrows(IllegalArgumentException.class, ()->store.addToStoreBalance( -10));


    }

    @Test
    void removeEmployeeTest(){ //Integration Test
        MusicStore store = new MusicStore("Fancy Store");
        Employee employee1=new Employee(12345, "Steve",store);
        Employee employee2= new Employee(23456, "Linda",store);
        Employee employee3= new Employee(34567, "John",store);
        Employee employee4= new Employee(45678, "Dustin",store);
        store.addEmployee(employee1);
        store.addEmployee(employee2);
        store.addEmployee(employee3);
        assertEquals(3, store.getEmployeeList().size()); //Equivalence class
        store.removeEmployee(12345);
        store.removeEmployee(34567);
        assertEquals(1, store.getEmployeeList().size()); //Equivalence class
        assertFalse(store.getEmployeeList().contains(employee1)); //Equivalence class
        assertFalse(store.getEmployeeList().contains(employee3)); //Equivalence class
        assertTrue(store.getEmployeeList().contains(employee2)); //Equivalence class
        assertThrows(IllegalArgumentException.class, ()-> store.removeEmployee(12345)); //Border Case
        assertThrows(IllegalArgumentException.class, ()-> store.removeEmployee(45678)); //Border Case
    }

    @Test
    void searchForEmployeeTest() { //Integration Test
        MusicStore store = new MusicStore("Fancy Store");
        store.addEmployee(new Employee(12345, "John", store));
        store.addEmployee(new Employee(13345, "Bob",store));
        store.addEmployee(new Employee(14345, "Nami",store));
        store.addEmployee(new Employee(15345, "Elise",store));

        //Search for valid item at front
        assertEquals(0, store.findEmployee(12345));

        //Search for item when there are two of the same
        assertEquals(1, store.findEmployee(13345));

        //Search for item at the end
        assertEquals(2, store.findEmployee(14345));

        //Search for item that doesnt exist
        assertEquals(-1, store.findEmployee(19345));
    }

    @Test
    void subtractFromStoreBalanceTest(){ //Unit Test

        //create valid store 
        MusicStore store = new MusicStore("Cool", 10);
        assertEquals(10, store.getStoreBalance());

        //subtract an int
        store.subtractFromStoreBalance(2);
        assertEquals(8, store.getStoreBalance());

        //subtract with 1 decimal
        store.subtractFromStoreBalance(2.1);
        assertEquals(5.9, store.getStoreBalance());

        //subtract with 2 decimal
        store.subtractFromStoreBalance(2.12);
        assertEquals(3.78, store.getStoreBalance());

        //sub with more than balance
        assertThrows(IllegalArgumentException.class, ()-> store.subtractFromStoreBalance(40));
        //sub with 3 decimals
        assertThrows(IllegalArgumentException.class, ()-> store.subtractFromStoreBalance(1.123));
        //sub with negative 3 decimals
        assertThrows(IllegalArgumentException.class, ()->store.subtractFromStoreBalance( -10.123));
        //sub with negative 2 decimals
        assertThrows(IllegalArgumentException.class, ()->store.subtractFromStoreBalance( -10.13));
        //sub with negative and 1 decimal
        assertThrows(IllegalArgumentException.class, ()->store.subtractFromStoreBalance( -10.3));
        //sub with negative
        assertThrows(IllegalArgumentException.class, ()->store.subtractFromStoreBalance( -10));

    }

    @Test
    void addToRepairTechListTest() { //Integration Test
        MusicStore store = new MusicStore("Fancy Store");
        //Add valid tech
        store.addToRepairTechList(new RepairTech(12345, "Nick", store));
        assertEquals(1, store.getRepairTechList().size());
        assertEquals("Nick", store.getRepairTech(12345).getName());

        //Add same tech
        store.addToRepairTechList(new RepairTech(12345, "Nick", store));
        assertEquals(2, store.getRepairTechList().size());
        assertEquals("Nick", store.getRepairTech(12345).getName());

    }

    @Test
    void removeRepairTechTest() { //Integration Test
        MusicStore store = new MusicStore("Fancy Store");
        store.addToRepairTechList(new RepairTech(12345, "Nick", store));
        //remove valid item
        store.removeRepairTech(12345);
        assertEquals(0, store.getRepairTechList().size());

        //Remove when items have same name but different price
        store.addToRepairTechList(new RepairTech(12345, "Nick", store));
        store.addToRepairTechList(new RepairTech(12346, "Nick", store));
        store.removeRepairTech(12345);
        assertEquals(1, store.getRepairTechList().size());
        assertEquals(12346, store.getRepairTechList().get(0).getID());

        //Remove an item that doesnt exist
        assertThrows(IllegalArgumentException.class, ()->store.removeRepairTech(12345)); 
    }

    @Test
    void searchForRepairTechTest() { //Integration Test
        MusicStore store = new MusicStore("Fancy Store");
        store.addToRepairTechList(new RepairTech(12345, "Nick", store));
        store.addToRepairTechList(new RepairTech(12346, "Beck", store));
        store.addToRepairTechList(new RepairTech(12349, "Beck", store));
        store.addToRepairTechList(new RepairTech(12348, "Tasha", store));

        //Search for valid item at front
        assertEquals(0, store.findRepairTech(12345));
        assertEquals(0, store.findRepairTech("Nick"));

        //Search for item when there are two of the same
        assertEquals(1, store.findRepairTech(12346));
        assertEquals(2, store.findRepairTech(12349));
        assertEquals(1, store.findRepairTech("Beck"));

        //Search for item at the end
        assertEquals(3, store.findRepairTech(12348));
        assertEquals(3, store.findRepairTech("Tasha"));

        //Search for item that doesnt exist
        assertEquals(-1, store.findRepairTech(22345));
        assertEquals(-1, store.findRepairTech("James"));
    }

    void getRepairPricingTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        assertEquals(80.0,ms.getRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN)); //Equivalence class
    }

    @Test
    void updateRepairPricingTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        assertEquals(80.0,ms.getRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN)); //Equivalence class
        ms.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, 120); 
        assertEquals(120.0, ms.getRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN)); //Equivalence class
        ms.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, 120.75);
        assertEquals(120.75, ms.getRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN)); //Equivalence class
        assertThrows(IllegalArgumentException.class,()->ms.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, -120)); //Border Case
        assertThrows(IllegalArgumentException.class,()->ms.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, -120.34)); //Border Case
        assertThrows(IllegalArgumentException.class,()->ms.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, -120.345)); //Border Case
        assertThrows(IllegalArgumentException.class,()->ms.updateRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN, 20.394)); //Border Case
        
        
    }

    @Test
    void getEquipmentTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        assertThrows(IllegalArgumentException.class, ()->ms.getEquipment(-1)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->ms.getEquipment(0)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->ms.getEquipment(1)); //Border Case

        Equipment e = new Equipment("guitar strings", 5.50);
        ms.addEquipment(e);

        assertEquals(e, ms.getEquipment(0)); //Equivalence class

        assertThrows(IllegalArgumentException.class, ()->ms.getEquipment(-1)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->ms.getEquipment(1)); //Border Case
        
    }

    @Test 
    void addEquipmentTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        assertEquals(0,ms.getEquipmentListSize()); //Equivalence class
        Equipment e = new Equipment("guitar strings",5.50);
        ms.addEquipment(e);
        assertEquals(1,ms.getEquipmentListSize()); //Equivalence class
        assertEquals(e,ms.getEquipment(0)); //Equivalence class

    }

    @Test
    void removeEquipmentTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        assertThrows(IllegalArgumentException.class,()->ms.removeEquipment(0)); //Border Case
        assertThrows(IllegalArgumentException.class,()->ms.removeEquipment(-1)); //Border Case
        assertThrows(IllegalArgumentException.class,()->ms.removeEquipment(1)); //Border Case

        Equipment e = new Equipment("guitar strings",5.50);
        ms.addEquipment(e);
        assertEquals(1,ms.getEquipmentListSize()); //Equivalence class
        assertThrows(IllegalArgumentException.class,()->ms.removeEquipment(-1)); //Border Case
        assertThrows(IllegalArgumentException.class,()->ms.removeEquipment(1));//Border Case
        ms.removeEquipment(0); 
        assertEquals(0,ms.getEquipmentListSize()); //Equivalence class
    }

    @Test
    void findEquipmentTest(){ //Unit Test
        MusicStore ms = new MusicStore("ms");
        Equipment e = new Equipment("guitar strings",5.50);
        assertEquals(-1,ms.findEquipment("ms")); //Equivalence class
        ms.addEquipment(e);
        assertEquals(0,ms.findEquipment("guitar strings")); //Equivalence class
    }

}
