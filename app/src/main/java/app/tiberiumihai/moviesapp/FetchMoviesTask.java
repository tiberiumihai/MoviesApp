package app.tiberiumihai.moviesapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by sirhuman on 09/06/15.
 */
public class FetchMoviesTask extends AsyncTask<Object, Void, List<Movie>> {

    private String MOVIEDB_API_URL = "api.themoviedb.org";
    private String MOVIEDB_API_KEY = "634ec817df4e0186919097375a618e92";
    private String sortBy = null;
    private Integer page = 1;

    private MovieAdapter movieAdapter = null;

    public FetchMoviesTask(MovieAdapter movieAdapter) {
        this.movieAdapter = movieAdapter;
    }

    public String getMoviesDiscoverRestUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder
                .scheme("http")
                .authority(MOVIEDB_API_URL)
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("api_key", MOVIEDB_API_KEY)
                .appendQueryParameter("page", page.toString());
        if (sortBy != null) {
            builder.appendQueryParameter("sort_by", sortBy);
        }
        return builder.build().toString();
    }

    private List<Movie> getMoviesFromRest() {
        List<Movie> movies = null;

        String moviesUrl = getMoviesDiscoverRestUrl();

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String moviesJson = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are available at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL(moviesUrl);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                moviesJson = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                moviesJson = null;
            } else {
                moviesJson = buffer.toString();

                try {
                    movies = JsonMapping.mapJsonToMovieList(moviesJson);
                } catch (JSONException e) {
                    Log.e("JsonMapping", "Can not map json data to MovieList", e);
                }
            }
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            moviesJson = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        return movies;
    }

    @Override
    protected List<Movie> doInBackground(Object... params) {

        if (params.length > 0) {
            this.sortBy = (String) params[0];
            this.page = (Integer) params[1];
        }

        List<Movie> movies = getMoviesFromRest();

        return movies;
    }

    protected void onPostExecute(List<Movie> movies) {
        if (movies != null) {
            movieAdapter.addMovies(movies);
        }
    }
}