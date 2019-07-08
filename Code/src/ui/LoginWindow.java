package ui;

<<<<<<< HEAD
import logic.Cashier;
import logic.CashierDBManager;
import logic.DBManager;
=======
import logic.User.Cashier;
import logic.DBManager.DBManager;
>>>>>>> 25ba95dad4c753f236ae7452cecce45a6e7d9cf9

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Made by Peyman on 5/23/2019.
 */
public class LoginWindow {
    private JFrame loginWindow = new JFrame("LoginWindow");
    private JPanel Login;
    private JButton buttonLogin;
    private JPasswordField passwordField;
    private JTextField textFieldUsername;

    private LoginWindow() {
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = loginValidation(textFieldUsername.getText(), passwordField.getPassword());
                if (valid) {
                    Login.setVisible(false);
                    loginWindow.setVisible(false);
                    boolean isMan = isManager(textFieldUsername.getText(), passwordField.getPassword());
                    MainWindow mainWindow = new MainWindow(new Cashier(CashierDBManager.getNewCashierID())); //TODO !!!!!!!!!!1
                    mainWindow.start();
                    if (isMan){
                        UserMaker userMaker = new UserMaker(new Cashier(CashierDBManager.getNewCashierID())); //TODO !!!!!!!!
                        userMaker.start();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "INVALID username or password");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginWindow().start());

    }

    private boolean isManager(String text, char[] password) {
        // TODO: 5/24/2019
        return true;
    }

    private boolean loginValidation(String text, char[] password) {
        // TODO: 5/23/2019
        return true;
    }

    private void start() {
        loginWindow.setContentPane(new LoginWindow().Login);
        loginWindow.pack();
        loginWindow.setLocationRelativeTo(null); // this line set the window in the center of the screen
        loginWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginWindow.setVisible(true);

    }

}
