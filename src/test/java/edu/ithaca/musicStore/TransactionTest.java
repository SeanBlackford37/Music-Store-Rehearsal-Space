package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    @Test
    void constructorTest(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms,"Bob");
        Item i = new Item("djembe",150,"Bob");
        Employee e = new Employee(10101,"Todd");
        Transaction t = new Transaction(i,c,e);

        //case where customer only rents an item
        assertNotEquals(null, t);
        assertThrows(IllegalArgumentException.class,()->new Transaction(i,null,null));
        assertThrows(IllegalArgumentException.class,()->new Transaction(null,c,null));
        assertThrows(IllegalArgumentException.class,()->new Transaction(null,null, e));

        assertEquals(150, t.getOrderAmount());
        assertEquals(i,t.getItemRented());
        assertEquals(c,t.getBuyer());
        assertTrue(t.getDescription().contains("Bob"));
        assertTrue(t.getDescription().contains("djembe"));
        assertTrue(t.getDescription().contains(String.valueOf(t.getOrderAmount())));
        assertTrue(t.getDescription().contains("Todd"));

        //case where customer only rents a room
    }
}
