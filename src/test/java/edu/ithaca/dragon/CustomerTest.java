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
    void getBalanceTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
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