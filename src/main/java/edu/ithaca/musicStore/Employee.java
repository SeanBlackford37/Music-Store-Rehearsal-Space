package edu.ithaca.musicStore;

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
    public Employee(int employeeID, String name, double payAmt){
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
    }

    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
          return true;
        }
       return false;
    }

    public boolean checkStock(String searchItem, ArrayList<Item> inventory){
        //change boolean
        for(int i=0; i<inventory.size(); i++){
            if(inventory.get(i).getName().equals(searchItem)){
                return true;
            }
        }
        return false;
        
    }

    public void chargeClient(ArrayList<Item> purchases, ArrayList<Item> inventory){
        double total=0;
        for(int i=0; i<purchases.size(); i++){
            total= total + purchases.get(i).getPrice();
            for(int j=0; j<inventory.size(); j++){
                String toRemove= purchases.get(i).getName();
                if(inventory.get(j).getName().equals(toRemove)){
                    inventory.remove(j);
                }
            }
        }

        //Add total to total funds in store
    }

    public String viewSpaceSchedule(ArrayList<Room> rentedRooms){
        String schedule="Currently rented rooms: \n";
        for(int i=0; i<rentedRooms.size(); i++){
            schedule= schedule + "Room number: " + Integer.toString(rentedRooms.get(i).getRoomNumber()) + " Renter name: " + rentedRooms.get(i).getRenterName() + "\n";
        }
        return schedule;

    }

    public String viewEquipmentSchedule(ArrayList<Item> rentedItems){
        String schedule="Currently rented items: \n";
        for(int i=0; i<rentedItems.size(); i++){
            schedule= schedule+ rentedItems.get(i).getName() + "\n";
        }

        return schedule;
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
