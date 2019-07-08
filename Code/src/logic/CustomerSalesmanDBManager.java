package logic;

import java.sql.*;
import java.util.ArrayList;

public abstract class CustomerSalesmanDBManager {
    private static String db_id = "id";


    private final static String db_customer_salesman = "customer_salesman";
    private final static String db_customer= "customer";
    private final static String db_salesman = "salesman";
    private final static String db_is_customer = "is_customer";
    private final static String db_cs_email = "email";
    private final static String db_cs_phone_number = "phone_number";
    private final static String db_customer_first_name = "first_name";
    private final static String db_customer_last_name = "last_name";
    private final static String db_salesman_official_name = "official_name";
    private final static String db_customer_score = "score";
    private final static String db_cs_parent = "parent";
    private static Connection myCon;


    public static void readCustomerSalesmanInfo ( CustomerSalesman CS){
        try  {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            boolean temp = false;
            ResultSet myRes = myState.executeQuery("select * from "+ db_customer_salesman);
            while(myRes.next()) {
                if(myRes.getInt(db_id)==CS.getId()){
                    temp = true;
                    CS.setCustomer(myRes.getBoolean(db_is_customer));
                    CS.setEmail(myRes.getString(db_cs_email));
                    CS.setPhoneNumber(myRes.getString(db_cs_phone_number));
                }
            }
            if(temp==false){
                System.out.printf("No customer with this ID.");
                return;
            }
            if(CS.isCustomer()){
                myRes = myState.executeQuery("select * from "+ db_customer);
                while(myRes.next()) {
                    if(myRes.getInt(db_cs_parent)==CS.getId()){
                        CS.setFirstName(myRes.getString(db_customer_first_name));
                        CS.setLastName( myRes.getString(db_customer_last_name));
                        CS.setScore(myRes.getInt(db_customer_score));
                    }
                }
            }
            else{
                myRes = myState.executeQuery("select * from "+ db_salesman);
                while(myRes.next()) {
                    if(myRes.getInt(db_cs_parent)==CS.getId()){
                        CS.setOfficialName(myRes.getString(db_salesman_official_name));
                    }
                }

            }
        }
        catch(Exception e){	System.out.println("fail");}
    }

    public static void insertNewCustomerSalesmanIntoDB (CustomerSalesman CS){
        try  {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            String sql = "insert into "+ db_customer_salesman+ "("
                    + db_id + ","+ db_cs_email + ","+ db_cs_phone_number +","+ db_is_customer
                    + ") VALUES(?,?,?,?)";

            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, CS.getId());
            pstmt.setString(2, CS.getEmail());
            pstmt.setString(3, CS.getPhoneNumber());
            pstmt.setBoolean(4, CS.isCustomer());
            pstmt.executeUpdate();
            if(CS.isCustomer()){
                sql = "insert into "+ db_customer+ "("
                        + db_cs_parent + ","+ db_customer_first_name + ","+ db_customer_last_name +","+ db_customer_score
                        + ") VALUES(?,?,?,?)";

                pstmt = myCon.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, CS.getId());
                pstmt.setString(2, CS.getFirstName());
                pstmt.setString(3, CS.getLastName());
                pstmt.setInt(4, CS.getScore());
                pstmt.executeUpdate();
            }
            else{
                sql = "insert into "+ db_salesman+ "("
                        + db_cs_parent + ","+ db_salesman_official_name
                        + ") VALUES(?,?)";

                pstmt = myCon.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, CS.getId());
                pstmt.setString(2, CS.getOfficialName());
                pstmt.executeUpdate();
            }
        }
        catch(Exception e){	System.out.println("fail");}

    }


    public static int getNewCustomerSalesmanID (){
        try{
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            ResultSet myRes = myState.executeQuery("select * from "+db_customer_salesman);
            int last_id ;
            last_id = 0;
            while(myRes.next()) {
                last_id = myRes.getInt("id");
            }
            return last_id+1;

        }catch (Exception e){

        }
        return  -1;
    }

    public static ArrayList<CustomerSalesman> getCustomerList (){
        ArrayList<CustomerSalesman> tempArr = new ArrayList<CustomerSalesman>();


        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            ResultSet myRes = myState.executeQuery("select * from "+db_customer_salesman);
            while (myRes.next()) {
                if(myRes.getBoolean(db_is_customer)) {
                    CustomerSalesman customer = new CustomerSalesman(myRes.getInt(db_id));
                    readCustomerSalesmanInfo(customer);
                    tempArr.add(customer);
                }
            }




        } catch (Exception e) {
        }

        return tempArr;

    }


    public static ArrayList<CustomerSalesman> getSalesmanList (){
        ArrayList<CustomerSalesman> tempArr = new ArrayList<CustomerSalesman>();


        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            ResultSet myRes = myState.executeQuery("select * from "+db_customer_salesman);
            while (myRes.next()) {
                if(!myRes.getBoolean(db_is_customer)) {
                    CustomerSalesman salesman = new CustomerSalesman(myRes.getInt(db_id));
                    readCustomerSalesmanInfo(salesman);
                    tempArr.add(salesman);
                }
            }




        } catch (Exception e) {
        }

        return tempArr;

    }




}
