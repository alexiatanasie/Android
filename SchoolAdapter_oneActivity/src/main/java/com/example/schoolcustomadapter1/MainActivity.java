package com.example.schoolcustomadapter1;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<School> schools = new ArrayList<>();
        schools.add(new School("Math", DateConverter.fromString("10-12-2023"), 9.5f, true));
        schools.add(new School("Physics", DateConverter.fromString("15-12-2023"), 8.7f, false));
        schools.add(new School("Chemistry", DateConverter.fromString("20-12-2023"), 10.0f, true));
        ListView listView = findViewById(R.id.listView);
        SchoolAdapter adapter = new SchoolAdapter(
                this,
                R.layout.item_student, 
                schools,
                getLayoutInflater()
        );
        listView.setAdapter(adapter);
    }
}
