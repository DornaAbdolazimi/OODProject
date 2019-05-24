package ui;

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

    UserMaker() {
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
                    makeCustomer(name,lastName,email);
                } else if (salesmanRadioButton.isSelected()){
                    makeSalesman(name,lastName,official,email);
                }else {
                    JOptionPane.showMessageDialog(null, "Type is not selected");
                }
            }
        });
    }

    private void makeSalesman(String name, String lastName, String official, String email) {
        // TODO: 5/24/2019
    }

    private void makeCustomer(String name, String lastName, String email) {
        // TODO: 5/24/2019
    }

    private void makeChashier(String name, String last, String username, String password) {
        // TODO: 5/24/2019
    }

    void start() {
        userMaker.setContentPane(new UserMaker().panel1);
        userMaker.pack();
        userMaker.setLocationRelativeTo(null); // this line set the window in the center of the screen
        userMaker.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userMaker.setVisible(true);

    }
}
