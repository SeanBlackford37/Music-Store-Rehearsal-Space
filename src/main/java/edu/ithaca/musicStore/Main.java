package edu.ithaca.musicStore;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner scan = new Scanner(System.in);
    
    public static void rentingRoom(MusicStore store , Customer custIn, Employee employeeIn){
        
        List<Room> availableRoomList =  store.availableRoomList();
        
        if(custIn.getRoomRented() == null){
            System.out.println("which room would you like to rent(Can only rent one at a time)");
            for(int i = 0; i < availableRoomList.size(); i++){
                System.out.println("Room number: " + availableRoomList.get(i).getRoomNumber());
            }
            int roomNum = -1;
            boolean isCorrectType = true;
            do{
                isCorrectType = true;
                try{
                    roomNum = scan.nextInt();
                    scan.nextLine();
                    if(store.findRoom(roomNum) != -1){
                        custIn.rentRoom(roomNum, employeeIn);
                    } 
                }catch(InputMismatchException e){
                    System.out.println("Input entered is not a number. Try again below.");
                    isCorrectType=false;
                }
            }
            while(isCorrectType==false);
        }else{
            System.out.println("Can't rent more than one room at a time!");
        }
    }
    public static void rentingEquipment(MusicStore store, Customer custIn, Employee employeeIn){
        System.out.println("whatEquipment would you like to rent, as many insturments as you want or enter 'Done' ");
        List<Item> inventoryList = store.getInventoryList();
        String input = "";
        while(!input.equalsIgnoreCase("done")){
            for(int i = 0; i < inventoryList.size(); i++){
                System.out.println("Inventory List: " + inventoryList.get(i).getName() +" "+ inventoryList.get(i).getPrice());
            }
            System.out.println("Or enter 'done' to be finished");
            input = scan.nextLine();
           
            if(store.searchForInventoryItem(input) != -1){
                custIn.rentItem(input, employeeIn);
            }else if(!input.equalsIgnoreCase("done")){
                System.out.println("Invalid Item/Not found");
            }
        }
    }
    public static void returnRentingRoom(Customer custIn, Room room){
        if(custIn.getRoomRented() != null){
            custIn.returnRoom(room.getRoomNumber());
            
            System.out.println("Returning rented room number: " + room.getRoomNumber());
        }else{
            System.out.println("You are not currently renting a room");
        }
       
    }
    public static void returnEquipment(MusicStore store, Customer custIn){
        ArrayList<Item> rentedItems = custIn.getRentedList();
        String equipmentToReturnOrDone = "";
        if(rentedItems.isEmpty()){
            System.out.println("No equipment to return");
        }else{
            System.out.println("What item would you like to return?");
        }
        while(!equipmentToReturnOrDone.equalsIgnoreCase("done") && !rentedItems.isEmpty()){
            System.out.println("Rented items:");
            for(int i = rentedItems.size()-1; i >= 0; i--){
                System.out.println(rentedItems.get(i).getName());
            }
            System.out.println("Or enter 'done' to be finished");
            equipmentToReturnOrDone = scan.nextLine();
            if(store.searchForRentedItem(equipmentToReturnOrDone) != -1){
                custIn.returnItem(equipmentToReturnOrDone);
                System.out.println(equipmentToReturnOrDone + " has been returned");
            }else if(!equipmentToReturnOrDone.equalsIgnoreCase("done")){
                System.out.println("You did not rent out that item");
                System.out.println("Please enter a valid rented item");
            }
            
        }
    }
    public static void displayInformation(Customer customerIn){
        if(customerIn.getRoomRented() != null){
        System.out.println("Currently rented room: " + customerIn.getRoomRented().getRoomNumber());
        }else{
            System.out.println("Currently rented room: no room" );
        }
        if(!customerIn.getRentedList().isEmpty()){
        ArrayList<Item> rentedItems = customerIn.getRentedList();
        System.out.println("Rented items:");
            for(int i =0; i < rentedItems.size(); i++){
                System.out.println(rentedItems.get(i).getName());
            }
        }else{
            System.out.println("You have no rented equipment");
        }
    }
    public static void transactionHistory(Customer customerIn){
        ArrayList<Transaction> transactions =  customerIn.getTransactionHistory();
        if(!transactions.isEmpty()){
            for(int i = transactions.size()-1; i >= 0; i--){
                System.out.println(transactions.get(i).getDescription());
            }
        }else{
            System.out.println("No transaction history!");
        }
    }
    public static void payEmployee(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Which employee do you want to pay?");
        employeeList(mStoreIn);

        System.out.println("Enter employee ID:");
        int employeeID = 0;
        boolean correctTypeEntered = true;
        do{
            correctTypeEntered=true;
            try{
                employeeID = scan.nextInt();
                scan.nextLine();
                System.out.println("Enter the employee type(Admin,Employee,RepairTech):");
                String employeeType = scan.nextLine();
                try{
                    adminIn.payEmployee(employeeID, employeeType);
                    System.out.println("Employee Paid");
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }catch(InputMismatchException ime){
                System.out.println("A number was not entered for an employee ID. Try Again.");
                correctTypeEntered=false;
            }
        }while(correctTypeEntered==false);
       
            
       
    }
    public static void hireEmployee(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Enter name of the new hire:");
        String newEmployeeName = scan.nextLine();
        System.out.println("Enter pay amount:");
        boolean isGoodInputType=true;
        do{
            isGoodInputType=true;
            try{
                double payAmt = scan.nextDouble();
                scan.nextLine();
                System.out.println("Enter the employee type(Admin,Employee,RepairTech):");
                String employeeType = scan.nextLine();
                int employeeID = (int)(Math.random() * (99999 - 10000) + 10000);
                try{
                    adminIn.hireEmployees(employeeID, newEmployeeName, payAmt, mStoreIn, employeeType);
                    System.out.println("New " + employeeType + " hired and added to the system");
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }catch(InputMismatchException ime){
                isGoodInputType=false;
                System.out.println("Invalid pay amount entered. Try again.");
            }
        }while(isGoodInputType==false);
        
        
    }
    public static void fireEmployee(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Which employee do you want to fire?");
        employeeList(mStoreIn);
      
        System.out.println("Enter employee ID:");
        int employeeID = 0;
        boolean isGoodInputType=true;
        do{
            isGoodInputType=true;
            try{
                employeeID = scan.nextInt();
                scan.nextLine();
                System.out.println("Enter the employee type(Admin,Employee,RepairTech):");
                String employeeType = scan.nextLine();
            
                try{
                    if(employeeID != adminIn.employeeID){
                        adminIn.fireEmployee(employeeID, employeeType);
                        System.out.println(employeeType + "has been terminate!");
                    }else{
                        System.out.println("Can'terminate self!");
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }catch(InputMismatchException ime){
                System.out.println("Invalid employee ID entered. Try again.");
                isGoodInputType=false;
            }
        }while(isGoodInputType==false);
    }
    public static void addRentalSpace(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Currently rented");
        for(int i = 0; i < mStoreIn.getRoomList().size(); i++ ){
            System.out.print("Room number: " + mStoreIn.getRoomList().get(i).getRoomNumber() + ", ");
            System.out.print(" Rent name: " + mStoreIn.getRoomList().get(i).getRenterName());
            System.out.println("");
        }
        System.out.println("Enter the new room number:");
        boolean isGoodInputType=true;
        do{
            isGoodInputType=true;
            try{
                int roomNumber = scan.nextInt();
                scan.nextLine();
                try{
                    adminIn.addSpaceToRental(roomNumber, mStoreIn.getRoomList());
                    System.out.println("Room added!");
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }catch(InputMismatchException ime){
                System.out.println("Input entered for room number was not a valid number. Try again.");
                isGoodInputType=false;
            }
        }while(isGoodInputType==false);
    }
    public static void cancelRentalSpace(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Currently rented");
    
        for(int i = 0; i < mStoreIn.getRoomList().size(); i++ ){
            System.out.print("Room number: " + mStoreIn.getRoomList().get(i).getRoomNumber() + ", ");
            System.out.print(" Rent name: " + mStoreIn.getRoomList().get(i).getRenterName());
            System.out.println("");
        }
        System.out.println("Enter the room number to cancel:");
        boolean isGoodInputType = true;
        do{
            isGoodInputType = true;
            try{
                int roomNumber = scan.nextInt();
                scan.nextLine();
                try{
                    adminIn.cancelSpaceRental(roomNumber, mStoreIn.getRoomList());
                    System.out.println("Room canceled!");
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }catch(InputMismatchException ime){
                System.out.println("Input entered for room number was not a valid number. Try again.");
                isGoodInputType = false;
            }
        }while(isGoodInputType == false);
    }
    public static void employeeList(MusicStore mStoreIn){
        for(int i = 0; i < mStoreIn.getAdminList().size(); i++){
            System.out.print("Name: " +   mStoreIn.getAdminList().get(i).getName() + ",");
            System.out.print(" Employee ID: " +  mStoreIn.getAdminList().get(i).getID() + ",");
            System.out.print(" Tag: " + mStoreIn.getAdminList().get(i).getClass().getSimpleName());
            System.out.println("");
        }
        for(int i = 0; i < mStoreIn.getEmployeeList().size(); i++){
            System.out.print("Name: " +   mStoreIn.getEmployeeList().get(i).getName() + ",");
            System.out.print(" Employee ID: " +  mStoreIn.getEmployeeList().get(i).getID() + ",");
            System.out.print(" Tag: " + mStoreIn.getEmployeeList().get(i).getClass().getSimpleName());
            System.out.println("");
        }
        for(int i = 0; i < mStoreIn.getRepairTechList().size(); i++){
            System.out.print("Name: " +   mStoreIn.getRepairTechList().get(i).getName() + ",");
            System.out.print(" Employee ID: " +  mStoreIn.getRepairTechList().get(i).getID() + ",");
            System.out.print(" Tag: " + mStoreIn.getRepairTechList().get(i).getClass().getSimpleName());
            System.out.println("");
        }
    }
    
    public static boolean validChoice(String input){
        String[] choices = {"rent room", "rent equipment", "return room rental", "return equipment", "done", "Display information", "transaction History"};
        for (int i=0;i<choices.length;i++){
            if(input.equalsIgnoreCase(choices[i])){
                return true;
            }
        }
        return false;
    }
    public static boolean validIntChoice(String input, int numChoices){
        int intInput = 0;
        try{
            intInput = Integer.parseInt(input);
        }
        catch(InputMismatchException e){
            return false;
        }
        //indexed @ 1
        if(intInput<1&&intInput>numChoices){
            return false;
        }
        else{
            return true;
        }
    }
    public static boolean hasInvalidCharacters(String input){
        for(int i=0;i<input.length();i++){
            char letter = input.charAt(i);
            if(letter<65&&letter!=32&&letter!=39){
                return true;
            }
            else if(letter>90&&letter<97||letter>122){
                return true;
            }
        }
        return false;
    }
    public static boolean isInvalidName(String name){
        if(name.length()<2){
            System.out.println("Name is too short");
            return true;
        }
        int spaceCount=0;
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)==32){
                spaceCount++;
            }
        }
        if(spaceCount==name.length()){
            System.out.println("Name entered cannot be all spaces");
            return true;
        }
        return false;
    }
    public static void customerInteraction(){
        MusicStore store = new MusicStore("Ithaca Music Store");
        store.addToRoomList(new Room(true,1,false,"none"));
        store.addToRoomList(new Room(false,2,true,"Joe Smith"));
        store.addToRoomList(new Room(true,3,false,"none"));
        store.addToRoomList(new Room(false,4,false,"Bob"));
        store.addToRoomList(new Room(true,5,false,"none"));
        
        store.addToInventory(new Item("Piano", 30, "none"));
        store.addToInventory(new Item("Saxophone", 15, "none"));
        store.addToInventory(new Item("Drums", 50, "none"));
        store.addToInventory(new Item("Guitar", 15, "none"));
        store.addToInventory(new Item("Guitar", 15, "none"));

        String input = "go";
        Employee employeeOne = new Employee(12345, "Toby", store);
        System.out.println("Enter your name");
        String name = scan.nextLine();
        while(hasInvalidCharacters(name)&&isInvalidName(name)){
            System.out.println("Invalid Name: characters entered are numbers or i");
            System.out.println("Enter your name");
            name = scan.nextLine();
        }
        
        Customer custOne= new Customer(store, name);

        while(!input.equalsIgnoreCase("done")){
            System.out.println("\n--Customer Menu--\nRent Room\nRent Equipment\nReturn Room Rental\nReturn Equipment\nDisplay information\nTransaction History\nDone\n");
            input = scan.nextLine();
           
            if (!validChoice(input)){
                System.out.println("Please enter a valid choice");
            } 
            else if(input.equalsIgnoreCase("rent room")){
                rentingRoom(store, custOne, employeeOne);
            }
            else if(input.equalsIgnoreCase("rent equipment")){
                rentingEquipment(store, custOne, employeeOne);
            }
            else if(input.equalsIgnoreCase("return room rental")){
                returnRentingRoom(custOne, custOne.getRoomRented());
            }
            else if(input.equalsIgnoreCase("return equipment")){
                returnEquipment(store, custOne);
              
            }else if(input.equalsIgnoreCase("Display information")){
                displayInformation(custOne);
            }
            else if(input.equalsIgnoreCase("transaction History")){
                transactionHistory(custOne);
            }
            
            
        }
        //System.out.println(custOne.getTransactionHistory().get(0).getRoomRented().getRoomNumber());
    }
    public static boolean validChoiceAdmin(String input){
        String[] choices = {"pay employee", "Hire employee", "Terminate employee", "View Employee list", "Done"};
        for (int i=0;i<choices.length;i++){
            if(input.equalsIgnoreCase(choices[i])){
                return true;
            }
        }
        return false;
    }
    public static void adminInterface(){
        MusicStore mStore = new MusicStore("Ithaca Music Store");
        mStore.addEmployee(new Employee(12346, "Sean", mStore));
        mStore.addEmployee(new Employee(12347, "Toby", mStore));
        mStore.addToRepairTechList(new RepairTech(12347, "Doug", mStore));
        System.out.println("Welcome to the Admin interface");
        
        String input = "go";
        System.out.println("Enter your name");
        String name = "Sean Blackford";
        name = scan.nextLine();
        Admin adminOne = new Admin(12345,  name, mStore);
        mStore.addAdmin(adminOne);

        while(!input.equalsIgnoreCase("done")){
            System.out.println("\n--Admin Menu--\nPay Employee\nHire Employee\nTerminate Employee\nView Employee list\nDone\n");
            input = scan.nextLine();

            if (!validChoiceAdmin(input)){
                System.out.println("Please enter a valid choice");
            } 
            else if(input.equalsIgnoreCase("pay employee")){
                payEmployee(mStore, adminOne);
            }
            else if(input.equalsIgnoreCase("hire employee")){
                hireEmployee(mStore, adminOne);
            }
            else if(input.equalsIgnoreCase("terminate employee")){
               fireEmployee(mStore, adminOne);
            }
            // else if(input.equalsIgnoreCase("add rental space")){
            //     addRentalSpace(mStore, adminOne);
            // }
            // else if(input.equalsIgnoreCase("cancel rental space")){
            //     cancelRentalSpace(mStore, adminOne);
            // }
            else if(input.equalsIgnoreCase("View Employee list")){
                employeeList(mStore);
            }
            
        }
    }

    public static void main(String[] args)  {

        customerInteraction();
        //adminInterface();


        //CAN UNCOMMENT TO SHOW TUNER
        //MusicStore store = new MusicStore("Place");
        //RepairTech tech= new RepairTech(12345, "Steve", store);
        //tech.tuner();
        
       

    }


}