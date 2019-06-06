package logic.Sell_Buy;

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




    public ArrayList<Order> getOrders() {
        return orders;
    }


}
