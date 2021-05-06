package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TransactionTest {
    @Test
    void constructorTest(){ //Integration Test
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms,"Bob");
        Item i = new Item("djembe",150,"Bob");
        Employee e = new Employee(10101,"Todd",ms);
        Transaction t = new Transaction(i,c,e);

        //case where customer only rents an item
        assertNotEquals(null, t); //Equivalence class
        assertThrows(IllegalArgumentException.class,()->new Transaction(i,null,null)); //Border Case
        
        assertEquals(150, t.getOrderAmount()); //Equivalence class
        assertEquals(i,t.getItemRented()); //Equivalence class
        assertEquals(c,t.getBuyer()); //Equivalence class
        assertEquals(e,t.getSeller()); //Equivalence class
        assertTrue(t.getDescription().contains("Bob")); //Equivalence class
        assertTrue(t.getDescription().contains("djembe")); //Equivalence class
        assertTrue(t.getDescription().contains(String.valueOf(t.getOrderAmount()))); //Equivalence class
        assertTrue(t.getDescription().contains("Todd")); //Equivalence class

        //case where customer only rents a room
        Room r = new Room(1);
        Transaction rt = new Transaction(c,e,r);
        assertNotEquals(null, rt);
        assertThrows(IllegalArgumentException.class,()->new Transaction(c,null,null)); //Border Case
        assertThrows(IllegalArgumentException.class,()->new Transaction(null,e,null)); //Border Case
        assertThrows(IllegalArgumentException.class,()->new Transaction(null,null, r)); //Border Case

        assertEquals(50, rt.getOrderAmount()); //Equivalence class
        assertEquals(r,rt.getRoomRented()); //Equivalence class
        assertEquals(c,rt.getBuyer()); //Equivalence class
        assertEquals(e,t.getSeller()); //Equivalence class
        assertTrue(rt.getDescription().contains("Bob")); //Equivalence class
        assertTrue(rt.getDescription().contains(String.valueOf(1))); //Equivalence class
        assertTrue(rt.getDescription().contains(String.valueOf(rt.getOrderAmount()))); //Equivalence class
        assertTrue(rt.getDescription().contains("Todd")); //Equivalence class
    }

    @Test
    void constructorTestMutipleItems(){ //Integration Test
        MusicStore ms = new MusicStore("ms");
        ArrayList<Item> itemsToRent = new ArrayList<Item>();
        itemsToRent.add(new Item("Piano", 30, "none"));
        itemsToRent.add(new Item("Saxophone", 15, "none"));
        itemsToRent.add(new Item("Drums", 50, "none"));
        itemsToRent.add(new Item("Guitar", 15, "none"));
        Transaction transactionOne = new Transaction(itemsToRent, new Customer(ms, "Bob"), new Employee(12345, "Jim", ms));
        assertEquals("Bob", transactionOne.getBuyer().getCustomerName()); //Equivalence class
        assertEquals(itemsToRent, transactionOne.getItemsRented()); //Equivalence class
        assertEquals(110, transactionOne.getOrderAmount()); //Equivalence class
        assertThrows(IllegalArgumentException.class,()->new Transaction(itemsToRent,null,null)); //Border Case
    }
    @Test
    void constructorTestOneItemOneRoom(){ //Integration Test
        MusicStore ms = new MusicStore("ms");
        Transaction transactionOne = new Transaction(new Item("Piano", 30, "none"), new Room(true,3,false,"none"), new Customer(ms, "Bob"), new Employee(12345, "Jim", ms));
        assertEquals("Bob", transactionOne.getBuyer().getCustomerName()); //Equivalence class
        assertEquals("Piano", transactionOne.getItemRented().getName()); //Equivalence class
        assertEquals(3, transactionOne.getRoomRented().getRoomNumber()); //Equivalence class
        assertEquals(80, transactionOne.getOrderAmount()); //Equivalence class
        assertThrows(IllegalArgumentException.class,()->new Transaction(new Item("Piano", 30, "none"),null,null)); //Border Case
        
    }
}
