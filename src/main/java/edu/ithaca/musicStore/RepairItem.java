package edu.ithaca.musicStore;

public class RepairItem {
    String itemName;
    String clientName;
    Double quotePrice;
    Double timeEstimate;
    Boolean isBroken;
    String damageDescription;
   
    
    public RepairItem(String itemName, String clientName, String damageDescription) {
        if (itemName == null || clientName == null || damageDescription == null){
            throw new IllegalArgumentException("All fields are not entered");
        }
        this.itemName = itemName;
        this.clientName = clientName;
        this.damageDescription = damageDescription;
        isBroken = true;

    }

    public void setisBroken(boolean bool){
        //
    }

    public String getDamageDescription(){
        return damageDescription;
    }
    
    public void displayDamageDescription(){
        System.out.println(damageDescription);
    }


    public void setTimeEstimate(double time){
        //
    }

    public String getItemName() {
        return itemName;
    }
    public String getClientName() {
        return clientName;
    }

    public Double getQuotePrice() {
        return quotePrice;
    }

    public Double getTimeEstimate() {
        return timeEstimate;
    }

    public Boolean getIsBroken() {
        return isBroken;
    }




}
