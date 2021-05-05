package edu.ithaca.musicStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RepairTech extends Employee{
    //private int employeeID;
    //private String name;
    //private double payAmt;
    private List<Repair> activeRepairs;
    //private double hoursWorked;
    //private MusicStore store;
    //private double wasPaid;

    public RepairTech(int employeeID, String name,MusicStore store) {
        super(employeeID, name,store);
        activeRepairs = new ArrayList<>();
    }
    public RepairTech(int employeeID, String name, double payAmt, MusicStore store) {
        super(employeeID, name, payAmt,store);
        activeRepairs = new ArrayList<>();
    }
    
    /*public RepairTech(int employeeID, String name, MusicStore store){
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
        activeRepairs = new ArrayList<>();
        hoursWorked=0;
        this.store = store;
    }*/

    /*public RepairTech(int employeeID, String name, double payAmt, MusicStore store){
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
        activeRepairs = new ArrayList<>();
        hoursWorked=0;
        this.store = store;
    
    }*/

    /*public int isIdValid(int IDNumber){
        int count=0;
        while (IDNumber !=0){
            IDNumber/=10;
            count++;
        }
        return count;
    }*/

   /*public int getID(){
        return employeeID;
    }*/    
    
    /*public String getName(){
        return name;
    }*/

   /* public double getPayAmt(){
        return payAmt;
    }*/

    /*public static boolean isAmountValid(double balance){
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
          return true;
        }
       return false;
    }*/

    /*public void addHours(double hrsWorked){
        if(hrsWorked<0){
            throw new IllegalArgumentException("Must enter a valid amount of hours");
        }
        else{
        hoursWorked+= hrsWorked;
        }
        
    }*/

    /*public double getHoursWorked(){
        return hoursWorked;
    }*/

 
    public boolean checkEquipInventory(String itemName){
        if(store.findEquipment(itemName) == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Equipment pullFromEquipInventory(String itemName){
        try{
            Equipment temp = store.getEquipment(store.findEquipment(itemName));
            store.removeEquipment(store.findEquipment(itemName));
            return temp;
        }
        catch(Exception e){
            throw new IllegalArgumentException("Item not found");
        }
    }



    public void tuner(){
        Scanner keyboard = new Scanner(System.in);
        //Random random = new Random();
        int notePlayed;
        System.out.println("Lets tune your guitar!");
        System.out.println("Play your E string by entering a frequency between 320 and 340 Hz: ");
        notePlayed= keyboard.nextInt();
        while(notePlayed>334 || notePlayed<326){
            if(notePlayed>331){
                System.out.println("The note is sharp. Tune and enter a new frequency between 320 and 340 Hz: ");
                notePlayed= keyboard.nextInt();
            }
            if(notePlayed<329){
                System.out.println("The note is flat. Tune and enter a new frequency between 320 and 340 Hz: ");
                notePlayed= keyboard.nextInt();
            }
        }
        System.out.println("Your E string is in tune!");
        System.out.println("Next let's tune your B string. Enter a frequency between 236 and 256");
        notePlayed= keyboard.nextInt();
        while(notePlayed>250 || notePlayed<242){
            if(notePlayed>250){
                System.out.println("The note is sharp. Tune and enter a new frequency between 320 and 340 Hz: ");
                notePlayed= keyboard.nextInt();
            }
            if(notePlayed<242){
                System.out.println("The note is flat. Tune and enter a new frequency between 320 and 340 Hz: ");
                notePlayed= keyboard.nextInt();
            }
        }
        System.out.println("Your B string is in tune!");
        System.out.println("Next let's tune your G string. Enter a frequency between 186 and 206");
        notePlayed= keyboard.nextInt();
        while(notePlayed>200 || notePlayed<192){
            if(notePlayed>200){
                System.out.println("The note is sharp. Tune and enter a new frequency between 320 and 340 Hz: ");
                notePlayed= keyboard.nextInt();
            }
            if(notePlayed<192){
                System.out.println("The note is flat. Tune and enter a new frequency between 320 and 340 Hz: ");
                notePlayed= keyboard.nextInt();
            }
            
        }

        System.out.println("Your G string is in tune!");
        System.out.println("Next let's tune your D string. Enter a frequency between 136 and 156");
        notePlayed= keyboard.nextInt();
        while(notePlayed>150 || notePlayed<142){
            if(notePlayed>150){
                System.out.println("The note is sharp. Tune and enter a new frequency between 320 and 340 Hz: ");
                notePlayed= keyboard.nextInt();
            }
            if(notePlayed<142){
                System.out.println("The note is flat. Tune and enter a new frequency between 320 and 340 Hz: ");
                notePlayed= keyboard.nextInt();
            }
            
        }
        System.out.println("Your D string is in tune!");
        System.out.println("Next let's tune your A string. Enter a frequency between 100 and 120");
        notePlayed= keyboard.nextInt();
        while(notePlayed>114 || notePlayed<106){
            if(notePlayed>114){
                System.out.println("The note is sharp. Tune and enter a new frequency between 320 and 340 Hz: ");
                notePlayed= keyboard.nextInt();
            }
            if(notePlayed<106){
                System.out.println("The note is flat. Tune and enter a new frequency between 320 and 340 Hz: ");
                notePlayed= keyboard.nextInt();
            }
            
        }
        System.out.println("The guitar is now in tune!");
        
            /*for (int i =1; i <7; i++){
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
            }*/
            keyboard.close();
}

    public void addToActiveRepairList(Repair repairToAdd){
        activeRepairs.add(repairToAdd);
    }

    public void removeFromActiveRepairList(String itemName, String clientName){
        int found = 0;
        for (int i =0; i < activeRepairs.size(); i++){
            if (activeRepairs.get(i).getItem().getClientName().equals(clientName) && activeRepairs.get(i).getItem().getItemName().equals(itemName) ){
                activeRepairs.remove(i);
                found++;
                break;
            }
        }
        if (found == 0){
            throw new IllegalArgumentException("Repair does not exist");
        }
    }

    public Repair getRepair(String itemName, String clientName){
        for (int i =0; i < activeRepairs.size(); i++){
            if (activeRepairs.get(i).getItem().getClientName().equalsIgnoreCase(clientName) && activeRepairs.get(i).getItem().getItemName().equalsIgnoreCase(itemName) ){
                return activeRepairs.get(i);
            }
        }
        throw new IllegalArgumentException("Repair does not exist");
    }

    public Repair getRepair(int index){
            if (index <= activeRepairs.size()-1 && index> -1){
                return activeRepairs.get(index);
            }
        throw new IllegalArgumentException("Out of bounds");
    }

    public int findRepair(String itemName, String clientName){
        for (int i =0; i < activeRepairs.size(); i++){
            if (activeRepairs.get(i).getItem().getClientName().equals(clientName) && activeRepairs.get(i).getItem().getItemName().equals(itemName) ){
                return i;
            }
        }
        return -1;
    }

    public List<Repair> getActiveRepairList(){
        return activeRepairs;
    }
    


    public void getPaid(double payment){
        wasPaid=payment;
        hoursWorked=0;
        //this currently reflects the most recent payment- could turn into a list to keep a record of all payments?
        //Sets hours worked to zero so employee starts with no hours for the next week

    }

    public double getPriceFromCategory(double timeEst){
        if (timeEst <= 0){
            throw new IllegalArgumentException("Invalid time est");
        }
        else if (timeEst > 0 && timeEst < 3){
            return store.getRepairPricing(RepairBusinessDayCategory.ONETOTHREE);
        }
        else if(timeEst >= 3 && timeEst < 5) {
            return store.getRepairPricing(RepairBusinessDayCategory.THREETOFIVE);
        }
        else if(timeEst >= 5 && timeEst < 7) {
            return store.getRepairPricing(RepairBusinessDayCategory.FIVETOSEVEN);
        }
        else{
            return store.getRepairPricing(RepairBusinessDayCategory.SEVENPLUS);
        }

    }

    
}
