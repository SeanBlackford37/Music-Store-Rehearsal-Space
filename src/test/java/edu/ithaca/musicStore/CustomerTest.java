package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void constructorTest() throws NullPointerException{
        MusicStore ms = null;

        //exception is thrown when music store is null
        assertThrows(NullPointerException.class,()->new Customer(ms));

        ms = new MusicStore();
        Customer c = new Customer(ms);

        //customer is created when music store is not null
        assertNotEquals(null, c);

        //customer has t list 
        assertNotEquals(null,c.getTransactionHistory());

        //customer has empty t list
        assertEquals(0,c.getTransactionHistory().size());

    }

    @Test
    void rentItemTest() throws IllegalArgumentException{
        MusicStore ms = new MusicStore();
        Customer c = new Customer(ms);
        ms.addItem("guitar",45, null);
        //Item is in inventory and price is correct
        assertEquals(45, c.rentItem("guitar"));
        assertEquals(1,c.getTransactionHistory().size());
        //Ask about below
        assertEquals(c.getTransactionHistory().get(0), c.findTransaction("guitar","3302021"));

        //Item isn't in inventory
        //no changes made to transaction list
        assertThrows(IllegalArgumentException.class, ()->c.rentItem("coffee"));
        assertEquals(1,c.getTransactionHistory().size());


        //Item is out of stock/ already being rented by you
        //no changes made to transaction list
        assertThrows(IllegalArgumentException.class, ()->c.rentItem("guitar"));
        assertEquals(1,c.getTransactionHistory().size());

        //Item is out of stock/ already being rented by another customer
        //no changes made to either transaction list
        Customer d = new Customer(ms);
        assertThrows(IllegalArgumentException.class, ()->d.rentItem("guitar"));
        assertEquals(0,d.getTransactionHistory().size());
        assertEquals(1,c.getTransactionHistory().size());



    }

    @Test
    void cancelRentalTest() throws IllegalArgumentException, RuntimeException{
        MusicStore ms = new MusicStore();
        Customer c = new Customer(ms);
        ms.addItem("guitar",45);
        
        //no transactions recorded for customer
        assertThrows(RuntimeException.class, ()->c.cancelItemRental("guitar"));
        
        c.rentItem("guitar");

        //no transaction for item listed
        assertThrows(IllegalArgumentException.class, ()->c.cancelItemRental("coffee"));
        Transaction t = c.getTransactionHistory().get(0);

        //transaction exists for item
        assertEquals(t,c.cancelItemRental("guitar"));
        assertEquals(0,c.getTransactionHistory().size());

        //by restoring it to inventory, would we be removing the item when renting?
        // or would we be updating the item itself

        
    }

    @Test
    void calculatePriceTest() {
    }

}