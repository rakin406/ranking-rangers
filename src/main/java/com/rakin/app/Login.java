package com.rakin.app;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class Login extends JPanel {
    private JLabel mainLabel, backLabel;
    private Font f1, f2, f3, f4;
    private JTextField tf1;
    private JButton loginBtn;
    private JPasswordField tf2;
    private Cursor cursor;

    Login(App app) {
        this.setLayout(null);

        // Fonts
        f1 = new Font("Segoe UI Black", Font.BOLD, 60);
        f2 = new Font("Segoe UI Black", Font.PLAIN, 25);
        f3 = new Font("Segoe UI", Font.PLAIN, 30);
        f4 = new Font("Segoe UI", Font.PLAIN, 22);

        // Cursor for buttons
        cursor = new Cursor(Cursor.HAND_CURSOR);

        // Go back
        backLabel = new JLabel("Back");
        backLabel.setBounds(10, 5, 215, 50);
        backLabel.setFont(f2);
        backLabel.setCursor(cursor);
        backLabel.setForeground(Color.WHITE);
        backLabel.setBackground(Color.decode("#2E75B6"));
        this.add(backLabel);

        // Title
        mainLabel = new JLabel();
        mainLabel.setText("Login");
        mainLabel.setBounds(430, 50, 500, 90);
        mainLabel.setFont(f1);
        this.add(mainLabel);

        // User Name
        mainLabel = new JLabel();
        mainLabel.setText("Username");
        mainLabel.setBounds(430, 145, 500, 50);
        mainLabel.setFont(f3);
        this.add(mainLabel);

        tf1 = new JTextField();
        tf1.setBounds(600, 155, 200, 35);
        tf1.setFont(f4);
        this.add(tf1);

        // Password
        mainLabel = new JLabel();
        mainLabel.setText("Password");
        mainLabel.setBounds(430, 205, 500, 50);
        mainLabel.setFont(f3);
        this.add(mainLabel);

        tf2 = new JPasswordField();
        tf2.setBounds(600, 215, 200, 35);
        tf2.setFont(f2);
        tf2.setEchoChar('*');
        this.add(tf2);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(590, 325, 215, 50);
        loginBtn.setFont(f2);
        loginBtn.setCursor(cursor);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(Color.decode("#2E75B6"));
        this.add(loginBtn);

        // Back click
        backLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                app.goBack();
            }
        });

        // Login Button
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String textField1 = tf1.getText().toLowerCase(); // User Name
                String textField2 = tf2.toString(); // Password

                if (textField1.isEmpty() || textField2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all of the fields.",
                        "Warning!", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        String userNameS = "User Name : " + textField1;
                        String passwordS = "Password : " + textField2;
                        BufferedReader reader =
                            new BufferedReader(new FileReader(".\\Data\\user_data.txt"));

                        int totalLines = 0;
                        while (reader.readLine() != null) totalLines++;
                        reader.close();

                        for (int i = 0; i <= totalLines; i++) {
                            String line =
                                Files.readAllLines(Paths.get(".\\Data\\user_data.txt")).get(i);
                            if (line.equals(userNameS)) {
                                String line2 =
                                    Files.readAllLines(Paths.get(".\\Data\\user_data.txt"))
                                        .get((i + 1));
                                if (line2.equals(passwordS)) {
                                    JOptionPane.showMessageDialog(null, "Login Successful.",
                                        "Travel Agency!", JOptionPane.WARNING_MESSAGE);

                                    setVisible(false);
                                    // Packs frame = new Packs();
                                    // frame.setVisible(true);
                                    break;
                                }
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid User Name or Password!",
                            "Warning!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }
}
