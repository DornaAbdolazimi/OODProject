package logic;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Hana on 5/7/2019.
 */
public class Main {

    public static void main(String args[]) {
        try {
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD","root", "09132035660");
            CustomerSalesman Hana = new CustomerSalesman(1);
            Hana.readDataFromDB(myCon);
            CustomerSalesman Dorna = new CustomerSalesman(CustomerSalesman.getNewID(myCon));
            Dorna.setEmail("test@test.com");
            Dorna.setPhoneNumber("09133333333");
            Dorna.setCustomer(true);
            Dorna.insertDataIntoDB(myCon);
            Item juice = new Item(1);
            juice.readDataFromDB(myCon);
            System.out.println(juice.getName());
            Item bread = new Item(Item.getNewID(myCon));
            bread.setName("Sahar Bread");
            bread.setPrice(30000);
            bread.insertDataIntoDB(myCon);
            Cashier Peyman = new Cashier(Cashier.getNewID(myCon));
            Peyman.setFirstName("Peyman");
            Peyman.setLastName("Kiasari");
            Peyman.setManager(false);
            Peyman.setUsername("peyman");
            Peyman.setPassword("12345");
            Peyman.insertDataIntoDB(myCon);
            Order order = new Order(1);
            order.readDataFromDB(myCon);
            System.out.println(order.getOrderItems().get(0).price());
            System.out.println(Group.findGroupIdByName("drinks", myCon));


        }catch (Exception e){

        }




       /* logic.Item book = new logic.Item(1, "book", 10);
        logic.Item pen = new logic.Item(2, "pen", 2);
        logic.CustomerSalesman Hana = new logic.CustomerSalesman(1);
        logic.OrderItems books = new logic.OrderItems(book, 3);
        logic.OrderItems pens = new logic.OrderItems(pen, 5);
        ArrayList <logic.OrderItems> myArraylist = new ArrayList<logic.OrderItems>();
        myArraylist.add(books);
        myArraylist.add(pens);
        logic.Date today = new logic.Date(2019, 5, 7, 23);
        logic.Order myOrder = new logic.Order(1, today, 0, myArraylist, true);
        System.out.print("The price of your order is ");
        System.out.println(myOrder.price());*/
    }
}
