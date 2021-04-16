package edu.ithaca.musicStore;
import java.util.HashMap;
import java.util.ArrayList;

public class Admin extends Employee {
    private HashMap<String,Double>repairPricing;

    public Admin(int employeeID, String name,MusicStore store) {
        super(employeeID, name,store);
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
        int length =repairTimeCategory.length();
        if(length<2){
            throw new IllegalArgumentException("invalid repair time range");
        }
        else if(length ==2 && !repairTimeCategory.equals("7+")){
            throw new IllegalArgumentException("invalid repair time range");
        }
        else if(length>3&&(length!=17||length!=16)){
            throw new IllegalArgumentException("invalid repair time range");
        }
        else if(length==16&&!repairTimeCategory.substring(2,15).equals(" business days")){
            throw new IllegalArgumentException("invalid repair time range");
        }
        else if(length==17&&!repairTimeCategory.substring(3,15).equals(" business days")){
            throw new IllegalArgumentException("invalid repair time range");
        }
        else if(length == 3)
    }
    public void updateRepairPricing(String repairTimeCategory, double amount) throws IllegalArgumentException{
        System.out.println("New pricing for "+repairTimeCategory+" is "+repairPricing.get(repairTimeCategory));
    }


    
}
