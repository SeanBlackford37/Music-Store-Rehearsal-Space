package edu.ithaca.musicStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepairItemTest {
    @Test
    void constructorTest(){
        RepairItem item = new RepairItem("Guitar", "Steph", "String broken");
        assertEquals("Guitar", item.getItemName());
        assertEquals("Steph", item.getClientName());
        assertEquals("String broken", item.getDamageDescription());
        assertEquals(true, item.getIsBroken());

        assertThrows(IllegalArgumentException.class, ()-> new RepairItem(null, "Steve", "Bonk"));
        assertThrows(IllegalArgumentException.class, ()-> new RepairItem("guitar", null, "Bonk"));
        assertThrows(IllegalArgumentException.class, ()-> new RepairItem("guitar", "Steve", null));
    }
     
    @Test
    void isBrokenTest(){
        RepairItem item = new RepairItem("Guitar", "Steph", "String broken");
        assertEquals(true, item.getIsBroken());
        item.setisBroken(false);
        assertEquals(false, item.getIsBroken());
    }

    @Test 
    void setTimeEstimate(){
        RepairItem item = new RepairItem("Guitar", "Steph", "String broken");
        item.setTimeEstimate(1);
        assertEquals(0,Double.compare(1, item.getTimeEstimate()));
        item.setTimeEstimate(1.5);
        assertEquals(0,Double.compare(1.5, item.getTimeEstimate()));
        item.setTimeEstimate(1.35);
        assertEquals(0,Double.compare(1.35, item.getTimeEstimate()));

        assertThrows(IllegalArgumentException.class, ()-> item.setTimeEstimate(12.403));
        assertThrows(IllegalArgumentException.class, ()-> item.setTimeEstimate(-12.4));
        assertThrows(IllegalArgumentException.class, ()-> item.setTimeEstimate(-12.402));
    }

    @Test
    void setQuotePrice(){
        RepairItem item = new RepairItem("Guitar", "Steph", "String broken");
        item.setQuotePrice(1);
        assertEquals(0,Double.compare(1, item.getQuotePrice()));
        item.setQuotePrice(1.5);
        assertEquals(0,Double.compare(1.5, item.getQuotePrice()));
        item.setQuotePrice(1.35);
        assertEquals(0,Double.compare(1.35, item.getQuotePrice()));

        assertThrows(IllegalArgumentException.class, ()-> item.setQuotePrice(12.403));
        assertThrows(IllegalArgumentException.class, ()-> item.setQuotePrice(-12.4));
        assertThrows(IllegalArgumentException.class, ()-> item.setQuotePrice(-12.402));
    }
}
