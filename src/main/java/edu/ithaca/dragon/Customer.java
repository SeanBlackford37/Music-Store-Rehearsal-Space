package edu.ithaca.dragon;
import java.util.ArrayList;

public class Customer {

    private MusicStore currentStore;
    private ArrayList<Transaction> transactionList;

    public Customer(MusicStore currentStoreIn) throws NullPointerException{
        if(currentStoreIn!=null){
            currentStore=currentStoreIn;
            transactionList = new ArrayList<>();
        }
        else{
            throw new NullPointerException("Customer is not associated with a store");
        }
        
    }

    
    public void rentItem(String itemName) throws IllegalArgumentException{
        //if it's in the inventory, get it 
        //create transaction
        //add it to the transactionlist
        // throws exception if item is out of stock/already rented out
    }

    /**
     * @pre transaction must be added to transactionList and store inventory
     * @param rentalToCancel
     */
    public void cancelItemRental(Item rentalToCancel){
        //if in transaction list, delete it
        //restore it to inventory
    }

    public void rentRoom(Room spaceToRent){}

    public void cancelRoomRental(Room rentalToCancel){}

    public double calculatePrice(){}





}
