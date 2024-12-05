package eu.ase.ro.lab2_1108;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import eu.ase.ro.lab2_1108.network.DownloadCallableTask;
import eu.ase.ro.lab2_1108.network.HttpConnection;
import eu.ase.ro.lab2_1108.util.Movie;
import eu.ase.ro.lab2_1108.util.MovieAdapter;
import parser.JsonParser;

public class JsonParserActivity extends AppCompatActivity {
    ListView lvJson;
    List<Movie> movies=new ArrayList<>();
    private final static String URL = "https://pastebin.com/raw/dTixEYMy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parser2);

        lvJson=findViewById(R.id.lv_json_parser);
        HttpConnection connection=new HttpConnection(URL);
        DownloadCallableTask callableTask=new DownloadCallableTask(connection);

        ExecutorService executorService= Executors.newCachedThreadPool();
        Future<String> threadResult=executorService.submit(callableTask);
        try {
            String result=threadResult.get();
            JsonParser jsonParser=new JsonParser();
            movies=jsonParser.getParseMovieList(result);
            MovieAdapter movieAdapter=new MovieAdapter(getApplicationContext(),R.layout.newlayout,movies,getLayoutInflater());
            lvJson.setAdapter(movieAdapter);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}