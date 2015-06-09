package app.tiberiumihai.moviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by sirhuman on 09/06/15.
 */
public class MovieAdapter extends BaseAdapter {

    private Context context;
    private Movie[] movies;
    LayoutInflater inflater;

    private String MOVIE_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private String MOVIE_IMAGE_DEFAULT_SIZE = "w185";

    public MovieAdapter(Context context, Movie[] movies) {
        this.context = context;
        this.movies = movies;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movies.length;
    }

    @Override
    public Object getItem(int position) {
        return movies[position];
    }

    @Override
    public long getItemId(int position) {
        return movies[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_movie_item, null);
        }

        Movie movie = movies[position];

        ImageView imageView = (ImageView) convertView.findViewById(R.id.moviePoster);
        Picasso
            .with(context)
            .load(MOVIE_IMAGE_BASE_URL + MOVIE_IMAGE_DEFAULT_SIZE + movie.getPosterPath())
            .into(imageView);

        TextView textView = (TextView) convertView.findViewById(R.id.movieTitle);
        textView.setText(movie.getTitle());

        return convertView;
    }
}
