package logic.User;

import logic.DBManager.DBManager;
import logic.Info.Group;
import logic.Info.Item;
import logic.Sell_Buy.CustomerSalesman;
import logic.Sell_Buy.Order;
import logic.Sell_Buy.OrderItem;

import java.sql.*;

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
        return  0;
    }



    public void makeItem(String name, float price, int quantity, String group) {
            Item item = new Item(DBManager.getNewItemID(), name, Math.round(price), quantity);
            item.setGroupId(DBManager.getGroupWithName(group).getId());
            DBManager.insertNewItemIntoDB(item);


    }

    public void makeSalesman( String official, String email) {

        CustomerSalesman salesman = new CustomerSalesman(DBManager.getNewCustomerSalesmanID());
        salesman.setCustomer(false);
        salesman.setOfficialName(official);
        salesman.setEmail(email);
        salesman.setPhoneNumber(String.valueOf(Math.random())); //TODO
        DBManager.insertNewCustomerSalesmanIntoDB(salesman);



    }

    public void makeCustomer(String firstName, String lastName, String email) {
        CustomerSalesman customer = new CustomerSalesman(DBManager.getNewCustomerSalesmanID());
        customer.setCustomer(true);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhoneNumber(String.valueOf(Math.random())); //TODO
        DBManager.insertNewCustomerSalesmanIntoDB(customer);

    }

    public void createGroup (String name, String parentName){
        Group group = new Group(name);
        group.setId(DBManager.getNewGroupID());

        if(!parentName.equals("")){
            Group parent = new Group(parentName);
            DBManager.readGroupInfoFromDB(parent);
            group.setSuperGroup(parent);

        }


    }

    public void createOrder (boolean isSell, int personID,
                             String[] itemsAndQuantity){

        Order order = new Order(DBManager.getNewOrderID());
        order.setSelling(isSell);
        order.setOtherSide(personID);
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis() );
        order.setDate(date);
        //TODO there is no paid getPrice in UI
        order.setPaidPrice(0);
        for (int i = 0; i < itemsAndQuantity.length; i++) {
            String itemName = itemsAndQuantity[i].split("-")[0]; //TODO
            Item item = DBManager.getItemWithName(itemName);
            for (int j = 0; j < item.getQuantityHistory().size() ; j++) {
                System.out.println("quantity");
                System.out.println(item.getQuantityHistory().get(j).getQuantity());
            }
            int quantity = Integer.parseInt(itemsAndQuantity[i].split("-")[1]); //TODO change it
            OrderItem orderItem = new OrderItem(item, quantity, item.getPrice()*quantity, order.getId());
            order.addOrderItems(orderItem);

            item.decreaseQuantity(quantity);
            DBManager.updateItemQuantityInDB(item);
        }
        DBManager.insertNewOrderIntoDB (order);
        //TODO Customer's score should be updated
    }





}
