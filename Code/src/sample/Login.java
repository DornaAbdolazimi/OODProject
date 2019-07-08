package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import logic.Cashier;
import logic.CashierDBManager;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    public Label label;
    public Button loginButt;
    public TextField usernameText;
    public PasswordField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void beClicked(ActionEvent actionEvent) {
        String username = usernameText.getText();
        String password = passwordField.getText();
        if (isValid(username,password)){
            return;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "information is not valid", ButtonType.OK);
            alert.showAndWait();

        }

    }

    private boolean isValid(String username, String password) {
        // TODO: 7/2/2019

        Cashier cashier = new Cashier();
        cashier.setUsername(username);
        CashierDBManager.readCashierInfoFramDB(cashier);

        if(cashier.getPassword().equals(password)){
            return true;
        }
            return false;
    }
}
