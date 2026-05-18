package com.rangers.app.components;

import com.rangers.app.pages.Bookmarks;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class NavigationBar extends JPanel {
    private JLabel bookmarksLabel, usernameLabel;
    private Font f1;
    private Cursor cursor;

    public NavigationBar(String username) {
        this.setLayout(new BorderLayout(0, 4));
        this.setBackground(Color.DARK_GRAY);

        f1 = new Font("Segoe UI", Font.PLAIN, 22);
        cursor = new Cursor(Cursor.HAND_CURSOR);

        bookmarksLabel = new JLabel();
        bookmarksLabel.setText("Bookmarks");
        bookmarksLabel.setFont(f1);
        bookmarksLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bookmarksLabel.setCursor(cursor);
        bookmarksLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Bookmarks(username).setVisible(true);
            }
        });
        this.add(bookmarksLabel);

        usernameLabel = new JLabel();
        usernameLabel.setText(username);
        usernameLabel.setFont(f1);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(usernameLabel, BorderLayout.EAST);
    }
}
