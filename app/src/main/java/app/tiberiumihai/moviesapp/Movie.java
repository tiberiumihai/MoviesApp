package app.tiberiumihai.moviesapp;

/**
 * Created by sirhuman on 09/06/15.
 */
public class Movie {
    private boolean adult;
    private String backdropPath;
    private int id;
    private String originalTitle;
    private String release_date;
    private String posterPath;
    private double popularity;
    private String title;
    private int voteAverage;
    private int voteCount;

    public Movie(boolean adult, String backdropPath, int id, String originalTitle, String release_date, String posterPath, double popularity, String title, int voteAverage, int voteCount) {
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.id = id;
        this.originalTitle = originalTitle;
        this.release_date = release_date;
        this.posterPath = posterPath;
        this.popularity = popularity;
        this.title = title;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
