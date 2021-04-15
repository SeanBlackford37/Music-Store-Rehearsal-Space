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
        this.isBroken = bool;
    }

    public String getDamageDescription(){
        return damageDescription;
    }

    public void displayDamageDescription(){
        System.out.println(damageDescription);
    }

    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
          return true;
        }
       return false;
    }


    public void setTimeEstimate(double time){
       if (isAmountValid(time)){
           this.timeEstimate = time;
       }
       else{
           throw new IllegalArgumentException("Not a good time");
       }
    }

    public void setQuotePrice(double price){
        if (isAmountValid(price)){
            this.quotePrice = price;
        }
        else{
            throw new IllegalArgumentException("Not a good money value");
        }
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
