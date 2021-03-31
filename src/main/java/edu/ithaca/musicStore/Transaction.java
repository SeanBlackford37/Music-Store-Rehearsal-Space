package edu.ithaca.musicStore;

import java.util.Calendar;
import java.util.TimeZone;

public class Transaction {
    private String dateTime;
    private Item itemRented;
    private Employee seller;
    private Customer buyer;
    private double orderAmount;

    public Transaction(Item itemIn, Employee sellerIn, Customer buyerIn){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Philadelphia") );
        dateTime=String.valueOf(cal.get(Calendar.MONTH))+String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
        +String.valueOf(cal.get(Calendar.YEAR));
        itemRented=itemIn;
        seller=sellerIn;
        buyer=buyerIn;
        orderAmount=0;
    }

}
