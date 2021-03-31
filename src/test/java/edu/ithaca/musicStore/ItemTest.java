package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    void constructorTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        assertEquals("Electric guitar", guitar.getName()); //equivalence class

        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new Item("Electric guitar", 200, "")); //border case
        assertThrows(IllegalArgumentException.class, ()-> new Item("", 200, "Sean Blackford")); //border case
        assertThrows(IllegalArgumentException.class, ()-> new Item("Electric guitar", -200, "Sean Blackford")); //border case
        
        Item guitarTwo = new Item("Electric guitar", 200);
        assertEquals("Electric guitar", guitarTwo.getName()); //equivalence class
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new Item("", 200)); //border case
        assertThrows(IllegalArgumentException.class, ()-> new Item("Electric guitar", -200)); //border case
        assertThrows(IllegalArgumentException.class, ()-> new Item("Electric guitar", 200.555)); //border case
        
        
    }
    @Test
    void getNameTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        assertEquals("Electric guitar", guitar.getName());
    }
    @Test
    void getPriceTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        assertEquals(200, guitar.getPrice());
        Item guitarTwo = new Item("Electric guitar", 200.10, "Sean Blackford");
        assertEquals(200.10, guitarTwo.getPrice());
    }
    @Test
    void getRenterNameTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        assertEquals("Sean Blackford", guitar.getRenterName());
    }

    @Test
    void setNameTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        guitar.setName("Guitar");
        assertEquals("Guitar", guitar.getName());
    }
    @Test
    void setPriceTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        guitar.setPrice(300);;
        assertEquals(300, guitar.getPrice());
    }
    @Test
    void setRenterNameTest() {
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        guitar.setRenterName("Joe Bob");
        assertEquals("Joe Bob", guitar.getRenterName());
    }


    @Test
    void isAmountValid() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, ()->new Item("Electric guitar", 200.055, "Sean Blackford")); //border case
        assertThrows(IllegalArgumentException.class, ()->new Item("Electric guitar", -200, "Sean Blackford")); //border case
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        assertEquals(200, guitar.getPrice()); //equivalence class
        Item guitarTwo = new Item("Electric guitar", 200.55, "Sean Blackford");
        assertEquals(200.55, guitarTwo.getPrice()); //equivalence class
    }
    
}
