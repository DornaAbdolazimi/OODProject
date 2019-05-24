import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
        try{
            Statement myState = myCon.createStatement();
            ResultSet myRes = myState.executeQuery("select * from cashier");
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
            ResultSet myRes = myState.executeQuery("select * from cashier");
            while(myRes.next()) {
                if(myRes.getInt(db_id)==this.id){
                    temp = true;
                    this.firstName = myRes.getString(db_first_name);
                    this.lastName = myRes.getString(db_last_name);
                    this.isManager = myRes.getBoolean(db_is_manager);
                    this.username = myRes.getString(db_username);
                    this.password = myRes.getString(db_pass);

                }
            }
            if(temp==false){
                System.out.printf("No cashier with this ID.");
                return;
            }
        }
        catch(Exception e){	System.out.println("fail");}


    }

    public void insertDataIntoDB(Connection myCon){
        try  {
            String sql = "insert into "+ db_cashier + "("
                    + db_id + ","+ db_first_name + ","+ db_last_name +","+ db_is_manager +","+db_username+","+db_pass
                    + ") VALUES(?,?,?,?,?,?)";

            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setBoolean(4, isManager);
            pstmt.setString(5, username);
            pstmt.setString(6, password);


            pstmt.executeUpdate();
        }
        catch(Exception e){	System.out.println("fail");}


    }

}
