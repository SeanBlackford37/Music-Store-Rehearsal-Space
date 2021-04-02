package edu.ithaca.musicStore;

public class Room {
    private boolean isEmptyRoom;
    private int roomNumber;
    private boolean hasEquipment;
    private String renterName;
    private double rate;
    //If nonone is in the room isEmptyRoom has to be True, and hasEquipment has to be false, renterName has to be empty
    public Room(boolean isEmptyRoom, int roomNumber, boolean hasEquipment, String renterName){
       
        if(roomNumber <= 0 || renterName.length() == 0 && hasEquipment){
            throw new IllegalArgumentException("invalid argument");
        }else if(isEmptyRoom && hasEquipment ){
            throw new IllegalArgumentException("invalid argument");
        }else{
            this.isEmptyRoom = isEmptyRoom;
            this.roomNumber = roomNumber;
            this.hasEquipment = hasEquipment;
            this.renterName = renterName;
            this.rate = 50;
        }
    }

    public Room(int roomNumber){
        if(roomNumber <= 0){
            throw new IllegalArgumentException("invalid argument");
        }else{
            this.hasEquipment=false;
            this.isEmptyRoom=true;
            this.roomNumber = roomNumber;
            this.renterName = "n/a";
            this.rate = 50;
        }
    }

    public boolean getIsEmptyRoom(){
        return isEmptyRoom;
    }
    public int getRoomNumber(){
        return roomNumber;
    }
    public boolean getHasEquipment(){
        return hasEquipment;
    }
    public String getRenterName(){
        return renterName;
    }
    public double getRate(){
        return rate;
    }

    public void setIsEmptyRoom(boolean isEmptyRoom){
        this.isEmptyRoom = isEmptyRoom;
    }
    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }
    public void setHasEquipment(boolean hasEquipment){
        this.hasEquipment = hasEquipment;
    }
    public void setRenterName(String renterName){
        this.renterName = renterName;
    }
}
