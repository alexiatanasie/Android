package com.example.ticket3_booking_xml;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity {

    Button btnSort;
    ListView lvdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database2);

        initComponents();
    }
    private void initComponents(){
        btnSort=findViewById(R.id.btnSort);
    }
}