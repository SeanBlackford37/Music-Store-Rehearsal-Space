package edu.ithaca.musicStore;

import java.util.ArrayList;
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
    public static void cancelRentingRoom(Customer custIn, Room room){
        if(custIn.getRoomRented() != null){
            custIn.cancelRoom(room.getRoomNumber()); 
            System.out.println("Canceled room " + room.getRoomNumber());
        }else{
            System.out.println("You are not currently renting a room");
        }
       
    }

    public static void returnEquipment(MusicStore store, Customer custIn){
        
        System.out.println("What item would you like to return?");
        ArrayList<Item> rentedItems = custIn.getRentedList();
        String equipmentToReturn = "";
        
        while(!equipmentToReturn.equalsIgnoreCase("done") && !rentedItems.isEmpty()){
            System.out.println("Rented items:");
            for(int i = rentedItems.size()-1; i >= 0; i--){
                System.out.println(rentedItems.get(i).getName());
            }
            equipmentToReturn = scan.nextLine();
            if(store.searchForRentedItem(equipmentToReturn) != -1){
                custIn.cancelItemRental(equipmentToReturn);
                System.out.println(equipmentToReturn + " has been returned");
            }else if(!equipmentToReturn.equalsIgnoreCase("done")){
                System.out.println("You did not rent out that item");
                System.out.println("Please enter a valid rented item");
            }
            
        }
    }

    public static boolean validChoice(String input){
        String[] choices = {"rent room", "rent equipment", "cancel room rental", "return equipment", "done", "Display information", "transaction History"};
        for (int i=0;i<choices.length;i++){
            if(input.equalsIgnoreCase(choices[i])){
                return true;
            }
        }
        return false;
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
            System.out.println("You have nothing rented!");
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
        Employee employeeOne = new Employee(12345, "Toby");
        System.out.println("Enter your name");
        String name = "Sean Blackford";
        name = scan.nextLine();
        
        Customer custOne= new Customer(store, name);

        while(!input.equalsIgnoreCase("done")){
            System.out.println("\n--Customer Menu--\nRent Room\nRent Equipment\nCancel Room Rental\nReturn Equipment\nDone\nDisplay information\nTransaction History\n");
            input = scan.nextLine();
           
            if (!validChoice(input)){
                
                System.out.println("Please enter a valid choice");
            } 
            if(input.equalsIgnoreCase("rent room")){
                rentingRoom(store, custOne, employeeOne);
            }
            else if(input.equalsIgnoreCase("rent equipment")){
                rentingEquipment(store, custOne, employeeOne);
            }
            else if(input.equalsIgnoreCase("cancel room rental")){
                cancelRentingRoom(custOne, custOne.getRoomRented());
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

    public static void main(String[] args)  {
        customerInteraction();
       
    }


}