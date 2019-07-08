package logic;

import java.sql.*;

public abstract class OrderDBManager {

    private static Connection myCon;


    private static String db_id = "id";

    public static String db_order = "orders";
    public static String db_order_id = "order_id";
    public static String db_item_id = "item_id";
    public static String db_quantity = "quantity";
    public static String db_price = "getPrice";
    public static String db_is_selling = "is_selling";
    public static String db_other_side = "other_side";
    public static String db_date = "date";




    public static void readOrderInfoFromDB(Order order){
        try  {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            boolean temp = false;
            ResultSet myRes = myState.executeQuery("select * from orders");
            while(myRes.next()) {
                if(myRes.getInt(db_id)==order.getId()){
                    temp = true;
                    order.setSelling( myRes.getBoolean(db_is_selling));
                    order.setOtherSide( myRes.getInt(db_other_side));
                }
            }
            if(temp==false){
                System.out.printf("No logic.Order with this ID.");
                return;
            }
            temp = false;
            myRes = myState.executeQuery("select * from order_item");
            while(myRes.next()) {
                if(myRes.getInt(db_order_id)==order.getId()){
                    temp = true;
                    int itemId = myRes.getInt(db_item_id);
                    Item item = new Item(itemId);
                    ItemDBManager.readItemInfoFromDB(item);
                    OrderItem orderItem = new OrderItem(item,
                            myRes.getInt(db_quantity), myRes.getInt(db_price), myRes.getInt(db_id));
                    order.addOrderItems(orderItem);

                }
            }

        }
        catch(Exception e){	System.out.println("fail");}


    }

    public static void insertNewOrderIntoDB(Order order){
        try  {
            String sql = "insert into "+ db_order + "("
                    + db_id + ","+ db_is_selling + ","+
                    db_other_side + ","+ db_date + "," + "final_price, done_price"+
                    ") VALUES(?,?,?,?,?,?)";

            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, order.getId());
            pstmt.setBoolean(2, order.isSelling());
            pstmt.setInt(3, order.getOtherSide());
            pstmt.setDate(4, order.getDate());
            pstmt.setInt(5, order.getTotalPrice());
            pstmt.setInt(6, order.getPaidPrice());

            pstmt.executeUpdate();




            for (int i = 0; i < order.getOrderItems().size(); i++) {
                sql = "insert into "+ "order_item" + "("
                        +  "order_id, item_id, quantity, price"+
                        ") VALUES(?,?,?,?)";

                pstmt = myCon.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                OrderItem orderItem =  order.getOrderItems().get(i);
                pstmt.setInt(1, orderItem.getOrder());
                pstmt.setInt(2, orderItem.getItem().getId());
                pstmt.setInt(3, orderItem.getQuantity());
                pstmt.setInt(4, orderItem.getPrice());

                pstmt.executeUpdate();
            }



        }
        catch(Exception e){	System.out.println("fail!");}


    }


    public static int getNewOrderID (){

        try{
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            ResultSet myRes = myState.executeQuery("select * from orders" );
            int last_id = 0;
            while(myRes.next()) {
                last_id = myRes.getInt("id");
            }
            return last_id+1;

        }catch (Exception e){

        }
        return -1;

    }


}
