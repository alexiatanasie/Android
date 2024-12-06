package com.example.tanasie_alexia_1108;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Book> books = new ArrayList<>();
        books.add(new Book("Ion", "Rebreanu", 29.99f, DateConverter.fromString("10-11-1900")));
        books.add(new Book("Moara cu noroc", "Slavici", 27.49f, DateConverter.fromString("10-11-1900")));
        books.add(new Book("Heidi", "Han", 10.99f, DateConverter.fromString("10-11-1900")));

        listView = findViewById(R.id.lv_tanasie_alexia);

        BookAdapter adapter = new BookAdapter(this, R.layout.newlayout_tanasiealexia, books, getLayoutInflater());
      listView.setAdapter(adapter);
    }

}