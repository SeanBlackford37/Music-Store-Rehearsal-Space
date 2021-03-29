package edu.ithaca.dragon;
import java.util.ArrayList;

public class Customer {

    private MusicStore currentStore;
    private ArrayList<Transaction> transactionHistory;

    public Customer(MusicStore currentStoreIn) throws NullPointerException{
        if(currentStoreIn!=null){
            currentStore=currentStoreIn;
            transactionList = new ArrayList<>();
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
    }

    /**
     * @pre transaction must be added to transactionList and store inventory
     * @param rentalToCancel
     */
    public Transaction cancelItemRental(String itemName) throws IllegalArgumentException{
        //if in transaction list, remove transaction
        //restore it to inventory
    }

    public double calculatePrice(){
        //for one item? or multiple items?
        //does that mean we change transaction and rentItem for multiple
    }

    //return item method?
    //property for items and rooms currently rented out
    //or will method use musicStore





}
