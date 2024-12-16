package com.example.ticket1_songs_xml;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    public static final String songURL="https://pastebin.com/raw/qK0n6JBy";
    Button btnSave,btnSync,btnView;
    List<Song> songs=new ArrayList<>();

    private static final String KEY_JSON="songs";
    private static final String KEY_SONG_TITLE="songTitle";
    private static final String KEY_ARTIST="artist";
    private static final String KEY_NO_VIEWS="noOfViews";
    private static final String KEY_RELEASE_DATE="songReleaseDate";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();


        //ex 4 Sync data btn ,getting over the network from ex 3 with collection
        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService threadpool= Executors.newCachedThreadPool();
                Future<List<Song>> future=threadpool.submit(new Callable<List<Song>>() {
                    @Override
                    public List<Song> call() throws Exception {
                        List<Song>songs=new ArrayList<>();
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(new URL(songURL).openStream());
                        document.getDocumentElement().normalize();

                        NodeList nodeList = document.getElementsByTagName("song");

                        for (int i = 0; i < nodeList.getLength(); i++) {
                            Node node = nodeList.item(i);

                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element element = (Element) node;

                                String title = element.getElementsByTagName("songTitle").item(0).getTextContent();
                                String artist = element.getElementsByTagName("artist").item(0).getTextContent();
                                int noOfViews = Integer.parseInt(element.getElementsByTagName("noOfViews").item(0).getTextContent());
                                Date date = DateConverter.fromString(element.getElementsByTagName("songReleaseDate").item(0).getTextContent());

                                Song song = new Song(title, artist, noOfViews, date);
                                songs.add(song);
                            }
                        }
                        return songs;
                    }
                });

                //ex 5 Toast with messages
                try {
                    threadpool.shutdown();

                    songs=future.get();
                    if(songs.size()==2){
                        Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"FAILURE",Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        //ex 6 save btn
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongsDAO dao=SongsDB.getInstance(getApplicationContext()).getSongsDao();
                for(Song song: songs){
                    dao.insertSong(song);
                }
                Toast.makeText(MainActivity.this,"database successfully inserted",Toast.LENGTH_LONG).show();
            }
        });
        //btn View (7) to open a new activity
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), DatabaseActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initComponents(){
        btnSave=findViewById(R.id.btnSave);
        btnSync=findViewById(R.id.btnSync);
        btnView=findViewById(R.id.btnView);
    }
}