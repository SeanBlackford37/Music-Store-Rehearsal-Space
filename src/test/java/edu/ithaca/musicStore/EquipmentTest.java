package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquipmentTest {
    @Test
    void constructorTest(){ //Unit Test
        Equipment e =  new Equipment("guitar strings",5.25);
        assertNotEquals(null,e); //Equivalence class
        assertEquals("guitar strings", e.getEquipmentName()); //Equivalence class
        assertEquals(5.25, e.getPrice()); //Equivalence class
        assertEquals("n/a", e.getRepairTechName()); //Equivalence class

        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.34)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.345)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",5.345)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.34)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.345)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5.345)); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5)); //Border Case


        Equipment e2 =  new Equipment("trumpet valve",15.0, "Steph");
        assertNotEquals(null,e2); //Equivalence class
        assertEquals("trumpet valve", e2.getEquipmentName()); //Equivalence class
        assertEquals(15.0, e2.getPrice()); //Equivalence class
        assertEquals("Steph", e2.getRepairTechName()); //Equivalence class

        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5,"Steph")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.34,"Steph")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.345,"Steph")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",5.345,"Steph")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5,"Steph")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.34,"Steph")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.345,"Steph")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5.345,"Steph")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5,"Steph")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5,"")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.34,"")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.345,"")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",5.345,"")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5,"")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.34,"")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.345,"")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5.345,"")); //Border Case
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5,"")); //Border Case

    }

    @Test
    void setRepairTechNameTest(){ //Unit Test
        Equipment e = new Equipment("trumpet valve", 15.50);
        assertEquals("n/a",e.getRepairTechName()); //Equivalence class
        assertThrows(IllegalArgumentException.class, ()->e.setRepairTechName("")); //Border Case
        assertEquals("n/a",e.getRepairTechName()); //Equivalence class
        e.setRepairTechName("Barnes"); 
        assertEquals("Barnes",e.getRepairTechName()); //Equivalence class
    }

}
