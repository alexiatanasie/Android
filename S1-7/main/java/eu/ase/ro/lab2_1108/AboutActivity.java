package eu.ase.ro.lab2_1108;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManagerNonConfig;
import androidx.fragment.app.FragmentTransaction;

public class AboutActivity extends AppCompatActivity {
    public static final String STRING_KEY="stringKey";
    public static final String INT_KEY="stringInt";


    TextView tvAbout, tvUserName, tvUserAge;
    Intent intent;
    Button btnFragment1,btnFragment2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initComponents();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,new Fragment1()).commit();
        }
        Fragment1 fragment1=new Fragment1();
        Fragment2 fragment2=new Fragment2();
        btnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(fragment1);
            }
        });
        btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(fragment2);
                sendDataFromActivity("about activity is sending...", 45,fragment2);
            }
        });
        if(intent.hasExtra(MainActivity.USER_INFO_KEY)) {
            String aboutInfo = intent.getStringExtra(MainActivity.USER_INFO_KEY);
            tvAbout.setText(aboutInfo);
        }

        if(intent.hasExtra(MainActivity.USER_NAME_KEY)) {
            String aboutInfo = intent.getStringExtra(MainActivity.USER_NAME_KEY);
            tvUserName.setText(aboutInfo);
        }

        if(intent.hasExtra(MainActivity.USER_AGE_KEY)) {
            int age = intent.getIntExtra(MainActivity.USER_AGE_KEY, 0);
            tvUserAge.setText(String.valueOf(age));
        }
    }

    private void sendDataFromActivity(String valueString,int valueInt, Fragment fragment){
        Bundle bundle=new Bundle();

        bundle.putString(STRING_KEY,valueString);
        bundle.putInt(INT_KEY,valueInt);
        fragment.setArguments(bundle);
    }
    private void openFragment(Fragment fragment1){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment1);
        fragmentTransaction.commit();
    }
    private void initComponents() {
        tvAbout = findViewById(R.id.tvAboutInfo);
        tvUserName = findViewById(R.id.tvUserInfo);
        tvUserAge = findViewById(R.id.tvUserAge);

        btnFragment1=findViewById(R.id.showFragment1);
        btnFragment2=findViewById(R.id.showFragement2);
        intent = getIntent();
    }
}