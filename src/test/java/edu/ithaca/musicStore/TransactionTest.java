package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    @Test
    void constructorTest(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms,"Bob");
        Item i = new Item("djembe",150,"Bob");
        Transaction t = new Transaction(i,c);

        assertNotEquals(null, t);
        assertThrows(IllegalArgumentException.class,()->new Transaction(null,null));
        assertThrows(IllegalArgumentException.class,()->new Transaction(i,null));
        assertThrows(IllegalArgumentException.class,()->new Transaction(null,c));

        assertEquals(150, t.getOrderAmount());
    }
}
