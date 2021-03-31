package edu.ithaca.musicStore;
import java.util.ArrayList;

public class Customer {

    private MusicStore currentStore;
    private ArrayList<Transaction> transactionHistory;
    private ArrayList<Item> rentedItems;

    public Customer(MusicStore currentStoreIn) throws NullPointerException{
        if(currentStoreIn!=null){
            currentStore=currentStoreIn;
            transactionHistory = new ArrayList<>();
            rentedItems = new ArrayList<>();
        }
        else{
            throw new NullPointerException("Customer is not associated with a store");
        }
        
    }

    
    public double rentItem(String itemName) throws IllegalArgumentException{
        //if it's in the inventory, get it 
        //create transaction
        //add it to the transactionlist
        // throws exception if item is out of stock/already rented out
        return -1;
    }

    /**
     * @pre transaction must be added to transactionList and store inventory
     * @param rentalToCancel
     */
    public String cancelItemRental(String itemName) throws IllegalArgumentException{
        // if in transaction list, remove transaction
        // give customer transaction printout?
        // restore it to inventory
        return "";
    }

    //return item method?
    //property for items and rooms currently rented out
    //or will method use musicStore
    public void returnItem(String itemName) throws IllegalArgumentException{

    }

    public ArrayList<Transaction> getTransactionHistory(){
        return transactionHistory;
    }

    public Transaction findTransaction(String itemName){
        return null;
    }

    public Transaction findTransaction(int index){
        return null;
    }

    public int getTransactionHistorySize(){
        return transactionHistory.size();
    }
    public int getRentedItemsSize(){
        return rentedItems.size();
    }



}
