package edu.ithaca.musicStore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void constructorTest(){
        

        //exception is thrown when music store is null
        assertThrows(NullPointerException.class,()->new Customer(null, "Bob"));

        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");

        //customer is created when music store is not null
        assertNotEquals(null, c);

        //customer has empty t list
        assertEquals(0,c.getTransactionHistorySize());

    }

    @Test
    void rentItemTest(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Item i =new Item("guitar",45, "n/a");
        ms.addToInventory(i);
        Employee e = new Employee(10101,"Todd");
        //invalid employee
        assertThrows(IllegalArgumentException.class,()->c.rentItem("guitar",null));
        //Item is in inventory and price is correct
        assertEquals(45, c.rentItem("guitar",e));
        assertEquals(1,c.getTransactionHistorySize());
        assertEquals("Bob",c.findTransaction("guitar").getItemRented().getRenterName());

        
        assertEquals(i, c.findTransaction("guitar").getItemRented());
        assertEquals(0,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());

        ms.addToInventory(new Item("djembe",150,"n/a"));
        //Item isn't in inventory
        //no changes made to transaction list
        assertThrows(IllegalArgumentException.class, ()->c.rentItem("coffee",e));
        assertEquals(1,c.getTransactionHistorySize());
        assertEquals(1,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());


        //Item is out of stock/ already being rented by you
        //no changes made to transaction list
        assertThrows(IllegalArgumentException.class, ()->c.rentItem("guitar",e));
        assertEquals(1,c.getTransactionHistorySize());
        assertEquals(1,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());

        //Item is out of stock/ already being rented by another customer
        //no changes made to either transaction list
        Customer d = new Customer(ms, "Bob");
        assertThrows(IllegalArgumentException.class, ()->d.rentItem("guitar",e));
        assertEquals(0,d.getTransactionHistorySize());
        assertEquals(1,c.getTransactionHistorySize());
        assertEquals(1,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());



    }
    @Test 
    void returnItemTest() {
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Employee e = new Employee(10101,"Todd");
        ms.addToInventory(new Item("guitar",45, "n/a"));
        //no items rented, item in inventory
        assertThrows(IllegalArgumentException.class, ()->c.returnItem("guitar"));
        //no items rented, item not in inventory
        assertThrows(IllegalArgumentException.class, ()->c.returnItem("djembe"));

        c.rentItem("guitar",e);

        // item rented, not in inventory
        assertThrows(IllegalArgumentException.class, ()->c.returnItem("djembe"));

        assertEquals(0,ms.getInventorySize());
        assertEquals(1,ms.getRentedSize());
        assertEquals(1,c.getRentedItemsSize());

        Item i = ms.getRentedItem(0);
        assertEquals(i,c.returnItem("guitar"));

        assertEquals(1,ms.getInventorySize());
        assertEquals(0,ms.getRentedSize());
        assertEquals(0,c.getRentedItemsSize());
        assertEquals("n/a",i.getRenterName());
    }
    @Test
    void findTransactionTest() {
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Item i = new Item("guitar",45, "n/a");
        Employee e = new Employee(10101,"Todd");
        ms.addToInventory(i);
        
        //find transaction using index
        //index out of range, empty list
        assertThrows(IndexOutOfBoundsException.class, ()->c.findTransaction(0));
        assertThrows(IndexOutOfBoundsException.class, ()->c.findTransaction(1));
        assertThrows(IndexOutOfBoundsException.class, ()->c.findTransaction(-1));
        
        c.rentItem("guitar",e);
        //index out of range, non-empty list
        assertThrows(IndexOutOfBoundsException.class, ()->c.findTransaction(1));
        //item is e
        assertEquals(ms.getRentedItem(0),c.findTransaction(0).getItemRented());
        c.cancelItemRental("guitar");

        //findTransaction using itemName
        //empty list, item not in inventory
        assertThrows(IllegalArgumentException.class, ()->c.findTransaction("djembe"));
        //empty list, item in inventory
        assertThrows(IllegalArgumentException.class,()->c.findTransaction("guitar"));

        c.rentItem("guitar",e);
        //non empty list, item never in inventory
        assertThrows(IllegalArgumentException.class, ()->c.findTransaction("djembe"));
        //transaction in list
        assertEquals(i,c.findTransaction("guitar").getItemRented());

    }

    @Test
    void cancelRentalTest() {
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Employee e = new Employee(10101,"Todd");
        ms.addToInventory(new Item("guitar",45, "n/a"));
        Room r = new Room(1);
        
        //no transactions recorded for customer
        assertThrows(IllegalArgumentException.class, ()->c.cancelItemRental("guitar"));
        
        c.rentItem("guitar",e);

        //no transaction for item listed
        assertThrows(IllegalArgumentException.class, ()->c.cancelItemRental("coffee"));
        
        Transaction t = c.findTransaction(0);

        //transaction exists for item
        assertEquals(t,c.cancelItemRental("guitar"));
        assertEquals(0,c.getTransactionHistorySize());

        //TH includes room transaction
        c.rentRoom(1,e);
        c.rentItem("guitar", e);

        Transaction t2 = c.findTransaction(1);
        assertEquals(t2,c.cancelItemRental("guitar"));
        assertEquals(0,c.getTransactionHistorySize());


    }

    @Test
    void rentRoomTest(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Employee e = new Employee(10101,"Todd");
        Room room =new Room(1);
        ms.addToRoomList(room);
        int num = room.getRoomNumber();
        //renting an unprepared room or unavailable rooms
        room.setHasEquipment(true);
        assertThrows(IllegalArgumentException.class,()->c.rentRoom(num,e));
        room.setIsEmptyRoom(false);
        assertThrows(IllegalArgumentException.class,()->c.rentRoom(num,e));
        room.setHasEquipment(false);
        assertThrows(IllegalArgumentException.class,()->c.rentRoom(num,e));

        //room is rentable again
        room.setIsEmptyRoom(true);

        assertEquals(50,c.rentRoom(num,e));
        assertEquals("Bob",room.getRenterName());
        Transaction t = c.findTransaction(0);
        assertEquals(room,t.getRoomRented());
        assertFalse(room.getIsEmptyRoom());
        assertEquals(1,c.getTransactionHistorySize());
        assertEquals(t,c.findTransaction(0));

        //customer rents the same room twice
        assertThrows(IllegalArgumentException.class, ()->c.rentRoom(1, e));
        //another customer tries to rent the same room
        Customer d = new Customer(ms,"Fran");
        assertThrows(IllegalArgumentException.class,()->d.rentRoom(1,e));
        

    }
    @Test
    void returnRoom(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Employee e = new Employee(10101,"Todd");
        Room room =new Room(1);

        //room not included in store
        assertThrows(IllegalArgumentException.class,()->c.returnRoom(1));

        ms.addToRoomList(room);

        //room hasn't been rented yet
        assertThrows(IllegalArgumentException.class,()->c.returnRoom(1));

        
        c.rentRoom(1, e);
        assertFalse(room.getIsEmptyRoom());
        assertEquals("Bob",room.getRenterName());

        //customer tries to return a room that's rented by another customer
        Customer d = new Customer(ms,"Fran");
        assertThrows(IllegalArgumentException.class,()->d.returnRoom(1));
        
        c.returnRoom(1);
        
        assertTrue(room.getIsEmptyRoom());
        assertEquals("n/a",room.getRenterName());

    } 
    @Test
    void cancelRoom(){
        MusicStore ms = new MusicStore("ms");
        Customer c = new Customer(ms, "Bob");
        Employee e = new Employee(10101,"Todd");
        Room room =new Room(1);

         //room not included in store
         assertThrows(IllegalArgumentException.class,()->c.cancelRoom(1));
         assertEquals(0,c.getTransactionHistorySize());

         ms.addToRoomList(room);
        

        //empty transaction history
        assertThrows(IllegalArgumentException.class, ()->c.cancelRoom(1));
        assertEquals(0,c.getTransactionHistorySize());

        Item i =new Item("guitar",45, "n/a");
        ms.addToInventory(i);
        c.rentItem("guitar",e); //+1 transaction

        //TH with only item transaction
        assertThrows(IllegalArgumentException.class, ()->c.cancelRoom(1));
        assertEquals(1,c.getTransactionHistorySize());

        c.rentRoom(1,e); //+1 transaction
        c.returnRoom(1);
        //transaction exists but room was already returned
        assertThrows(IllegalArgumentException.class, ()->c.cancelRoom(1));
        assertEquals(2,c.getTransactionHistorySize());

        //transaction exists alone
        c.cancelItemRental("guitar");
        c.rentRoom(1,e);
        Transaction t = c.findTransaction(1);// 1 trans for returned room, 1 trans for unreturned room
        System.out.println(t.getOrderAmount());
        assertEquals(t,c.cancelRoom(1));
        assertEquals(1,c.getTransactionHistorySize());

    }
}