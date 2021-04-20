package edu.ithaca.musicStore;
import java.util.HashMap;
import java.util.ArrayList;


public class Admin extends Employee {

    public Admin(int employeeID, String name,MusicStore store) {
        super(employeeID, name,store);
    }
    public Admin(int employeeID, String name, double payAmt, MusicStore store) {
        super(employeeID, name, payAmt,store);
    }

    /*public Admin(int employeeID, String name, double payAmt, MusicStore worksAt){
        super(employeeID, name, payAmt, worksAt);
    }*/

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

    public double getRepairPricing(RepairCategory rc) {
        return store.getRepairPricing(rc);
        
    }
    public void updateRepairPricing(RepairCategory rc, double amount) throws IllegalArgumentException{
        if(MusicStore.isAmountValid(amount)){
            store.updateRepairPricing(rc, amount);
        }
        else{throw new IllegalArgumentException("invalid amount for pricing");}
    }

        public void payEmployee(int employeeID, String employeeType) throws IllegalArgumentException{
            
            if(employeeType.equals("Employee") || employeeType.equals("RepairTech")|| employeeType.equals("Admin")){
                if(employeeType.equals("Employee")){
            Employee toPay= store.getEmployee(store.findEmployee(employeeID));
            double salary= toPay.getHoursWorked()*toPay.getPayAmt();
            toPay.getPaid(salary);
            store.subtractFromStoreBalance(salary); 
                }
                if(employeeType.equals("RepairTech")){
                    RepairTech toPay= store.getRepairTech(employeeID);
                    double salary= toPay.getHoursWorked()*toPay.getPayAmt();
                    toPay.getPaid(salary);
                    store.subtractFromStoreBalance(salary);
                }

                if(employeeType.equals("Admin")){
                    Employee toPay=store.getAdmin(store.findAdmin(employeeID));
                    double salary= toPay.getHoursWorked()*toPay.getPayAmt();
                    toPay.getPaid(salary);
                    store.subtractFromStoreBalance(salary);
                }
            }
            else{
                throw new IllegalArgumentException("Employee ID does not exist");
            }
        }

    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance <=1 && result[1].length() <= 2 && balance >=.01){
          return true;
        }
       return false;
    }
    public void raisePay(Employee employeeIn, double raiseAmt){
        if(isAmountValid(raiseAmt)){
            employeeIn.setPayAmt(employeeIn.getPayAmt() + (employeeIn.getPayAmt() * raiseAmt)); 
        }else{
            throw new IllegalArgumentException("invalid amt to raise to employee");
        }
       
    }


        //use to raise or lower employee pay
        public void changeEmployeePay(int employeeID, double newPayAmt) throws IllegalArgumentException{
            if(MusicStore.isAmountValid(newPayAmt)== false){
                throw new IllegalArgumentException("Enter a valid amount");
            }
            else if(store.findEmployee(employeeID)==-1){
                throw new IllegalArgumentException("Employee ID does not exist");
            }
            else{
            Employee toPay= store.getEmployee(store.findEmployee(employeeID));
            toPay.setPayAmt(newPayAmt);
            }
        }

        public void fireEmployee(int employeeID, String employeeType) throws IllegalArgumentException{
            if(employeeType.equals("Employee") || employeeType.equals("RepairTech")|| employeeType.equals("Admin")){
             
            if(employeeType.equals("Employee")){
                if(store.findEmployee(employeeID)==-1){
                    throw new IllegalArgumentException("Employee ID does not exist");
                }
                store.removeEmployee(employeeID);
            }
            else if(employeeType.equals("RepairTech")){
                if(store.findRepairTech(employeeID)==-1){
                    throw new IllegalArgumentException("Employee ID does not exist");
                }
                store.removeRepairTech(employeeID);
            }

            else if(employeeType.equals("Admin")){
                if(store.findAdmin(employeeID)==-1){
                    throw new IllegalArgumentException("Employee ID does not exist");
                }
                store.removeAdmin(employeeID);
            
            }
        }
        else{
            throw new IllegalArgumentException("Invalid Employee Type");
        }
    }
            
            /*if(store.findEmployee(employeeID)==-1){
                throw new IllegalArgumentException("Employee ID does not exist");
            }
            store.removeEmployee(employeeID);
        }*/

        public void hireEmployees(int employeeID, String name, MusicStore store, String employeeType){
            if(employeeType.equals("Employee")){
                Employee toHire= new Employee(employeeID, name, store);
                store.addEmployee(toHire);
            }
            else if(employeeType.equals("RepairTech")){
                RepairTech toHire= new RepairTech(employeeID, name, store);
                store.addToRepairTechList(toHire);
            }
            else if(employeeType.equals("Admin")){
                Admin toHire= new Admin(employeeID, name, store);
                store.addAdmin(toHire);

            }
        }
    
}
