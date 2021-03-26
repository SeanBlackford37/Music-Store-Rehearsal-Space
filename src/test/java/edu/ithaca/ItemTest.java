package edu.ithaca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    void getNameTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");

        assertEquals("Electric guitar", guitar.getName());
    }
    void getPriceTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");

        assertEquals(200, guitar.getPrice());
    }
    void getRenterNameTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");

        assertEquals("Sean Blackford", guitar.getRenterName());
    }
    
}
