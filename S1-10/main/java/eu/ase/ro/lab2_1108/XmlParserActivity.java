package eu.ase.ro.lab2_1108;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import eu.ase.ro.lab2_1108.network.DownloadCallableTask;
import eu.ase.ro.lab2_1108.network.HttpConnection;
import eu.ase.ro.lab2_1108.network.XMLCallableTask;
import eu.ase.ro.lab2_1108.util.Movie;
import eu.ase.ro.lab2_1108.util.MovieAdapter;

public class XmlParserActivity extends AppCompatActivity {
    ListView listViewXML;
    List<Movie> movies=new ArrayList<>();

    public final static String URLXml = "https://pastebin.com/raw/BUXXtTfx";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parser);
        listViewXML = findViewById(R.id.lvXmlParser);

        HttpConnection httpConnection = new HttpConnection(URLXml);
        XMLCallableTask callableTaskXML = new XMLCallableTask(httpConnection);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(callableTaskXML);
        try {
            String result = submit.get();
            MovieAdapter movieArrayAdapter = new MovieAdapter(getApplicationContext(), R.layout.newlayout, movies, getLayoutInflater());
            listViewXML.setAdapter(movieArrayAdapter);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}