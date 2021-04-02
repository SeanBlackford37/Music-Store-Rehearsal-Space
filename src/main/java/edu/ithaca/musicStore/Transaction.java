package edu.ithaca.musicStore;

//import java.util.Calendar;
//import java.util.TimeZone;

public class Transaction {
    //private String dateTime;
    private Item itemRented;
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
        if( buyerIn==null||roomIn==null||sellerIn==null){
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
