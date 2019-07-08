package logic;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

public abstract class ItemDBManager {

    private static Connection  myCon;


    private static String db_id = "id";



    private final static String db_item = "item";
    private final static String db_item_name = "name";
    private final static String db_item_group_id = "group_id";
    private final static String db_item_current_price = "current_price";
    private final static String db_item_quantity = "quantity";



    public static void readItemInfoFromDB ( Item item) {

        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            boolean temp = false;
            ResultSet myRes = myState.executeQuery("select * from "+db_item);
            while (myRes.next()) {

                if (myRes.getInt(db_id) == item.getId() || myRes.getString(db_item_name).equals(item.getName())  ) {

                    temp = true;
                    item.setId(myRes.getInt(db_id));
                    item.setName(myRes.getString(db_item_name));
                    item.setGroupId(myRes.getInt(db_item_group_id));
                    item.setPrice(myRes.getInt(db_item_current_price));
                    item.setQuantity(myRes.getInt(db_item_quantity));

                    ResultSet myRes2 = myState.executeQuery("select * from item_quantity");
                    item.setQuantities(new ArrayList<Quantity>());
                    while (myRes2.next()) {
                        if (myRes2.getInt("item_id") == item.getId()){
                            Quantity quantity = new Quantity(myRes2.getDate("start_date"),
                                    myRes2.getTime("start_time"), myRes2.getInt("item_id"), myRes2.getInt("quantity"));
                            quantity.setEndDate(myRes2.getDate("end_date"));
                            item.getQuantities().add(quantity);

                        }

                    }
                    return;

                }
            }

            System.out.println("finished");

            if (temp == false) {
                System.out.printf("No item with this ID.");
                return;
            }
        } catch (Exception e) {
            System.out.println("fail in reading from Item");
        }

    }

    public static void insertNewItemIntoDB(Item item){
        try  {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            String sql = "insert into "+ db_item + "("
                    + db_id + ","+ db_item_name + ","+ db_item_group_id +","+ db_item_current_price + ","+ db_item_quantity
                    + ") VALUES(?,?,?,?,?)";


            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);


            pstmt.setInt(1, item.getId());
            pstmt.setString(2, item.getName());
            pstmt.setInt(3, item.getGroupId());
            pstmt.setInt(4, item.getPrice());
            pstmt.setInt(5, item.getQuantity());

            pstmt.executeUpdate();


            sql = "insert into "+ "item_quantity"+ "("
                    + "item_id, start_date, start_time, quantity"
                    + ") VALUES(?,?,?,?)";


            pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);


            pstmt.setInt(1, item.getId());
            pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            pstmt.setTime(3, new Time(System.currentTimeMillis()));
            pstmt.setInt(4, item.getQuantity());

            pstmt.executeUpdate();
        }
        catch(Exception e){	System.out.println("fail in writing in Item");}


    }

    public static int getNewItemID (){
        try{
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            ResultSet myRes = myState.executeQuery("select * from "+db_item);
            int lastId = 0;
            while(myRes.next()) {
                lastId = myRes.getInt("id");
            }
            return lastId+1;

        }catch (Exception e){

        }
        return -1;
    }

    public static Item getItemWithName (String name){
        Item item = new Item(name);
        readItemInfoFromDB(item);
        return  item;

    }



    public static void updateItemQuantityInDB (Item item){

        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");

            String sql = "update " + db_item + " set quantity = ? where id = ?";


            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, item.getQuantity());
            pstmt.setInt(2, item.getId());

            pstmt.executeUpdate();
        } catch(Exception e){	System.out.println("fail updating item 1");}

        try{

            String sql3 = "update item_quantity" + " set end_date = ? where  item_id = ? and end_date is null ";

            PreparedStatement pstmt = myCon.prepareStatement(sql3,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setDate(1, new Date(System.currentTimeMillis()));
            pstmt.setInt(2, item.getId());

            System.out.println(1);

            pstmt.executeUpdate();
            System.out.println(2);


        } catch (Exception e){
            System.out.println("fail updating item 3");}
        try {

            String sql = "insert into "+ "item_quantity"+ "("
                    + "item_id, start_date, start_time, quantity"
                    + ") VALUES(?,?,?,? )";


            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);


            pstmt.setInt(1, item.getId());
            pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            pstmt.setTime(3, new Time(System.currentTimeMillis()));
            pstmt.setInt(4, item.getQuantity());

            pstmt.executeUpdate();


        } catch(Exception e){	System.out.println("fail updating item 2");}



    }

    public static   ArrayList<Item> getItemList (){
        ArrayList<Item> tempArr = new ArrayList<Item>();


        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            boolean temp = false;
            ResultSet myRes = myState.executeQuery("select * from "+db_item);
            while (myRes.next()) {
                tempArr.add(new Item(myRes.getInt(db_id), myRes.getString(db_item_name),
                     myRes.getInt(db_item_current_price),  myRes.getInt(db_item_quantity) ));

            }

        } catch (Exception e) {
        }

        return tempArr;

    }










}
