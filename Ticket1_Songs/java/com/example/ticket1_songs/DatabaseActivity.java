package com.example.ticket1_songs;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    Button btnSort;
    ListView lvDb;
    List<Song>songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initComponents();

        SongsDAO dao=SongsDB.getInstance(getApplicationContext()).getSongsDao();
        songs=dao.getSongs();

        ArrayAdapter<Song>adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,songs);
        lvDb.setAdapter(adapter);
    }
    private void initComponents(){
        lvDb=findViewById(R.id.lvDb);
    }
}