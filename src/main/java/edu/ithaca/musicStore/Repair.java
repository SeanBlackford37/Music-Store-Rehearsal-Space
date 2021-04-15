package edu.ithaca.musicStore;

import java.util.List;

public class Repair {
    private String timeCategory;
    private ThingToBeRepaired item;
    private RepairTech tech;
    private List<Item> equipmentUsed;
    private Double price;
    private boolean isRepairFinished;

    public Repair(ThingToBeRepaired item, RepairTech tech){
        if (item == null || tech == null){
            throw new IllegalArgumentException();
        }
        else{
            this.item = item;
            this.tech = tech;
            isRepairFinished = false;
        }
    }

    public double createQuote(){
        //TODO
    }

    public void setPrice(){
        //TODO
    }

    public void setRepairIsFinished(boolean bool){
        //TODO
    }

    public void addItemToEquipmentUsed(Item itemToAdd){
        //TODO
    }

    public void removeItemToEquipmentUsed(String itemName){
        //TODO
    }
    
}
