package com.rakin.app.pages;

import com.rakin.app.App;
import info.movito.themoviedbapi.TmdbApi;
import io.github.cdimascio.dotenv.Dotenv;
import java.awt.*;
import javax.swing.*;

public class Home extends JPanel {
    private static Dotenv dotenv;
    private static TmdbApi tmdbApi;
    private JLabel label1;
    private Font f1;

    static {
        dotenv = Dotenv.load();
        tmdbApi = new TmdbApi(dotenv.get("TMDB_ACCESS_TOKEN"));
    }

    public Home(App app) {
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
