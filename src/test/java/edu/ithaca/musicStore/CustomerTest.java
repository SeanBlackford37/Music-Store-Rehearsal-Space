package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        ms.addToInventory(new Item("guitar",45, "n/a"));
        //Item is in inventory and price is correct
        assertEquals(45, c.rentItem("guitar"));
        assertEquals(1,c.getTransactionHistorySize());

        //update value with today's date
        assertEquals(c.findTransaction(0), c.findTransaction("guitar"));
        assertEquals(0,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());

        ms.addToInventory(new Item("djembe",150,"n/a"));
        //Item isn't in inventory
        //no changes made to transaction list
        assertThrows(IllegalArgumentException.class, ()->c.rentItem("coffee"));
        assertEquals(1,c.getTransactionHistorySize());
        assertEquals(1,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());


        //Item is out of stock/ already being rented by you
        //no changes made to transaction list
        assertThrows(IllegalArgumentException.class, ()->c.rentItem("guitar"));
        assertEquals(1,c.getTransactionHistorySize());
        assertEquals(1,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());

        //Item is out of stock/ already being rented by another customer
        //no changes made to either transaction list
        Customer d = new Customer(ms, "Bob");
        assertThrows(IllegalArgumentException.class, ()->d.rentItem("guitar"));
        assertEquals(0,d.getTransactionHistorySize());
        assertEquals(1,c.getTransactionHistorySize());
        assertEquals(1,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());



    }
    @Test 
    void returnItemTest() {
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        ms.addToInventory(new Item("guitar",45, "n/a"));
        //no items rented, item in inventory
        assertThrows(IllegalArgumentException.class, ()->c.returnItem("guitar"));
        //no items rented, item not in inventory
        assertThrows(IllegalArgumentException.class, ()->c.returnItem("djembe"));

        c.rentItem("guitar");

        // item rented, not in inventory
        assertThrows(IllegalArgumentException.class, ()->c.returnItem("djembe"));

        assertEquals(0,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());
        assertEquals(1,c.getRentedItemsSize());

        Item i = ms.getRentedItem(0);
        assertEquals(i,c.returnItem("guitar"));

        assertEquals(1,ms.getInventorySize());
        assertEquals(0,ms.getRentedSize());
        assertEquals(0,c.getRentedItemsSize());
    }
    @Test
    void findTransactionTest() {
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        ms.addToInventory(new Item("guitar",45, "n/a"));
        
        //index out of range, empty list
        assertThrows(IndexOutOfBoundsException.class, ()->c.findTransaction(0));
        assertThrows(IndexOutOfBoundsException.class, ()->c.findTransaction(1));
        assertThrows(IndexOutOfBoundsException.class, ()->c.findTransaction(-1));

       


        
        c.rentItem("guitar");
        //index out of range, non-empty list
        assertThrows(IndexOutOfBoundsException.class, ()->c.findTransaction(1));
        //item is e
        assertEquals(ms.getRentedItem(0),c.findTransaction(0).getItemRented());



        
    }

    @Test
    void cancelRentalTest() {
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        ms.addToInventory(new Item("guitar",45, "n/a"));
        
        //no transactions recorded for customer
        assertThrows(IllegalArgumentException.class, ()->c.cancelItemRental("guitar"));
        
        c.rentItem("guitar");

        //no transaction for item listed
        assertThrows(IllegalArgumentException.class, ()->c.cancelItemRental("coffee"));
        
        Transaction t = c.findTransaction(0);

        //transaction exists for item
        assertEquals(t,c.cancelItemRental("guitar"));
        assertEquals(0,c.getTransactionHistorySize());

    }

}