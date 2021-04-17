package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TransactionTest {
    @Test
    void constructorTest(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms,"Bob");
        Item i = new Item("djembe",150,"Bob");
        Employee e = new Employee(10101,"Todd",ms);
        Transaction t = new Transaction(i,c,e);

        //case where customer only rents an item
        assertNotEquals(null, t);
        assertThrows(IllegalArgumentException.class,()->new Transaction(i,null,null));
        
        assertEquals(150, t.getOrderAmount());
        assertEquals(i,t.getItemRented());
        assertEquals(c,t.getBuyer());
        assertEquals(e,t.getSeller());
        assertTrue(t.getDescription().contains("Bob"));
        assertTrue(t.getDescription().contains("djembe"));
        assertTrue(t.getDescription().contains(String.valueOf(t.getOrderAmount())));
        assertTrue(t.getDescription().contains("Todd"));

        //case where customer only rents a room
        Room r = new Room(1);
        Transaction rt = new Transaction(c,e,r);
        assertNotEquals(null, rt);
        assertThrows(IllegalArgumentException.class,()->new Transaction(c,null,null));
        assertThrows(IllegalArgumentException.class,()->new Transaction(null,e,null));
        assertThrows(IllegalArgumentException.class,()->new Transaction(null,null, r));

        assertEquals(50, rt.getOrderAmount());
        assertEquals(r,rt.getRoomRented());
        assertEquals(c,rt.getBuyer());
        assertEquals(e,t.getSeller());
        assertTrue(rt.getDescription().contains("Bob"));
        assertTrue(rt.getDescription().contains(String.valueOf(1)));
        assertTrue(rt.getDescription().contains(String.valueOf(rt.getOrderAmount())));
        assertTrue(rt.getDescription().contains("Todd"));
    }

    @Test
    void constructorTestMutipleItems(){
        MusicStore ms = new MusicStore("ms");
        ArrayList<Item> itemsToRent = new ArrayList<Item>();
        itemsToRent.add(new Item("Piano", 30, "none"));
        itemsToRent.add(new Item("Saxophone", 15, "none"));
        itemsToRent.add(new Item("Drums", 50, "none"));
        itemsToRent.add(new Item("Guitar", 15, "none"));
        Transaction transactionOne = new Transaction(itemsToRent, new Customer(ms, "Bob"), new Employee(12345, "Jim", ms));
        assertEquals("Bob", transactionOne.getBuyer().getCustomerName());
        assertEquals(itemsToRent, transactionOne.getItemsRented());
        assertEquals(110, transactionOne.getOrderAmount());
        assertThrows(IllegalArgumentException.class,()->new Transaction(itemsToRent,null,null));
    }
    @Test
    void constructorTestOneItemOneRoom(){
        MusicStore ms = new MusicStore("ms");
        Transaction transactionOne = new Transaction(new Item("Piano", 30, "none"), new Room(true,3,false,"none"), new Customer(ms, "Bob"), new Employee(12345, "Jim", ms));
        assertEquals("Bob", transactionOne.getBuyer().getCustomerName());
        assertEquals("Piano", transactionOne.getItemRented().getName());
        assertEquals(3, transactionOne.getRoomRented().getRoomNumber());
        assertEquals(80, transactionOne.getOrderAmount());
        assertThrows(IllegalArgumentException.class,()->new Transaction(new Item("Piano", 30, "none"),null,null));
        
    }
}
