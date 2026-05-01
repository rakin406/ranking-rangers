package com.rakin.app;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

public class Login extends JPanel {
    // private ImageIcon icon, logo;
    private JLabel label1, imgLabel;
    private Font f1, f2, f3, f4, f5, f6;
    private JTextField tf1;
    private JButton btn1, btn2, btn3, nBtn;
    private JPasswordField tf2;
    private Cursor cursor;

    Login(App app) {
        this.setLayout(null);
        this.setBackground(Color.decode("#F2F2F2"));

        // Icon
        // icon = new ImageIcon(getClass().getResource("/images/Icon.png"));
        // this.setIconImage(icon.getImage());

        // Logo
        // logo = new ImageIcon(getClass().getResource("/images/LogoBlue.png"));
        // imgLabel = new JLabel(logo);
        // imgLabel.setBounds(30, 50, logo.getIconWidth(), logo.getIconHeight());
        // this.add(imgLabel);

        // Fonts
        f1 = new Font("Segoe UI Black", Font.BOLD, 60);
        f2 = new Font("Segoe UI Black", Font.PLAIN, 25);
        f3 = new Font("Segoe UI Semibold", Font.PLAIN, 35);
        f4 = new Font("Segoe UI", Font.PLAIN, 30);
        f5 = new Font("Segoe UI", Font.PLAIN, 22);
        f6 = new Font("Segoe UI", Font.PLAIN, 25);

        // Title
        label1 = new JLabel();
        label1.setText("User Login");
        label1.setBounds(450, 50, 500, 90);
        label1.setFont(f1);
        this.add(label1);

        // User Name
        label1 = new JLabel();
        label1.setText("User Name");
        label1.setBounds(430, 145, 500, 50);
        label1.setFont(f4);
        this.add(label1);

        tf1 = new JTextField();
        tf1.setBounds(600, 155, 200, 35);
        tf1.setFont(f5);
        this.add(tf1);

        // Password
        label1 = new JLabel();
        label1.setText("Password");
        label1.setBounds(430, 205, 500, 50);
        label1.setFont(f4);
        this.add(label1);

        tf2 = new JPasswordField();
        tf2.setBounds(600, 215, 200, 35);
        tf2.setFont(f2);
        tf2.setEchoChar('*');
        this.add(tf2);

        // Cursor for JButtons
        cursor = new Cursor(Cursor.HAND_CURSOR);

        // JButtons
        btn1 = new JButton("Exit");
        btn1.setBounds(90, 325, 215, 50);
        btn1.setFont(f2);
        btn1.setCursor(cursor);
        btn1.setForeground(Color.WHITE);
        btn1.setBackground(Color.decode("#C00000"));
        this.add(btn1);

        btn2 = new JButton("Back");
        btn2.setBounds(340, 325, 215, 50);
        btn2.setFont(f2);
        btn2.setCursor(cursor);
        btn2.setForeground(Color.WHITE);
        btn2.setBackground(Color.decode("#2E75B6"));
        this.add(btn2);

        btn3 = new JButton("Login");
        btn3.setBounds(590, 325, 215, 50);
        btn3.setFont(f2);
        btn3.setCursor(cursor);
        btn3.setForeground(Color.WHITE);
        btn3.setBackground(Color.decode("#2E75B6"));
        this.add(btn3);

        nBtn = new JButton("");
        nBtn.setBounds(0, 0, 0, 0);
        this.add(nBtn);

        // Exit Button
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        // Back Button
        // btn2.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent ae) {
        //         setVisible(false);
        //         Home frame = new Home();
        //         frame.setVisible(true);
        //     }
        // });

        // Login Button
        btn3.addActionListener(new ActionListener() {
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
