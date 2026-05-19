package com.rangers.app.util;

import com.rangers.app.model.BookmarkedMovie;
import java.io.*;
import java.util.*;

public class BookmarkManager {
    private static final String FILE_PATH = "bookmarks.txt";
    private static final String DELIMITER = "|";

    // Read
    public static synchronized List<BookmarkedMovie> getBookmarks(String username) {
        List<BookmarkedMovie> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists())
            return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 4);
                if (parts.length == 4 && parts[0].equals(username)) {
                    list.add(
                        new BookmarkedMovie(Integer.parseInt(parts[1].trim()), parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static synchronized boolean isBookmarked(String username, int movieId) {
        return getBookmarks(username).stream().anyMatch(m -> m.getId() == movieId);
    }

    // Write
    public static synchronized void addBookmark(String username, BookmarkedMovie movie) {
        if (isBookmarked(username, movie.getId()))
            return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + DELIMITER + movie.getId() + DELIMITER + movie.getTitle()
                + DELIMITER + movie.getReleaseDate());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void removeBookmark(String username, int movieId) {
        File file = new File(FILE_PATH);
        if (!file.exists())
            return;

        List<String> remaining = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 4);
                boolean isTarget = parts.length == 4 && parts[0].equals(username)
                    && Integer.parseInt(parts[1].trim()) == movieId;
                if (!isTarget)
                    remaining.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : remaining) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
