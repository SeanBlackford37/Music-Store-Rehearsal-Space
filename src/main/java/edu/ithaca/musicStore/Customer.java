package edu.ithaca.musicStore;
import java.util.ArrayList;

public class Customer {

    private MusicStore currentStore;
    private ArrayList<Transaction> transactionHistory;
    private ArrayList<Item> rentedItems;
    private String customerName;
    private Room roomRented;
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

    public double rentMultipleItems(ArrayList<String> itemsToRent, Employee seller){
        int itemStock = 0;
        if(itemsToRent == null){
            throw new IllegalArgumentException("items to rent is empty!");
        }
        for(int i = 0; i < itemsToRent.size(); i++){
            if(currentStore.searchForInventoryItem(itemsToRent.get(i)) != -1){
                itemStock++;
            }
       
        }
        if(itemStock == itemsToRent.size()){
            if(seller!=null){
                int itemStockIndex = -1;
                ArrayList <Item> rentedList = new ArrayList <Item>();

                for(int i = 0; i < itemsToRent.size(); i++){
                    itemStockIndex = currentStore.searchForInventoryItem(itemsToRent.get(i));
                    Item rental = currentStore.getInventoryItem(itemStockIndex);
                    rental.setRenterName(customerName);
                    
                    rentedItems.add(rental);
                    rentedList.add(rental);
                    }
                    transactionHistory.add(new Transaction(rentedList,this, seller));
                
                for(int i = 0; i < itemsToRent.size(); i++){
                    currentStore.moveToRented(itemsToRent.get(i));
                }

                return transactionHistory.get(transactionHistory.size()-1).getOrderAmount();
            }
            else{throw new IllegalArgumentException("Invalid seller entered");}
        }
        else{ throw new IllegalArgumentException("One or more items is out of inventory");}
       
    }

    public void rentItemAndRoom(String itemIn, int roomIn, Employee employeeIn){
        int itemStockIndex = currentStore.searchForInventoryItem(itemIn);
        int roomIndex=currentStore.findRoom(roomIn);
        
        if(itemStockIndex>=0 && roomIndex !=-1){
            Room r = currentStore.getRoom(roomIndex);
            if(employeeIn !=null){
                Item rental = currentStore.getInventoryItem(itemStockIndex);
                rental.setRenterName(customerName);
                currentStore.moveToRented(itemIn);
               
                rentedItems.add(rental);
                if(r.getIsEmptyRoom()&&!r.getHasEquipment()){
                    r.setIsEmptyRoom(false);
                    r.setRenterName(customerName);
                    this.roomRented = r;
                    currentStore.removeFromRoomList(roomIn);
                }
                Transaction t = new Transaction(rental, r, this, employeeIn);
                transactionHistory.add(t);
            }
            else{throw new IllegalArgumentException("Invalid seller entered and/or invalid room number");}
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
                if(t.getItemRented().getName().equalsIgnoreCase(itemName)){
                    returnItem(itemName);
                    return transactionHistory.remove(i);
                }
            }
        }
        throw new IllegalArgumentException("No transaction exists for this item");
    }
    public Transaction cancelMultipleItemRentals(ArrayList<String> itemsToCancel, Customer customerIn, Employee employeeIn) throws IllegalArgumentException{
       
        for(int i=transactionHistory.size()-1;i>=0;i--){
            Transaction t = transactionHistory.get(i);
            ArrayList<Item> itemsForUpdatedList = new ArrayList<Item>();
            if(t.getItemsRented()!=null || !t.getItemsRented().isEmpty()){
                ArrayList <Item> rentedList = t.getItemsRented();
                int itemCount = 0;
                for(int j = 0; j < rentedList.size(); j++){
                    int num = 0;
                    for(int m = 0; m < itemsToCancel.size(); m++){
                        if(rentedList.get(j).getName().equalsIgnoreCase(itemsToCancel.get(m))){
                            returnItem(itemsToCancel.get(m));
                            itemCount++;
                        }else{
                            num++;
                        }
                            
                    }
                    if(num == itemsToCancel.size()){ //Item that isnt removed
                    itemsForUpdatedList.add(rentedList.get(j));
                    }
                }
                    if(itemCount == rentedList.size()){
                        return transactionHistory.remove(i);
                    }else{
                        Transaction updatedTransaction = new Transaction(itemsForUpdatedList, customerIn, employeeIn);
                        transactionHistory.remove(i);
                        transactionHistory.add(i,updatedTransaction);
                        
                    }

                
            }
        }
        return null;
        //throw new IllegalArgumentException("No transaction exists for this items");
      
    }

    public Item returnItem(String itemName) throws IllegalArgumentException{
        int itemStoreRentedIndex = currentStore.searchForRentedItem(itemName);
        if(itemStoreRentedIndex>=0){

            for(int i=0;i<rentedItems.size();i++){
                Item returnItem = rentedItems.get(i);
                if(returnItem.getName().equalsIgnoreCase(itemName)){
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

    public Transaction getTransaction(int index) throws IndexOutOfBoundsException{
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
                this.roomRented = r;
                //figure out equipment for next sprint
                Transaction t = new Transaction(this, seller, r);
                transactionHistory.add(t);
                //currentStore.removeFromRoomList(roomNumber);
                return r.getRate();
            }
            else{throw new IllegalArgumentException("room is currently occupied or unprepared");}
        }
    }
    public Room getRoomRented(){
        return roomRented;
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
        
        for(int i=transactionHistory.size()-1;i>=0;i--){
            Transaction t = transactionHistory.get(i);
            if(t.getRoomRented()!=null){
                Room r = t.getRoomRented();
                if(r.getRoomNumber()==roomNum&&r.getRenterName()==customerName){
                    returnRoom(roomNum);
                    return transactionHistory.remove(i);
                }
            }

        }
        throw new IllegalArgumentException("no transaction exists for this room");

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
    public ArrayList<Item> getRentedList(){
        return rentedItems;
    }

    public Item getRentedItem(int index)throws IndexOutOfBoundsException{
        if(index>=rentedItems.size()||index<0){
            throw new IndexOutOfBoundsException("invalid index entered");
        }
        return rentedItems.get(index);
    }

    public Item findRentedItem(String itemName) throws IllegalArgumentException{
        for(int i=0;i<rentedItems.size();i++){
            Item item = rentedItems.get(i);
            if(item.getName().equals(itemName)){
                return item;
            }
        }
        throw new IllegalArgumentException("Customer is not currently renting this item");
    } 
}
