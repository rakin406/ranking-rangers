package com.rakin.app.pages;

import com.rakin.app.App;
import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.core.*;
import info.movito.themoviedbapi.tools.model.time.TimeWindow;
import io.github.cdimascio.dotenv.Dotenv;
import java.awt.*;
import javax.swing.*;

public class Home extends JPanel {
    private static Dotenv dotenv;
    private static TmdbApi tmdbApi;
    private Font f1;

    static {
        dotenv = Dotenv.load();
        tmdbApi = new TmdbApi(dotenv.get("TMDB_ACCESS_TOKEN"));
    }

    public Home(App app) {
        this.setLayout(null);

        // Fonts
        f1 = new Font("Ranking Rangers", Font.BOLD, 48);

        try {
            // Fetch trending movies
            MovieResultsPage trendingMovies =
                tmdbApi.getTrending().getMovies(TimeWindow.DAY, "en-US");

            // Display movies
            for (Movie movie : trendingMovies.getResults()) {
                String title = movie.getTitle();
                String overview = movie.getOverview();
                String posterPath = movie.getPosterPath();
                String posterURL = "https://image.tmdb.org/t/p/w500" + posterPath;
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
