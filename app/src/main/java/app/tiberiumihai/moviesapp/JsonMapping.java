package app.tiberiumihai.moviesapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirhuman on 09/06/15.
 */
public class JsonMapping {
    private JsonMapping() { }

    public static List<Movie> mapJsonToMovieList(String moviesJson) throws JSONException {
        List<Movie> movies = new ArrayList<Movie>();
        JSONObject jsonObject = new JSONObject(moviesJson);
        JSONArray moviesObject = jsonObject.getJSONArray("results");
        for (int i = 0; i < moviesObject.length(); i++) {
            Movie movie = new Movie();
            JSONObject movieObject = moviesObject.getJSONObject(i);
            movie.setAdult(movieObject.getBoolean("adult"));
            movie.setBackdropPath(movieObject.getString("backdrop_path"));
            movie.setId(movieObject.getInt("id"));
            movie.setOriginalTitle(movieObject.getString("original_title"));
            movie.setRelease_date(movieObject.getString("release_date"));
            movie.setPosterPath(movieObject.getString("poster_path"));
            movie.setPopularity(movieObject.getDouble("popularity"));
            movie.setTitle(movieObject.getString("title"));
            movie.setVoteAverage(movieObject.getInt("vote_average"));
            movie.setVoteCount(movieObject.getInt("vote_count"));
            movies.add(movie);
        }
        return movies;
    }
}
