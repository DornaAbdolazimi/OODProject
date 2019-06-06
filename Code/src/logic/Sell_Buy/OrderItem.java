package logic.Sell_Buy;

import logic.Info.Item;

/**
 * Created by Hana on 5/7/2019.
 */
public class OrderItem {

    private Item item;
    private int quantity;
    private int price;
    private int orderID;



    public OrderItem(Item item, int quantity, int price, int orderID ){
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.orderID = orderID;
    }

    public int getOrder() {
        return orderID;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return item.getPrice() * quantity;
    }
}
