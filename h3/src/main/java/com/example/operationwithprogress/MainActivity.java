package com.example.operationwithprogress;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textView;
    private Button btn;
    private Handler handler = new Handler();
    private int progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload();
            }
        });
    }
    private void startDownload() {
        progress = 0;
        progressBar.setProgress(progress);
        textView.setText("Progress: " + progress + "%");

        new Thread(() -> {
            while (progress < 100) {
                progress += 10;
                handler.post(() -> {
                    progressBar.setProgress(progress);
                    textView.setText("Progress: " + progress + "%");
                });

                try {
                    Thread.sleep(500); // Simulate time for downloading
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void initComponents(){
        progressBar=findViewById(R.id.idProgressBar);
        textView=findViewById(R.id.idTextView);
        btn=findViewById(R.id.idButton);

    }
}