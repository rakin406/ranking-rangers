package com.rakin.app.pages;

import com.rakin.app.App;
import com.rakin.app.components.*;
import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.core.*;
import info.movito.themoviedbapi.tools.model.time.TimeWindow;
import io.github.cdimascio.dotenv.Dotenv;
import java.awt.*;
import javax.swing.*;

public class Home extends JFrame {
    private static Dotenv dotenv;
    private static TmdbApi tmdbApi;
    private JScrollPane scroll;
    private NavigationBar navBar;

    static {
        dotenv = Dotenv.load();
        tmdbApi = new TmdbApi(dotenv.get("TMDB_ACCESS_TOKEN"));
    }

    public Home(String username) {
        setTitle("Ranking Rangers");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        navBar = new NavigationBar(username);
        this.add(navBar, BorderLayout.NORTH);

        try {
            // Fetch trending movies
            MovieResultsPage trendingMovies =
                tmdbApi.getTrending().getMovies(TimeWindow.DAY, "en-US");

            MovieList movieList = new MovieList(trendingMovies.getResults());

            // Make it scrollable in case content overflows
            scroll = new JScrollPane(movieList);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroll.setBorder(null);

            this.add(scroll, BorderLayout.CENTER);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
