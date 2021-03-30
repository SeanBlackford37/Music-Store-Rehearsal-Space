package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;

import edu.ithaca.musicStore.Item;

public class MusicStore {
    private String storeName;
    private List<Item> inventoryList;
    private List<Item> rentedList;
    //private List<Employee> employeeList;
    //private List<RepairTech> repairTechList;
    //private List<Room> roomList;

    public MusicStore(String name){
        storeName = name;
        inventoryList = new ArrayList<>();
        rentedList = new ArrayList<>();
        //employeeList = new ArrayList<>();
        //repairTechList = new ArrayList<>();
        //roomList = new ArrayList<>();
    }

    public void addToInventory(Item itemToAdd){
        //inventoryList.add(itemToAdd);
    }

    public void removeFromInventory(String itemName, double price){
        //inventoryList.remove(itemToRemove);
    }

    public String getStoreName(){
        return storeName;
    }

    public int searchForInventoryItem(String itemName){
        return -3;
    }
    
    public Item getInventoryItem(int index){
        return inventoryList.get(index);
    }

    public int getInventorySize(){
        return inventoryList.size();
    }

    public void moveToRented(String itemName){

    }

    public void moveToInventory(String itemName){

    }

    public void addToRented(Item itemToAdd){
        //inventoryList.add(itemToAdd);
    }

    public void removeFromRented(String itemName, double price){
        //inventoryList.remove(itemToRemove);
    }

    public int searchForRentedItem(String itemName){
        return -3;
    }

    public int getRentedSize(){
        return rentedList.size();
    }


}
