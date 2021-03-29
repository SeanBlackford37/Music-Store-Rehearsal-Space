package edu.ithaca.dragon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void constructorTest() throws NullPointerException{
        MusicStore ms = null;

        //exception is thrown when music store is null
        assertThrows(NullPointerException.class,()->new Customer(ms));

        ms = new MusicStore();
        Customer c = new Customer(ms);

        //customer is created when music store is not null
        assertNotEquals(null, c);

        //customer has t list 
        assertNotEquals(null,c.getTransactionList());

        //customer has empty t list
        assertEquals(0,c.getTransactionList.size());

    }

    @Test
    void rentItemTest() throws IllegalArgumentException{
        MusicStore ms = new MusicStore();
        Customer c = new Customer(ms);
        ms.addItem("Gibson Acoustic Guitar",45);
        //Item is in inventory
        c.rentItem("Gibson Acoustic Guitar");
        assertEquals(1,c.getTransactionList().size());
        //Ask about below
        assertEquals("Gibson Acoustic Guitar", c.findTransaction("Transaction ID/Date?"));

        //Item isn't in inventory
        //no changes made to transaction list
        assertThrows(IllegalArgumentException.class, ()->c.rentItem("Coffee"));
        assertEquals(1,c.getTransactionList().size());


        //Item is out of stock/ already being rented by you
        //no changes made to transaction list
        assertThrows(IllegalArgumentException.class, ()->c.rentItem("Gibson Acoustic Guitar"));
        assertEquals(1,c.getTransactionList().size());

        //Item is out of stock/ already being rented by another customer
        //no changes made to either transaction list
        Customer d = new Customer(ms);
        assertThrows(IllegalArgumentException.class, ()->d.rentItem("Gibson Acoustic Guitar"));
        assertEquals(0,d.getTransactionList().size());
        assertEquals(1,c.getTransactionList().size());



    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));
    }

    @Test
    void otherTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}