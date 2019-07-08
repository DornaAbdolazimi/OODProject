package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import logic.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Made by Peyman on 7/7/2019.
 */
public class MainController implements Initializable {

    public HBox hBoxGoods;
    public Button buttGoods;
    public ChoiceBox<String> choiceGoods;
    public Spinner<Integer> spinnerGoods;
    public Button buttClearGoods;
    public VBox vBoxGoods;
    public ScrollPane scrollGoods;
    public HBox hBoxSell;
    public ToggleGroup radioSell;
    public ChoiceBox<String> choiceSell;

    private ArrayList<String> listOfOrders = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hBoxGoods.setSpacing(30);
        hBoxSell.setSpacing(30);

        spinnerGoods.setEditable(true);
        spinnerGoods.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99999, 1));

        choiceGoods.setItems(FXCollections.observableArrayList(getGoodsIDs()));
        choiceGoods.getSelectionModel().selectFirst();
        choiceSell.setItems(FXCollections.observableArrayList(getCustomersIDs()));
        choiceSell.getSelectionModel().selectFirst();


        scrollGoods.setFitToWidth(true);

    }

    private ArrayList<String> getGoodsIDs() {
        ArrayList<Item> temp = ItemDBManager.getItemList();
        ArrayList<String> tempArr = new ArrayList<String>();


        for (int i = 0; i < temp.size(); i++) {
            tempArr.add(temp.get(i).getName());
        }

        return tempArr;
    }

    private ArrayList<String> getCustomersIDs() {
        ArrayList<CustomerSalesman> temp = CustomerSalesmanDBManager.getCustomerList();
        ArrayList<String> tempArr = new ArrayList<String>();


        for (int i = 0; i < temp.size(); i++) {
            tempArr.add(temp.get(i).getFirstName() + " "+temp.get(i).getLastName()+ " - ID: "+  temp.get(i).getId());
        }

        return tempArr;
    }

    private ArrayList<String> getSalemansIDs() {
        ArrayList<CustomerSalesman> temp = CustomerSalesmanDBManager.getSalesmanList();
        ArrayList<String> tempArr = new ArrayList<String>();


        for (int i = 0; i < temp.size(); i++) {
            tempArr.add(temp.get(i).getOfficialName() +  " - ID: "+  temp.get(i).getId());
        }

        return tempArr;

    }

    public void doButtGoods(ActionEvent actionEvent) {

        String[] goodFeatures = getOrderFeatures(choiceGoods.getValue(), spinnerGoods.getValue());
        vBoxGoods.getChildren().add(makeOffer(goodFeatures[0], goodFeatures[1], goodFeatures[2], goodFeatures[3], goodFeatures[4]));

        listOfOrders.add(choiceGoods.getValue() + ":" + String.valueOf(spinnerGoods.getValue()));

    }

    private String[] getOrderFeatures(String itemName, Integer quantity) {


        Item item  = ItemDBManager.getItemWithName(itemName);


        return new String[]{item.getName(), String.valueOf(quantity), "23 %", String.valueOf(item.getPrice()*quantity), "imageaddress.png"};
    }

    HBox makeOffer(String name, String quantity, String off, String price, String imageAddress) {
        HBox pane = new HBox();
        pane.setSpacing(40);
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setBackground(new Background(new BackgroundFill(
                Color.BURLYWOOD,
                new CornerRadii(30),
                new Insets(2.1))));
        pane.setBorder(new Border(new BorderStroke(Color.BURLYWOOD,
                BorderStrokeStyle.SOLID, new CornerRadii(30), BorderWidths.DEFAULT)));


        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("box.png")));
        try {
            imageView.setImage(new Image(getClass().getResourceAsStream(imageAddress)));
        } catch (Exception e) {

        }

        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        pane.getChildren().add(imageView);
//        ----------------------------------------------------
        Label nameLabel = new Label("Name: " + name + spaceMaker(25 - name.length()));
        Label quaintLabel = new Label("Quantity: " + quantity + spaceMaker(10 - quantity.length()));
        Label offLabel = new Label("Off: " + off + spaceMaker(6 - off.length()));
        Label priceLabel = new Label("price: " + price + spaceMaker(10 - price.length()));

        nameLabel.setStyle("-fx-font-weight: bold");
        offLabel.setStyle("-fx-font-weight: bold");
        quaintLabel.setStyle("-fx-font-weight: bold");
        priceLabel.setStyle("-fx-font-weight: bold");


        pane.getChildren().add(nameLabel);
        pane.getChildren().add(quaintLabel);
        pane.getChildren().add(offLabel);
        pane.getChildren().add(priceLabel);

        return pane;
    }

    private String spaceMaker(Integer integer) {
        StringBuilder outputBuffer = new StringBuilder(integer);
        for (int i = 0; i < integer; i++) {
            outputBuffer.append(" ");
        }
        return outputBuffer.toString();
    }

    public void doClearButtGoods(ActionEvent actionEvent) {
        vBoxGoods.getChildren().clear();
        listOfOrders.clear();
    }

    public void radioBuy(ActionEvent actionEvent) {
        choiceSell.setItems(FXCollections.observableArrayList(getSalemansIDs()));
        choiceSell.getSelectionModel().selectFirst();
    }

    public void radioSell(ActionEvent actionEvent) {
        choiceSell.setItems(FXCollections.observableArrayList(getCustomersIDs()));
        choiceSell.getSelectionModel().selectFirst();
    }

    public void doButtSell(ActionEvent actionEvent) {
        creatFactor(listOfOrders, choiceSell.getValue(), ((RadioButton) radioSell.getSelectedToggle()).getText());
    }

    private void creatFactor(ArrayList<String> listOfOrders, String IDcoustomerOrSalesman, String sellOrBuy) {
        System.out.println(sellOrBuy);
        for (int i = 0; i <listOfOrders.size() ; i++) {

            System.out.println(listOfOrders.get(i));

        }

        Cashier cashier = new Cashier();
        String [] temp = IDcoustomerOrSalesman.split(" ");
        int idofCS = Integer.parseInt(temp[temp.length-1]);
        boolean isSell = false;
        if (sellOrBuy.equals("sell")){
            isSell = true;
        }
        cashier.createOrder( isSell, idofCS, listOfOrders );
    }
}
