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


    private int id;
    private String name = "";
    private int currentPrice = 0;
    private int groupId = 1;

    public Item(int id) {
        this.id = id;

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
        catch(Exception e){	System.out.println("fail");}


    }

    public void insertDataIntoDB(Connection myCon){
        try  {
            String sql = "insert into "+ db_item + "("
                    + db_id + ","+ db_name + ","+ db_group_id +","+ db_current_price
                    + ") VALUES(?,?,?,?)";

            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, groupId);
            pstmt.setInt(4, currentPrice);
            pstmt.executeUpdate();
        }
        catch(Exception e){	System.out.println("fail");}


    }

}
