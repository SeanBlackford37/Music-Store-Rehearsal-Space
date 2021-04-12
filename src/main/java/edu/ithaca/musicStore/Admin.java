package edu.ithaca.musicStore;

import java.util.ArrayList;

public class Admin extends Employee {

    public Admin(int employeeID, String name) {
        super(employeeID, name);
    }
    public Admin(int employeeID, String name, double payAmt) {
        super(employeeID, name, payAmt);
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
        public void payEmployee(int employeeID){

        }

        //use to raise or lower employee pay
        public void changeEmployeePay(int employeeID, double newPayAmt){

        }

    
}
