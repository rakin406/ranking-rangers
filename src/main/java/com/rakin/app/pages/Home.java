package com.rakin.app.pages;

import com.rakin.app.App;
import com.rakin.app.components.MovieList;
import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.core.*;
import info.movito.themoviedbapi.tools.model.time.TimeWindow;
import io.github.cdimascio.dotenv.Dotenv;
import java.awt.*;
import javax.swing.*;

public class Home extends JPanel {
    private static Dotenv dotenv;
    private static TmdbApi tmdbApi;

    static {
        dotenv = Dotenv.load();
        tmdbApi = new TmdbApi(dotenv.get("TMDB_ACCESS_TOKEN"));
    }

    public Home(App app) {
        this.setLayout(new BorderLayout());

        try {
            // Fetch trending movies
            MovieResultsPage trendingMovies =
                tmdbApi.getTrending().getMovies(TimeWindow.DAY, "en-US");

            MovieList movieList = new MovieList(trendingMovies.getResults());

            // Make it scrollable in case content overflows
            JScrollPane scroll = new JScrollPane(movieList);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroll.setBorder(null);

            this.add(scroll, BorderLayout.CENTER);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
