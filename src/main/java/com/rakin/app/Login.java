package com.rakin.app;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class Login extends JPanel {
    private JLabel mainLabel, backLabel;
    private Font f1, f2, f3, f4;
    private JTextField usernameFld;
    private JButton loginBtn, registerBtn;
    private JPasswordField passwordFld;
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
        // backLabel = new JLabel("Back");
        // backLabel.setBounds(10, 5, 215, 50);
        // backLabel.setFont(f2);
        // backLabel.setCursor(cursor);
        // this.add(backLabel);

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

        usernameFld = new JTextField();
        usernameFld.setBounds(600, 155, 200, 35);
        usernameFld.setFont(f4);
        this.add(usernameFld);

        // Password
        mainLabel = new JLabel();
        mainLabel.setText("Password");
        mainLabel.setBounds(430, 205, 500, 50);
        mainLabel.setFont(f3);
        this.add(mainLabel);

        passwordFld = new JPasswordField();
        passwordFld.setBounds(600, 215, 200, 35);
        passwordFld.setFont(f2);
        passwordFld.setEchoChar('*');
        this.add(passwordFld);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(430, 325, 215, 50);
        loginBtn.setFont(f2);
        loginBtn.setCursor(cursor);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(Color.decode("#2E75B6"));
        this.add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(655, 325, 215, 50);
        registerBtn.setFont(f2);
        registerBtn.setCursor(cursor);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setBackground(Color.decode("#ff2800"));
        this.add(registerBtn);

        // Back click
        // backLabel.addMouseListener(new MouseAdapter() {
        //     public void mouseClicked(MouseEvent me) {
        //         app.goBack();
        //     }
        // });

        // Login Button
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String username = usernameFld.getText().trim();
                String password = passwordFld.toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all of the fields.",
                        "Warning!", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        String usernameLine = "Username: " + username;
                        String passwordLine = "Password: " + password;
                        BufferedReader reader =
                            new BufferedReader(new FileReader(App.USER_DATA_PATH));

                        int totalLines = 0;
                        while (reader.readLine() != null) totalLines++;
                        reader.close();

                        for (int i = 0; i <= totalLines; i++) {
                            String line = Files.readAllLines(Paths.get(App.USER_DATA_PATH)).get(i);
                            if (line.equals(usernameLine)) {
                                String line2 =
                                    Files.readAllLines(Paths.get(App.USER_DATA_PATH)).get((i + 1));
                                if (line2.equals(passwordLine)) {
                                    JOptionPane.showMessageDialog(null, "Login Successful.",
                                        "Ranking Rangers", JOptionPane.WARNING_MESSAGE);

                                    app.showCard(App.HOME_PANEL);
                                    break;
                                }
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password!",
                            "Warning!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // Register Button
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String username = usernameFld.getText().trim();
                String password = passwordFld.toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all of the fields.",
                        "Warning!", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        File file = new File(App.USER_DATA_PATH);
                        if (!file.exists()) {
                            file.createNewFile();
                        }

                        FileWriter fw = new FileWriter(file, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println("Username: " + username);
                        pw.println("Password: " + password);
                        pw.println("===============================================");
                        pw.close();
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }
                }
            }
        });
    }
}
