package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String dbUrl = "jdbc:mysql://localhost:3306/product_db?useUnicode=true&serverTimezone=UTC&useSSL=false";
        String user = "root";
        String password = "qwerty12345678";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(dbUrl, user, password);
        Scanner scanner = new Scanner(System.in);
        JFrame jFrame = new JFrame();
        JLabel label = new JLabel();
        label.setBounds(135, 50, 150, 50);
        label.setText("Form Registration");
        JTextField userTxt = new JTextField();
        userTxt.setBounds(110, 100, 150, 20);
        JTextField passwordTxt = new JTextField();
        passwordTxt.setBounds(110, 140, 150, 20);
        JButton button = new JButton();
        button.setBounds(130, 180, 100, 20);
        button.setText("Insert");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1 = userTxt.getText();
                String s2 = passwordTxt.getText();
                try {
                    String query = "INSERT INTO registration (User, Password) VALUES (?, ?)";
                    PreparedStatement preparedStatement = c.prepareStatement(query);
                    preparedStatement.setString(1, s1);
                    preparedStatement.setString(2, s2);
                    int res = preparedStatement.executeUpdate();
                    if(res > 0){
                        JOptionPane.showMessageDialog(jFrame,"Successful created account");
                        c.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        jFrame.add(label);
        jFrame.add(userTxt);
        jFrame.add(passwordTxt);
        jFrame.add(button);
        jFrame.setSize(400, 500);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }
}