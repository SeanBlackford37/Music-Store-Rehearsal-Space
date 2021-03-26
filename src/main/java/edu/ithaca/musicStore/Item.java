package edu.ithaca.musicStore;

public class Item {
    private String name;
    private double price;
    private String renterName;

    public Item(String name, double price, String renterName){
        this.name = name;
        this.price = price;
        this.renterName = renterName;
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
}
