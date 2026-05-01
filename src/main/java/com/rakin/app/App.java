package com.rakin.app;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import javax.swing.*;

public class App extends JFrame {
    JPanel cards;
    final static String HOME_PANEL = "Home";
    final static String LOGIN_PANEL = "Login";

    App() {
        // Frame Layout
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ranking Rangers");
        this.setSize(900, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        cards = new JPanel(new CardLayout());
        cards.add(new Login(this), LOGIN_PANEL);
        cards.add(new Home(this), HOME_PANEL);

        add(cards, BorderLayout.CENTER);

        setVisible(true);
    }

    public void showCard(String name) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, name);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(App::new);
    }
}
