package com.example.s1yt;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
//each activity will extend AppCompatActivity for having access to newer features of the plaftorm when using older Android versions

public class MainActivity extends AppCompatActivity {

    //TextView tvMainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity","onCreate");
        //tvMainActivity=findViewById(R.id.tvMainActivity);

        Toast.makeText(getApplicationContext(),R.string.helllo,Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),getString(R.string.method_call,"onCreate"),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","onStart");
        Toast.makeText(getApplicationContext(), getString(R.string.method_call, "onStart"), Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","onPause");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","onResume");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","onDestroy");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu,menu);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_option1) {
            Toast.makeText(getApplicationContext(), R.string.menu_option1, Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.menu_option2) {
            Toast.makeText(getApplicationContext(), R.string.menu_option2, Toast.LENGTH_LONG).show();

        }
        return true;
    }
}