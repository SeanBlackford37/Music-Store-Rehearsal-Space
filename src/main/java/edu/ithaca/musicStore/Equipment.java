package edu.ithaca.musicStore;

public class Equipment {
    private String equipmentName;
    private String repairTechName;
    private double price;

    public Equipment(String equipmentName, double price) throws IllegalArgumentException{
        if(equipmentName.length()==0){
            throw new IllegalArgumentException("Invalid equipment name");
        }
        else if(!MusicStore.isAmountValid(price)){
            throw new IllegalArgumentException("invalid price set for equipment");
        }
        else{
            this.equipmentName = equipmentName;
            this.price = price;
            repairTechName="n/a";
        }

    }
    public Equipment(String equipmentName, double price, String repairTechName) throws IllegalArgumentException{
        if(equipmentName.length()==0){
            throw new IllegalArgumentException("invalid equipment name");
        }
        else if(!MusicStore.isAmountValid(price)){
            throw new IllegalArgumentException("invalid price set for equipment");
        }
        else if(repairTechName.length()==0){
            throw new IllegalArgumentException("invalid repair tech name entered");
        }
        else{
            this.equipmentName = equipmentName;
            this.price = price;
            this.repairTechName=repairTechName;
        }
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public double getPrice() {
        return price;
    }

    public String getRepairTechName() {
        return repairTechName;
    }
    

    
}
