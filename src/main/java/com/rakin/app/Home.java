package com.rakin.app;

import java.awt.*;
import javax.swing.*;

public class Home extends JPanel {
    private JLabel label1;
    private Font f1;

    Home(App app) {
        this.setLayout(null);

        // Fonts
        f1 = new Font("Tahoma", Font.BOLD, 48);

        // Title
        label1 = new JLabel();
        label1.setText("Travel Anywhere");
        label1.setBounds(420, 55, 500, 65);
        label1.setFont(f1);
        this.add(label1);

        label1 = new JLabel();
        label1.setText("In the World!");
        label1.setBounds(420, 120, 500, 65);
        label1.setFont(f1);
        this.add(label1);
    }
}
