package ui;

import logic.Cashier;
import logic.DBManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Made by Peyman on 5/24/2019.
 */
public class UserMaker {
    private JFrame userMaker = new JFrame("UserMaker");
    private JPanel panel1;
    private JTextField nameCustomer;
    private JTextField lastCustomer;
    private JTextField officialCustomer;
    private JTextField emailCustomer;
    private JRadioButton customerRadioButton;
    private JRadioButton salesmanRadioButton;
    private JButton createButton;
    private JButton createButton1;
    private JTextField nameCashier;
    private JTextField lastCashier;
    private JTextField userCashier;
    private JTextField passCashier;

    UserMaker(Cashier cashier) {
        createButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameCashier.getText();
                String last = lastCashier.getText();
                String username = userCashier.getText();
                String password = passCashier.getText();
                makeChashier(name,last,username,password);
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameCustomer.getText();
                String lastName = lastCustomer.getText();
                String official = officialCustomer.getText();
                String email = emailCustomer.getText();
                if (customerRadioButton.isSelected()){
                    cashier.makeCustomer(name,lastName,email);
                } else if (salesmanRadioButton.isSelected()){
                    cashier.makeSalesman(official,email);
                }else {
                    JOptionPane.showMessageDialog(null, "Type is not selected");
                }
            }
        });
    }



    private void makeChashier(String name, String last, String username, String password) {
        // TODO: 5/24/2019
    }

    void start() {
        userMaker.setContentPane(new UserMaker(new Cashier(DBManager.getNewCashierID())).panel1); //TODO I did something very bad here
        userMaker.pack();
        userMaker.setLocationRelativeTo(null); // this line set the window in the center of the screen
        userMaker.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userMaker.setVisible(true);

    }
}
