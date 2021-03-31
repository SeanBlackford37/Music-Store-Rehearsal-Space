package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void constructorTest() throws NullPointerException{
        

        //exception is thrown when music store is null
        assertThrows(NullPointerException.class,()->new Customer(null));

        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms);

        //customer is created when music store is not null
        assertNotEquals(null, c);

        //customer has t list 
        assertNotEquals(null,c.getTransactionHistory());

        //customer has empty t list
        assertEquals(0,c.getTransactionHistorySize());

    }

    @Test
    void rentItemTest() throws IllegalArgumentException{
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms);
        ms.addToInventory(new Item("guitar",45, null));
        //Item is in inventory and price is correct
        assertEquals(45, c.rentItem("guitar"));
        assertEquals(1,c.getTransactionHistorySize());

        //update value with today's date
        assertEquals(c.findTransaction(0), c.findTransaction("guitar"));
        assertEquals(0,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());

        ms.addToInventory(new Item("djembe",150,null));
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
        Customer d = new Customer(ms);
        assertThrows(IllegalArgumentException.class, ()->d.rentItem("guitar"));
        assertEquals(0,d.getTransactionHistorySize());
        assertEquals(1,c.getTransactionHistorySize());
        assertEquals(1,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());



    }
    @Test 
    void returnItemTest() throws IllegalArgumentException{
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms);
        ms.addToInventory(new Item("guitar",45, null));
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

        c.returnItem("guitar");

        assertEquals(1,ms.getInventorySize());
        assertEquals(0,ms.getRentedSize());
        assertEquals(0,c.getRentedItemsSize());
    }

    @Test
    void cancelRentalTest() throws IllegalArgumentException, RuntimeException{
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms);
        ms.addToInventory(new Item("guitar",45, null));
        
        //no transactions recorded for customer
        assertThrows(RuntimeException.class, ()->c.cancelItemRental("guitar"));
        
        c.rentItem("guitar");

        //no transaction for item listed
        assertThrows(IllegalArgumentException.class, ()->c.cancelItemRental("coffee"));
        
        Transaction t = c.findTransaction(0);

        //transaction exists for item
        assertEquals(t,c.cancelItemRental("guitar"));
        assertEquals(0,c.getTransactionHistorySize());

    }

}