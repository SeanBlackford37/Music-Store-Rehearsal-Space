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

    public Transaction(Item itemIn, Customer buyerIn, Employee sellerIn) throws IllegalArgumentException{
        if(itemIn==null || buyerIn==null){
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

}
