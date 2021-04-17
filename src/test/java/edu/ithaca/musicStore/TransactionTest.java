package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}
