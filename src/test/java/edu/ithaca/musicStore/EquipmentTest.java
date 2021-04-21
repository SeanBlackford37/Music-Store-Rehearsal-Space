package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquipmentTest {
    @Test
    void constructorTest(){
        Equipment e =  new Equipment("guitar strings",5.25);
        assertNotEquals(null,e);
        assertEquals("guitar strings", e.getEquipmentName());
        assertEquals(5.25, e.getPrice());
        assertEquals("n/a", e.getRepairTechName());

        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.34));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.345));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",5.345));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.34));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.345));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5.345));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5));


        Equipment e2 =  new Equipment("trumpet valve",15.0, "Steph");
        assertNotEquals(null,e2);
        assertEquals("trumpet valve", e2.getEquipmentName());
        assertEquals(15.0, e2.getPrice());
        assertEquals("Steph", e2.getRepairTechName());

        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5,"Steph"));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.34,"Steph"));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.345,"Steph"));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",5.345,"Steph"));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5,"Steph"));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.34,"Steph"));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.345,"Steph"));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5.345,"Steph"));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5,"Steph"));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5,""));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.34,""));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",-5.345,""));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("guitar strings",5.345,""));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5,""));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.34,""));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",-5.345,""));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5.345,""));
        assertThrows(IllegalArgumentException.class, ()->new Equipment("",5,""));

    }

    @Test
    void setRepairTechNameTest(){
        Equipment e = new Equipment("trumpet valve", 15.50);
        assertEquals("n/a",e.getRepairTechName());
        assertThrows(IllegalArgumentException.class, ()->e.setRepairTechName(""));
        assertEquals("n/a",e.getRepairTechName());
        e.setRepairTechName("Barnes");
        assertEquals("Barnes",e.getRepairTechName());
    }

}
