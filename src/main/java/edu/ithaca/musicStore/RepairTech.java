package edu.ithaca.musicStore;

import java.util.ArrayList;
import java.util.List;

public class RepairTech {
    int employeeID;
    String name;
    double payAmt;
    List<Item> activeRepairs;
    double hoursWorked;

    public RepairTech(int employeeID, String name){
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
        activeRepairs = new ArrayList<>();
        hoursWorked=0;
    }

    public RepairTech(int employeeID, String name, double payAmt){
        //check to make sure employeeID is 5 digits
        if(isIdValid(employeeID) !=5){
            throw new IllegalArgumentException("EmployeeID is not valid");
        }
        if(name.isEmpty()){
            throw new IllegalArgumentException("You must enter a name");
        }
        if(!isAmountValid(payAmt)){
            throw new IllegalArgumentException("You must enter a valid pay amount");
        }
        this.name= name;
        this.employeeID= employeeID;
        this.payAmt= payAmt;
        activeRepairs = new ArrayList<>();
        hoursWorked=0;
    
    }

    public int isIdValid(int IDNumber){
        int count=0;
        while (IDNumber !=0){
            IDNumber/=10;
            count++;
        }
        return count;
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
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
          return true;
        }
       return false;
    }

    public void addHours(double hrsWorked){
        if(hrsWorked<0){
            throw new IllegalArgumentException("Must enter a valid amount of hours");
        }
        else{
        hoursWorked+= hrsWorked;
        }
        
    }

    public double getHoursWorked(){
        return hoursWorked;
    }

    public void addToRepairList(Item itemToAdd){
        activeRepairs.add(itemToAdd);
    }

    public void removeFromRepairList(String itemName, String customerName){
       for (int i =0; i<activeRepairs.size(); i++){
           if (activeRepairs.get(i).getName().equals(itemName) && activeRepairs.get(i).getRenterName().equals(customerName)){
               activeRepairs.remove(i);
           }
       }
    }

    public int findRepair(String itemName, String customerName){
        for (int i =0; i<activeRepairs.size(); i++){
            if (activeRepairs.get(i).getName().equals(itemName) && activeRepairs.get(i).getRenterName().equals(customerName)){
                return i;
            }
        }
        return -1;
    }

    public List<Item> getRepairList(){
        return activeRepairs;
    }
    
}
