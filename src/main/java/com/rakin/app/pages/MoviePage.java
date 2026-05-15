package com.rakin.app.pages;

import com.rakin.app.components.*;
import info.movito.themoviedbapi.model.core.Movie;
import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MoviePage extends JFrame {
    private JPanel panel;
    private JLabel titleLabel;
    private Font f1;

    public MoviePage(Movie m) {
        setTitle("Ranking Rangers");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        f1 = new Font("Segoe UI", Font.PLAIN, 23);

        try {
            String title = m.getTitle();
            String posterPath = m.getPosterPath();

            panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(Color.DARK_GRAY);

            // Poster image
            if (posterPath != null && !posterPath.isEmpty()) {
                URL posterURL = new URL("https://image.tmdb.org/t/p/w500" + posterPath);
                Image raw = ImageIO.read(posterURL);
                Image scaled = raw.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
                panel.add(new JLabel(new ImageIcon(scaled)), BorderLayout.CENTER);
            } else {
                panel.add(new JLabel("No Image", SwingConstants.CENTER), BorderLayout.CENTER);
            }

            // Title
            titleLabel = new JLabel(title, SwingConstants.CENTER);
            titleLabel.setFont(f1);
            panel.add(titleLabel, BorderLayout.SOUTH);

            this.add(panel);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
