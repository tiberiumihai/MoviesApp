package app.tiberiumihai.moviesapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_main, container, false);

        FetchMoviesTask task = new FetchMoviesTask();
        task.execute();

        Movie[] movieArray = {
                new Movie(
                        false,
                        "/cKw3HY835PMp6bzse3LMivIY5Nl.jpg",
                        1884,
                        "The Ewok Adventure",
                        "1984-11-25",
                        "/x2nKP0FCJwNLHgCyUI1cL8bF6nL.jpg",
                        0.72905031478,
                        "The Ewok Adventure",
                        10,
                        4),
                new Movie(
                        false,
                        "/s0LjxgmzKdTny2CiuHNBjV8urmf.jpg",
                        1537,
                        "Changing Lanes",
                        "2002-04-07",
                        "/bbINOtOFyQFAtaKo0fdMKXB1Og5.jpg",
                        1.1399252536936,
                        "Changing Lanes",
                        10,
                        1
                )
        };

        GridView movies = (GridView) viewRoot.findViewById(R.id.gridview_movies);
        movies.setAdapter(new MovieAdapter(getActivity(), movieArray));

        return viewRoot;
    }

    class FetchMoviesTask extends AsyncTask<String, Void, Void> {

        private String MOVIE_DISCOVER_REST_BASE_URL = "api.themoviedb.org";
        private String MOVIEDB_API_KEY = "634ec817df4e0186919097375a618e92";
        private String sortBy = null;

        public FetchMoviesTask() {
        }

        public String getMoviesDiscoverRestUrl() {
            Uri.Builder builder = new Uri.Builder();
            builder
                .scheme("http")
                .authority(MOVIE_DISCOVER_REST_BASE_URL)
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("api_key", MOVIEDB_API_KEY);
            if (sortBy != null) {
                builder.appendQueryParameter("sort_by", sortBy);
            }
            return builder.build().toString();
        }

        @Override
        protected Void doInBackground(String... params) {

            if (params.length > 0) {
                this.sortBy = params[0];
            }

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
                }
                moviesJson = buffer.toString();
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attempting
                // to parse it.
                moviesJson = null;
            } finally{
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

            if (moviesJson != null) {
                Log.e("MOVIEEEES", moviesJson);
            } else {
                Log.e("URL", moviesUrl);
            }

            return null;
        }
    }
}
