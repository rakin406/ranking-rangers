package com.rakin.app.components;

import java.awt.*;
import javax.swing.*;

public class NavigationBar extends JPanel {
    private JLabel bookmarksLabel;
    private Font f1;
    private Cursor cursor;

    public NavigationBar() {
        this.setLayout(new BorderLayout(0, 4));
        this.setBackground(Color.DARK_GRAY);

        f1 = new Font("Segoe UI", Font.PLAIN, 22);
        cursor = new Cursor(Cursor.HAND_CURSOR);

        bookmarksLabel = new JLabel();
        bookmarksLabel.setText("Bookmarks");
        bookmarksLabel.setFont(f1);
        bookmarksLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bookmarksLabel.setCursor(cursor);
        this.add(bookmarksLabel);
    }
}
