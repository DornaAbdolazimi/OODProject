import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Hana on 5/7/2019.
 */
public class CustomerSalesman {
    public static String db_customer_salesman = "customer_salesman";
    public static String db_customer= "customer";
    public static String db_salesman = "salesman";
    public static String db_id = "id";
    public static String db_is_customer = "is_customer";
    public static String db_email = "email";
    public static String db_phone_number = "phone_number";
    public static String db_first_name= "first_name";
    public static String db_last_name= "last_name";
    public static String db_official_name= "official_name";
    public static String db_score= "score";
    public static String db_parent= "parent";







    private int id = 0;
    private boolean isCustomer = false;
    private String email = "";
    private String phoneNumber = "";
    private int score = 0;
    private String firstName = "";
    private String lastName = "";
    private String officialName = "";
    private ArrayList<Order> orders = new ArrayList<Order>();

    public CustomerSalesman(int id){
        this.id = id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public void readDataFromDB(Connection myCon){
        try  {
            Statement myState = myCon.createStatement();
            boolean temp = false;
            ResultSet myRes = myState.executeQuery("select * from customer_salesman");
            while(myRes.next()) {
               if(myRes.getInt(db_id)==this.id){
                    temp = true;
                    this.isCustomer=myRes.getBoolean(db_is_customer);
                    this.email = myRes.getString(db_email);
                    this.phoneNumber = myRes.getString(db_phone_number);
                }
            }
            if(temp==false){
                System.out.printf("No customer with this ID.");
                return;
            }
            if(isCustomer){
                myRes = myState.executeQuery("select * from customer");
                while(myRes.next()) {
                    if(myRes.getInt(db_parent)==this.id){
                        this.firstName = myRes.getString(db_first_name);
                        this.lastName = myRes.getString(db_last_name);
                        this.score = myRes.getInt(db_score);

                    }
                }

            }
            else{
                myRes = myState.executeQuery("select * from salesman");
                while(myRes.next()) {
                    if(myRes.getInt(db_parent)==this.id){
                        this.officialName = myRes.getString(db_official_name);

                    }
                }

            }
        }
        catch(Exception e){	System.out.println("fail");}


    }

    public void insertDataIntoDB(Connection myCon){
        try  {
            String sql = "insert into "+ db_customer_salesman+ "("
                    + db_id + ","+ db_email + ","+ db_phone_number +","+ db_is_customer
                    + ") VALUES(?,?,?,?)";

            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setString(2, email);
            pstmt.setString(3, phoneNumber);
            pstmt.setBoolean(4, isCustomer);
            pstmt.executeUpdate();
            if(isCustomer){
                //TODO
            }
            else{
                //TODO
            }
        }
        catch(Exception e){	System.out.println("fail");}


    }

     public static int getNewID (Connection myCon){
            try{
                Statement myState = myCon.createStatement();
                ResultSet myRes = myState.executeQuery("select * from customer_salesman");
                int last_id = 0;
                while(myRes.next()) {
                    last_id = myRes.getInt("id");
                }
                return last_id+1;

            }catch (Exception e){

            }
            return -1;
     }




    public ArrayList<Order> getOrders() {
        return orders;
    }


}
