package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    void getNameTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");

        assertEquals("Electric guitar", guitar.getName());
    }
    @Test
    void getPriceTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");

        assertEquals(200, guitar.getPrice());
    }
    @Test
    void getRenterNameTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");

        assertEquals("Sean Blackford", guitar.getRenterName());
    }

    @Test
    void isAmountValid(){
        assertThrows(IllegalArgumentException.class, ()->new Item("Electric guitar", 200.055, "Sean Blackford")); //border case
        assertThrows(IllegalArgumentException.class, ()->new Item("Electric guitar", -200, "Sean Blackford")); //border case
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        assertEquals(200, guitar.getPrice()); //equivalence class
        Item guitarTwo = new Item("Electric guitar", 200.55, "Sean Blackford");
        assertEquals(200.55, guitarTwo.getPrice()); //equivalence class
    }
    
}
