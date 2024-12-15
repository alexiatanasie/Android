package eu.ase.ro.lab7;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eu.ase.ro.lab7.database.MovieDB;
import eu.ase.ro.lab7.utils.Cinema;
import eu.ase.ro.lab7.utils.DateConverter;
import eu.ase.ro.lab7.utils.Movie;

public class DatabaseActivity extends AppCompatActivity {

    ListView lv;
    List<Movie> movies=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

       lv=findViewById(R.id.lvDb);
        ArrayAdapter<Movie>adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,movies);
        lv.setAdapter(adapter);

        Movie m1=new Movie("Harry Potter and the Philosopher's Stone", DateConverter.fromString("16-11-2001"),974,"Fantasy","hbo");
        Movie m2=new Movie("Zodiac", DateConverter.fromString("02-02-2007"),84,"drama","netflix");
        Movie m3=new Movie("1917", DateConverter.fromString("25-12-2019"),27,"war film","netflix");

        insertMovie(m1);
        insertMovie(m2);
        insertMovie(m3);
        loadMovies();

    }
    private void insertMovie(Movie movie){
        MovieDB movieDB=MovieDB.getInstance(getApplicationContext());

        Random random=new SecureRandom();
        Cinema cinema = new Cinema(random.nextInt(), "CinemaCity", "AFI Cotroceni", 14);
        movie.setIdCinema(cinema.getId());

        movieDB.getCinemaDao().insert(cinema);
        movieDB.getMovieDao().insert(movie);

    }
    private void loadMovies(){
        MovieDB movieDB=MovieDB.getInstance(getApplicationContext());
        List<Movie>movieList=movieDB.getMovieDao().getAllMovies();
        movies.addAll(movieList);
        ArrayAdapter adapter= (ArrayAdapter) lv.getAdapter();
        adapter.notifyDataSetChanged();
    }

}