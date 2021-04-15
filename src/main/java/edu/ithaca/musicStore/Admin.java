package edu.ithaca.musicStore;
import java.util.HashMap;
import java.util.ArrayList;

public class Admin extends Employee {
    private HashMap<String,Double>repairPricing;

    public Admin(int employeeID, String name,MusicStore store) {
        super(employeeID, name,store);
        repairPricing = new HashMap<>();
        repairPricing.put("1-3 business days", 40.00);
        repairPricing.put("3-5 business days", 60.00);
        repairPricing.put("5-7 business days", 80.00);
        repairPricing.put("7+ business days", 100.00);
    }
    public Admin(int employeeID, String name, double payAmt, MusicStore store) {
        super(employeeID, name, payAmt,store);
    }

    public ArrayList<Room> cancelSpaceRental(int roomNumber, ArrayList<Room> rentedRooms) throws IllegalArgumentException{
        int count = 0;
        for(int i = 0; i < rentedRooms.size(); i++){
            if(roomNumber == rentedRooms.get(i).getRoomNumber()){
                rentedRooms.get(i).setIsEmptyRoom(true);
                rentedRooms.get(i).setHasEquipment(false);
                rentedRooms.get(i).setRenterName("");
                count++;
            }
        }
        if(count == 0){
            throw new IllegalArgumentException("Room does not exist");
        }
        return rentedRooms;
    }
    public ArrayList<Room> addSpaceToRental(int roomNumber, ArrayList<Room> rentedRooms) throws IllegalArgumentException{
        int count = 0;
        for(int i = 0; i < rentedRooms.size(); i++){
            if(roomNumber == rentedRooms.get(i).getRoomNumber()){
                rentedRooms.get(i).setIsEmptyRoom(true);
                rentedRooms.get(i).setHasEquipment(false);
                rentedRooms.get(i).setRenterName("");
                count++;
            }
        }
        if(count > 0){
            throw new IllegalArgumentException("Room can not have the same number");
        }

        rentedRooms.add(new Room(true, roomNumber, false, ""));
        return rentedRooms;
    }

    public double getRepairPricing(String repairTimeCategory) throws IllegalArgumentException{
        return -1;
    }
    public void updateRepairPricing(String repairTimeCategory, double amount) throws IllegalArgumentException{
        System.out.println("New pricing for "+repairTimeCategory+" is "+repairPricing.get(repairTimeCategory));
    }


    
}
