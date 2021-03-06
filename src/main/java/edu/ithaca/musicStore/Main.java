package edu.ithaca.musicStore;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
            boolean isCorrectType = true;
            do{
                try{
                    System.out.println("Enter room num:");
                    roomNum = Integer.parseInt(scan.nextLine());
                    if(store.findRoom(roomNum) != -1){
                        custIn.rentRoom(roomNum, employeeIn);
                    }
                    isCorrectType=true;
                }catch(NumberFormatException e){
                    System.out.println("Input entered is not a number. Try again.");
                    isCorrectType=false;
                }
            }
            while(isCorrectType==false);
        }else{
            System.out.println("Can't rent more than one room at a time!");
        }
    }
    public static void rentingEquipment(MusicStore store, Customer custIn, Employee employeeIn){
        System.out.println("What equipment would you like to rent, as many insturments as you want or enter 'Done' ");
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
                System.out.println(input + " rented");
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

    public static void requestRepair(MusicStore mStoreIn, Customer customerIn, RepairTech currTech){
        System.out.println("Enter the name of the item to be repaired:");
        String itemToFix= scan.nextLine();
        System.out.println("Enter a description of the damage:");
        String damage= scan.nextLine();
        ThingToBeRepaired fixThis= new ThingToBeRepaired(itemToFix, customerIn.getCustomerName(), damage);
        Repair newRepair= new Repair(fixThis, currTech);
        currTech.addToActiveRepairList(newRepair);
        double timeEst= Math.random()* 5;
        String quote= currTech.getRepair(itemToFix, customerIn.getCustomerName()).createQuote(timeEst);
        System.out.println(quote);
        String answer="invalid";
        while(answer.equals("invalid")){
            System.out.println("Would you like to accept this repair? (Y/N): ");
            answer = scan.nextLine();
            if(answer.equalsIgnoreCase("yes")||answer.equalsIgnoreCase("y")){
                mStoreIn.addToStoreBalance(newRepair.getPrice());
                currTech.addToActiveRepairList(newRepair);
                System.out.println("You can pick up this item when the repair is complete");
            }
            else if(answer.equalsIgnoreCase("no")||answer.equalsIgnoreCase("n")){
                System.out.println("Feel free to return for other repairs soon!");
            }
            else{
                answer = "invalid";
                System.out.println("invalid input entered. Try again.");
            }
        }

    }

    public static void pickUpRepairedItem(MusicStore mStoreIn, Customer customerIn, RepairTech currTech){
        System.out.println("Enter the name of the item you got repaired: ");
        String itemName= scan.nextLine();
        if(currTech.getActiveRepairList().size()==0 || currTech.findRepair(itemName, customerIn.getCustomerName())==-1){
            System.out.println("This repair does not exist");
        }
        else{
        currTech.removeFromActiveRepairList(itemName, customerIn.getCustomerName());
        System.out.println("Your " + itemName + " is fixed.");
        }
    }

    public static void payEmployee(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Which employee do you want to pay?");
        employeeList(mStoreIn);

        
        int employeeID = 0;
        boolean correctTypeEntered = true;
        do{
            System.out.println("Enter employee ID:");
            try{
                employeeID = Integer.parseInt(scan.nextLine());
                System.out.println("Enter the employee type(Admin,Employee,RepairTech):");
                String employeeType = scan.nextLine();
                try{
                    adminIn.payEmployee(employeeID, employeeType);
                    System.out.println("Employee Paid");
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                correctTypeEntered=true;
            }catch(NumberFormatException ime){
                System.out.println("A number was not entered for an employee ID. Try Again.");
                correctTypeEntered=false;
            }
        }while(correctTypeEntered==false);
       
            
       
    }

    public static void giveEmployeeHours(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Which employee do you want to give hours?");
        employeeList(mStoreIn);
        int employeeID = 0;
        boolean isGoodType= true;
        do{
            System.out.println("Enter your employee ID");
            try{
            employeeID = Integer.parseInt(scan.nextLine());
            isGoodType= true;
            }
            catch(NumberFormatException nfe){
                isGoodType= false;
                System.out.println("Invalid employee ID. Try again.");
            }
        }while(isGoodType==false);
        System.out.println("Enter the employee type(Admin,Employee,RepairTech):");
        String employeeType = scan.nextLine();
        
        //X TYPE CHECK HERE EMMA!!!! <3 XOXOX
        boolean isCorrectType = true;
        boolean isValidAmt = true;
        double hours=-1;
        do{
            System.out.println("Enter the amount of hours:");
            try{
                hours = Double.parseDouble(scan.nextLine());
                try{
                    adminIn.hoursEmployee(employeeID, employeeType, hours);
                    System.out.println("Employee Given Hours");
                    isValidAmt=true;
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    isValidAmt=false;
                }
                isCorrectType=true;
            }catch(NumberFormatException nfe){
                System.out.println("Price entered contains nonnumerical input outside of a '.' ... Try again.");
                isCorrectType=false;
            }
        }while(isCorrectType==false||isValidAmt==false);
       
            
       
    }

    public static void hireEmployee(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Enter name of the new hire:");
        String newEmployeeName = scan.nextLine();
        boolean isGoodInputType=true;
        do{
            System.out.println("Enter pay amount:");
            try{
                double payAmt = Double.parseDouble(scan.nextLine());
                System.out.println("Enter the employee type(Admin,Employee,RepairTech):");
                String employeeType = scan.nextLine();
                int employeeID = (int)(Math.random() * (99999 - 10000) + 10000);
                try{
                    adminIn.hireEmployees(employeeID, newEmployeeName, payAmt, mStoreIn, employeeType);
                    System.out.println("New " + employeeType + " hired and added to the system");
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                isGoodInputType=true;
            }catch(NumberFormatException ime){
                isGoodInputType=false;
                System.out.println("Invalid pay amount entered. Try again.");
            }
        }while(isGoodInputType==false);
        
        
    }
    public static void fireEmployee(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Which employee do you want to fire?");
        employeeList(mStoreIn);
      
        
        int employeeID = 0;
        boolean isGoodInputType=true;
        do{
            System.out.println("Enter employee ID:");
            try{
                employeeID = Integer.parseInt(scan.nextLine());
                System.out.println("Enter the employee type(Admin,Employee,RepairTech):");
                String employeeType = scan.nextLine();
            
                try{
                    if(employeeID != adminIn.employeeID){
                        adminIn.fireEmployee(employeeID, employeeType);
                        System.out.println(employeeType + " has been terminated!");
                    }else{
                        System.out.println("Can'terminate self!");
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                isGoodInputType=true;
            }catch(NumberFormatException ime){
                System.out.println("Invalid employee ID entered. Try again.");
                isGoodInputType=false;
            }
        }while(isGoodInputType==false);
    }

    public static void viewStoreBalance(MusicStore mStoreIn){
        System.out.println("Store balance: " + mStoreIn.getStoreBalance());
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
        boolean isGoodInputType=true;
        do{
            try{
                int roomNumber = Integer.parseInt(scan.nextLine());
                try{
                    adminIn.addSpaceToRental(roomNumber, mStoreIn.getRoomList());
                    System.out.println("Room added!");
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                isGoodInputType=true;
            }catch(NumberFormatException ime){
                System.out.println("Input entered for room number was not a valid number. Try again.");
                isGoodInputType=false;
            }
        }while(isGoodInputType==false);
    }
    public static void cancelRentalSpace(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Currently rented");
        if(mStoreIn.getRoomList().isEmpty()){
            System.out.println("No rooms are currently being rented!");
        }else{
            for(int i = 0; i < mStoreIn.getRoomList().size(); i++ ){
                System.out.print("Room number: " + mStoreIn.getRoomList().get(i).getRoomNumber() + ", ");
                System.out.print(" Renter name: " + mStoreIn.getRoomList().get(i).getRenterName());
                System.out.println("");
            }
            //X TYPE CHECK HERE EMMA!!!! <3 XOXOX
            int roomNumber=-1;
            boolean isCorrectType=true;
            do{
                System.out.println("Enter the room number to cancel:");
                try{
                    roomNumber = Integer.parseInt(scan.nextLine());
                    try{
                        adminIn.cancelSpaceRental(roomNumber, mStoreIn.getRoomList());
                        System.out.println("Room canceled!");
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    isCorrectType=true;
                }catch(NumberFormatException nfe){
                    System.out.println("Non numerical input entered. Try again.");
                    isCorrectType=false;
                }
            }while(isCorrectType==false);
        }
        
    }
    

    public static void orderItem(MusicStore mStoreIn, Admin adminIn){
        String itemName = "";
        while(!itemName.equalsIgnoreCase("done")){
            System.out.println("Enter the name of the product or 'done':");
            itemName = scan.nextLine();
            if (itemName.equalsIgnoreCase("done")){
                break;
            }
            //X TYpe check here
            double price = -1;
            boolean isCorrectType = true;
            boolean isValidAmt = true;
            do{
                System.out.println("What is the price?");
                try{
                    price = Double.parseDouble(scan.nextLine());
                    
                    try{
                        if(isAmountValid(price)){
                            mStoreIn.subtractFromStoreBalance(price);
                            adminIn.orderItem(itemName, price);
                            System.out.println(itemName + " added to the inventory to rent");
                            isValidAmt=true;
                        }else{
                            System.out.println("Please make sure to enter a valid amount");
                        }
                    }
                    catch(Exception e){
                        isValidAmt=false;
                        System.out.println(e.getMessage());
                    }
                    isCorrectType=true;
                }catch(NumberFormatException nfe){
                    System.out.println("Price entered contains nonnumerical input outside of a '.' ... Try again.");
                    isCorrectType=false;
                }
            }while(isCorrectType==false||isValidAmt==false);
        }    

    }
    public static void orderEquipment(MusicStore mStoreIn, Admin adminIn){
        System.out.println("Enter name of equipment you want to add: ");
        String itemName = scan.nextLine();
        //TYPE CHECK HERE EMMA!!!! <3 XOXOX
        boolean isCorrectType = true;
        boolean isValidAmt = true;
        double price=-1;
        do{
            System.out.println("Enter price of equipment you want to add: ");
            try{
                price = Double.parseDouble(scan.nextLine());
                try{
                    mStoreIn.subtractFromStoreBalance(price);
                    adminIn.orderEquipment(itemName, price);
                    isValidAmt=true;
                    System.out.println("Equipment ordered");
                }catch(Exception e){
                    isValidAmt=false;
                    System.out.println("Invalid price entered");
                }
                isCorrectType=true;
            }catch(NumberFormatException nfe){
                System.out.println("Price entered contains nonnumerical input outside of a '.' ... Try again.");
                isCorrectType=false;
            }
        }while(isCorrectType==false||isValidAmt==false);
    }

    public static void displayRepairPricingInfo(MusicStore storeIn){
        System.out.println("\nBusiness Days \tPrice");
        storeIn.printRepairPricings();
        System.out.println("");
    }
    
    public static void updateRepairPricing(MusicStore mStoreIn, Admin adminIn){
        displayRepairPricingInfo(mStoreIn);
        System.out.println("Enter the number for the kind of repair of which you'd like to update the price:");
        RepairBusinessDayCategory rc=null;
        boolean isIntType=true;
        do{
            try{
                //-1 since key set is indexed at 0
                int i = Integer.parseInt(scan.nextLine())-1;
                try{
                rc = mStoreIn.getRepairBusinessCategory(i);
                }
                catch(Exception e){
                    System.out.println("Invalid number entered");
                }
                isIntType=true;
            }catch(NumberFormatException nfe){
                isIntType=false;
                System.out.println("Input entered was not a number. Try again.");
            }
        }while(rc==null||isIntType==false);
        boolean isCorrectType = true;
        boolean isValidAmt = true;
        double amount=-1;
        do{
            System.out.println("Enter the price you'd like to set for a repair that's "+rc.toString()+" business days: ");
            try{
                amount = Double.parseDouble(scan.nextLine());
                try{
                    adminIn.updateRepairPricing(rc, amount);
                    isValidAmt=true;
                }catch(Exception e){
                    isValidAmt=false;
                    System.out.println("Invalid price entered");
                }
                isCorrectType=true;
            }catch(NumberFormatException nfe){
                System.out.println("Price entered contains nonnumerical input outside of a '.' ... Try again.");
                isCorrectType=false;
            }
        }while(isCorrectType==false||isValidAmt==false);

        System.out.println("Pricing for "+rc.toString()+" business day repairs has been updated to $"+amount);
        
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
        "cancel equipment", "cancel room rental", "order total", "done", "Display information", "transaction History", "request repair", "pick up repaired item"};

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
    public static void customerInteraction(MusicStore store){
        String input = "go";
        Employee employeeOne = new Employee(12345, "Toby", store);
        RepairTech employeeTwo= new RepairTech(23456, "Max", store);
        System.out.println("Enter your name");
        String name = scan.nextLine();
        while(hasInvalidCharacters(name) || isInvalidName(name)){
            System.out.println("Invalid Name: characters entered are numbers or invalid");
            System.out.println("Enter your name");
            name = scan.nextLine();
        }
        
        Customer custOne= new Customer(store, name);

        while(!input.equalsIgnoreCase("done")){
            System.out.println("\n--Customer Menu--\nRent Room\nRent Equipment\nReturn Room Rental\nReturn Equipment\nCancel Room Rental\nCancel Equipment\nOrder Total\nDisplay information\nTransaction History\nRequest Repair\nPick Up Repaired Item\nDone\n");

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
            else if(input.equalsIgnoreCase("request repair")){
                requestRepair(store, custOne, employeeTwo);
            }
            else if(input.equalsIgnoreCase("pick up repaired item")){
                pickUpRepairedItem(store, custOne, employeeTwo);
            }
            
            
        }
        //System.out.println(custOne.getTransactionHistory().get(0).getRoomRented().getRoomNumber());
    }
    public static boolean validChoiceAdmin(String input){
        String[] choices = {"Pay employee", "Hire employee", "Terminate Employee", "View Employee List", 
        "Add Rental Space", "Cancel Rental space", "Display Repair Pricing Info","Update a Repair Price", "Order Item", "Order Equipment", "Set Employee Hours", "View Store Balance" , "Done"};

        for (int i=0;i<choices.length;i++){
            if(input.equalsIgnoreCase(choices[i])){
                return true;
            }
        }
        return false;
    }
    public static void adminInterface(MusicStore mStore, Customer customerIn){
        System.out.println("Enter 'client' for ringing up purposes or 'admin' for managerial purposes:");
        String uiPick = scan.nextLine();
        if(uiPick.equalsIgnoreCase("client")){
            employeeInterface(mStore, customerIn);
        }else if(uiPick.equalsIgnoreCase("admin")){
            System.out.println("Welcome to the Admin interface");
            String input = "go";
            Admin adminOne = null;
          
           while(!input.equals("valid")){
                int employeeID = 0;
                boolean isCorrectType= true;
                do{
                    System.out.println("Enter your employee ID");
                    try{
                    employeeID = Integer.parseInt(scan.nextLine());
                    isCorrectType= true;
                    }
                    catch(NumberFormatException nfe){
                        isCorrectType= false;
                        System.out.println("Invalid employee ID. Try again.");
                    }
                }while(isCorrectType==false);
                
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

                System.out.println("\n--Admin Menu--\nPay Employee\nHire Employee\nTerminate Employee\nSet Employee Hours\nView Employee list\nAdd Rental space\nCancel Rental Space\nOrder Item\nOrder Equipment\nDisplay Repair Pricing Info\nUpdate a Repair Price\nView Store Balance\nDone\n");

                input = scan.nextLine();

                if (!validChoiceAdmin(input)){
                    System.out.println("Please enter a valid choice");
                } 
                else if(input.equalsIgnoreCase("pay employee")){
                    payEmployee(mStore, adminOne);
                }
                else if(input.equalsIgnoreCase("Set Employee Hours")){
                    giveEmployeeHours(mStore, adminOne);
                }
                else if(input.equalsIgnoreCase("hire employee")){
                    hireEmployee(mStore, adminOne);
                }
                else if(input.equalsIgnoreCase("View Store balance")){
                    viewStoreBalance(mStore);
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
                else if(input.equalsIgnoreCase("order item")){
                    orderItem(mStore, adminOne);
                }else if(input.equalsIgnoreCase("order equipment")){
                    orderEquipment(mStore, adminOne);
                }
                else if(input.equalsIgnoreCase("display repair pricing info")){
                    displayRepairPricingInfo(mStore);
                }
                else if(input.equalsIgnoreCase("update a repair price")){
                    updateRepairPricing(mStore, adminOne);
                }
                else if(input.equalsIgnoreCase("View Employee list")){
                    employeeList(mStore);
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
            currTech.removeFromActiveRepairList(itemName, clientName);
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
            return;
        }
        //X TYPE CHECK HERE EMMA!!!! <3 XOXOX 
        boolean isCorrectType=true;
        do{
            try{
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
                isCorrectType=true;
            }catch(NumberFormatException nfe){
                isCorrectType=false;
                System.out.println("Invalid time estimate. Try again.");
            }
        }while(isCorrectType==false);
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
        }
    
        
        
    }

    public static void repairInterface(MusicStore mStore){
        System.out.println("Welcome to the Repair Tech interface");
        
        String input = "go";
        System.out.println("Enter your name");
        String name = scan.nextLine();
        while(hasInvalidCharacters(name) || isInvalidName(name)){
            System.out.println("Invalid Name: characters entered are numbers or invalid");
            System.out.println("Enter your name");
            name = scan.nextLine();
        }
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

    public static void chargeCustomer(MusicStore storeIn, Employee employeeIn, Customer customerIn){
        String input = "go";
        while(!input.equalsIgnoreCase("done")){
            System.out.println("Charge room, item, or done");
            input = scan.nextLine();
            if(input.equalsIgnoreCase("room")){
                //X TYPE CHECK HERE EMMA!!!! <3 XOXOX
                for(int i = 0; i < storeIn.availableRoomList().size(); i++){
                    System.out.println("Room number: " + storeIn.availableRoomList().get(i).getRoomNumber());
                }
                int roomNumber=-1;
                boolean isCorrectType=true;
                do{
                    System.out.println("Enter number of room:");
                    try{
                    roomNumber = Integer.parseInt(scan.nextLine());
                    isCorrectType=true;
                    }catch(NumberFormatException nfe){
                        System.out.println("invalid room number. try again.");
                        isCorrectType=false;
                    }
                }while(isCorrectType==false);
                try{
                    employeeIn.chargeCustomerForRoomRental(customerIn, roomNumber);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                } 
            }
            else if(input.equalsIgnoreCase("item")){
                for(int i = 0; i < storeIn.getInventoryList().size(); i++){
                    System.out.println("Inventory List: " + storeIn.getInventoryList().get(i).getName() +" "+ storeIn.getInventoryList().get(i).getPrice());
                }
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
                System.out.println("Customer rented: " + customerIn.getRoomRented().getRoomNumber());
                System.out.println("Enter number of room:");
                //X TYPE CHECK HERE EMMA!!!! <3 XOXOX
                int roomNumber=-1;
                boolean isCorrectType=true;
                do{
                    try{
                    roomNumber = Integer.parseInt(scan.nextLine());
                    isCorrectType=true;
                    }catch(NumberFormatException nfe){
                        System.out.println("Invalid room number. Try again");
                        isCorrectType=false;
                    }
                }while(isCorrectType==false);
                try{
                employeeIn.refundCustomerForRoomRental(customerIn, roomNumber);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if(input.equalsIgnoreCase("item")){
                for(int i = 0; i < customerIn.getRentedList().size(); i++){
                    System.out.println("Customer renting: " + customerIn.getRentedList().get(i).getName());
                }
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
                System.out.println("Has items: " + musicStoreIn.getRoomList().get(i).getHasEquipment());
                System.out.println("Room occupied: " + !musicStoreIn.getRoomList().get(i).getIsEmptyRoom());
                System.out.println("Room rate: " + musicStoreIn.getRoomList().get(i).getRate());
                System.out.println("Renter name: " + musicStoreIn.getRoomList().get(i).getRenterName());
                System.out.println();
            }
        }
    }
    public static void viewItemSchedule(MusicStore musicStoreIn){
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
        String[] choices = {"check stock", "charge customer", "refund customer", "view space schedule", "view item schedule", "done"};
        for (int i=0;i<choices.length;i++){
            if(input.equalsIgnoreCase(choices[i])){
                return true;
            }
        }
        return false;
    }
    public static void employeeInterface(MusicStore mStore, Customer customerIn){
        System.out.println("Welcome to the Employee interface");
        Employee currEmployee = null;
        String input = "go";
        while(!input.equals("valid")){
            int employeeID = 0;
            //X TYPE CHECK HERE EMMA!!!! <3 XOXOX
            boolean isCorrectType= true;
            do{
                System.out.println("Enter your employee ID");
                try{
                employeeID = Integer.parseInt(scan.nextLine());
                isCorrectType= true;
                }
                catch(NumberFormatException nfe){
                    isCorrectType= false;
                    System.out.println("Invalid employee ID. Try again.");
                }
            }while(isCorrectType==false);
            
            if (mStore.findEmployee(employeeID) !=-1){
                int index = mStore.findEmployee(employeeID);
                currEmployee = mStore.getEmployee(index);
                input = "valid";
            }else if(mStore.findAdmin(employeeID) !=-1){
                int index = mStore.findAdmin(employeeID);
                currEmployee = mStore.getAdmin(index);
                input = "valid";
            }
            else{
                System.out.println("Incorrect employeeID");
            }
        }
        System.out.println("Welcome: " + currEmployee.getName());
        while(!input.equalsIgnoreCase("done")){
            System.out.println("\n--Employee Menu--\nCheck Stock\nCharge Customer\nRefund Customer\nView Space Schedule\nView Item Schedule\nDone\n");
            input = scan.nextLine();

            if (!validChoiceEmployee(input)){
                System.out.println("Please enter a valid choice");
            }
            else if(input.equalsIgnoreCase("check stock")){
                checkStock(mStore, currEmployee);
            }
            else if(input.equalsIgnoreCase("charge customer")){
                chargeCustomer(mStore, currEmployee, customerIn);
            }
            else if(input.equalsIgnoreCase("refund customer")){
                refundCustomer(currEmployee, customerIn);
            }
            else if(input.equalsIgnoreCase("view space schedule")){
                viewSpaceSchedule(mStore);
            }
            else if(input.equalsIgnoreCase("view item schedule")){
                viewItemSchedule(mStore);
            }
            
        }
    }

    public static void main(String[] args)  {
        Scanner scan = new Scanner(System.in);
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
        
        Customer currCustomer = new Customer(mStore, "Jim Bob");
        currCustomer.rentRoom(1, new Employee(12345, "Doe", mStore));
        currCustomer.rentItem("Piano",  new Employee(12345, "Doe", mStore));
        currCustomer.rentItem("Guitar",  new Employee(12345, "Doe", mStore));
        
        mStore.addToStoreBalance(50000);
        
        mStore.addAdmin(new Admin(12346, "Sean Blackford", mStore));
        mStore.addEmployee(new Employee(12347, "Toby", mStore));
        //mStore.addToRepairTechList(new RepairTech(12348, "Doug", mStore));
        //mStore.addToRepairTechList(new RepairTech(12346, "Morgan", mStore));
        //mStore.addToRepairTechList(new RepairTech(12348, "Sam", mStore));

        mStore.addEquipment(new Equipment("guitar string", 12));
        mStore.addEquipment(new Equipment("glue", 6));

        int menuNum=-1;
        int numMenus=4;
        boolean isCorrectType = true;
        while(isCorrectType==false||menuNum<0||menuNum>numMenus){
            System.out.println("--------User Menu--------");
            System.out.println("Customer              (1)");
            System.out.println("Admin                 (2)");
            System.out.println("RepairTech            (3)");
            System.out.println("Employee              (4)");
            try{
                System.out.println("\nEnter a number for the user menu you'd like to use or '0' to exit: ");
                menuNum = Integer.parseInt(scan.nextLine());
                if(menuNum==1){
                    customerInteraction(mStore);
                    menuNum=-1;
                }
                else if(menuNum==2){
                    adminInterface(mStore, currCustomer);
                    menuNum=-1;
                }
                else if(menuNum==3){
                    repairInterface(mStore);
                    menuNum=-1;
                }
                else if(menuNum==4){
                    employeeInterface(mStore, currCustomer);
                    menuNum=-1;
                }
                else if (menuNum==0){
                    System.out.println("Thank you for coming to the Ithaca Music Store. Come back soon.");
                }
                else{
                    System.out.println("Invalid menu number provided. Try again.\n");
                }
                isCorrectType=true;
            }catch(NumberFormatException e){
                System.out.println("Input entered is not a number. Try again.\n");
                isCorrectType=false;
            }
        }
        scan.close();
    }
}
