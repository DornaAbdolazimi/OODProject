import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Hana on 5/7/2019.
 */
public class Order {
    public static String db_order = "orders";
    public static String db_id = "id";
    public static String db_order_id = "order_id";
    public static String db_item_id = "item_id";
    public static String db_quantity = "quantity";
    public static String db_price = "price";
    public static String db_is_selling = "is_selling";
    public static String db_other_side = "other_side";
    public static String db_date = "date";

    private int id;
    private Date date;
    private boolean isSelling; //0: buying, 1: Selling
    private ArrayList<OrderItems> orderItems= new ArrayList<OrderItems>();
    private boolean done;   //state
    private int otherSide;

    public Order(int id){
        this.id = id;
    }




    public void setOtherSide(int otherSide) {
        this.otherSide = otherSide;
    }

    public int getOtherSide() {
        return otherSide;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSelling() {
        return isSelling;
    }

    public void setSelling(boolean selling) {
        this.isSelling = selling;
    }

    public ArrayList<OrderItems> getOrderItems() {
        return orderItems;
    }

    public boolean isDone() {
        return done;
    }

    public void settle() {
        this.done = true;
    }

    public double price() {
        double res = 0;
        for (int i = 0; i < orderItems.size(); i++) {
            res += orderItems.get(i).price();
        }
        return res;
    }

    public static int getNewID (Connection myCon){
        try{
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

    public void readDataFromDB(Connection myCon){
        try  {
            Statement myState = myCon.createStatement();
            boolean temp = false;
            ResultSet myRes = myState.executeQuery("select * from orders");
            while(myRes.next()) {
                if(myRes.getInt(db_id)==this.id){
                    temp = true;
                    this.isSelling = myRes.getBoolean(db_is_selling);
                    this.otherSide = myRes.getInt(db_other_side);
                    this.id= myRes.getInt(db_id);
                }
            }
            if(temp==false){
                System.out.printf("No Order with this ID.");
                return;
            }
            temp = false;
            myRes = myState.executeQuery("select * from order_item");
            while(myRes.next()) {
                if(myRes.getInt(db_order_id)==this.id){
                    temp = true;
                    int itemId = myRes.getInt(db_item_id);
                    Item item = new Item(itemId);
                    item.readDataFromDB(myCon);
                    OrderItems orderItem = new OrderItems(item,
                            myRes.getInt(db_quantity), myRes.getInt(db_price));
                    orderItems.add(orderItem);

                }
            }

        }
        catch(Exception e){	System.out.println("fail");}


    }

    public void insertDataIntoDB(Connection myCon){
        try  {
            String sql = "insert into "+ db_order + "("
                    + db_id + ","+ db_is_selling + ","+ db_other_side + ","+ db_date +") VALUES(?,?,?,?)";

            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setBoolean(2, isSelling);
            pstmt.setInt(3, otherSide);
            pstmt.setDate(4, Date.valueOf(LocalDate.EPOCH)); //TODO
            pstmt.executeUpdate();
        }
        catch(Exception e){	System.out.println("fail!");}


    }



}
