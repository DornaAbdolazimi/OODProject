package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Hana on 5/7/2019.
 */
public class Item {


    private int id;
    private String name = "";
    private int currentPrice = 0;
    private int groupId = 1;
    private  int quantity = 0;

    public Item(int id) {
        this.id = id;

    }

    public Item(int id, String name, int currentPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.quantity = quantity;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return currentPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.currentPrice = price;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }






}
