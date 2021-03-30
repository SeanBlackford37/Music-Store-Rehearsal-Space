package edu.ithaca.dragon.bank;

import java.util.ArrayList;

public class Employee {
    int employeeID;
    String name;
    double payAmt;

    public Employee(int employeeID, String name){
        //check to make sure employeeID is 5 digits
        if(isIdValid(employeeID) !=5){
            throw new IllegalArgumentException("EmployeeID is not valid");
        }
        if(name.isEmpty()){
            throw new IllegalArgumentException("You must enter a name");
        }
        this.name= name;
        this.employeeID= employeeID;
        payAmt= 15.00;
    }

    public ArrayList<Item> checkStock(String searchItem){
        ArrayList<Item> inventory= MusicStore.getInventoryList();//Will this be a static method? If not will change this
        ArrayList<Item> inStock- new ArrayList<Item>();
        for(int i=0; i<inventory.size(); i++){
            if(inventory.get(i).getName().equals(searchItem));
            inStock.add(inventory.get(i));
        }
        return inStock;
        
    }

    public void chargeClient(ArrayList<Item> purchases){
        double total=0;
        for(int i=0; i<purchases.size(); i++){
            total= total + purchases.get(i).getPrice();
        }
        //Do we have a field for total funds in the store? If so, add total
        //Need to look at how inventory list is structured
        //Will put in loop and remove the first instance of each purchased item
    }

    public void viewSpaceSchedule(){

    }

    public void viewEquipmentSchedule(){

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

    public int isIdValid(int IDNumber){
        int count=0;
        while (IDNumber !=0){
            IDNumber/=10;
            count++;
        }
        return count;
    }
    
}
