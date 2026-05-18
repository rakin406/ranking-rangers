# 🎬 Ranking Rangers

A Java Swing desktop application for discovering popular movies, reading their descriptions, and managing a personal watchlist — all with a secure login system.

---

## Features

- **Popular Movies Feed** — Browse a curated list of trending and top-rated movies pulled live from The Movie Database (TMDB) API.
- **Movie Details** — View detailed descriptions, ratings, and metadata for each movie.
- **Bookmarks** — Save your favourite movies to a personal bookmark list for quick access later.
- **User Authentication** — Register and log in with a personal account to keep your bookmarks tied to your profile.
- **Modern UI** — Built with FlatLaf for a clean, modern look and feel on top of Java Swing.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| UI Framework | Java Swing + [FlatLaf](https://www.formdev.com/flatlaf/) 3.7.1 |
| Movie Data | [themoviedbapi](https://github.com/c-eg/themoviedbapi) 2.6.0 |
| Environment Config | [dotenv-java](https://github.com/cdimascio/dotenv-java) 3.2.0 |
| Build Tool | Apache Maven |

---

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- A free [TMDB API](https://www.themoviedb.org/settings/api) account and access token

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/rakin406/ranking-rangers.git
cd ranking-rangers
```

### 2. Set up environment variables

Copy the example env file and fill in your TMDB access token:

```bash
cp .env.example .env
```

Open `.env` and replace the placeholder:

```env
TMDB_ACCESS_TOKEN=your-tmdb-access-token
```

You can obtain a free access token from the [TMDB API settings page](https://www.themoviedb.org/settings/api).

### 3. Build the project

```bash
mvn package
```

This produces a self-contained JAR in the `target/` directory.

### 4. Run the application

```bash
java -jar target/ranking-rangers-1.0-SNAPSHOT-jar-with-dependencies.jar
```

---

## Usage

1. **Register / Log In** — Create an account or sign in with existing credentials on the login screen.
2. **Browse Movies** — The home screen displays a list of currently popular movies fetched from TMDB.
3. **View Details** — Click on any movie to see its full description, release date, and rating.
4. **Bookmark** — Hit the bookmark button on any movie card to save it to your personal list.
5. **View Bookmarks** — Access all your saved movies from the bookmarks section.

---

## Project Structure

```
ranking-rangers/
├── src/
│   └── main/
│       └── java/
│           └── com/rangers/app/   # Application source code
├── .env.example                 # Environment variable template
├── pom.xml                      # Maven build configuration
└── README.md
```

---

## License

This project was developed as a university project. Feel free to use it as a reference or learning resource.
