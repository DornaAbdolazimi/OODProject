import java.util.ArrayList;

/**
 * Created by Hana on 5/7/2019.
 */
public class OrderItems {

    private Item item;
    private int quantity;
    private int price;



    public OrderItems(Item item, int quantity, int price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
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

    public double price() {
        return item.getPrice() * quantity;
    }
}
