package edu.ithaca.musicStore;

import java.util.ArrayList;
import java.util.List;

public class Repair {
    private String timeCategory;
    private ThingToBeRepaired item;
    private RepairTech tech;
    private List<Equipment> equipmentUsed;
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
            equipmentUsed = new ArrayList<>();
            price = 0.0;
        }
    }

    
    
    public String createQuote(double timeEst){
        if (timeEst <= 0){
            throw new IllegalArgumentException("Invalid time est");
        }
        else {
            setPrice(getTech().getPriceFromCategory(timeEst));
        }
        
        String quote = "Repair Tech: " + tech.getName() + "\n" + "Customer: " + item.getClientName() + "\n" + "Damage Description: " + item.getDamageDescription();
        if (!equipmentUsed.isEmpty()){
            quote += "\nEquipment Used in Repair:";
            for (int i =0; i < equipmentUsed.size(); i++){
                quote += "\n";
                quote += equipmentUsed.get(i).getEquipmentName();
                quote += " Cost $";
                quote += equipmentUsed.get(i).getPrice();
            }
        }
        quote += "\nTotal cost: $" + price.toString();
        return quote;
    }

    public void setPrice(double estPrice){
        if (isAmountValid(estPrice)){
            if (equipmentUsed.size() > 0){
                price = estPrice;
                for (int i = 0; i < equipmentUsed.size(); i++){
                    price+= equipmentUsed.get(i).getPrice();
                }
            }
            else{
                price = estPrice;
            }
        }
        else{
            throw new IllegalArgumentException("Not a valid est price");
        }
        int num  = (int)(price * 100);
        price = ((double)num) /100;
    }

    public void setRepairIsFinished(boolean bool){
        isRepairFinished = bool;
    }

    public void addItemToEquipmentUsed(Equipment itemToAdd){
        equipmentUsed.add(itemToAdd);
    }

    public Equipment removeItemFromEquipmentUsed(String itemName){
        Equipment temp = null;
        for (int i= 0; i<equipmentUsed.size(); i++){
            if (equipmentUsed.get(i).getEquipmentName().equalsIgnoreCase(itemName)){
                temp = equipmentUsed.get(i);
                equipmentUsed.remove(i);
                break;
            }
        }
        return temp;
    }

    public String getTimeCategory() {
        return timeCategory;
    }

    public boolean getIsRepairFinished() {
        return isRepairFinished;
    }

    public ThingToBeRepaired getItem() {
        return item;
    }

    public RepairTech getTech() {
        return tech;
    }

    public List<Equipment> getEquipmentUsed() {
        return equipmentUsed;
    }

    //either need to create a quote first or manually set the base price or else it will be 0
    public Double getPrice() {
        return price;
    }

    public boolean isRepairFinished() {
        return isRepairFinished;
    }

    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
          return true;
        }
       return false;
    }
    
    
}
