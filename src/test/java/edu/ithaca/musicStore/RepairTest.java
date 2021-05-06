package edu.ithaca.musicStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepairTest {

    @Test
    void createQuoteTest(){ //Integration Test
        MusicStore store = new MusicStore("Place");
        store.addEquipment(new Equipment("string", 12));
        Repair repair = new Repair(new ThingToBeRepaired("guitar", "steph", "broken string"), new RepairTech(12345, "Kelsi", store));
        RepairTech tech = repair.getTech();
        System.out.println(repair.createQuote(2)+"\n");

        repair.addItemToEquipmentUsed(tech.pullFromEquipInventory("string"));
        System.out.println(repair.createQuote(2));
    }

    @Test
    void setPriceTest(){ //Integration Test
        MusicStore store = new MusicStore("Place");
        store.addEquipment(new Equipment("string", 12));
        Repair repair = new Repair(new ThingToBeRepaired("guitar", "steph", "broken string"), new RepairTech(12345, "Kelsi", store));
        RepairTech tech = repair.getTech();
        repair.setPrice(12);
        assertEquals(0, Double.compare(12, repair.getPrice())); //Equivalence class
        repair.setPrice(12.01);
        assertEquals(0, Double.compare(12.01, repair.getPrice())); //Equivalence class
        repair.setPrice(12.1);
        assertEquals(0, Double.compare(12.1, repair.getPrice())); //Equivalence class

        //create store with 3 decimals
        assertThrows(IllegalArgumentException.class, ()->repair.setPrice(12.101));
        //create store with negative 3 decimals
        assertThrows(IllegalArgumentException.class, ()->repair.setPrice(-12.101));
        //create store with negative 2 decimals
        assertThrows(IllegalArgumentException.class, ()->repair.setPrice(-12.11));
        //create store with negative and 1 decimal
        assertThrows(IllegalArgumentException.class, ()->repair.setPrice(-12.1));
        //create store with negative
        assertThrows(IllegalArgumentException.class, ()->repair.setPrice(-12));

        //repair.addItemToEquipmentUsed(tech.pullFromInventory("string"));
        //repair.setPrice(1.01);
        //assertEquals(0, Double.compare(14.0, repair.getPrice()));

    }

    @Test
    void setRepairIsFinished(){ //Unit Test
        MusicStore store = new MusicStore("Place");
        Repair repair = new Repair(new ThingToBeRepaired("guitar", "steph", "broken string"), new RepairTech(12345, "Kelsi", store));
        assertEquals(false,repair.getIsRepairFinished()); //Integration Test
        repair.setRepairIsFinished(true);
        assertEquals(true,repair.getIsRepairFinished()); //Integration Test
        repair.setRepairIsFinished(false);
        assertEquals(false, repair.getIsRepairFinished()); //Integration Test
    }

    @Test
    void addItemToEquipmentUsedTest(){ //Integration Test
        MusicStore store = new MusicStore("Place");
        store.addEquipment(new Equipment("string", 12));
        Repair repair = new Repair(new ThingToBeRepaired("guitar", "steph", "broken string"), new RepairTech(12345, "Kelsi", store));
        RepairTech tech = repair.getTech();
        repair.addItemToEquipmentUsed(tech.pullFromEquipInventory("string"));
        assertEquals(-1, store.searchForInventoryItem("string")); //Equivalence class
        assertEquals("string",repair.getEquipmentUsed().get(0).getEquipmentName()); //Equivalence class
    }

    @Test
    void removeItemFromEquipmentUsedTest(){ //Integration Test
        MusicStore store = new MusicStore("Place");
        store.addEquipment(new Equipment("string", 12));
        Repair repair = new Repair(new ThingToBeRepaired("guitar", "steph", "broken string"), new RepairTech(12345, "Kelsi", store));
        RepairTech tech = repair.getTech();
        repair.addItemToEquipmentUsed(tech.pullFromEquipInventory("string"));
        assertEquals("string", repair.removeItemFromEquipmentUsed("string").getEquipmentName()); //Equivalence class
        assertEquals(0, repair.getEquipmentUsed().size()); //Equivalence class
    }
    
}
