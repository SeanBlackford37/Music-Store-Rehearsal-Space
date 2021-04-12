package edu.ithaca.musicStore;

import java.util.List;

public class RepairTech {
    int employeeID;
    String name;
    double payAmt;
    List<Item> activeRepairs;

    public RepairTech(int employeeID, String name){
        //check to make sure employeeID is 5 digits
       
    }
    public RepairTech(int employeeID, String name, double payAmt){
        //check to make sure employeeID is 5 digits
        
    }

    public int isIdValid(int IDNumber){
       //
    }

    public int getID(){
        return employeeID;
    }

    public String getName(){
        return name;
    }

    public double getPayAmt(){
        return payAmt;
    }

    public static boolean isAmountValid(double balance){
        //
    }

    public boolean checkStock(String searchItem, ArrayList<Item> inventory){
        //change boolean
        
        
    }

    public void addToRepairList(Item itemToAdd){
        //
    }

    public void removeFromRepairList(String itemName, String customerName){
        //
    }

    public int findRepair(String itemName, String customerName){
        //
    }

    public List<Item> getRepairList(){
        //
    }
    
}
