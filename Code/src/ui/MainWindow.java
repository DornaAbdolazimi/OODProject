package ui;

import logic.Group;
import logic.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Made by Peyman on 5/23/2019.
 */
public class MainWindow {
    private JFrame mainWindow = new JFrame("MainWindow");
    private JPanel panel1;
    private JTextField nameItem;
    private JTextField priceItem;
    private JTextField quanItem;
    private JTextField groupeItem;
    private JButton addItemButton;
    private JTextField idFactor;
    private JTextArea txtFactor;
    private JTextArea txtReport;
    private JTextField textField7;
    private JButton addFactorButton;
    private JButton settleButton;
    private JTextField idSettle;
    private JTextField stOff;
    private JTextField edOff;
    private JTextField stScore;
    private JTextField edScore;
    private JTextField itemOff;
    private JTextField valOff;
    private JRadioButton amountRadioButton;
    private JButton addOffButton;
    private JRadioButton percentRadioButton;
    private JRadioButton holdingsRadioButton;
    private JRadioButton debtRadioButton;
    private JRadioButton customersReportRadioButton;
    private JRadioButton costAndBenefitRadioButton;
    private JButton getTheReportButton;
    private JTextField stReport;
    private JTextField edReport;
    private JRadioButton sellRadioButton;
    private JRadioButton buyRadioButton;


    MainWindow(boolean manager) {
        getTheReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] tempStDate = stReport.getText().split("/");
                    int yearST = Integer.parseInt(tempStDate[0]);
                    int monthST = Integer.parseInt(tempStDate[1]);
                    int dayST = Integer.parseInt(tempStDate[2]);

                    tempStDate = edReport.getText().split("/");
                    int yearED = Integer.parseInt(tempStDate[0]);
                    int monthED = Integer.parseInt(tempStDate[1]);
                    int dayED = Integer.parseInt(tempStDate[2]);

                    if (costAndBenefitRadioButton.isSelected() && !manager) {
                        JOptionPane.showMessageDialog(null, "You are not manager");
                    } else {
                        if (costAndBenefitRadioButton.isSelected()) {
                            txtReport.setText(getReportCostAndBenefit(yearST, monthST, dayST, yearED, monthED, dayED));
                        } else if (customersReportRadioButton.isSelected()) {
                            txtReport.setText(getReportCustomer(yearST, monthST, dayST, yearED, monthED, dayED));
                        } else if (debtRadioButton.isSelected()) {
                            txtReport.setText(getReportDebt(yearST, monthST, dayST, yearED, monthED, dayED));
                        } else if (holdingsRadioButton.isSelected()) {
                            txtReport.setText(getReportHolding(yearST, monthST, dayST, yearED, monthED, dayED));
                        }
                    }

                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "err in date");
                }

            }
        });
        addOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (amountRadioButton.isSelected() || percentRadioButton.isSelected()) {
                        boolean isAmount = costAndBenefitRadioButton.isSelected();

                        String[] tempStDate = stOff.getText().split("/");
                        int yearST = Integer.parseInt(tempStDate[0]);
                        int monthST = Integer.parseInt(tempStDate[1]);
                        int dayST = Integer.parseInt(tempStDate[2]);

                        tempStDate = edOff.getText().split("/");
                        int yearED = Integer.parseInt(tempStDate[0]);
                        int monthED = Integer.parseInt(tempStDate[1]);
                        int dayED = Integer.parseInt(tempStDate[2]);

                        int scoreST = Integer.parseInt(stScore.getText());
                        int scoreED = Integer.parseInt(edScore.getText());

                        int itemID = Integer.parseInt(itemOff.getText());
                        float offValue = Float.parseFloat(valOff.getText());

                        makeOff(isAmount, itemID, offValue, scoreST, scoreED, yearST, monthST, dayST, yearED, monthED, dayED);
                    } else {
                        JOptionPane.showMessageDialog(null, "Type is not selected");
                    }


                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "err in date");
                }
            }
        });
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameItem.getText();
                String group = groupeItem.getText();
                float price = Float.parseFloat(priceItem.getText());
                int quantity = Integer.parseInt(quanItem.getText());

                makeItem(name, price, quantity, group);
            }
        });
        addFactorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buyRadioButton.isSelected() || sellRadioButton.isSelected()) {
                    boolean isSell = sellRadioButton.isSelected();

                    int personID = Integer.parseInt(idFactor.getText());

                    try {
                        String[] date = textField7.getText().split("/");
                        int year = Integer.parseInt(date[0]);
                        int month = Integer.parseInt(date[1]);
                        int day = Integer.parseInt(date[2]);

                        try {
                            String[] itemsAndQuantity = txtFactor.getText().split("\n");

                            makeFactor(isSell, personID, itemsAndQuantity, year, month, day);
                        } catch (Exception err) {
                            JOptionPane.showMessageDialog(null, "err in items and quantity");
                        }


                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, "err in date");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Type is not selected");
                }
            }
        });
        settleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settleFactor(Integer.parseInt(idSettle.getText()));
            }
        });
    }

    private void settleFactor(int i) {
        // TODO: 5/24/2019  
    }

    private void makeFactor(boolean isSell, int personID, String[] itemsAndQuantity, int year, int month, int day) {
        // TODO: 5/24/2019  
    }

    private void makeItem(String name, float price, int quantity, String group) {

        try {
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOD", "root", "09132035660");
            Item item = new Item(Item.getNewID(myCon), name, Math.round(price), quantity);
            item.setGroupId(Group.findGroupIdByName(group,myCon));
            item.insertDataIntoDB(myCon);

        }catch (Exception e){
            System.out.println("fail in makeItem.");
        }

    }

    private void makeOff(boolean isAmount, int itemID, float offValue, int scoreST, int scoreED, int yearST, int monthST, int dayST, int yearED, int monthED, int dayED) {
        // TODO: 5/24/2019
    }

    private String getReportHolding(int yearST, int monthST, int dayST, int yearED, int monthED, int dayED) {
        // TODO: 5/24/2019  
        return null;
    }

    private String getReportDebt(int yearST, int monthST, int dayST, int yearED, int monthED, int dayED) {
        // TODO: 5/24/2019  
        return null;
    }

    private String getReportCustomer(int yearST, int monthST, int dayST, int yearED, int monthED, int dayED) {
        // TODO: 5/24/2019  
        return "report\treport\treport\nreport\treport\treport\nreport\treport\treport\nreport\treport\treport\nreport\treport\treport\nreport\treport\treport\n";
    }

    private String getReportCostAndBenefit(int yearST, int monthST, int dayST, int yearED, int monthED, int dayED) {
        // TODO: 5/24/2019
        return "getReportCostAndBenefit";
    }

    void start() {
        mainWindow.setContentPane(panel1);
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null); // this line set the window in the center of thr screen
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

    }
}
