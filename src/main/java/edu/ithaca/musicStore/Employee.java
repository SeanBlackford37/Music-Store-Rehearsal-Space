package edu.ithaca.musicStore;

import java.util.ArrayList;

public class Employee {
    int employeeID;
    String name;
    double payAmt;
    double hoursWorked;
    MusicStore store;

    public Employee(int employeeID, String name, MusicStore store){
        //check to make sure employeeID is 5 digits
        if(isIdValid(employeeID) !=5){
            throw new IllegalArgumentException("EmployeeID is not valid");
        }
        if(name.isEmpty()){
            throw new IllegalArgumentException("You must enter a name");
        }
        if(store==null){
            throw new IllegalArgumentException("Employee must be associated with a store");
        }
        this.name= name;
        this.employeeID= employeeID;
        this.store=store;
        payAmt= 15.00;
        hoursWorked=0;
    }
    public Employee(int employeeID, String name, double payAmt, MusicStore store){
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
        if(store==null){
            throw new IllegalArgumentException("Employee must be associated with a store");
        }
        this.name= name;
        this.employeeID= employeeID;
        this.payAmt= payAmt;
        this.store=store;
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

    public String viewSpaceSchedule(ArrayList<Room> rentedRoostore){
        String schedule="Currently rented roostore: \n";
        for(int i=0; i<rentedRoostore.size(); i++){
            schedule= schedule + "Room number: " + Integer.toString(rentedRoostore.get(i).getRoomNumber()) + " Renter name: " + rentedRoostore.get(i).getRenterName() + "\n";
        }
        return schedule;

    }

    public String viewEquipmentSchedule(ArrayList<Item> rentedItestore){
        String schedule="Currently rented itestore: \n";
        for(int i=0; i<rentedItestore.size(); i++){
            schedule= schedule+ rentedItestore.get(i).getName() + "\n";
        }

        return schedule;
    }

    public void addHours(double hrsWorked){
        if(hrsWorked<0){
            throw new IllegalArgumentException("Must enter a valid amount of hours");
        }
        else{
        hoursWorked+= hrsWorked;
        }
        
    }

    public void chargeCustomerForItemRental(Customer c, String itemName) throws IllegalArgumentException{
        double amount = c.rentItem(itemName,this);
        store.addToStoreBalance(amount);
        System.out.println(itemName+" Rental Transaction Approved For "+amount);
    }

    public void chargeCustomerForRoomRental(Customer c, int roomNum) throws IllegalArgumentException{
        double amount = c.rentRoom(roomNum,this);
        store.addToStoreBalance(amount);
        System.out.println("Room "+roomNum+" Rental Transaction Approved For "+amount);
    }

    public void refundCustomerForItemRental(Customer c, String itemName) throws IllegalArgumentException{
        Transaction t = c.cancelItemRental(itemName);
        double amount = t.getOrderAmount();
        store.subtractFromStoreBalance(amount);
        System.out.println(itemName+" Rental Refund Approved For "+amount);

    }

    public void refundCustomerForRoomRental(Customer c, int roomNum) throws IllegalArgumentException{
        Transaction t = c.cancelRoom(roomNum);
        double amount = t.getOrderAmount();
        store.subtractFromStoreBalance(amount);
        System.out.println("Room "+roomNum+" Rental Refund Approved For "+amount);

    }


    public double getHoursWorked(){
        return hoursWorked;
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
