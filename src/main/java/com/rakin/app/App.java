package com.rakin.app;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        FlatDarkLaf.setup();

        SwingUtilities.invokeLater(() -> {
            Home frame = new Home();
            frame.setVisible(true);
        });
    }
}
