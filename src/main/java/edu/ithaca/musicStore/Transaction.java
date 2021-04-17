package edu.ithaca.musicStore;

import java.util.ArrayList;

//import java.util.Calendar;
//import java.util.TimeZone;

public class Transaction {
    //private String dateTime;
    private Item itemRented;
    private ArrayList<Item> itemsRented;
    private Customer buyer;
    private Employee seller;
    private double orderAmount;
    private String description;
    private Room roomRented;

    public Transaction(Item itemIn, Customer buyerIn, Employee sellerIn) throws IllegalArgumentException{
        if(itemIn==null || buyerIn==null||sellerIn==null){
            throw new IllegalArgumentException("null argument entered");
        }
        //Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Philadelphia") );
        //dateTime=String.valueOf(cal.get(Calendar.MONTH))+String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
        //+String.valueOf(cal.get(Calendar.YEAR));
        itemRented=itemIn;
        buyer=buyerIn;
        orderAmount=itemIn.getPrice();
        seller = sellerIn;
        description="\nTransaction\n\nSeller: "+seller.getName()+"\nCustomer: "+buyer.getCustomerName()
        +"\nRental Item: "+itemRented.getName()+"\nTotal: $"+orderAmount;
        
    }
    public Transaction(Item itemIn, Room roomIn, Customer buyerIn, Employee sellerIn) throws IllegalArgumentException{
        if(itemIn==null || roomIn==null || buyerIn==null||sellerIn==null){
            throw new IllegalArgumentException("null argument entered");
        }
        //Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Philadelphia") );
        //dateTime=String.valueOf(cal.get(Calendar.MONTH))+String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
        //+String.valueOf(cal.get(Calendar.YEAR));
        itemRented=itemIn;
        roomRented = roomIn;
        buyer=buyerIn;
        orderAmount=itemIn.getPrice() + roomIn.getRate();
        seller = sellerIn;
        description="\nTransaction\n\nSeller: "+seller.getName()+"\nCustomer: "+buyer.getCustomerName()
        +"\nRental Item: "+itemRented.getName()+"\nRented Room number: "+roomRented.getRoomNumber()+"\nTotal: $"+orderAmount;
        
    }
    public Transaction(ArrayList<Item> itemsIn, Customer buyerIn, Employee sellerIn) throws IllegalArgumentException{
        if(itemsIn.isEmpty() || buyerIn==null||sellerIn==null || itemsIn == null){
            throw new IllegalArgumentException("null argument entered");
        }
        //Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Philadelphia") );
        //dateTime=String.valueOf(cal.get(Calendar.MONTH))+String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
        //+String.valueOf(cal.get(Calendar.YEAR));
       
        buyer=buyerIn;
        itemsRented = new ArrayList <Item>();
        StringBuilder itemsRentedS = new StringBuilder();
        for(int i = 0; i <itemsIn.size();i++){
            itemsRented.add(itemsIn.get(i));
            orderAmount += itemsIn.get(i).getPrice();
            //For the description
            if(itemsIn.size() == i+1){
                itemsRentedS = itemsRentedS.append(itemsIn.get(i).getName());
            }else{
                itemsRentedS = itemsRentedS.append(itemsIn.get(i).getName() + ", ");
            }
        }
       
        
        seller = sellerIn;
        description="\nTransaction\n\nSeller: "+seller.getName()+"\nCustomer: "+buyer.getCustomerName()
        +"\nRental Item: "+ itemsRentedS + "\nTotal: $"+orderAmount;
        
    }
    /*
    public Transaction(Item itemIn, Room roomIn, Customer buyerIn, Employee sellerIn) throws IllegalArgumentException{
        if(itemIn==null || buyerIn==null||roomIn==null||sellerIn==null){
            throw new IllegalArgumentException("invalid null argument");
        }
        //Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Philadelphia") );
        //dateTime=String.valueOf(cal.get(Calendar.MONTH))+String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
        //+String.valueOf(cal.get(Calendar.YEAR));
        itemRented=itemIn;
        buyer=buyerIn;
        orderAmount=itemIn.getPrice();
        seller = sellerIn;
        roomRented = roomIn;
        description="\nTransaction\n\nSeller: "+seller.getName()+"\nCustomer: "+buyer.getCustomerName()
        +"\nRoom Number: "+roomRented.getRoomNumber()+"\nRental Item: "+itemRented.getName()+"\nTotal: $"+orderAmount;
        
    }*/

    public Transaction(Customer buyerIn, Employee sellerIn, Room roomIn) throws IllegalArgumentException{
        if(buyerIn==null||roomIn==null||sellerIn==null){
            throw new IllegalArgumentException("invalid null argument");
        }
        //Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Philadelphia") );
        //dateTime=String.valueOf(cal.get(Calendar.MONTH))+String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
        //+String.valueOf(cal.get(Calendar.YEAR));
        
        buyer=buyerIn;
        
        orderAmount=roomIn.getRate();
        seller = sellerIn;
        roomRented = roomIn;
        description="\nTransaction\n\nSeller: "+seller.getName()+"\nCustomer: "+buyer.getCustomerName()
        +"\nRoom Number: "+roomRented.getRoomNumber()+"\nTotal: $"+orderAmount;
        
    }

    public Item getItemRented(){
        return itemRented;
    }
    public ArrayList<Item> getItemsRented(){
        return itemsRented;
    }
    public double getOrderAmount(){
        return orderAmount;
    }
    public Customer getBuyer(){
        return buyer;
    }
    public String getDescription(){
        return description;
    }
    public Room getRoomRented(){
        return roomRented;
    }
    public Employee getSeller(){
        return seller;
    }

}
