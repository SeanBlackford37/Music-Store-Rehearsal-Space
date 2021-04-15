package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class CustomerTest {

    @Test
    void constructorTest(){
        

        //exception is thrown when music store is null
        assertThrows(NullPointerException.class,()->new Customer(null, "Bob"));

        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");

        //customer is created when music store is not null
        assertNotEquals(null, c);

        //customer has empty t list
        assertEquals(0,c.getTransactionHistorySize());

    }

    @Test
    void rentItemTest(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Item i =new Item("guitar",45, "n/a");
        ms.addToInventory(i);
        Employee e = new Employee(10101,"Todd",ms);

        //Narrative 0: Employee never clocked in before charging the customer
        assertThrows(IllegalArgumentException.class,()->c.rentItem("guitar",null));

        //Narrative 1: Customer rents item that's available in the store inventory
            //Customer rents item and the price is checked
        assertEquals(45, c.rentItem("guitar",e));
            //Transaction is created for item going from 0 to 1
        assertEquals(1,c.getTransactionHistorySize());
            //Item listed for the transaction is for the right item
         assertEquals(i, c.findTransaction("guitar").getItemRented());
            //Renter name associated with the item is the customer's name
        assertEquals("Bob",c.findTransaction("guitar").getItemRented().getRenterName());
            //Item exists in customer's list of currently rented items
        assertEquals(i,c.findRentedItem("guitar"));
            //Music Store inventory decreases by one item to get to 0
        assertEquals(0,ms.getInventorySize());
            //Music Store record of items being rented increases by one item from 0
        assertEquals(1,ms.getRentedSize());

        ms.addToInventory(new Item("djembe",150,"n/a"));
        
        //Narrative 2: Customer attempts to rent an item not in the store's inventory or rented list
            //Exception is thrown for invalid item when customer goes to rent it
        assertThrows(IllegalArgumentException.class, ()->c.rentItem("coffee",e));
            //Transaction history for the customer is unchanged from 1
        assertEquals(1,c.getTransactionHistorySize());
            //Rented list for customer is unchanged from 1
        assertEquals(1,c.getRentedItemsSize());
            //Music store inventory is unchanged from 1
        assertEquals(1,ms.getInventorySize());
            //Music store record of rented items is unchanged from 1
        assertEquals(1,ms.getRentedSize());

        //Narrative 3: Customer tries to rent an item they're already renting
            //Exception is thrown for invalid item when customer goes to rent it
        assertThrows(IllegalArgumentException.class, ()->c.rentItem("guitar",e));
            //Transaction history for the customer is unchanged from 1
        assertEquals(1,c.getTransactionHistorySize());
            //Rented list for customer is unchanged from 1
        assertEquals(1,c.getRentedItemsSize());
            //Music store inventory is unchanged from 1
        assertEquals(1,ms.getInventorySize());
            //Music store record of rented items is unchanged from 1
        assertEquals(1,ms.getRentedSize());

        //Narrative 4: Customer tries to rent an item another customer is already renting
        Customer d = new Customer(ms, "Fran");
            //Exception is thrown for invalid item when customer goes to rent it
        assertThrows(IllegalArgumentException.class, ()->d.rentItem("guitar",e));
            //Transaction history for the customer trying to rent the item is unchanged from 0
        assertEquals(0,d.getTransactionHistorySize());
            //Transaction history for the customer with the item is unchanged from 1
        assertEquals(1,c.getTransactionHistorySize());
            //Rented items list for customer trying to rent is unchanged from 0
        assertEquals(0, d.getRentedItemsSize());
            //Rented items list for customer with item is unchanged from 1
        assertEquals(1,c.getRentedItemsSize());
            //Music store inventory is unchanged from 1
        assertEquals(1,ms.getInventorySize());
            //Music store record of rented items is unchanged from 1
        assertEquals(1,ms.getRentedSize());



    }
    @Test 
    void returnItemTest() {
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Employee e = new Employee(10101,"Todd",ms);
        Item i = new Item("guitar",45, "n/a");
        ms.addToInventory(i);

        //Narrative 0: Customer is trying to return an item from the store they're not renting
        //with an empty list of currently rented items
            //Exception is thrown for the item not in the customer's rented item list
        assertThrows(IllegalArgumentException.class, ()->c.returnItem("guitar"));
            //Customer's rented items list is unchanged from 0
        assertEquals(0,c.getRentedItemsSize());
            //Store's rented items list is unchanged from 0
        assertEquals(0,ms.getRentedSize());
            //Renter name associated with item is "n/a"
        assertEquals("n/a", i.getRenterName());

        Item i2 = new Item("djembe",100,"n/a");

        //Narrative 0.5: Customer is trying to return an item not from the store that they also haven't rented
        //with an empty list of currently rented items
            //Exception is thrown for the item not in the customer's rented item list (nor in store's inventory)
        assertThrows(IllegalArgumentException.class, ()->c.returnItem("djembe"));
            //Customer's rented items list is unchanged from 0
        assertEquals(0,c.getRentedItemsSize());
            //Store's rented items list is unchanged from 0
        assertEquals(0,ms.getRentedSize());
            //Store's inventory is unchanged from 1
        assertEquals(1,ms.getInventorySize());
            //Renter name associated with item is "n/a"
        assertEquals("n/a", i2.getRenterName());

        c.rentItem("guitar",e);

        //Narrative 1: Customer returns an item not from the store while renting an item from the store
            //Exception is thrown for item not from the store
        assertThrows(IllegalArgumentException.class, ()->c.returnItem("djembe"));
            //Customer's rented items list is unchanged from 1
        assertEquals(1,c.getRentedItemsSize());
            //Store's inventory is unchanged from 0
        assertEquals(0,ms.getInventorySize());
            //Store's rented items list is unchanged from 1
        assertEquals(1,ms.getRentedSize());
            //Renter name associated with item is "n/a"
        assertEquals("n/a", i2.getRenterName());

        //Narrative 2: Customer returns an item they rented from the store
            //Item returned is the item that the customer rented
        assertEquals(i,c.returnItem("guitar"));
            //Customer's rented items list has decreased to zero from one
        assertEquals(0,c.getRentedItemsSize());
            //Store's inventory increases by one from zero
        assertEquals(1,ms.getInventorySize());
            //Item returned to inventory is item returned by the customer
        assertEquals(i, ms.getInventoryItem(0));
            //Store's rented items list decreases to zero from one
        assertEquals(0,ms.getRentedSize());
            //Renter Name associated with item is "n/a"
        assertEquals("n/a",i.getRenterName());
            //Transaction for return item still exists for customer
        assertEquals(1, c.getTransactionHistorySize());

        //add narrative for trying to return an item they've already canceled
        //add narrative for trying to return an item they've already returned
        //add narrative for invalid return when there's only a transaction for a room in TH
    }
    @Test
    void getandfindTransactionTest() {
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Item i = new Item("guitar",45, "n/a");
        Employee e = new Employee(10101,"Todd",ms);
        ms.addToInventory(i);
        
        //find transaction using index
        //index out of range, empty list
        assertThrows(IndexOutOfBoundsException.class, ()->c.getTransaction(0));
        assertThrows(IndexOutOfBoundsException.class, ()->c.getTransaction(1));
        assertThrows(IndexOutOfBoundsException.class, ()->c.getTransaction(-1));
        
        c.rentItem("guitar",e);
        //index out of range, non-empty list
        assertThrows(IndexOutOfBoundsException.class, ()->c.getTransaction(1));
        //item is e
        assertEquals(ms.getRentedItem(0),c.getTransaction(0).getItemRented());
        c.cancelItemRental("guitar");

        //findTransaction using itemName
        //empty list, item not in inventory
        assertThrows(IllegalArgumentException.class, ()->c.findTransaction("djembe"));
        //empty list, item in inventory
        assertThrows(IllegalArgumentException.class,()->c.findTransaction("guitar"));

        c.rentItem("guitar",e);
        //non empty list, item never in inventory
        assertThrows(IllegalArgumentException.class, ()->c.findTransaction("djembe"));
        //transaction in list
        assertEquals(i,c.findTransaction("guitar").getItemRented());

    }

    @Test
    void findRentedItemTest(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Item i = new Item("guitar",45, "n/a");
        Employee e = new Employee(10101,"Todd",ms);
        ms.addToInventory(i);

        //empty list, item not in inventory
        assertThrows(IllegalArgumentException.class, ()->c.findRentedItem("djembe"));
        //empty list, item in inventory
        assertThrows(IllegalArgumentException.class,()->c.findRentedItem("guitar"));

        c.rentItem("guitar",e);
        //non empty list, item never in inventory
        assertThrows(IllegalArgumentException.class, ()->c.findRentedItem("djembe"));
        //transaction in list
        assertEquals(i,c.findRentedItem("guitar"));
        
    }

    @Test
    void cancelItemRentalTest() {
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Employee e = new Employee(10101,"Todd",ms);
        ms.addToInventory(new Item("guitar",45, "n/a"));
        Room r = new Room(1);
        ms.addToRoomList(r);
        
        //Narrative 0: Customer cancels a nonexistent transaction for an item from the store
        //with an empty transaction history(TH)
            //Exception is thrown for the invalid item
        assertThrows(IllegalArgumentException.class, ()->c.cancelItemRental("guitar"));
            //Customer TH is unchanged from 0
        assertEquals(0,c.getTransactionHistorySize());
            //Customer rented items list is unchanged from 0
        assertEquals(0, c.getRentedItemsSize());

        //Narrative 0.5: Customer cancels a nonexistent transaction for an item not from store
        //with an empty transaction history
            //Exception is thrown for the invalid item
        assertThrows(IllegalArgumentException.class, ()->c.cancelItemRental("coffee"));
            //Customer TH is unchanged from 0
        assertEquals(0,c.getTransactionHistorySize());
            //Customer rented items list is unchanged from 0
        assertEquals(0, c.getRentedItemsSize());
        
        c.rentItem("guitar",e);

        //Narrative 1: Customer cancels a nonexistent transaction for an item not from store
        //with a nonempty transaction history
            //Exception is thrown for the invalid item
        assertThrows(IllegalArgumentException.class, ()->c.cancelItemRental("coffee"));
            //Customer TH is unchanged
        assertEquals(1,c.getTransactionHistorySize());
            //Customer rented items list is unchanged from 1
        assertEquals(1, c.getRentedItemsSize());
        
        Transaction t = c.getTransaction(0);
        

        //Narrative 2: Customer cancels transaction for item from store they rented
            //Transaction canceled is the transaction for the guitar
        assertEquals(t,c.cancelItemRental("guitar"));
            //Customer TH is decreased by one
        assertEquals(0,c.getTransactionHistorySize());
            //Customer's rented items list decreases by one
        assertEquals(0,c.getRentedItemsSize());
            //Store's inventory increases by one from zero
        assertEquals(1, ms.getInventorySize());
            //Store's rented item list decreases to zero from one
        assertEquals(0,ms.getRentedSize());
            //Renter name for item is "n/a"
        Item i = ms.getInventoryItem(0);
        assertEquals("n/a", i.getRenterName());

        c.rentRoom(1,e);
        c.rentItem("guitar", e);
        Transaction t2 = c.getTransaction(1);

        //Narrative 3: Customer cancels transaction when TH contains a room rental transaction
            //Transaction canceled is the transaction for the guitar
        assertEquals(t2,c.cancelItemRental("guitar"));
            //TH still contains room transaction (goes from 2 to 1 transactions)
        assertEquals(1,c.getTransactionHistorySize());


        //add narrative for invalid cancellation when item was already returned
        //add narrative for invalid cancellation when item was already canceled
        //add narrative for ensuring most recent transaction is getting canceled
    }

    @Test
    void rentRoomTest(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Employee e = new Employee(10101,"Todd",ms);
        Room room =new Room(1);
        ms.addToRoomList(room);
        int num = room.getRoomNumber();
        //Narrative 0: Customer tries to rent a room with stuff already in it
            //Exception is thrown for invalid room
        room.setHasEquipment(true);
        assertThrows(IllegalArgumentException.class,()->c.rentRoom(num,e));
            //Customer TH is unchanged from 0
        assertEquals(0, c.getTransactionHistorySize());
            //Room renter name is "n/a"
        assertEquals("n/a", room.getRenterName());

        //Narrative 0.5: Customer tries to rent room with people and stuff already in it
            //Exception is thrown for invalid room
        room.setIsEmptyRoom(false);
        assertThrows(IllegalArgumentException.class,()->c.rentRoom(num,e));
            //Customer TH is unchanged from 0
        assertEquals(0, c.getTransactionHistorySize());
            //Room renter name is "n/a"
        assertEquals("n/a", room.getRenterName());

        //Narrative 0.75: Customer tries to rent a room with just people already in it
        room.setHasEquipment(false);
        assertThrows(IllegalArgumentException.class,()->c.rentRoom(num,e));
            //Customer TH is unchanged from 0
        assertEquals(0, c.getTransactionHistorySize());
            //Room renter name is "n/a"
        assertEquals("n/a", room.getRenterName());

        //room is rentable again
        room.setIsEmptyRoom(true);

        //Narrative 1: Customer rents available room
            //Room rental is for the room's price
        assertEquals(50,c.rentRoom(num,e));
            //Room's renter name is the customer's name
        assertEquals("Bob",room.getRenterName());
            //Transaction is created for rental (0 to 1)
        assertEquals(1,c.getTransactionHistorySize());
            //Transaction created is for the room the customer rented
        Transaction t = c.getTransaction(0);
        assertEquals(room,t.getRoomRented());
            //Room is now considered occupied by the customer
        assertFalse(room.getIsEmptyRoom());
       

        //Narrative 2: Customer tries to rent the room they're already renting
            //Exception is thrown for invalid room
        assertThrows(IllegalArgumentException.class, ()->c.rentRoom(1, e));
            //TH is unchanged from 1
        assertEquals(1,c.getTransactionHistorySize());

        Customer d = new Customer(ms,"Fran");

        //Narrative 3: Another customer tries to rent a room another customer is already renting
            //Exception is thrown for invalid room
        assertThrows(IllegalArgumentException.class,()->d.rentRoom(1,e));
            //New customer's TH is unchanged 
        assertEquals(0,d.getTransactionHistorySize());
            //Room's renter name is unchanged from the original customer's name
        assertEquals("Bob",room.getRenterName());
        
        //add narrative for room not in store list
    }
    @Test
    void returnRoomTest(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Employee e = new Employee(10101,"Todd",ms);
        Room room =new Room(1);

        //Narrative 0: Customer returns a room not considered one of the store's
            //Exception is thrown
        assertThrows(IllegalArgumentException.class,()->c.returnRoom(1));

        ms.addToRoomList(room);

        //Narrative 0.5: Customer returns a room they haven't rented yet
            //Exception is thrown
        assertThrows(IllegalArgumentException.class,()->c.returnRoom(1));
        
        c.rentRoom(1, e);
        assertFalse(room.getIsEmptyRoom());
        assertEquals("Bob",room.getRenterName());
        Customer d = new Customer(ms,"Fran");

        //Narrative 1: A customer tries to return a room they haven't rented that's being rented by another customer
            //Exception is thrown
        assertThrows(IllegalArgumentException.class,()->d.returnRoom(1));
            //Room renter name is still that of the original customer
        assertEquals("Bob", room.getRenterName());
            //Room is still occupied
        assertFalse(room.getIsEmptyRoom());
        
        c.returnRoom(1);

        //Narrative 2: Customer returns room they've rented from the store
            //Room is considered empty
        assertTrue(room.getIsEmptyRoom());
            //Room renter name is set back to "n/a"
        assertEquals("n/a",room.getRenterName());
            //Transaction for room rental still exists in TH
        assertEquals(1, c.getTransactionHistorySize());

        //add narrative for a customer that's returning a room they've already returned
        //add narrative for a customer that's returning a room they've already canceled
        //add narrative for trying to return a room when they've only rented an item

    } 
    @Test
    void cancelRoomTest(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Employee e = new Employee(10101,"Todd",ms);
        Room room =new Room(1);

        //Narrative 0: Customer cancels a room not considered one of the store's
            //Exception is thrown
        assertThrows(IllegalArgumentException.class,()->c.cancelRoom(1));
            //TH is unchanged from 0
        assertEquals(0,c.getTransactionHistorySize());

        ms.addToRoomList(room);
        

        //Narrative 0.5: Customer cancels a room they haven't rented yet
            //Exception is thrown
        assertThrows(IllegalArgumentException.class, ()->c.cancelRoom(1));
            //TH is unchanged from 0
        assertEquals(0,c.getTransactionHistorySize());

        Item i =new Item("guitar",45, "n/a");
        ms.addToInventory(i);
        c.rentItem("guitar",e); //TH = 1

        //Narrative 1: Customer tries to cancel a room they haven't rented yet
        //when their TH has an item transaction recorded
            //Exception is thrown
        assertThrows(IllegalArgumentException.class, ()->c.cancelRoom(1));
            //TH is unchanged from 1
        assertEquals(1,c.getTransactionHistorySize());

        c.rentRoom(1,e); //TH = 2
        c.returnRoom(1);

        //Narrative 2: Customer tries to cancel a room they've already returned
            //Exception is thrown
        assertThrows(IllegalArgumentException.class, ()->c.cancelRoom(1));
            //TH is unchanged from 2
        assertEquals(2,c.getTransactionHistorySize());

        
        c.cancelItemRental("guitar"); //TH=1 for returned room
        c.rentRoom(1,e);
        Transaction t = c.getTransaction(1);

        //Narrative 4: Customer cancels a new transaction for a room they've rented and returned before
            //Transaction cancelled is the most recent transaction for the room
        assertEquals(t,c.cancelRoom(1));
            //TH decreases from 2 to 1
        assertEquals(1,c.getTransactionHistorySize());
            //Room renter name is "n/a"
        assertEquals("n/a", room.getRenterName());
            //Room is empty
        assertTrue(room.getIsEmptyRoom());

        //add narrative for room cancellation when no prior transaction exists
    }
    
   
}