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

import eu.ase.ro.lab7.network.HttpConnection;
import eu.ase.ro.lab7.network.XmlCallableTask;
import eu.ase.ro.lab7.utils.Movie;
import eu.ase.ro.lab7.utils.MovieAdapter;

public class XmlParserActivity extends AppCompatActivity {
    ListView lvXML;
    String urlXML = "https://pastebin.com/raw/BUXXtTfx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parser);

        lvXML = findViewById(R.id.lv_xml_parser);

        HttpConnection httpConnection = new HttpConnection(urlXML);
        XmlCallableTask xmlCallableTask = new XmlCallableTask(httpConnection);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<List<Movie>> submit = executorService.submit(xmlCallableTask);
        try {
            List<Movie> result = submit.get();
            MovieAdapter movieArrayAdapter = new MovieAdapter(getApplicationContext(), R.layout.lv_movies_row, result, getLayoutInflater());
            lvXML.setAdapter(movieArrayAdapter);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}