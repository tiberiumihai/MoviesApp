package app.tiberiumihai.moviesapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    MovieAdapter movieAdapter = null;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_main, container, false);

        movieAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>());

        FetchMoviesTask movieFetchTask = new FetchMoviesTask(movieAdapter);
        movieFetchTask.execute();

        GridView movies = (GridView) viewRoot.findViewById(R.id.gridview_movies);
        movies.setAdapter(movieAdapter);

        return viewRoot;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_most_popular) {
            // load
            movieAdapter.reset();
            FetchMoviesTask movieFetchTask = new FetchMoviesTask(movieAdapter);
            movieFetchTask.execute("popularity.desc", 1);
            return true;
        } else if (id == R.id.action_highest_rated) {
            // load
            movieAdapter.reset();
            FetchMoviesTask movieFetchTask = new FetchMoviesTask(movieAdapter);
            movieFetchTask.execute("vote_average.desc", 1);
            return true;
        } else if (id == R.id.action_settings) {
            return true;
        }

            return super.onOptionsItemSelected(item);
    }
}
