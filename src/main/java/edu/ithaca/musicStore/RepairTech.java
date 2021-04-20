package edu.ithaca.musicStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RepairTech {
    private int employeeID;
    private String name;
    private double payAmt;

    private double hoursWorked;
    private MusicStore store;

    public RepairTech(int employeeID, String name, MusicStore store){
        //check to make sure employeeID is 5 digits
        if(isIdValid(employeeID) !=5){
            throw new IllegalArgumentException("EmployeeID is not valid");
        }
        if(name.isEmpty()){
            throw new IllegalArgumentException("You must enter a name");
        }
        if (store == null){
            throw new IllegalArgumentException("You must link employee to a store");
        }
        this.name= name;
        this.employeeID= employeeID;
        payAmt= 15.00;
        
        hoursWorked=0;
        this.store = store;
    }

    public RepairTech(int employeeID, String name, double payAmt, MusicStore store){
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
        if (store == null){
            throw new IllegalArgumentException("You must link employee to a store");
        }
        this.name= name;
        this.employeeID= employeeID;
        this.payAmt= payAmt;
       
        hoursWorked=0;
        this.store = store;
    
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

    

    public boolean checkInventory(String itemName){
        if(store.searchForInventoryItem(itemName) == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Item pullFromInventory(String itemName){
        try{
            Item temp = store.getInventoryItem(store.searchForInventoryItem(itemName));
            store.removeFromInventory(itemName);
            return temp;
        }
        catch(Exception e){
            throw new IllegalArgumentException("Item not found");
        }
    }

    public void tuner(){
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();
        int tuned =0;
        
            for (int i =1; i <7; i++){
                System.out.print("Play your " + i + " string then hit enter: ");
                keyboard.nextLine();
                tuned = 0;
                while(tuned != 2){
                    tuned = random.nextInt(3);
                    if (tuned == 0){
                        System.out.println("The note is flat");
                        System.out.print("Adjust your " + i + " string then hit enter: ");
                        keyboard.nextLine();
                    }
                    else if (tuned == 1){
                        System.out.println("The note is sharp");
                        System.out.print("Adjust your " + i + " string then hit enter: ");
                        keyboard.nextLine();
                    }
                    else{
                        System.out.println("The note is in tune");
                    }
                }
            }
            keyboard.close();
    }
    
}
