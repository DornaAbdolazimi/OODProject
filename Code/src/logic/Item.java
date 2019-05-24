package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Hana on 5/7/2019.
 */
public class Item {
    public static String db_item = "item";
    public static String db_id = "id";
    public static String db_name= "name";
    public static String db_group_id= "group_id";
    public static String db_current_price= "current_price";
    public static String db_quantity= "quantity";



    private int id;
    private String name = "";
    private int currentPrice = 0;
    private int groupId = 1;
    private  int quantity = 0;

    public Item(int id) {
        this.id = id;

    }

    public Item(int id, String name, int currentPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return currentPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.currentPrice = price;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public static int getNewID (Connection myCon){
        try{
            Statement myState = myCon.createStatement();
            ResultSet myRes = myState.executeQuery("select * from item");
            int lastId = 0;
            while(myRes.next()) {
                lastId = myRes.getInt("id");
            }
            return lastId+1;

        }catch (Exception e){

        }
        return -1;
    }

    public void readDataFromDB(Connection myCon){
        try  {
            Statement myState = myCon.createStatement();
            boolean temp = false;
            ResultSet myRes = myState.executeQuery("select * from item");
            while(myRes.next()) {
                if(myRes.getInt(db_id)==this.id){
                    temp = true;
                    this.name = myRes.getString(db_name);
                    this.groupId = myRes.getInt(db_group_id);
                    this.currentPrice = myRes.getInt(db_current_price);
                }
            }
            if(temp==false){
                System.out.printf("No item with this ID.");
                return;
            }
        }
        catch(Exception e){	System.out.println("fail in reading from Item");}


    }

    public void insertDataIntoDB(Connection myCon){
        try  {
            String sql = "insert into "+ db_item + "("
                    + db_id + ","+ db_name + ","+ db_group_id +","+ db_current_price + ","+ db_quantity
                    + ") VALUES(?,?,?,?,?)";

            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            System.out.println(id);
            System.out.println(name);
            System.out.println(groupId);
            System.out.println(currentPrice);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, groupId);
            pstmt.setInt(4, currentPrice);
            pstmt.setInt(5, quantity);

            pstmt.executeUpdate();
        }
        catch(Exception e){	System.out.println("fail in writing in Item");}


    }

}
