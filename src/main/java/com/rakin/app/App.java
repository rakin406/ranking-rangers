package com.rakin.app;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.swing.*;

public class App extends JFrame {
    public final static String HOME_PANEL = "Home";
    public final static String LOGIN_PANEL = "Login";

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private final Deque<String> history = new ArrayDeque<>();
    private String currentCard = HOME_PANEL;

    App() {
        // Frame Layout
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ranking Rangers");
        this.setSize(900, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Card container
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(new Login(this), LOGIN_PANEL);
        cardPanel.add(new Home(this), HOME_PANEL);

        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /** Switch the visible card and push the current one onto the history stack. */
    public void showCard(String name) {
        if (name.equals(currentCard)) {
            return;
        }
        history.push(currentCard);
        currentCard = name;
        cardLayout.show(cardPanel, name);
    }

    /** Navigate to the previous card. */
    public void goBack() {
        if (history.isEmpty()) {
            return;
        }
        currentCard = history.pop();
        cardLayout.show(cardPanel, currentCard);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(App::new);
    }
}
