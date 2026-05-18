package com.rangers.app.pages;

import com.rangers.app.App;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class Login extends JFrame {
    private Container c;
    private ImageIcon logoIcon;
    private JLabel mainLabel, logoLabel;
    private Font f1, f2, f3;
    private JTextField usernameFld;
    private JButton loginBtn, registerBtn;
    private JPasswordField passwordFld;
    private Cursor cursor;

    public Login() {
        setTitle("Ranking Rangers - Login");
        setSize(900, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        c = this.getContentPane();
        c.setLayout(null);

        // Logo
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        this.setIconImage(logoIcon.getImage());

        // Fonts
        f1 = new Font("Segoe UI Black", Font.BOLD, 60);
        f2 = new Font("Segoe UI Black", Font.PLAIN, 25);
        f3 = new Font("Segoe UI", Font.PLAIN, 22);

        logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(50, 70, logoIcon.getIconWidth(), logoIcon.getIconHeight());
        c.add(logoLabel);

        // Cursor for buttons
        cursor = new Cursor(Cursor.HAND_CURSOR);

        // Title
        mainLabel = new JLabel();
        mainLabel.setText("Login");
        mainLabel.setBounds(430, 50, 500, 90);
        mainLabel.setFont(f1);
        this.add(mainLabel);

        // Username
        usernameFld = new JTextField();
        usernameFld.setBounds(430, 145, 350, 50);
        usernameFld.setFont(f3);
        usernameFld.putClientProperty("JTextField.placeholderText", "Username");
        this.add(usernameFld);

        // Password
        passwordFld = new JPasswordField();
        passwordFld.setBounds(430, 205, 350, 50);
        passwordFld.setFont(f3);
        passwordFld.setEchoChar('*');
        passwordFld.putClientProperty("JTextField.placeholderText", "Password");
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

        // Login Button
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String username = usernameFld.getText().trim();
                String password = new String(passwordFld.getPassword()).trim();

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
                                        "Ranking Rangers", JOptionPane.INFORMATION_MESSAGE);
                                    openHome();
                                    return;
                                }
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password!",
                            "Warning!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Register Button
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String username = usernameFld.getText().trim();
                String password = new String(passwordFld.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all of the fields.",
                        "Warning!", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        File file = new File(App.USER_DATA_PATH);
                        if (!file.exists()) {
                            file.createNewFile();
                        }

                        String usernameLine = "Username: " + username;
                        String passwordLine = "Password: " + password;

                        // Check if account already exists
                        for (String line : Files.readAllLines(file.toPath())) {
                            if (line.equals(usernameLine)) {
                                JOptionPane.showMessageDialog(null, "Account already exists!",
                                    "Warning!", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }

                        FileWriter fw = new FileWriter(file, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(usernameLine);
                        pw.println(passwordLine);
                        pw.println("===============================================");
                        pw.close();

                        JOptionPane.showMessageDialog(null, "Registration Successful.",
                            "Ranking Rangers", JOptionPane.INFORMATION_MESSAGE);
                        openHome();
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }
                }
            }
        });
    }

    /** Open the Home frame and close this one. */
    private void openHome() {
        String username = usernameFld.getText().trim();
        new Home(username).setVisible(true);
        dispose();
    }
}
