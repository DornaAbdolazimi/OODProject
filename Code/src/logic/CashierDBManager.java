package logic;

import java.sql.*;

public abstract class CashierDBManager {

    private static Connection myCon;


    private static String db_id = "id";



    private static String db_cashier =  "cashier";
    private static String db_cashier_first_name = "first_name";
    private static String db_cashier_last_name = "last_name";
    private static String db_is_manager = "is_manager";
    private static String db_cashier_username = "username";
    private static String db_cashier_pass = "pass";

    public static void readCashierInfoFramDB ( Cashier cashier){
        try  {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            boolean temp = false;
            ResultSet myRes = myState.executeQuery("select * from cashier");
            while(myRes.next()) {
                if(myRes.getString(db_cashier_username).equals(cashier.getUsername())){
                    temp = true;
                    cashier.setFirstName(myRes.getString(db_cashier_first_name));
                    cashier.setLastName(myRes.getString(db_cashier_last_name));
                    cashier.setManager(myRes.getBoolean(db_is_manager));
                    cashier.setId(myRes.getInt(db_id));
                    cashier.setPassword(db_cashier_pass);

                }
            }
            if(temp==false){
                System.out.printf("No cashier with this ID.");
                return;
            }
        }
        catch(Exception e){	System.out.println("fail");}

    }

    public void insertNewCashierIntoDB(Cashier cashier){
        try  {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            String sql = "insert into "+ db_cashier + "("
                    + db_id + ","+ db_cashier_first_name + ","+ db_cashier_last_name +","+ db_is_manager +
                    ","+db_cashier_username+","+db_cashier_pass
                    + ") VALUES(?,?,?,?,?,?)";

            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, cashier.getId());
            pstmt.setString(2, cashier.getFirstName());
            pstmt.setString(3, cashier.getLastName());
            pstmt.setBoolean(4, cashier.isManager());
            pstmt.setString(5, cashier.getUsername());
            pstmt.setString(6, cashier.getPassword());


            pstmt.executeUpdate();
        }
        catch(Exception e){	System.out.println("fail");}


    }

    public static int getNewCashierID (){
        try{
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
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


}
