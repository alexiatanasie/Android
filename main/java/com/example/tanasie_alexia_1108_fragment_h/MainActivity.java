package com.example.tanasie_alexia_1108_fragment_h;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    Button btnFragment1, btnFragment2;
    Fragment1 fragment1 = new Fragment1();
    Fragment2 fragment2 = new Fragment2();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        btnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(fragment1);            }
        });

        btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(fragment2);            }
        });
    }
    private void initComponents(){
        btnFragment1 = findViewById(R.id.btnFragment1);
        btnFragment2 = findViewById(R.id.btnFragment2);

        //intent = getIntent();
    }
    //open a fragment at button click
    private void openFragment(Fragment fragment) {
        //getting the fragment manager for changing the fragments between them
        FragmentManager fragmentManager = getSupportFragmentManager();
        //begin the transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //replacing the fragment => placing the fragment in the layout from AboutActivity
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        //the user can see the result of the transaction
        fragmentTransaction.commit();
    }

}