package eu.ase.ro.lab2_1108.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import eu.ase.ro.lab2_1108.R;

public class MovieAdapter extends ArrayAdapter<Movie> {
    TextView tvTitle,tvProfit,tvPlatform,tvGenre,tvDate;
    private int resource;
    private List<Movie> movieList;
    private LayoutInflater layoutInflater;


    public MovieAdapter(@NonNull Context context, int resource, @NonNull List<Movie> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.resource=resource;
        this.movieList=objects;
        this.layoutInflater= layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=layoutInflater.inflate(this.resource,parent,false);
        initializeVisualComponent(row);
        Movie movie=movieList.get(position);
        populateVisualComponent(movie);
        return row;
    }

    void initializeVisualComponent(View row){

        tvTitle= row.findViewById(R.id.tvMovieTitle);
        tvDate=row.findViewById(R.id.tvReleaseDate);
        tvGenre=row.findViewById(R.id.tvMovieGenre);
        tvPlatform=row.findViewById(R.id.tvPlatform);
        tvProfit=row.findViewById(R.id.tvProfit);

    }
    void populateVisualComponent(Movie movie){
        tvTitle.setText(movie.getTitle());
        tvDate.setText(DateConverter.fromDate(movie.getReleaseDate()));
        tvProfit.setText(String.valueOf(movie.getProfit()));
        tvGenre.setText(movie.getMovieGenre());
        tvPlatform.setText(movie.getPlatform());
    }
}
