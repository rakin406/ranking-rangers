package com.rakin.app.pages;

import com.rakin.app.model.BookmarkedMovie;
import com.rakin.app.util.BookmarkManager;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Bookmarks extends JFrame {
    private final String username;
    private JPanel listPanel;
    private JScrollPane scrollPane;
    private final Font titleFont = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font numberFont = new Font("Segoe UI", Font.BOLD, 16);
    private final Font yearFont = new Font("Segoe UI", Font.PLAIN, 14);

    public Bookmarks(String username) {
        this.username = username;

        setTitle("Bookmarks");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // don't kill the whole app
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Bookmarks", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setForeground(Color.WHITE);
        header.setOpaque(true);
        header.setBackground(Color.DARK_GRAY);
        header.setBorder(BorderFactory.createEmptyBorder(14, 0, 14, 0));
        add(header, BorderLayout.NORTH);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.DARK_GRAY);

        scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        refreshList();
    }

    // ── Build / rebuild the movie rows ──────────────────────────────────────

    private void refreshList() {
        listPanel.removeAll();

        List<BookmarkedMovie> movies = BookmarkManager.getBookmarks(username);

        if (movies.isEmpty()) {
            JLabel empty = new JLabel("No bookmarks yet.", SwingConstants.CENTER);
            empty.setFont(titleFont);
            empty.setForeground(Color.LIGHT_GRAY);
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            empty.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            listPanel.add(empty);
        } else {
            for (int i = 0; i < movies.size(); i++) {
                listPanel.add(buildRow(movies.get(i), i));
                JSeparator sep = new JSeparator();
                sep.setForeground(new Color(80, 80, 80));
                sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
                listPanel.add(sep);
            }
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private JPanel buildRow(BookmarkedMovie movie, int index) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(index % 2 == 0 ? new Color(60, 60, 60) : new Color(50, 50, 50));
        row.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        // Number + title (left side)
        JLabel number = new JLabel((index + 1) + ".");
        number.setFont(numberFont);
        number.setForeground(Color.LIGHT_GRAY);
        number.setPreferredSize(new Dimension(40, 30));

        JLabel title = new JLabel(movie.getTitle());
        title.setFont(titleFont);
        title.setForeground(Color.WHITE);

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        left.setOpaque(false);
        left.add(number);
        left.add(title);

        // Year + delete button (right side)
        String releaseDate = movie.getReleaseDate();
        String year = (releaseDate != null && releaseDate.length() >= 4)
            ? releaseDate.substring(0, 4)
            : "N/A";

        JLabel yearLabel = new JLabel(year);
        yearLabel.setFont(yearFont);
        yearLabel.setForeground(Color.LIGHT_GRAY);

        JButton deleteBtn = new JButton("✕");
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        deleteBtn.setForeground(new Color(220, 80, 80));
        deleteBtn.setBackground(new Color(70, 70, 70));
        deleteBtn.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        deleteBtn.setFocusPainted(false);
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteBtn.setToolTipText("Remove bookmark");
        deleteBtn.addActionListener(e -> {
            BookmarkManager.removeBookmark(username, movie.getId());
            refreshList();
        });

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        right.setOpaque(false);
        right.add(yearLabel);
        right.add(deleteBtn);

        row.add(left, BorderLayout.CENTER);
        row.add(right, BorderLayout.EAST);
        return row;
    }
}
