package logic;

import java.sql.*;

/**
 * Created by Hana on 5/7/2019.
 */
public class Cashier {
    public static String db_cashier =  "cashier";
    public static String db_first_name= "first_name";
    public static String db_last_name= "last_name";
    public static String db_id = "id";
    public static String db_is_manager = "is_manager";
    public static String db_username= "username";
    public static String db_pass= "pass";




    public Cashier (int id){
        this.id = id;
    }



    private  int id;
    private  boolean isManager;
    private  String lastName = "";
    private  String firstName = "";
    private  String username = "";
    private  String password = "";



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isManager() {
        return isManager;
    }
    public static int getNewID (Connection myCon){
        return  0;
    }



    public void makeItem(String name, float price, int quantity, String group) {
        try {

            Item item = new Item(DBManager.getNewItemID(), name, Math.round(price), quantity);
            item.setGroupId(new Group(group).findGroupIdByName());
            DBManager.insertNewItemIntoDB(item);
        }
        catch (Exception e){

        }


    }



}
