package com.rakin.app.components;

import java.awt.*;
import javax.swing.*;

public class MovieList extends JPanel {
    private Font f1;
    private Cursor cursor;

    public MovieList(String movies) {
        this.setLayout(new GridLayout(5, 0, 10, 10));

        f1 = new Font("Segoe UI", Font.PLAIN, 22);

        cursor = new Cursor(Cursor.HAND_CURSOR);
    }
}
