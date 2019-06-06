package logic.Info;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Hana on 5/7/2019.
 */
public class Item {


    private int id;
    private String name = "";
    private int currentPrice = 0;
    private int groupId = 1;
    private  int quantity = 0;

    public Group getGroup() {
        return group;
    }

    public void updateGroup(Group group) {
        this.group = group;
    }

    private Group group;
    private ArrayList<Quantity> quantityHistory = new ArrayList<Quantity>();

    public Item(int id) {
        this.id = id;

    }

    public Item(String name) {
        this.name = name;

    }

    public Item(int id, String name, int currentPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
        quantityHistory.add(new Quantity(new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), id, quantity));

    }

    public void setId(int id) {
        this.id = id;
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




    public void increaseQuantity (int amount){

    }

    public void decreaseQuantity (int amount){

        if(quantity>=amount){
            this.quantity = quantity -amount;
            if(quantityHistory.size()>0) {
                quantityHistory.get(quantityHistory.size() - 1).setEndDate(new Date(System.currentTimeMillis()));

                quantityHistory.add((new Quantity(new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), id, quantity)));
            }
        }


    }

    public ArrayList<Quantity> getQuantityHistory() {
        return quantityHistory;
    }

    public void setQuantityHistory(ArrayList<Quantity> quantityHistory) {
        this.quantityHistory = quantityHistory;
    }
}
