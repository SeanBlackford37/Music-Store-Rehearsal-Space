package edu.ithaca.musicStore;

public class Room {
    private boolean isEmptyRoom;
    private int roomNumber;
    private boolean hasEquipment;
    private String renterName;
    //If nonone is in the room isEmptyRoom has to be True, and hasEquipment has to be false, renterName has to be empty
    public Room(boolean isEmptyRoom, int roomNumber, boolean hasEquipment, String renterName){

    }

    public boolean getIsEmptyRoom(){
        return false;
    }
    public int getRoomNumber(){
        return -1;
    }
    public boolean getHasEquipment(){
        return false;
    }
    public String getRenterName(){
        return null;
    }

    public void setIsEmptyRoom(boolean isEmptyRoom){
        //NA
    }
    public void setRoomNumber(int roomNumber){
        //NA
    }
    public void setHasEquipment(boolean hasEquipment){
        //NA
    }
    public void setRenterName(String renterName){
        //NA
    }
}
