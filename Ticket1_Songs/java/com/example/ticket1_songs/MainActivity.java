package com.example.ticket1_songs;

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

public class MainActivity extends AppCompatActivity {
    public static final String songURL="https://pastebin.com/raw/Vqg3vJDF";
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
                        StringBuilder json=new StringBuilder();
                        HttpURLConnection connection=(HttpURLConnection) new URL(songURL).openConnection();
                       try( BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                            String line;
                            while ((line=reader.readLine())!=null){
                                json.append(line);
                            }
                        }
                        connection.disconnect();


                       if (json!=null){
                           try{
                               JSONObject object=new JSONObject(json.toString());
                               JSONArray array=object.getJSONArray(KEY_JSON);

                               for(int i=0;i<array.length();i++){
                                   JSONObject jsonObject=array.getJSONObject(i);
                                   String title=jsonObject.getString(KEY_SONG_TITLE);
                                   String artist=jsonObject.getString(KEY_ARTIST);
                                   int noOfViews=jsonObject.getInt(KEY_NO_VIEWS);
                                   Date date= DateConverter.fromString(jsonObject.getString(KEY_RELEASE_DATE));

                                   Song song=new Song(title,artist,noOfViews,date);
                                   songs.add(song);
                               }

                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                       }
                        return songs;
                    }
                });

                //ex 5 Toast with messages
                try {
                    threadpool.shutdown();

                    songs=future.get();
                    if(songs.size()==4){
                        Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"FAILURE",Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
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