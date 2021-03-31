package edu.ithaca.musicStore;

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

    public void removeFromRented(String itemName, double price){
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


}