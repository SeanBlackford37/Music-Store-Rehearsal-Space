package edu.ithaca.musicStore;

public class Item {
    private String name;
    private double price;
    private String renterName;

    public Item(String name, double price, String renterName){
        if(!isAmountValid(price)){
            throw new IllegalArgumentException("invalid amount");
        }else{
            this.name = name;
            this.price = price;
            this.renterName = renterName;
        }
        
    }

    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
          return true;
        }
       return false;
    }

    public String getName(){
        return name;
    }
    
    public double getPrice(){
        return price;
    }

    public String getRenterName(){
        return renterName;
    }

    public void setName(String name){
      this.name = name;
    }
    
    public void setPrice(double price){
        this.price = price;
    }

    public void setRenterName(String renter){
        this.renterName = renter;
    }
}
