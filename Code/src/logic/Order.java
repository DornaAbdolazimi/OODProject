package logic;

import java.sql.Date;
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
    public static String db_price = "getPrice";
    public static String db_is_selling = "is_selling";
    public static String db_other_side = "other_side";
    public static String db_date = "date";

    private int id;
    private Date date;
    private boolean isSelling; //0: buying, 1: Selling
    private ArrayList<OrderItem> orderItems= new ArrayList<OrderItem>();
    private boolean done;   //state
    private int otherSide;
    private  int paidPrice;

    public Order(int id){
        this.id = id;
    }


    public int getTotalPrice() {
        int res = 0;
        for (int i = 0; i < orderItems.size(); i++) {
            res += orderItems.get(i).getPrice();
        }
        return res;
    }

    public void setPaidPrice(int paidPrice) {
        this.paidPrice = paidPrice;
    }

    public int getPaidPrice() {
        return paidPrice;
    }

    public void addOrderItems(OrderItem item) {
        orderItems.add(item);
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

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public boolean isDone() {
        return done;
    }

    public void settle() {
        this.done = true;
    }









}
