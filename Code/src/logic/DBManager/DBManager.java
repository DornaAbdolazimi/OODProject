package logic.DBManager;

import logic.Info.Group;
import logic.Info.Item;
import logic.Info.Quantity;
import logic.Sell_Buy.CustomerSalesman;
import logic.Sell_Buy.Order;
import logic.Sell_Buy.OrderItem;
import logic.User.Cashier;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

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
    public static String db_price = "getPrice";
    public static String db_is_selling = "is_selling";
    public static String db_other_side = "other_side";
    public static String db_date = "date";


    public static String db_group_name = "name";
    public static String db_super_group = "super_group";
    public static String db_sub_group = "sub_group";







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
                    item.setQuantityHistory(new ArrayList<Quantity>());
                    while (myRes2.next()) {
                        if (myRes2.getInt("item_id") == item.getId()){
                            Quantity quantity = new Quantity(myRes2.getDate("start_date"),
                                    myRes2.getTime("start_time"), myRes2.getInt("item_id"), myRes2.getInt("quantity"));
                            quantity.setEndDate(myRes2.getDate("end_date"));
                            item.getQuantityHistory().add(quantity);

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
            pstmt.setDate(2, new Date(System.currentTimeMillis()));
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
        try {

            String sql = "insert into "+ "item_quantity"+ "("
                    + "item_id, start_date, start_time, quantity"
                    + ") VALUES(?,?,?,?)";


            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);


            pstmt.setInt(1, item.getId());
            pstmt.setDate(2, new Date(System.currentTimeMillis()));
            pstmt.setTime(3, new Time(System.currentTimeMillis()));
            pstmt.setInt(4, item.getQuantity());

            pstmt.executeUpdate();


        } catch(Exception e){	System.out.println("fail updating item 2");}


        /*    try{






            String sql3 = "update item_quantity" + " set end_date = ? where end_date = null ";


            pstmt = myCon.prepareStatement(sql3,
                    Statement.RETURN_GENERATED_KEYS);

            Quantity lastQuantity =  item.getQuantityHistory().get(item.getQuantityHistory().size()-1);
            pstmt.setDate(1, lastQuantity.getEndDate());


            pstmt.executeUpdate();
            }
        }*/

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
                System.out.printf("No logic.Sell_Buy.Order with this ID.");
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


    public static void readGroupInfoFromDB (Group group){
        try{
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            ResultSet myRes = myState.executeQuery("select * from item_group");
            while(myRes.next()) {
                if (myRes.getString("name").equals( group.getName()) || myRes.getString("id").equals( group.getId())){
                    group.setId(myRes.getInt("id"));
                    group.setName(myRes.getString("name"));
                }
            }

        }catch (Exception e){

        }
    }

    public static int extractParentIDFromDB(Group group) {
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            ResultSet myRes = myState.executeQuery("select * from group_rel");
            while (myRes.next()) {
                if (myRes.getInt("sub_group") == group.getId()) {
                    return myRes.getInt("super_group");
                }
            }

        } catch (Exception e) {

        }
        return  -1;
    }

    public static void InsertNewGroupIntoDB (Group group){
        try  {
            String sql = "insert into item_group"+ "("
                    + db_id +  ","+ db_group_name +") VALUES(?,?)";
            PreparedStatement pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, group.getId());
            pstmt.setString(2, group.getName());
            pstmt.executeUpdate();


            sql = "insert into group_rel"+ "("
                    + db_super_group +  ","+ db_sub_group +") VALUES(?,?)";
            pstmt = myCon.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, group.getSuperGroup().getId());
            pstmt.setInt(2, group.getId());
            pstmt.executeUpdate();


        }
        catch(Exception e){	System.out.println("fail!");}

    }

    public static int getNewGroupID(){
        try{
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Statement myState = myCon.createStatement();
            ResultSet myRes = myState.executeQuery("select * from item_group" );
            int last_id = 0;
            while(myRes.next()) {
                last_id = myRes.getInt("id");
            }
            return last_id+1;

        }catch (Exception e){

        }
        return -1;

    }


    public static Group getGroupWithName (String name){
        Group group = new Group(name);
        readGroupInfoFromDB(group);
        return  group;

    }







}
