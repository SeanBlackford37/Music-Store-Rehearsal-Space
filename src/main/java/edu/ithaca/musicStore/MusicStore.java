package edu.ithaca.musicStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.EnumMap;
import java.util.Iterator;



public class MusicStore {
    private String storeName;
    private List<Item> inventoryList;
    private List<Item> rentedList;
    private List<Employee> employeeList;
    private List<RepairTech> repairTechList;
    private List<Admin> adminList;
    private List<Room> roomList;
    private double storeBalance;
    private EnumMap<RepairBusinessDayCategory,Double> repairPricings;
    private List<Equipment> equipmentForRepairsList;


    public MusicStore(String name){
        storeName = name;
        inventoryList = new ArrayList<>();
        rentedList = new ArrayList<>();
        employeeList = new ArrayList<>();
        repairTechList = new ArrayList<>();
        adminList= new ArrayList<>();
        roomList = new ArrayList<>();
        equipmentForRepairsList = new ArrayList<>();
        storeBalance = 0;
        repairPricings = new EnumMap<RepairBusinessDayCategory,Double>(RepairBusinessDayCategory.class);
        repairPricings.put(RepairBusinessDayCategory.ONETOTHREE, 40.00);
        repairPricings.put(RepairBusinessDayCategory.THREETOFIVE, 60.00);
        repairPricings.put(RepairBusinessDayCategory.FIVETOSEVEN, 80.00);
        repairPricings.put(RepairBusinessDayCategory.SEVENPLUS, 100.00);
    }


    public MusicStore(String name, double balance) throws IllegalArgumentException{
        if(!isAmountValid(balance)){
            throw new IllegalArgumentException("invalid argument");
        }
        else{
            storeName = name;
            inventoryList = new ArrayList<>();
            rentedList = new ArrayList<>();
            employeeList = new ArrayList<>();
            repairTechList = new ArrayList<>();
            adminList= new ArrayList<>();
            roomList = new ArrayList<>();
            equipmentForRepairsList = new ArrayList<>();
            storeBalance =  balance;
        }
    }

    public void addToRepairTechList(RepairTech techToAdd){
        repairTechList.add(techToAdd);
    }
    public List<Item> getRentedList(){
        return rentedList;
    }
    public List<RepairTech> getRepairTechList(){
        return repairTechList;
    }

    public void removeRepairTech(int id){
        int found = 0;
        for (int i = 0;i < repairTechList.size(); i++){
            if (repairTechList.get(i).getID() == id){
                repairTechList.remove(i);
                found++;
                break;
            }
        }
        if (found == 0){
            throw new IllegalArgumentException("Repair Tech does not exist");
        }
    }

    public int findRepairTech(int id){
        for (int i = 0;i < repairTechList.size(); i++){
            if (repairTechList.get(i).getID() == id){
                return i;
            }
        }
        return -1;
    }

    public int findRepairTech(String name){
        for (int i = 0;i < repairTechList.size(); i++){
            if (repairTechList.get(i).getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }

    public RepairTech getRepairTech(int id){
        int index = findRepairTech(id);
        if (index != -1){
            return repairTechList.get(index);
        }
        else{
            throw new IllegalArgumentException("Repair Tech does not exist");
        }
    }

    public RepairTech getRepairTech(String name){
        int index = findRepairTech(name);
        if (index != -1){
            return repairTechList.get(index);
        }
        else{
            throw new IllegalArgumentException("Repair Tech does not exist");
        }
    }

    public void addToInventory(Item itemToAdd){
        inventoryList.add(itemToAdd);
    }

    public void removeFromInventory(String itemName, double price) throws IllegalArgumentException{
        int found = 0;
        for (int i = 0; i < inventoryList.size(); i++){
            if (inventoryList.get(i).getName().equalsIgnoreCase(itemName)){
                if(inventoryList.get(i).getPrice() == price){
                    inventoryList.remove(i);
                    found++;
                    break;
                }
            }
        }
        if (found == 0){
            throw new IllegalArgumentException("Item does not exist");
        }
    }

    public void removeFromInventory(String itemName) throws IllegalArgumentException{
        int found = 0;
        for (int i = 0; i < inventoryList.size(); i++){
            if (inventoryList.get(i).getName().equals(itemName)){
                    inventoryList.remove(i);
                    found++;
                    break;
            }
        }
        if (found == 0){
            throw new IllegalArgumentException("Item does not exist");
        }
    }



    public String getStoreName(){
        return storeName;
    }

    public int searchForInventoryItem(String itemName){
        for (int i = 0; i < inventoryList.size(); i++){
            if (inventoryList.get(i).getName().equalsIgnoreCase(itemName)){
                return i;
            }
        }
        return -1;
    }
    
    public Item getInventoryItem(int index){
        return inventoryList.get(index);
    }

    public Item getRentedItem(int index){
        return rentedList.get(index);
    }

    public int getInventorySize(){
        return inventoryList.size();
    }
    public List<Item> getInventoryList(){
        return inventoryList;
    }


    public void moveToRented(String itemName) throws IllegalArgumentException{
        int itemIndex = searchForInventoryItem(itemName);
        if (itemIndex == -1){
            throw new IllegalArgumentException("Item does not exist");
        }
        else{
            addToRented(inventoryList.get(itemIndex));
            removeFromInventory(itemName, inventoryList.get(itemIndex).getPrice());
        }
        

    }

    public void moveToInventory(String itemName){
        int itemIndex = searchForRentedItem(itemName);
        if (itemIndex == -1){
            throw new IllegalArgumentException("Item does not exist");
        }
        else{
            addToInventory(rentedList.get(itemIndex));
            removeFromRented(itemName, rentedList.get(itemIndex).getPrice());
        }
    }

    public void addToRented(Item itemToAdd){
        rentedList.add(itemToAdd);
    }

    public void removeFromRented(String itemName, double price) throws IllegalArgumentException{
        int found = 0;
        for (int i = 0; i < rentedList.size(); i++){
            if (rentedList.get(i).getName().equalsIgnoreCase(itemName)){
                if(rentedList.get(i).getPrice() == price){
                    rentedList.remove(i);
                    found++;
                    break;
                }
            }
        }
        if (found == 0){
            throw new IllegalArgumentException("Item does not exist");
        }
    }

    public int searchForRentedItem(String itemName){
        for (int i = 0; i < rentedList.size(); i++){
            if (rentedList.get(i).getName().equalsIgnoreCase(itemName)){
                return i;
            }
        }
        return -1;
    }

    public int getRentedSize(){
        return rentedList.size();
    }

    
    public void addToRoomList(Room itemToAdd){
        roomList.add(itemToAdd);
    }
    public List<Room> availableRoomList(){
        List<Room> availableRoomList =  new ArrayList<>();
        for(int i = 0; i < roomList.size(); i++){
            if(roomList.get(i).getIsEmptyRoom()){
                availableRoomList.add(roomList.get(i));
            }
        }
        return availableRoomList;
    }

    public void removeFromRoomList(int roomName) throws IllegalArgumentException{
        int found = 0;
        for (int i = 0; i < roomList.size(); i++){
            if (roomList.get(i).getRoomNumber()==(roomName)){
                    roomList.remove(i);
                    found++;
                    break;
            }
        }
        if (found == 0){
            throw new IllegalArgumentException("Item does not exist");
        }
    }

    public int findRoom(int roomNumber){
        for (int i = 0; i < roomList.size(); i++){
            if (roomList.get(i).getRoomNumber()==(roomNumber)){
                return i;
            }
        }
        return -1;
        //stuff
    }

    public Room getRoom(int index){
        return roomList.get(index);
    }

    public int getRoomListSize(){
        return roomList.size();
    }

    public double getStoreBalance(){
        return storeBalance;
    }


    public void addEmployee(Employee toAdd){
        employeeList.add(toAdd);
    }

    public void removeEmployee(int employeeID){
        boolean contains= false;
        for(int i=0; i<employeeList.size(); i++){
            if(employeeList.get(i).getID()== employeeID){
                contains= true;
                employeeList.remove(i);
            }
        }
        if(contains== false){
            throw new IllegalArgumentException("Employee does not exist");
        }
        }
       
    
    public  List<Room> getRoomList(){
        return roomList;
    }
    public List<Employee> getEmployeeList(){
        return employeeList;
    }

    public Employee getEmployee(int index){
        return employeeList.get(index);
    }

    public void addAdmin(Admin toAdd){
        adminList.add(toAdd);
    }

    public void removeAdmin(int adminID){
        boolean contains= false;
        for(int i=0; i<adminList.size(); i++){
            if(employeeList.get(i).getID()== adminID){
                contains= true;
                adminList.remove(i);
            }
        }
        if(contains== false){
            throw new IllegalArgumentException("Admin does not exist");
        }
    }

    public List<Admin> getAdminList(){
        return adminList;
    }

    public Admin getAdmin(int index){
        return adminList.get(index);
    }

    public int findAdmin(int ID){
        for (int i = 0; i < adminList.size(); i++){
            if (adminList.get(i).getID()==(ID)){
                return i;
            }
        }
        return -1;
    
    }




    public void addToStoreBalance(double profit) throws IllegalArgumentException{
        if(!isAmountValid(profit)){
            throw new IllegalArgumentException("invalid argument");
        }else{
            storeBalance += profit;
        }
    }

    public void subtractFromStoreBalance(double cost) throws IllegalArgumentException{
        if(!isAmountValid(cost) ){
            throw new IllegalArgumentException("invalid argument");
        }
        else if(cost > storeBalance){
            throw new IllegalArgumentException("Not enough money");
        }
        else{
            storeBalance -= cost;
            int num  = (int)(storeBalance * 100);
            storeBalance = ((double)num) /100;
        }
    }

    public static boolean isAmountValid(double balance) {
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
          return true;
        }
       return false;
    }

     public int findEmployee(int ID){
        for (int i = 0; i < employeeList.size(); i++){
            if (employeeList.get(i).getID()==(ID)){
                return i;
            }
        }
        return -1;
    
    }

    public ArrayList<Item> getInventory(){
        return (ArrayList<Item>) inventoryList;
    }


    public double getRepairPricing(RepairBusinessDayCategory rc) {
        return repairPricings.get(rc);
    }

    public RepairBusinessDayCategory getRepairBusinessCategory(int i) {
        Set<RepairBusinessDayCategory> keys = repairPricings.keySet();
        Iterator<RepairBusinessDayCategory> iter = keys.iterator();
        if(i<0||i>=keys.size()){
            throw new IndexOutOfBoundsException("number entered is too large or negative");
        }
        else{
            int x =0;
            while(iter.hasNext()){
                RepairBusinessDayCategory rc = iter.next();
                if(x==i){
                    return rc;
                }
                x++;
            }
            throw new RuntimeException("Error: no repair business day category was found");
        }
    }

    public void updateRepairPricing(RepairBusinessDayCategory rc,double amount) throws IllegalArgumentException{
        if(isAmountValid(amount)){
            repairPricings.replace(rc, amount);
        }
        else{throw new IllegalArgumentException("invalid amount for a repair pricing.");}
    }

    public void printRepairPricings(){
        Set<Entry<RepairBusinessDayCategory,Double>> entries = repairPricings.entrySet();
        Iterator<Entry<RepairBusinessDayCategory,Double>> eI = entries.iterator();
        int i=1;
        while(eI.hasNext()){
            Entry<RepairBusinessDayCategory,Double> entry = eI.next();
            System.out.println("("+i+") "+entry.getKey().name()+"\t"+entry.getValue());
            i++;
        }
        
    }

    public void addEquipment(Equipment e){
        equipmentForRepairsList.add(e);
    }
    public void removeEquipment(int index) throws IllegalArgumentException{
        try{
        equipmentForRepairsList.remove(index);
        }
        catch(IndexOutOfBoundsException e){
            throw new IllegalArgumentException("invalid index");
        }
    }
    public Equipment getEquipment(int index)throws IllegalArgumentException{
        try{
        return equipmentForRepairsList.get(index);
        }
        catch(IndexOutOfBoundsException e){
            throw new IllegalArgumentException("invalid index");
        }
    }
    public int findEquipment(String name){
        for(int i=0;i<equipmentForRepairsList.size();i++){
            if(equipmentForRepairsList.get(i).getEquipmentName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }

    public int getEquipmentListSize(){
        return equipmentForRepairsList.size();
    }

}
