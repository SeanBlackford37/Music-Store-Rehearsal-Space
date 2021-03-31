package edu.ithaca.musicStore;
import java.util.ArrayList;

public class Customer {

    private MusicStore currentStore;
    private ArrayList<Transaction> transactionHistory;
    private ArrayList<Item> rentedItems;
    private String customerName;

    public Customer(MusicStore currentStoreIn, String nameIn) throws NullPointerException{
        if(currentStoreIn!=null){
            currentStore=currentStoreIn;
            transactionHistory = new ArrayList<>();
            rentedItems = new ArrayList<>();
            customerName = nameIn;
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
        int itemStockIndex = currentStore.searchForInventoryItem(itemName);
        if(itemStockIndex>=0){
            Item rental = currentStore.getInventoryItem(itemStockIndex);
            rental.setRenterName(customerName);
            currentStore.moveToRented(itemName);
            transactionHistory.add(new Transaction(rental,this));
            return rental.getPrice();
        }
        else{ throw new IllegalArgumentException("Item is out of inventory");}
    }

    /**
     * @pre transaction must be added to transactionList and store inventory
     * @param rentalToCancel
     */
    public Transaction cancelItemRental(String itemName) throws IllegalArgumentException{
        // if in transaction list, remove transaction
        // give customer transaction printout?
        // restore it to inventory
        return null;
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
