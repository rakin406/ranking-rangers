package com.rakin.app.pages;

import com.rakin.app.components.*;
import info.movito.themoviedbapi.model.core.Movie;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Bookmarks extends JFrame {
    private Font f1;

    public Bookmarks(List<Movie> movies) {
        setTitle("Bookmarks");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        f1 = new Font("Segoe UI", Font.PLAIN, 16);

        // Header
        JLabel header = new JLabel("Bookmarks", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setForeground(Color.WHITE);
        header.setOpaque(true);
        header.setBackground(Color.DARK_GRAY);
        header.setBorder(BorderFactory.createEmptyBorder(14, 0, 14, 0));
        add(header, BorderLayout.NORTH);

        // List panel
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.DARK_GRAY);

        if (movies == null || movies.isEmpty()) {
            JLabel empty = new JLabel("No bookmarks yet.", SwingConstants.CENTER);
            empty.setFont(f1);
            empty.setForeground(Color.LIGHT_GRAY);
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            empty.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            listPanel.add(empty);
        } else {
            for (int i = 0; i < movies.size(); i++) {
                Movie m = movies.get(i);

                JPanel row = new JPanel(new BorderLayout());
                row.setBackground(i % 2 == 0 ? new Color(60, 60, 60) : new Color(50, 50, 50));
                row.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
                row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

                // Number
                JLabel number = new JLabel((i + 1) + ".");
                number.setFont(new Font("Segoe UI", Font.BOLD, 16));
                number.setForeground(Color.LIGHT_GRAY);
                number.setPreferredSize(new Dimension(40, 30));

                // Title
                JLabel title = new JLabel(m.getTitle());
                title.setFont(f1);
                title.setForeground(Color.WHITE);

                // Release year (if available)
                String releaseDate = m.getReleaseDate();
                String year = (releaseDate != null && releaseDate.length() >= 4)
                    ? releaseDate.substring(0, 4)
                    : "N/A";
                JLabel yearLabel = new JLabel(year);
                yearLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                yearLabel.setForeground(Color.LIGHT_GRAY);
                yearLabel.setHorizontalAlignment(SwingConstants.RIGHT);

                JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
                left.setOpaque(false);
                left.add(number);
                left.add(title);

                row.add(left, BorderLayout.CENTER);
                row.add(yearLabel, BorderLayout.EAST);

                listPanel.add(row);

                // Divider
                JSeparator sep = new JSeparator();
                sep.setForeground(new Color(80, 80, 80));
                sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
                listPanel.add(sep);
            }
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }
}
