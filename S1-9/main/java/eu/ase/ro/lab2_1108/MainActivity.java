package eu.ase.ro.lab2_1108;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.lab2_1108.util.DateConverter;
import eu.ase.ro.lab2_1108.util.Movie;

public class MainActivity extends AppCompatActivity {
    public static final String USER_INFO_KEY = "userAboutKey";
    public static final String USER_NAME_KEY = "userNameKey";
    public static final String USER_AGE_KEY = "userAgeKey";
    //declare an FloatingActionButton object
    FloatingActionButton fab;
    ActivityResultLauncher<Intent> launcher;
    ListView lvMovies;
    List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMovies = findViewById(R.id.lvMovies);
        ArrayAdapter<Movie> movieAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, movies);
        lvMovies.setAdapter(movieAdapter);

        launcher = registerLauncher();
        initComponents();

        //initialize an object
        fab = findViewById(R.id.fabAddMovie);
        fab.setOnClickListener(new View.OnClickListener() {
            //onClick method -> write the logic that will happen at button click
            @Override
            public void onClick(View view) {
                //initialize an Intent object
                Intent intent = new Intent(getApplicationContext(), AddMovieActivity.class);
                //start a new activity
               launcher.launch(intent);
            }
        });

    }

    /** In order to be able to display a simple menu, it is mandatory to attach an ActionBar to the screen
     * The simplest way to do this is to edit the app theme from themes.xml
     * and add "Theme.MaterialComponents.DayNight.DarkActionBar" as parent theme
     * */

    //attach the menu from the resource files to the toolbar of the main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //method that will implement the logic associated to pressing each option menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //get the menu option based on the associated id from the menu.xml file
        if(item.getItemId() == R.id.menu_option_1) {
            String aboutInfo = "These are the user's info";
            String userName = "userApp";
            int userAge = 22;

            Intent intent = new Intent(getApplicationContext(),
                    AboutActivity.class);
            intent.putExtra(USER_INFO_KEY, aboutInfo);
            intent.putExtra(USER_NAME_KEY, userName);
            intent.putExtra(USER_AGE_KEY, userAge);
            startActivity(intent);

        } else if(item.getItemId() == R.id.menu_option_2) {
            Intent intent=new Intent(getApplicationContext(),NetworkActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.menu_option_3) {
            Intent json=new Intent(getApplicationContext(),JsonParserActivity.class);
            startActivity(json);
        }
        return true;
    }

    private ActivityResultLauncher<Intent> registerLauncher() {
        ActivityResultCallback<ActivityResult> callback = getCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                callback);
    }

    private ActivityResultCallback<ActivityResult> getCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Movie movie = (Movie) result.getData()
                            .getSerializableExtra(AddMovieActivity.MOVIE_KEY);
                    movies.add(movie);
                    ArrayAdapter movieAdapter = (ArrayAdapter) lvMovies.getAdapter();
                    movieAdapter.notifyDataSetChanged();

                }

            }
        };
    }
    private void initComponents(){
//initialize an object
        fab = findViewById(R.id.fabAddMovie);
        lvMovies = findViewById(R.id.lvMovies);

//        Movie movie = new Movie("Bohemian Rapsody", DateConverter.fromString("24-10-2018"), 67, "Drama", "Netflix");
//        movies.add(movie);

        //initialize an array adapter using a predefined layout
        //ArrayAdapter<Movie> movieArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, movies);

        //initialize a custom array adapter
        MovieAdapter movieArrayAdapter = new MovieAdapter(getApplicationContext(), R.layout.newlayout, movies, getLayoutInflater());
        lvMovies.setAdapter(movieArrayAdapter);
    }
}
