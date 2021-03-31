package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    @Test
    void constructorTest() throws IllegalArgumentException {
        Room roomOne = new Room(false,210,true,"Sean");
        assertEquals("Sean", roomOne.getRenterName()); //equivalence class

        Room roomTwo = new Room(false,210,true,"Joe"); //equivalence class
        assertEquals("Joe", roomTwo.getRenterName()); //equivalence class
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new Room(false,210,true,"")); //border case
        assertThrows(IllegalArgumentException.class, ()-> new Room(false,-210,true,"Sean")); //border case
        assertThrows(IllegalArgumentException.class, ()-> new Room(true,210,true,"Sean")); //border case
        Room roomThree = new Room(true,210,false,""); //equivalence class
        assertEquals(false, roomThree.getHasEquipment()); //equivalence class
    }

    @Test
    void getIsEmptyRoomTest(){
        Room roomOne = new Room(false,210,true,"Sean");
        assertEquals(false, roomOne.getIsEmptyRoom()); //equivalence class
        Room roomTwo = new Room(true,210,false,"");
        assertEquals(true, roomTwo.getIsEmptyRoom()); //equivalence class
    }
    @Test
    void getRoomNumberTest(){
        Room roomOne = new Room(false,210,true,"Sean");
        assertEquals(210, roomOne.getRoomNumber()); //equivalence class
    }
    @Test
    void getHasEquipmentTest(){
        Room roomOne = new Room(false,210,true,"Sean");
        assertEquals(true, roomOne.getHasEquipment()); //equivalence class
    }
    @Test
    void getRenterNameTest(){
        Room roomOne = new Room(false,210,true,"Sean");
        assertEquals("Sean", roomOne.getRenterName()); //equivalence class
    }
    
    
    @Test
    void setIsEmptyRoomTest(){ //This may be an issue
        Room roomOne = new Room(false,210,true,"Sean");
        assertEquals(false, roomOne.getIsEmptyRoom()); //equivalence class
        roomOne.setIsEmptyRoom(true);
        assertEquals(true, roomOne.getIsEmptyRoom()); //equivalence class
    }
    @Test
    void setRoomNumberTest(){
        Room roomOne = new Room(false,210,true,"Sean");
        assertEquals(210, roomOne.getRoomNumber()); //equivalence class
        roomOne.setRoomNumber(211);
        assertEquals(211, roomOne.getRoomNumber()); //equivalence class
    }
    @Test
    void setHasEquipment(){ //This may be an issue
        Room roomOne = new Room(false,210,true,"Sean");
        assertEquals(true, roomOne.getHasEquipment()); //equivalence class
        roomOne.setHasEquipment(false);
        assertEquals(false, roomOne.getHasEquipment()); //equivalence class
    }
    @Test
    void setRenterNameTest(){
        Room roomOne = new Room(false,210,true,"Sean");
        assertEquals("Sean", roomOne.getRenterName()); //equivalence class
        roomOne.setRenterName("Jim");
        assertEquals("Jim", roomOne.getRenterName()); //equivalence class
    }
    


}
