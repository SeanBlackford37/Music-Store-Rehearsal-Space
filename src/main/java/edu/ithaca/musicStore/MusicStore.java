package edu.ithaca.musicStore;

import java.util.ArrayList;
import java.util.List;



public class MusicStore {
    private String storeName;
    private List<Item> inventoryList;
    private List<Item> rentedList;
    private List<Employee> employeeList;
    //private List<RepairTech> repairTechList;
    private List<Room> roomList;
    private double storeBalance;

    public MusicStore(String name){
        storeName = name;
        inventoryList = new ArrayList<>();
        rentedList = new ArrayList<>();
        employeeList = new ArrayList<>();
        //repairTechList = new ArrayList<>();
        roomList = new ArrayList<>();
        storeBalance = 0;
    }


    public MusicStore(String name, double balance) throws IllegalArgumentException{
        if(!isAmountValid(balance)){
            throw new IllegalArgumentException("invalid argument");
        }
        else{
            storeName = name;
            inventoryList = new ArrayList<>();
            rentedList = new ArrayList<>();
            employeeList = new ArrayList<>();
            //repairTechList = new ArrayList<>();
            roomList = new ArrayList<>();
            storeBalance =  balance;
        }
    }

    public void addToInventory(Item itemToAdd){
        inventoryList.add(itemToAdd);
    }

    public void removeFromInventory(String itemName, double price) throws IllegalArgumentException{
        int found = 0;
        for (int i = 0; i < inventoryList.size(); i++){
            if (inventoryList.get(i).getName().equals(itemName)){
                if(inventoryList.get(i).getPrice() == price){
                    inventoryList.remove(i);
                    found++;
                    break;
                }
            }
        }
        if (found == 0){
            throw new IllegalArgumentException("Item does not exist");
        }
    }

    public String getStoreName(){
        return storeName;
    }

    public int searchForInventoryItem(String itemName){
        for (int i = 0; i < inventoryList.size(); i++){
            if (inventoryList.get(i).getName().equals(itemName)){
                return i;
            }
        }
        return -1;
    }
    
    public Item getInventoryItem(int index){
        return inventoryList.get(index);
    }

    public Item getRentedItem(int index){
        return rentedList.get(index);
    }

    public int getInventorySize(){
        return inventoryList.size();
    }

    public void moveToRented(String itemName) throws IllegalArgumentException{
        int itemIndex = searchForInventoryItem(itemName);
        if (itemIndex == -1){
            throw new IllegalArgumentException("Item does not exist");
        }
        else{
            addToRented(inventoryList.get(itemIndex));
            removeFromInventory(itemName, inventoryList.get(itemIndex).getPrice());
        }
        

    }

    public void moveToInventory(String itemName){
        int itemIndex = searchForRentedItem(itemName);
        if (itemIndex == -1){
            throw new IllegalArgumentException("Item does not exist");
        }
        else{
            addToInventory(rentedList.get(itemIndex));
            removeFromRented(itemName, rentedList.get(itemIndex).getPrice());
        }
    }

    public void addToRented(Item itemToAdd){
        rentedList.add(itemToAdd);
    }

    public void removeFromRented(String itemName, double price) throws IllegalArgumentException{
        int found = 0;
        for (int i = 0; i < rentedList.size(); i++){
            if (rentedList.get(i).getName().equals(itemName)){
                if(rentedList.get(i).getPrice() == price){
                    rentedList.remove(i);
                    found++;
                    break;
                }
            }
        }
        if (found == 0){
            throw new IllegalArgumentException("Item does not exist");
        }
    }

    public int searchForRentedItem(String itemName){
        for (int i = 0; i < rentedList.size(); i++){
            if (rentedList.get(i).getName().equals(itemName)){
                return i;
            }
        }
        return -1;
    }

    public int getRentedSize(){
        return rentedList.size();
    }

    public void addToRoomList(Room itemToAdd){
        roomList.add(itemToAdd);
    }

    public void removeFromRoomList(int roomName) throws IllegalArgumentException{
        int found = 0;
        for (int i = 0; i < roomList.size(); i++){
            if (roomList.get(i).getRoomNumber()==(roomName)){
                    roomList.remove(i);
                    found++;
                    break;
            }
        }
        if (found == 0){
            throw new IllegalArgumentException("Item does not exist");
        }
    }

    public int findRoom(int roomNumber){
        for (int i = 0; i < roomList.size(); i++){
            if (roomList.get(i).getRoomNumber()==(roomNumber)){
                return i;
            }
        }
        return -1;
        //stuff
    }

    public Room getRoom(int index){
        return roomList.get(index);
    }

    public int getRoomListSize(){
        return roomList.size();
    }

    public double getStoreBalance(){
        return storeBalance;
    }


    public void addEmployee(Employee toAdd){
        employeeList.add(toAdd);
    }

    public void removeEmployee(int employeeID){
        boolean contains= false;
        for(int i=0; i<employeeList.size(); i++){
            if(employeeList.get(i).getID()== employeeID){
                contains= true;
                employeeList.remove(i);
            }
        }
        if(contains== false){
            throw new IllegalArgumentException("Employee does not exist");
        }
        }
       
    

    public List<Employee> getEmployeeList(){
        return employeeList;
    }

    public Employee getEmployee(int index){
        return employeeList.get(index);
    }



    public void addToStoreBalance(double profit) throws IllegalArgumentException{
        if(!isAmountValid(profit)){
            throw new IllegalArgumentException("invalid argument");
        }else{
            storeBalance += profit;
        }
    }

    public void subtractFromStoreBalance(double cost) throws IllegalArgumentException{
        if(!isAmountValid(cost) || cost > storeBalance){
            throw new IllegalArgumentException("invalid argument");
        }else{
            storeBalance -= cost;
            int num  = (int)(storeBalance * 100);
            storeBalance = ((double)num) /100;
        }
    }

    public static boolean isAmountValid(double balance) {
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
          return true;
        }
       return false;
    }

     public int findEmployee(int ID){
        
    
    }



}
