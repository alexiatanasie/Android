package eu.ase.ro.lab7;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import eu.ase.ro.lab7.network.DownloadCallableTask;
import eu.ase.ro.lab7.network.HttpConnection;
import eu.ase.ro.lab7.network.XmlCallableTask;
import eu.ase.ro.lab7.parsers.JsonParser;
import eu.ase.ro.lab7.utils.Movie;
import eu.ase.ro.lab7.utils.MovieAdapter;

public class JsonParserActivity extends AppCompatActivity {
    ListView lvJson;
    List<Movie> movies;
    String urlJson = "https://pastebin.com/raw/dTixEYMy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parser);

        lvJson = findViewById(R.id.lv_json);

        HttpConnection httpConnection = new HttpConnection(urlJson);
        DownloadCallableTask downloadCallableTask = new DownloadCallableTask(httpConnection);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(downloadCallableTask);
        try {
            String result = submit.get();
            JsonParser jsonParser = new JsonParser();
            movies = jsonParser.getMoviesFromJson(result);
            MovieAdapter movieArrayAdapter = new MovieAdapter(getApplicationContext(), R.layout.lv_movies_row, movies, getLayoutInflater());
            lvJson.setAdapter(movieArrayAdapter);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}