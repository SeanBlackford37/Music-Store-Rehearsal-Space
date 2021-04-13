package edu.ithaca.musicStore;

import java.util.ArrayList;

public class Admin extends Employee {

    public Admin(int employeeID, String name) {
        super(employeeID, name);
    }
    public Admin(int employeeID, String name, double payAmt) {
        super(employeeID, name, payAmt);
    }

    public Admin(int employeeID, String name, double payAmt, MusicStore worksAt){
        super(employeeID, name, payAmt, worksAt);
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
            Employee toPay= worksAt.getEmployee(worksAt.findEmployee(employeeID));
            double salary= toPay.getHoursWorked()*toPay.getPayAmt();
            toPay.getPaid(salary); 
        }

        //use to raise or lower employee pay
        public void changeEmployeePay(int employeeID, double newPayAmt){
            if(MusicStore.isAmountValid(newPayAmt)== false){
                throw new IllegalArgumentException("Enter a valid amount");
            }
            Employee toPay= worksAt.getEmployee(worksAt.findEmployee(employeeID));
            toPay.setPayAmt(newPayAmt);
        }

    
}
