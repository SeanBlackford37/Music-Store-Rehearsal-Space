package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    void constructorTest() { //Unit Test
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        assertEquals("Electric guitar", guitar.getName()); //Equivalence class

        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new Item("Electric guitar", 200, "")); //Border case
        assertThrows(IllegalArgumentException.class, ()-> new Item("", 200, "Sean Blackford")); //Border case
        assertThrows(IllegalArgumentException.class, ()-> new Item("Electric guitar", -200, "Sean Blackford")); //Border case
        
        Item guitarTwo = new Item("Electric guitar", 200);
        assertEquals("Electric guitar", guitarTwo.getName()); //Equivalence class
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new Item("", 200)); //Border case
        assertThrows(IllegalArgumentException.class, ()-> new Item("Electric guitar", -200)); //Border case
        assertThrows(IllegalArgumentException.class, ()-> new Item("Electric guitar", 200.555)); //Border case
        
        
    }
    @Test
    void getNameTest() { //Unit Test
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        assertEquals("Electric guitar", guitar.getName()); //Equivalence class
    }
    @Test
    void getPriceTest() { //Unit Test
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        assertEquals(200, guitar.getPrice()); //Equivalence class
        Item guitarTwo = new Item("Electric guitar", 200.10, "Sean Blackford");
        assertEquals(200.10, guitarTwo.getPrice()); //Equivalence class
    }
    @Test
    void getRenterNameTest() { //Unit Test
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        assertEquals("Sean Blackford", guitar.getRenterName()); //Equivalence class
    }

    @Test
    void setNameTest() { //Unit Test
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        guitar.setName("Guitar");
        assertEquals("Guitar", guitar.getName()); //Equivalence class
    }
    @Test
    void setPriceTest() { //Unit Test
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        guitar.setPrice(300);;
        assertEquals(300, guitar.getPrice()); //Equivalence class
    }
    @Test
    void setRenterNameTest() { //Unit Test
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        guitar.setRenterName("Joe Bob");
        assertEquals("Joe Bob", guitar.getRenterName()); //Equivalence class
    }


    @Test
    void isAmountValid() throws IllegalArgumentException{ //Unit Test
        assertThrows(IllegalArgumentException.class, ()->new Item("Electric guitar", 200.055, "Sean Blackford")); //border case
        assertThrows(IllegalArgumentException.class, ()->new Item("Electric guitar", -200, "Sean Blackford")); //border case
        Item guitar = new Item("Electric guitar", 200, "Sean Blackford");
        assertEquals(200, guitar.getPrice()); //equivalence class
        Item guitarTwo = new Item("Electric guitar", 200.55, "Sean Blackford");
        assertEquals(200.55, guitarTwo.getPrice()); //equivalence class
    }
    
}
