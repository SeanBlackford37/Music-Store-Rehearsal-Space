package edu.ithaca.musicStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    
    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(result[1].length() <= 2 && balance >=.01){
          return true;
        }
       return false;
    }
    public static void rentingRoom(MusicStore store , Customer custIn, Employee employeeIn){
        
        List<Room> availableRoomList =  store.availableRoomList();
        
        if(custIn.getRoomRented() == null){
            System.out.println("which room would you like to rent(Can only rent one at a time)");
            for(int i = 0; i < availableRoomList.size(); i++){
                System.out.println("Room number: " + availableRoomList.get(i).getRoomNumber());
            }
            int roomNum = -1;
            roomNum = scan.nextInt();
            scan.nextLine();
            if(store.findRoom(roomNum) != -1){
                custIn.rentRoom(roomNum, employeeIn);
            } 
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
    public static void cancelRentingRoom(Customer custIn, Room room){
        if(custIn.getRoomRented() != null){
            custIn.cancelRoom(room.getRoomNumber());
            System.out.println("Canceling rented room number: " + room.getRoomNumber());
        }else{
            System.out.println("You are not currently renting a room");
        }
       
    }
    public static void returnEquipment(MusicStore store, Customer custIn){
        ArrayList<Item> rentedItems = custIn.getRentedList();
        String equipmentToReturn = "";
        if(rentedItems.isEmpty()){
            System.out.println("No equipment to return");
        }else{
            System.out.println("What item would you like to return?");
        }
        while(!equipmentToReturn.equalsIgnoreCase("done") && !rentedItems.isEmpty()){
            System.out.println("Rented items:");
            for(int i = rentedItems.size()-1; i >= 0; i--){
                System.out.println(rentedItems.get(i).getName());
            }
            System.out.println("Or enter 'done' to be finished");
            equipmentToReturn = scan.nextLine();
            if(store.searchForRentedItem(equipmentToReturn) != -1){
                custIn.returnItem(equipmentToReturn);
                System.out.println(equipmentToReturn + " has been returned");
            }else if(!equipmentToReturn.equalsIgnoreCase("done")){
                System.out.println("You did not rent out that item");
                System.out.println("Please enter a valid rented item");
            }
            
        }
    }
    public static void cancelEquipment(MusicStore store, Customer custIn){
        ArrayList<Item> rentedItems = custIn.getRentedList();
        String equipmentToReturn = "";
        if(rentedItems.isEmpty()){
            System.out.println("No equipment to cancel");
        }else{
            System.out.println("What item would you like to cancel?");
        }
        while(!equipmentToReturn.equalsIgnoreCase("done") && !rentedItems.isEmpty()){
            System.out.println("Rented items:");
            for(int i = rentedItems.size()-1; i >= 0; i--){
                System.out.println(rentedItems.get(i).getName());
            }
            System.out.println("Or enter 'done' to be finished");
            equipmentToReturn = scan.nextLine();
            if(store.searchForRentedItem(equipmentToReturn) != -1){
                custIn.cancelItemRental(equipmentToReturn);
                System.out.println(equipmentToReturn + " has been canceled");
            }else if(!equipmentToReturn.equalsIgnoreCase("done")){
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
        orderTotal(customerIn);
        }else{
            System.out.println("You have no rented equipment");
        }
    }
    public static void orderTotal(Customer customerIn){
        ArrayList<Item> itemsList =  customerIn.getRentedList();
        double total = 0;
        if(customerIn.getRoomRented() != null){
            total += customerIn.getRoomRented().getRate();
        }
        if(!itemsList.isEmpty()){
            for(int i = itemsList.size()-1; i >= 0; i--){
                total += itemsList.get(i).getPrice();
            }
            System.out.println("Total: $" + total);
        }else{
            System.out.println("Total: $" + total);
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
       
            
       
    }
    public static void hireEmployee(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Enter name of the new hire:");
        String newEmployeeName = scan.nextLine();
        System.out.println("Enter pay amount:");
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
        
        
    }
    public static void fireEmployee(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Which employee do you want to fire?");
        employeeList(mStoreIn);
      
        System.out.println("Enter employee ID:");
        int employeeID = 0;
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
    }
    public static void addRentalSpace(MusicStore mStoreIn, Admin adminIn){
        
        if(mStoreIn.getRoomList().isEmpty()){
            System.out.println("No rooms are currently being rented!");
        }else{
            System.out.println("Currently rented");
            for(int i = 0; i < mStoreIn.getRoomList().size(); i++ ){
                System.out.print("Room number: " + mStoreIn.getRoomList().get(i).getRoomNumber() + ", ");
                System.out.print(" Rent name: " + mStoreIn.getRoomList().get(i).getRenterName());
                System.out.println("");
            }
        }
        System.out.println("Enter the new room number:");
        int roomNumber = scan.nextInt();
        scan.nextLine();
        try{
            adminIn.addSpaceToRental(roomNumber, mStoreIn.getRoomList());
            System.out.println("Room added!");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void cancelRentalSpace(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Currently rented");
        if(mStoreIn.getRoomList().isEmpty()){
            System.out.println("No rooms are currently being rented!");
        }else{
            for(int i = 0; i < mStoreIn.getRoomList().size(); i++ ){
                System.out.print("Room number: " + mStoreIn.getRoomList().get(i).getRoomNumber() + ", ");
                System.out.print(" Rent name: " + mStoreIn.getRoomList().get(i).getRenterName());
                System.out.println("");
            }
            System.out.println("Enter the room number to cancel:");
            int roomNumber = scan.nextInt();
            scan.nextLine();
            try{
                adminIn.cancelSpaceRental(roomNumber, mStoreIn.getRoomList());
                System.out.println("Room canceled!");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        
    }
    
    public static void addEquipmentToInventory(MusicStore mStoreIn, Admin adminIn){
        //ToDo for loop to add as many items as wanted
        
        String itemName = "";
        System.out.println("What Item would you like to add?");
        while(!itemName.equalsIgnoreCase("done")){
            System.out.println("What is the name of the product?");
            itemName = scan.nextLine();
            
            System.out.println("What is the price?");
            double price = scan.nextDouble();
            scan.nextLine();
            
            if(isAmountValid(price)){
                adminIn.addEquipmentToInventory(new Item(itemName, price), mStoreIn);
                System.out.println(itemName + " added to the inventory to rent");
            }else{
                System.out.println("Please make sure to enter a valid amount");
            }
            for(int i = 0; i < mStoreIn.getInventorySize(); i++){
                System.out.println(mStoreIn.getInventoryList().get(i).getName());
            }


            System.out.println("Enter 'done' to be finished or press enter to add another item");
            itemName = scan.nextLine();
            
            
        }
    }
    
    public static void getRepairPricing(Admin adminIn, RepairBusinessDayCategory rc){
        //TODO
        
        
    }
    public static void updateRepairPricing(MusicStore mStoreIn, Admin adminIn){
        //TODO
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

        String[] choices = {"rent room", "rent equipment", "return room rental", "return equipment", 
        "cancel equipment", "cancel room rental", "order total", "done", "Display information", "transaction History"};

        for (int i=0;i<choices.length;i++){
            if(input.equalsIgnoreCase(choices[i])){
                return true;
            }
        }
        return false;
    }
    public static void customerInteraction(MusicStore store){
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
        String name = "Sean Blackford";
        name = scan.nextLine();
        
        Customer custOne= new Customer(store, name);

        while(!input.equalsIgnoreCase("done")){
            System.out.println("\n--Customer Menu--\nRent Room\nRent Equipment\nReturn Room Rental\nReturn Equipment\nCancel Room Rental\nCancel Equipment\nOrder Total\nDisplay information\nTransaction History\nDone\n");

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
            }
            else if(input.equalsIgnoreCase("cancel room rental")){
                cancelRentingRoom(custOne, custOne.getRoomRented());
              
            }else if(input.equalsIgnoreCase("cancel equipment")){
                cancelEquipment(store, custOne);
            }
            else if(input.equalsIgnoreCase("Display information")){
                displayInformation(custOne);
            }
            else if(input.equalsIgnoreCase("order total")){
                orderTotal(custOne);
            }
            else if(input.equalsIgnoreCase("transaction History")){
                transactionHistory(custOne);
            }
            
            
        }
        //System.out.println(custOne.getTransactionHistory().get(0).getRoomRented().getRoomNumber());
    }
    public static boolean validChoiceAdmin(String input){
        String[] choices = {"Pay employee", "Hire employee", "Terminate Employee", "View Employee List", 
        "Add Rental Space", "Cancel Rental space", "Get Repair Pricing", "Add Item to Inventory","Done"};
        for (int i=0;i<choices.length;i++){
            if(input.equalsIgnoreCase(choices[i])){
                return true;
            }
        }
        return false;
    }
    public static void adminInterface(MusicStore mStore, Customer customerIn){
        System.out.println("Enter client or admin");
        String uiPick = scan.nextLine();
        if(uiPick.equalsIgnoreCase("client")){
            employeeInterface(mStore, customerIn);
        }else if(uiPick.equalsIgnoreCase("admin")){
            mStore.addAdmin(new Admin(12346, "Sean", mStore));
            mStore.addEmployee(new Employee(12347, "Toby", mStore));
            mStore.addToRepairTechList(new RepairTech(12348, "Doug", mStore));
            System.out.println("Welcome to the Admin interface");
            Admin adminOne = null;
            String input = "go";
                while(!input.equals("valid")){
                    System.out.println("Enter your employee ID");
                    int employeeID = 0;
                    employeeID = scan.nextInt();
                    scan.nextLine();
                   
                    if (mStore.findAdmin(employeeID) !=-1){
                        int index = mStore.findAdmin(employeeID);
                        adminOne = mStore.getAdmin(index);
                        input = "valid";
                    }
                    else{
                        System.out.println("Incorrect employeeID");
                    }
                }
            
          
            
            System.out.println("Welcome: " + adminOne.getName());
            while(!input.equalsIgnoreCase("done")){
                System.out.println("\n--Admin Menu--\nPay Employee\nHire Employee\nTerminate Employee\nView Employee list\nAdd Rental space\nCancel Rental Space\nAdd Item to Inventory\nDone\n");
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
                else if(input.equalsIgnoreCase("add rental space")){
                    addRentalSpace(mStore, adminOne);
                }
                else if(input.equalsIgnoreCase("cancel rental space")){
                    cancelRentalSpace(mStore, adminOne);
                }
                else if(input.equalsIgnoreCase("add item to inventory")){
                    addEquipmentToInventory(mStore, adminOne);
                }
                // else if(input.equalsIgnoreCase("get repair pricing")){
                //     getRepairPricing(adminOne, ONETOTHREE);
                // }
                else if(input.equalsIgnoreCase("View Employee list")){
                    employeeList(mStore);
                }
                else if(input.equalsIgnoreCase("check stock")){
                    checkStock(mStore, adminOne);
                }
                else if(input.equalsIgnoreCase("charge customer")){
                    chargeCustomer(adminOne, customerIn);
                }
                else if(input.equalsIgnoreCase("refund customer")){
                    refundCustomer(adminOne, customerIn);
                }
                else if(input.equalsIgnoreCase("view space schedule")){
                    viewSpaceSchedule(mStore);
                }
                else if(input.equalsIgnoreCase("view equipment schedule")){
                    viewEquipmentSchedule(mStore);
                }
            }
        }       
            
    }

        public static void addRepair(RepairTech currTech){
            System.out.println("Enter client's name");
            String itemName;
            String clientName;
            String damageDescription;
            clientName = scan.nextLine();
            System.out.println("Enter item name");
            itemName = scan.nextLine();
            System.out.println("Enter damage description");
            damageDescription = scan.nextLine();
            currTech.addToActiveRepairList(new Repair(new ThingToBeRepaired(itemName, clientName, damageDescription), currTech));
        }

        public static void giveQuote(RepairTech currTech){
            System.out.println("Enter client's name");
            String itemName;
            String clientName;
            double timeEst;
            clientName = scan.nextLine();
            System.out.println("Enter item name");
            itemName = scan.nextLine();
            try{
                currTech.getRepair(itemName, clientName);
            }
            catch(Exception e){
                System.out.println("Cannot find the Repair");
            }
            //TYPE CHECK HERE EMMA!!!! <3 XOXOX
            System.out.println("Enter time estimate (in business days)");
            timeEst = Double.parseDouble(scan.nextLine());
            try{
                String quote = currTech.getRepair(itemName, clientName).createQuote(timeEst);
                System.out.println(quote);
            }
            catch(Exception e){
                System.out.println("Cannot find the Repair");
                return;
            }
        }

        public static void pullFromInventoryForRepair(RepairTech currTech){
            System.out.println("Enter client's name");
            String itemName;
            String clientName;
            String inventoryItem;
            clientName = scan.nextLine();
            System.out.println("Enter item to repair name");
            itemName = scan.nextLine();
            System.out.println("Enter item to pull from equipment Inventory");
            inventoryItem = scan.nextLine();
            try{
                currTech.getRepair(itemName, clientName);
                
            }
            catch(Exception e){
                System.out.println("Cannot find the Repair");
                return;
            }
            try{
                currTech.getRepair(itemName, clientName).addItemToEquipmentUsed(currTech.pullFromEquipInventory(inventoryItem));
                
            }
            catch(Exception e){
                System.out.println("Cannot find the item wanted");
                return;
            }
        
            
            
        }

        public static void finishRepair(RepairTech currTech){
            System.out.println("Enter client's name");
            String itemName;
            String clientName;
            clientName = scan.nextLine();
            System.out.println("Enter item name");
            itemName = scan.nextLine();
            try{
                currTech.getRepair(itemName, clientName);
            }
            catch(Exception e){
                System.out.println("Cannot find the Repair");
                return;
            }
            try{
                currTech.getRepair(itemName, clientName).setRepairIsFinished(true);
            }
            catch(Exception e){
                System.out.println("Cannot find the Repair");
                return;
            }
        }

        public static void viewRepairs(RepairTech currTech){
            for (int i =0; i < currTech.getActiveRepairList().size(); i ++){
                Repair curRepair = currTech.getRepair(i);
                System.out.println("Repair " + i+1 + ":");
                System.out.println("\tClient: " + curRepair.getItem().getClientName());
                System.out.println("\tItem To Fix: " + curRepair.getItem().getItemName());
                System.out.println("\tDamage Description: " + curRepair.getItem().getDamageDescription());
            }
        }

        public static boolean validChoiceRepair(String input){
            String[] choices = {"give quote", "add repair", "pull from inventory for repair", "Finish Repair", "Use Tuner", "view active repairs", "Done"};
            for (int i=0;i<choices.length;i++){
                if(input.equalsIgnoreCase(choices[i])){
                    return true;
                }
            }
            return false;
        }

        public static void repairInterface(MusicStore mStore){
            
            mStore.addToRepairTechList(new RepairTech(12346, "Morgan", mStore));
            mStore.addToRepairTechList(new RepairTech(12348, "Sam", mStore));
            mStore.addEquipment(new Equipment("guitar string", 12));
            mStore.addEquipment(new Equipment("glue", 6));
            System.out.println("Welcome to the Repair Tech interface");
            
            String input = "go";
            System.out.println("Enter your name");
            String name = "Sean Blackford";
            name = scan.nextLine();
            RepairTech currTech;
            if (mStore.findRepairTech(name)!=-1){
                currTech = mStore.getRepairTech(name);
            }
            else{
                currTech = new RepairTech(12347, name, mStore);
            }
            mStore.addToRepairTechList(currTech);
    
            while(!input.equalsIgnoreCase("done")){
                System.out.println("\n--Repair Menu--\nView Active Repairs\nGive Quote\nAdd Repair\nPull From Inventory for Repair\nFinish Repair\nUse Tuner\nDone\n");
                input = scan.nextLine();
    
                if (!validChoiceRepair(input)){
                    System.out.println("Please enter a valid choice");
                } 
                else if(input.equalsIgnoreCase("give quote")){
                    giveQuote(currTech);
                }
                else if(input.equalsIgnoreCase("view active repairs")){
                    viewRepairs(currTech);
                }
                else if(input.equalsIgnoreCase("add repair")){
                    addRepair(currTech);
                }
                else if(input.equalsIgnoreCase("pull from inventory for repair")){
                    pullFromInventoryForRepair(currTech);
                }
                else if(input.equalsIgnoreCase("finish repair")){
                   finishRepair(currTech);
                }
                else if(input.equalsIgnoreCase("Use tuner")){
                    currTech.tuner();
                }
            }
        }

        public static void checkStock(MusicStore storeIn, Employee employeeIn){
            System.out.println("Enter name of item you are looking for:");
            String itemName = scan.nextLine();
            System.out.println();
            if(employeeIn.checkStock(itemName, storeIn.getInventory())){
                for(int i = 0 ; i < storeIn.getInventorySize(); i ++){
                    if(storeIn.getInventory().get(i).getName().equalsIgnoreCase(itemName)){
                        System.out.println(storeIn.getInventory().get(i).getName());
                        System.out.println("$" +storeIn.getInventory().get(i).getPrice());
                        System.out.println();
                    }
                }
            }else{
                System.out.println("Item not found!");
            }
        }

        public static void chargeCustomer(Employee employeeIn, Customer customerIn){
            String input = "go";
            while(!input.equalsIgnoreCase("done")){
                System.out.println("Charge room, item, or done");
                input = scan.nextLine();
                if(input.equalsIgnoreCase("room")){
                    System.out.println("Enter number of room:");
                    int roomNumber = scan.nextInt();
                    scan.nextLine();
                    try{
                        employeeIn.chargeCustomerForRoomRental(customerIn, roomNumber);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    } 
                }
                else if(input.equalsIgnoreCase("item")){
                    System.out.println("Enter name of item:");
                    String itemName = scan.nextLine();
                    try{
                        employeeIn.chargeCustomerForItemRental(customerIn, itemName);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        public static void refundCustomer(Employee employeeIn, Customer customerIn){
            String input = "go";
            while(!input.equalsIgnoreCase("done")){
                System.out.println("Refund room, item, or done");
                input = scan.nextLine();
                if(input.equalsIgnoreCase("room")){
                    System.out.println("Enter number of room:");
                    int roomNumber = scan.nextInt();
                    scan.nextLine();
                    try{
                    employeeIn.refundCustomerForRoomRental(customerIn, roomNumber);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                }
                else if(input.equalsIgnoreCase("item")){
                System.out.println("Enter name of item:");
                String itemName = scan.nextLine();
                    try{
                        employeeIn.refundCustomerForItemRental(customerIn, itemName);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            
        }
        public static void viewSpaceSchedule(MusicStore musicStoreIn){
            if(musicStoreIn.getRoomList().isEmpty()){
                System.out.println("No rooms present!");
            }else{
                for(int i = 0; i < musicStoreIn.getRoomListSize();i++){
                    System.out.println("Room number: " + musicStoreIn.getRoomList().get(i).getRoomNumber());
                    System.out.println("Has equipment: " + musicStoreIn.getRoomList().get(i).getHasEquipment());
                    System.out.println("Room occuiped: " + musicStoreIn.getRoomList().get(i).getIsEmptyRoom());
                    System.out.println("Room rate: " + musicStoreIn.getRoomList().get(i).getRate());
                    System.out.println("Renter name: " + musicStoreIn.getRoomList().get(i).getRenterName());
                    System.out.println();
                }
            }
        }
        public static void viewEquipmentSchedule(MusicStore musicStoreIn){
            if(musicStoreIn.getRentedList().isEmpty()){
                System.out.println("No items are currently rented!");
                if(musicStoreIn.getInventory().isEmpty()){
                    System.out.println("No items in the store to be rented");
                }
            }else{
                for(int i = 0; i < musicStoreIn.getRentedList().size(); i++){
                    System.out.println("Item name: " + musicStoreIn.getRentedList().get(i).getName());
                    System.out.println("Renter name: " + musicStoreIn.getRentedList().get(i).getRenterName());
                    System.out.println("Price: " + musicStoreIn.getRentedList().get(i).getPrice());
                    System.out.println();
                }
                for(int i = 0; i < musicStoreIn.getInventory().size(); i++){
                    System.out.println("Item name: " +musicStoreIn.getInventory().get(i).getName());
                    System.out.println("Price: " + musicStoreIn.getInventory().get(i).getPrice());
                    System.out.println();
                }
            }
        }
        public static boolean validChoiceEmployee(String input){
            String[] choices = {"check stock", "charge customer", "refund customer", "view space schedule", "view equipment schedule", "done"};
            for (int i=0;i<choices.length;i++){
                if(input.equalsIgnoreCase(choices[i])){
                    return true;
                }
            }
            return false;
        }
        public static void employeeInterface(MusicStore mStore, Customer customerIn){
            
            mStore.addEmployee(new Admin(12346, "Sean", mStore));
            mStore.addEmployee(new Employee(12347, "Toby", mStore));
            
            System.out.println("Welcome to the Employee interface");
            Employee currEmployee = null;
            String input = "go";
            while(!input.equals("valid")){
                System.out.println("Enter your employee ID");
                int employeeID = 0;
                employeeID = scan.nextInt();
                scan.nextLine();
                
                if (mStore.findEmployee(employeeID) !=-1){
                    int index = mStore.findEmployee(employeeID);
                    currEmployee = mStore.getEmployee(index);
                    input = "valid";
                }
                else{
                    System.out.println("Incorrect employeeID");
                }
            }
            System.out.println("Welcome: " + currEmployee.getName());
            while(!input.equalsIgnoreCase("done")){
                System.out.println("\n--Employee Menu--\nCheck Stock\nCharge Customer\nRefund Customer\nView Space Schedule\nView Equipment Schedule\nDone\n");
                input = scan.nextLine();
    
                if (!validChoiceEmployee(input)){
                    System.out.println("Please enter a valid choice");
                }
                else if(input.equalsIgnoreCase("check stock")){
                   checkStock(mStore, currEmployee);
                }
                else if(input.equalsIgnoreCase("charge customer")){
                    chargeCustomer(currEmployee, customerIn);
                }
                else if(input.equalsIgnoreCase("refund customer")){
                    refundCustomer(currEmployee, customerIn);
                }
                else if(input.equalsIgnoreCase("view space schedule")){
                    viewSpaceSchedule(mStore);
                }
                else if(input.equalsIgnoreCase("view equipment schedule")){
                    viewEquipmentSchedule(mStore);
                }
                
        }


    }

    public static void main(String[] args)  {
        MusicStore mStore = new MusicStore("Ithaca Music Store");
        mStore.addToInventory(new Item("Piano", 30, "none"));
        mStore.addToInventory(new Item("Saxophone", 15, "none"));
        mStore.addToInventory(new Item("Drums", 50, "none"));
        mStore.addToInventory(new Item("Guitar", 15, "none"));
        mStore.addToInventory(new Item("Guitar", 25, "none"));
        mStore.addToRented(new Item("Piano", 30, "Joe"));
        mStore.addToRented(new Item("Saxophone", 40, "Jim"));
        mStore.addToRoomList(new Room(true,1,false,"none"));
        mStore.addToRoomList(new Room(false,2,true,"Joe Smith"));
        mStore.addToRoomList(new Room(true,3,false,"none"));
        mStore.addToRoomList(new Room(false,4,false,"Bob"));
        mStore.addToRoomList(new Room(true,5,false,"none"));
        Customer currCustomer = new Customer(mStore, "Sean");
        currCustomer.rentRoom(1, new Employee(12345, "Doe", mStore));
        currCustomer.rentItem("Piano",  new Employee(12345, "Doe", mStore));
        currCustomer.rentItem("Guitar",  new Employee(12345, "Doe", mStore));
        mStore.addToStoreBalance(50000);
        //customerInteraction(mStore);
        adminInterface(mStore, currCustomer);
        //repairInterface(mStore);
        //employeeInterface(mStore, currCustomer); //No longer needs to be called


        //CAN UNCOMMENT TO SHOW TUNER
        //MusicStore store = new MusicStore("Place");
        //RepairTech tech= new RepairTech(12345, "Steve", store);
        //tech.tuner();
        
       

    }


}
