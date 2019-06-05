package logic;

import java.sql.*;

public abstract  class  DBManager {

    private static Connection  myCon;


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


    private final static String db_item = "item";
    private final static String db_item_name = "name";
    private final static String db_item_group_id = "group_id";
    private final static String db_item_current_price = "current_price";
    private final static String db_item_quantity = "quantity";


    private static String db_cashier =  "cashier";
    private static String db_cashier_first_name = "first_name";
    private static String db_cashier_last_name = "last_name";
    private static String db_is_manager = "is_manager";
    private static String db_cashier_username = "username";
    private static String db_cashier_pass = "pass";


    public static String db_order = "orders";
    public static String db_order_id = "order_id";
    public static String db_item_id = "item_id";
    public static String db_quantity = "quantity";
    public static String db_price = "price";
    public static String db_is_selling = "is_selling";
    public static String db_other_side = "other_side";
    public static String db_date = "date";





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
                //TODO
            }
            else{
                //TODO
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









    public static void readItemInfoFromDB ( Item item) {

        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            boolean temp = false;
            ResultSet myRes = myState.executeQuery("select * from "+db_item);
            while (myRes.next()) {
                if (myRes.getInt(db_id) == item.getId()) {
                    temp = true;
                    item.setName(myRes.getString(db_item_name));
                    item.setGroupId(myRes.getInt(db_item_group_id));
                    item.setPrice(myRes.getInt(db_item_current_price));
                    item.setQuantity(myRes.getInt(db_item_quantity));

                }
            }
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

            System.out.println("hi");


            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);


            System.out.println(item.getId());
            System.out.println(item.getName());
            System.out.println(item.getGroupId());
            System.out.println(item.getPrice());
            System.out.println(item.getQuantity());
            pstmt.setInt(1, item.getId());
            pstmt.setString(2, item.getName());
            pstmt.setInt(3, item.getGroupId());
            pstmt.setInt(4, item.getPrice());
            pstmt.setInt(5, item.getQuantity());

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
                    readItemInfoFromDB(item);
                    OrderItems orderItem = new OrderItems(item,
                            myRes.getInt(db_quantity), myRes.getInt(db_price));
                    order.addOrderItems(orderItem);

                }
            }

        }
        catch(Exception e){	System.out.println("fail");}


    }

    public void insertNewOrderIntoDB(Order order){
        try  {
            String sql = "insert into "+ db_order + "("
                    + db_id + ","+ db_is_selling + ","+ db_other_side + ","+ db_date +") VALUES(?,?,?,?)";

            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, order.getId());
            pstmt.setBoolean(2, order.isSelling());
            pstmt.setInt(3, order.getOtherSide());
//            pstmt.setDate(4, Date.valueOf(LocalDate.EPOCH)); //TODO
            //TODO: add order items as well
            pstmt.executeUpdate();

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
