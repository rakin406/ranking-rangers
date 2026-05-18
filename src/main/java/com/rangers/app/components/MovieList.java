package com.rangers.app.components;

import com.rangers.app.pages.MoviePage;
import info.movito.themoviedbapi.model.core.Movie;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MovieList extends JPanel {
    private Font f1;
    private Cursor cursor;

    private static final int CARD_W = 160;
    private static final int CARD_H = 280;
    private static final int IMG_W = CARD_W;
    private static final int IMG_H = 240;

    public MovieList(List<Movie> movies, String username) {
        this.setLayout(new GridLayout(0, 5, 10, 10)); // 5 columns, auto rows
        f1 = new Font("Segoe UI", Font.PLAIN, 13);
        cursor = new Cursor(Cursor.HAND_CURSOR);

        for (Movie m : movies) {
            try {
                String title = m.getTitle();
                String posterPath = m.getPosterPath();

                JPanel card = new JPanel();
                card.setLayout(new BorderLayout(0, 4));
                card.setPreferredSize(new Dimension(CARD_W, CARD_H));
                card.setCursor(cursor);
                card.setBackground(Color.DARK_GRAY);

                // Poster image
                if (posterPath != null && !posterPath.isEmpty()) {
                    URL posterURL = new URL("https://image.tmdb.org/t/p/w500" + posterPath);
                    Image raw = ImageIO.read(posterURL);
                    Image scaled = raw.getScaledInstance(IMG_W, IMG_H, Image.SCALE_SMOOTH);
                    card.add(new JLabel(new ImageIcon(scaled)), BorderLayout.CENTER);
                } else {
                    card.add(new JLabel("No Image", SwingConstants.CENTER), BorderLayout.CENTER);
                }

                // Title
                JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
                titleLabel.setFont(f1);
                card.add(titleLabel, BorderLayout.SOUTH);

                card.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new MoviePage(m, username).setVisible(true);
                    }
                });

                this.add(card);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
    }
}
