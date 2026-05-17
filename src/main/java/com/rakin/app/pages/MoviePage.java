package com.rakin.app.pages;

import com.rakin.app.model.BookmarkedMovie;
import com.rakin.app.util.BookmarkManager;
import info.movito.themoviedbapi.model.core.Movie;
import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MoviePage extends JFrame {
    private JPanel panel;
    private JLabel titleLabel;
    private Font f1;

    public MoviePage(Movie m, String username) {
        setTitle("Ranking Rangers");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        f1 = new Font("Segoe UI", Font.PLAIN, 23);

        try {
            String title = m.getTitle();
            String posterPath = m.getPosterPath();
            String overview = m.getOverview();
            String releaseDate = m.getReleaseDate();

            // Main panel
            panel = new JPanel(new BorderLayout(20, 0));
            panel.setBackground(new Color(45, 45, 45));
            panel.setBorder(new EmptyBorder(20, 20, 20, 20));

            // LEFT: Poster
            JLabel posterLabel;
            if (posterPath != null && !posterPath.isEmpty()) {
                URL posterURL = new URL("https://image.tmdb.org/t/p/w500" + posterPath);
                Image raw = ImageIO.read(posterURL);
                Image scaled = raw.getScaledInstance(280, 420, Image.SCALE_SMOOTH);
                posterLabel = new JLabel(new ImageIcon(scaled));
            } else {
                posterLabel = new JLabel("No Image", SwingConstants.CENTER);
                posterLabel.setPreferredSize(new Dimension(280, 420));
                posterLabel.setForeground(Color.LIGHT_GRAY);
                posterLabel.setOpaque(true);
                posterLabel.setBackground(new Color(60, 60, 60));
            }
            posterLabel.setPreferredSize(new Dimension(280, 420));
            panel.add(posterLabel, BorderLayout.WEST);

            // RIGHT: Details
            JPanel detailsPanel = new JPanel();
            detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
            detailsPanel.setBackground(new Color(45, 45, 45));
            detailsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            // Title
            titleLabel = new JLabel("<html>" + title + "</html>");
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Release year
            String year = (releaseDate != null && releaseDate.length() >= 4)
                ? releaseDate.substring(0, 4)
                : "Unknown";
            JLabel yearLabel = new JLabel("Release Year: " + year);
            yearLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            yearLabel.setForeground(new Color(180, 180, 180));
            yearLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Divider
            JSeparator sep = new JSeparator();
            sep.setForeground(new Color(100, 100, 100));
            sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

            // Overview
            JLabel descHeading = new JLabel("Overview");
            descHeading.setFont(new Font("Segoe UI", Font.BOLD, 15));
            descHeading.setForeground(new Color(200, 200, 200));
            descHeading.setAlignmentX(Component.LEFT_ALIGNMENT);

            String overviewText =
                (overview != null && !overview.isEmpty()) ? overview : "No description available.";
            JTextArea descArea = new JTextArea(overviewText);
            descArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            descArea.setForeground(Color.LIGHT_GRAY);
            descArea.setBackground(new Color(45, 45, 45));
            descArea.setLineWrap(true);
            descArea.setWrapStyleWord(true);
            descArea.setEditable(false);
            descArea.setFocusable(false);
            descArea.setAlignmentX(Component.LEFT_ALIGNMENT);
            descArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

            // Bookmark toggle
            boolean alreadyBookmarked = BookmarkManager.isBookmarked(username, m.getId());
            JButton bookmarkBtn = new JButton();
            bookmarkBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
            bookmarkBtn.setFocusPainted(false);
            bookmarkBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            bookmarkBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
            bookmarkBtn.setMaximumSize(new Dimension(180, 40));
            applyBookmarkStyle(bookmarkBtn, alreadyBookmarked);

            bookmarkBtn.addActionListener(e -> {
                boolean isBookmarked = BookmarkManager.isBookmarked(username, m.getId());
                if (isBookmarked) {
                    BookmarkManager.removeBookmark(username, m.getId());
                } else {
                    BookmarkManager.addBookmark(
                        username, new BookmarkedMovie(m.getId(), m.getTitle(), releaseDate));
                }
                applyBookmarkStyle(bookmarkBtn, !isBookmarked);
            });

            detailsPanel.add(titleLabel);
            detailsPanel.add(Box.createVerticalStrut(6));
            detailsPanel.add(yearLabel);
            detailsPanel.add(Box.createVerticalStrut(12));
            detailsPanel.add(sep);
            detailsPanel.add(Box.createVerticalStrut(12));
            detailsPanel.add(descHeading);
            detailsPanel.add(Box.createVerticalStrut(6));
            detailsPanel.add(descArea);
            detailsPanel.add(Box.createVerticalGlue());
            detailsPanel.add(bookmarkBtn);

            panel.add(detailsPanel, BorderLayout.CENTER);
            this.add(panel);

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    // Sets button text and color based on current bookmark state
    private void applyBookmarkStyle(JButton btn, boolean bookmarked) {
        if (bookmarked) {
            btn.setText("Remove Bookmark");
            btn.setBackground(new Color(80, 200, 120));
            btn.setForeground(Color.BLACK);
        } else {
            btn.setText("Bookmark");
            btn.setBackground(new Color(255, 180, 0));
            btn.setForeground(Color.BLACK);
        }
    }
}
