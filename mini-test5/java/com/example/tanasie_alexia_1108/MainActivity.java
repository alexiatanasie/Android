package com.example.tanasie_alexia_1108;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    List<Song> songs=new ArrayList<>();
    private ListView lv;

    public static final String songURL="https://pastebin.com/raw/Vqg3vJDF";

    private static final String KEY_JSON="songs";
    private static final String KEY_SONG_TITLE="songTitle";
    private static final String KEY_ARTIST="artist";
    private static final String KEY_NO_VIEWS="noOfViews";
    private static final String KEY_RELEASE_DATE="songReleaseDate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.tanasie_alexia_listView);
        showSongs();
    }
    private void showSongs(){
        ExecutorService threadpool= Executors.newCachedThreadPool();
        Future<List<Song>> future=threadpool.submit(new Callable<List<Song>>() {
            @Override
            public List<Song> call() throws Exception {
                HttpConnection connection=new HttpConnection(songURL);
                String json=connection.readFromHttp();
                List<Song> songs = new ArrayList<>();
                try{
                    JSONObject object=new JSONObject(json);
                    JSONArray array=object.getJSONArray(KEY_JSON);
                    for(int i=0;i<array.length();i++){
                        JSONObject jsonObject=array.getJSONObject(i);
                        String title = jsonObject.getString(KEY_SONG_TITLE);
                        String artist = jsonObject.getString(KEY_ARTIST);
                        int views = jsonObject.getInt(KEY_NO_VIEWS);
                        Date date = DateConverter.fromString(jsonObject.getString(KEY_RELEASE_DATE));

                        Song song = new Song(title, artist, views, date);
                        songs.add(song);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                return songs;
            }
        });
        try{
            songs=future.get();
            ArrayAdapter<Song> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, songs);
            lv.setAdapter(adapter);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}