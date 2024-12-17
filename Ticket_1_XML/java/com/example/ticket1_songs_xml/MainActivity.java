package com.example.ticket1_songs_xml;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
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
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
    public static final String songsURL = "https://pastebin.com/raw/qK0n6JBy";
    Button btnSave, btnSync, btnView;
    List<Song> songs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService threadpool = Executors.newCachedThreadPool();
                Future<List<Song>> future = threadpool.submit(new Callable<List<Song>>() {
                    @Override
                    public List<Song> call() throws Exception {
                        List<Song> songs = new ArrayList<>();
                        HttpURLConnection conn = null;


                        try {
                            conn = (HttpURLConnection) new URL(songsURL).openConnection();
                            InputStream inputStream = conn.getInputStream();
                            XmlPullParser parser = Xml.newPullParser();
                            parser.setInput(inputStream, "UTF-8");


                            String text = "";
                            Song song = null;


                            int eventType = parser.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                String tagName = parser.getName();
                                switch (eventType) {
                                    case XmlPullParser.START_TAG:
                                        if ("song".equalsIgnoreCase(tagName)) {
                                            song = new Song();
                                        }
                                        break;
                                    case XmlPullParser.TEXT:
                                        text = parser.getText();
                                        break;
                                    case XmlPullParser.END_TAG:
                                        if (song == null) break;
                                        if ("song".equalsIgnoreCase(tagName)) {
                                            songs.add(song);
                                        } else if ("songTitle".equalsIgnoreCase(tagName)) {
                                            song.setSongTitle(text);
                                        } else if ("artist".equalsIgnoreCase(tagName)) {
                                            song.setArtist(text);
                                        } else if ("noOfViews".equalsIgnoreCase(tagName)) {
                                            song.setNoOfViews(Integer.parseInt(text));
                                        } else if ("songReleaseDate".equalsIgnoreCase(tagName)) {
                                            song.setSongReleaseDate(DateConverter.fromString(text));
                                        }
                                        break;
                                }
                                eventType = parser.next();
                            }
                        } catch (IOException | XmlPullParserException e) {
                            Log.e("Error", "Error parsing XML: " + e.getMessage());
                        } finally {
                            if (conn != null) {
                                conn.disconnect(); // Ensure connection is always closed
                            }
                        }
                        return songs;
                    }
                });


                try {
                    songs = future.get();
                    threadpool.shutdown();
                    if (songs.size() == 2) {
                        Toast.makeText(getApplicationContext(), "SUCCESS, artist name: " + songs.get(0).getArtist(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "FAILURE", Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException | InterruptedException e) {

                    throw new RuntimeException(e);
                }
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), DatabaseActivity.class);
                startActivity(intent);
            }
        });

    }


    private void initComponents() {
        btnSave = findViewById(R.id.btnSave);
        btnSync = findViewById(R.id.btnSync);
        btnView = findViewById(R.id.btnView);
    }
}
