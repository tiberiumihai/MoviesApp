package app.tiberiumihai.moviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirhuman on 09/06/15.
 */
public class MovieAdapter extends BaseAdapter {

    private Context context;
    private List<Movie> movies = new ArrayList<Movie>();
    LayoutInflater inflater;

    private String MOVIE_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private String MOVIE_IMAGE_DEFAULT_SIZE = "w185";

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies.addAll(movies);
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addMovies(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return;
        }
        this.movies.addAll(movies);
        this.notifyDataSetChanged();
    }

    public void reset() {
        this.movies.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movies.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_movie_item, null);
        }

        Movie movie = movies.get(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.moviePoster);
        Picasso
            .with(context)
            .load(MOVIE_IMAGE_BASE_URL + MOVIE_IMAGE_DEFAULT_SIZE + movie.getPosterPath())
            .into(imageView);

        return convertView;
    }
}
