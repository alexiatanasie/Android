package com.example.schoolcustomadapter1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<School>schools=new ArrayList<>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lvMainActivity);

        // Create a list of schools
        List<School> schoolList = new ArrayList<>();
        schoolList.add(new School("Mathematics", new Date(), 9.5f, true));
        schoolList.add(new School("History", new Date(), 8.0f, false));
        schoolList.add(new School("Biology", new Date(), 7.5f, true));

        // Set up the adapter
        SchoolAdapter adapter = new SchoolAdapter(this, R.layout.school_layout, schoolList, getLayoutInflater());
        listView.setAdapter(adapter);
    }
}