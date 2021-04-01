package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MusicStoreTest {
    
    @Test
    void createStoreTest() {
        //Create store
        MusicStore store = new MusicStore("Fancy Store");
        assertEquals("Fancy Store", store.getStoreName());

    }

    @Test
    void addItemTest() {
        MusicStore store = new MusicStore("Fancy Store");
        //Add valid item
        store.addToInventory(new Item("Guitar", 94, "none"));
        assertEquals(1, store.getInventorySize());
        assertEquals("Guitar", store.getInventoryItem(0).getName());

        //Add same item
        store.addToInventory(new Item("Guitar", 94, "none"));
        assertEquals(2, store.getInventorySize());
        assertEquals("Guitar", store.getInventoryItem(1).getName());

    }

    @Test
    void removeItemTest() {
        MusicStore store = new MusicStore("Fancy Store");
        store.addToInventory(new Item("Guitar", 94, "none"));
        //remove valid item
        store.removeFromInventory("Guitar", 94);
        assertEquals(0, store.getInventorySize());

        //Remove when items have same name but different price
        store.addToInventory(new Item("Guitar", 94, "none"));
        store.addToInventory(new Item("Guitar", 93, "none"));
        store.removeFromInventory("Guitar", 94);
        assertEquals(1, store.getInventorySize());
        assertEquals(93, store.getInventoryItem(0).getPrice());

        //Remove an item that doesnt exist
        assertThrows(IllegalArgumentException.class, ()->store.removeFromInventory("Bongos", 50)); 
    }

    @Test
    void searchForItemTest() {
        MusicStore store = new MusicStore("Fancy Store");
        store.addToInventory(new Item("Flute", 23, "none"));
        store.addToInventory(new Item("Guitar", 94, "none"));
        store.addToInventory(new Item("Guitar", 94, "none"));
        store.addToInventory(new Item("Keyboard", 130, "none"));

        //Search for valid item at front
        assertEquals(0, store.searchForInventoryItem("Flute"));

        //Search for item when there are two of the same
        assertEquals(1, store.searchForInventoryItem("Guitar"));

        //Search for item at the end
        assertEquals(3, store.searchForInventoryItem("Keyboard"));

        //Search for item that doesnt exist
        assertEquals(-1, store.searchForInventoryItem("Bongos"));
    }

    @Test
    void moveToRentedTest() {
        MusicStore store = new MusicStore("Fancy Store");
        store.addToInventory(new Item("Flute", 23, "none"));
        store.addToInventory(new Item("Guitar", 94, "none"));
        store.addToInventory(new Item("Guitar", 94, "none"));
        store.addToInventory(new Item("Keyboard", 130, "none"));
        
        //Move Valid Item
        store.moveToRented("Flute");
        assertEquals(-1, store.searchForInventoryItem("Flute"));
        assertEquals(0, store.searchForRentedItem("Flute"));

        //Move when two identical items exist
        store.moveToRented("Guitar");
        assertEquals(2, store.getInventorySize());
        assertEquals(0, store.searchForInventoryItem("Guitar"));
        assertEquals(1, store.searchForRentedItem("Guitar"));

        //Move when an item doesnt exist
        assertThrows(IllegalArgumentException.class, ()->store.moveToRented("Bongos")); 

    }

    @Test
    void moveToInventoryTest() {
        MusicStore store = new MusicStore("Fancy Store");
        store.addToRented(new Item("Flute", 23, "none"));
        store.addToRented(new Item("Guitar", 94, "none"));
        store.addToRented(new Item("Guitar", 94, "none"));
        store.addToRented(new Item("Keyboard", 130, "none"));
        
        //Move Valid Item
        store.moveToInventory("Flute");
        assertEquals(-1, store.searchForRentedItem("Flute"));
        assertEquals(0, store.searchForInventoryItem("Flute"));

        //Move when two identical items exist
        store.moveToInventory("Guitar");
        assertEquals(2, store.getRentedSize());
        assertEquals(0, store.searchForRentedItem("Guitar"));
        assertEquals(1, store.searchForInventoryItem("Guitar"));

        //Move when an item doesnt exist
        assertThrows(IllegalArgumentException.class, ()->store.moveToInventory("Bongos")); 

    }

    @Test
    void addRoomTest() {
        MusicStore store = new MusicStore("Fancy Store");
        //Add valid item
        store.addToRoomList(new Room(true, 1, false, "none"));
        assertEquals(1, store.getRoomListSize());
        assertEquals(1, store.getRoom(0).getRoomNumber());

        //Add same item
        store.addToRoomList(new Room(true, 1, false, "none"));
        assertEquals(2, store.getRoomListSize());
        assertEquals(1, store.getRoom(1).getRoomNumber());

    }

    @Test
    void removeRoomTest() {
        MusicStore store = new MusicStore("Fancy Store");
        store.addToRoomList(new Room(true, 1, false, "none"));
        //remove valid item
        store.removeFromRoomList(1);
        assertEquals(0, store.getRoomListSize());

        //Remove when items have same name but different price
        store.addToRoomList(new Room(true, 1, false, "none"));
        store.addToRoomList(new Room(true, 1, false, "none"));
        store.removeFromRoomList(1);
        assertEquals(1, store.getRoomListSize());
        assertEquals(1, store.getRoom(0).getRoomNumber());

        //Remove an item that doesnt exist
        assertThrows(IllegalArgumentException.class, ()->store.removeFromRoomList(50)); 
    }

    @Test
    void searchForRoomTest() {
        MusicStore store = new MusicStore("Fancy Store");
        store.addToRoomList(new Room(true, 1, false, "none"));
        store.addToRoomList(new Room(true, 2, false, "none"));
        store.addToRoomList(new Room(true, 3, false, "none"));
        store.addToRoomList(new Room(true, 4, false, "none"));

        //Search for valid item at front
        assertEquals(0, store.findRoom(1));

        //Search for item when there are two of the same
        assertEquals(0, store.findRoom(2));

        //Search for item at the end
        assertEquals(0, store.findRoom(4));

        //Search for item that doesnt exist
        assertEquals(-1, store.findRoom(30));
    }

}
