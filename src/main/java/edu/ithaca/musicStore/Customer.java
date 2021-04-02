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

    
    public double rentItem(String itemName, Employee seller) throws IllegalArgumentException{
        
        int itemStockIndex = currentStore.searchForInventoryItem(itemName);
        if(itemStockIndex>=0){
            if(seller!=null){
                Item rental = currentStore.getInventoryItem(itemStockIndex);
                rental.setRenterName(customerName);
                currentStore.moveToRented(itemName);
                transactionHistory.add(new Transaction(rental,this, seller));
                rentedItems.add(rental);
                return rental.getPrice();
            }
            else{throw new IllegalArgumentException("Invalid seller entered");}
        }
        else{ throw new IllegalArgumentException("Item is out of inventory");}
    }

    /**
     * @pre transaction must be added to transactionList and store inventory
     * @param rentalToCancel
     */
    public Transaction cancelItemRental(String itemName) throws IllegalArgumentException{
        for(int i=transactionHistory.size()-1;i>=0;i--){
            Transaction t = transactionHistory.get(i);
            if(t.getItemRented()!=null){
                if(t.getItemRented().getName().equals(itemName)){
                    returnItem(itemName);
                    return transactionHistory.remove(i);
                }
            }
        }
        throw new IllegalArgumentException("No transaction exists for this item");
    }

    public Item returnItem(String itemName) throws IllegalArgumentException{
        int itemStoreRentedIndex = currentStore.searchForRentedItem(itemName);
        if(itemStoreRentedIndex>=0){

            for(int i=0;i<rentedItems.size();i++){
                Item returnItem = rentedItems.get(i);
                if(returnItem.getName().equals(itemName)){
                    returnItem.setRenterName("n/a");
                    rentedItems.remove(returnItem);
                    currentStore.moveToInventory(itemName);
                    return returnItem;
                }
            }
            throw new IllegalArgumentException("you haven't rented this item");
        }else{throw new IllegalArgumentException("we don't have any record of this item being rented out"); }
    }

    public ArrayList<Transaction> getTransactionHistory(){
        return transactionHistory;
    }

    public Transaction findTransaction(String itemName) throws IllegalArgumentException{
        for(int i=0;i<transactionHistory.size();i++){
            Transaction t = transactionHistory.get(i);
            if(t.getItemRented()!=null){
                if(t.getItemRented().getName().equals(itemName)){
                    return t;
                }
            }
        }
        throw new IllegalArgumentException("No transaction exists for that item");
    }

    public Transaction findTransaction(int index) throws IndexOutOfBoundsException{
        if(index>=transactionHistory.size()||index<0){
            throw new IndexOutOfBoundsException("invalid index entered");
        }
        return transactionHistory.get(index);
        
    }

    public double rentRoom(int roomNumber, Employee seller) throws IllegalArgumentException{
        int roomIndex=currentStore.findRoom(roomNumber);
        if(seller==null){
            throw new IllegalArgumentException("seller not in system");
        }
        else if(roomIndex==-1){
            throw new IllegalArgumentException("room is not in our system");
        }
        else{
            Room r = currentStore.getRoom(roomIndex);
            if(r.getIsEmptyRoom()&&!r.getHasEquipment()){
                r.setIsEmptyRoom(false);
                r.setRenterName(customerName);
                //figure out equipment for next sprint
                Transaction t = new Transaction(this, seller, r);
                transactionHistory.add(t);
                //currentStore.removeFromRoomList(roomNumber);
                return r.getRate();
            }
            else{throw new IllegalArgumentException("room is currently occupied or unprepared");}
        }
    }

    public Room returnRoom(int roomNum) throws IllegalArgumentException{
        int roomIndex=currentStore.findRoom(roomNum);
        if(roomIndex==-1){
            throw new IllegalArgumentException("room is not in our system");
        }
        else{
            Room r = currentStore.getRoom(roomIndex);
            if(!r.getIsEmptyRoom()&&r.getRenterName().equals(customerName)){
                r.setIsEmptyRoom(true);
                r.setRenterName("n/a");
                return r;
            }
            else if(r.getIsEmptyRoom()){
                throw new IllegalArgumentException("room is already unoccupied");
            }
            else{throw new IllegalArgumentException("room is being rented by another patron");}
        }

    }

    public Transaction cancelRoom(int roomNum) throws IllegalArgumentException{
        return null;
    }

    public int getTransactionHistorySize(){
        return transactionHistory.size();
    }
    public int getRentedItemsSize(){
        return rentedItems.size();
    }

    public String getCustomerName(){
        return customerName;
    }

    public Item getRentedItem(int index)throws IndexOutOfBoundsException{
        if(index>=rentedItems.size()||index<0){
            throw new IndexOutOfBoundsException("invalid index entered");
        }
        return rentedItems.get(index);
    }
}
