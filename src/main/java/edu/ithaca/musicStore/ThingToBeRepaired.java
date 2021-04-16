package edu.ithaca.musicStore;

public class ThingToBeRepaired {
    private String itemName;
    private String clientName;
    private Boolean isBroken;
    private String damageDescription;
   
    
    public ThingToBeRepaired(String itemName, String clientName, String damageDescription) {
        if (itemName == null || clientName == null || damageDescription == null){
            throw new IllegalArgumentException("All fields are not entered");
        }
        this.itemName = itemName;
        this.clientName = clientName;
        this.damageDescription = damageDescription;
        isBroken = true;

    }

    public void setisBroken(boolean bool){
        this.isBroken = bool;
    }

    public String getDamageDescription(){
        return damageDescription;
    }

    public void displayDamageDescription(){
        System.out.println(damageDescription);
    }

    
    public String getItemName() {
        return itemName;
    }
    public String getClientName() {
        return clientName;
    }


    public Boolean getIsBroken() {
        return isBroken;
    }




}
