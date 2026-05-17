package com.rakin.app;

import com.formdev.flatlaf.FlatDarkLaf;
import com.rakin.app.pages.*;
import javax.swing.*;

public class App {
    public final static String USER_DATA_PATH = ".\\Data\\user_data.txt";

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(() -> { new Login().setVisible(true); });
    }
}
