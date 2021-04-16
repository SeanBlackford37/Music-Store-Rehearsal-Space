package edu.ithaca.musicStore;

import java.util.ArrayList;
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
            equipmentUsed = new ArrayList<>();
            price = 0.0;
        }
    }

    //MUST RUN SET PRICE First or else cost will be 0
    public String createQuote(){
        String quote = "Repair Tech: " + tech.getName() + "\n" + "Customer: " + item.getClientName() + "\n" + "Damage Description: " + item.getDamageDescription();
        if (!equipmentUsed.isEmpty()){
            quote += "\nEquipment Used in Repair:";
            for (int i =0; i < equipmentUsed.size(); i++){
                quote += "\n";
                quote += equipmentUsed.get(i).getName();
                quote += "Cost $";
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

    public void addItemToEquipmentUsed(Item itemToAdd){
        equipmentUsed.add(itemToAdd);
    }

    public Item removeItemFromEquipmentUsed(String itemName){
        Item temp = null;
        for (int i= 0; i<equipmentUsed.size(); i++){
            if (equipmentUsed.get(i).getName().equalsIgnoreCase(itemName)){
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

    public List<Item> getEquipmentUsed() {
        return equipmentUsed;
    }

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
