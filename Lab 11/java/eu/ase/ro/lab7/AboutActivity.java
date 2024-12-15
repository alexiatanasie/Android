package eu.ase.ro.lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    public static final String KEY_ACTIVITY_TO_FRAGMENT = "sendDataBetweenActivityFragment";
    public static final String KEY_ACTIVITY_TO_FRAGMENT_INT = "sendIntBetweenActivityFragment";

    //new
    public static final String SHARED_PREF_NAME="sharedPrefFile";
    public static final String RATING_KEY="ratingKey";

    private TextView tvUserInfo, tvUserName, tvUserAge;
    Intent intent;
    Button btnFragment1, btnFragment2;
    RatingBar ratingBar;
    SharedPreferences sharedPreferences;
    Fragment1 fragment1 = new Fragment1();
    Fragment2 fragment2 = new Fragment2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initComponents();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Fragment2()).commit();
        }

        saveDataInSharedPref();
        readDataFromSharedPref();
        //hasExtra() -> checks if there is something stored at the key that is received as a parameter
        if(intent.hasExtra(MainActivity.USER_INFO_KEY)) {
            //method from Intent class that is used to get a custom object from the intent
            //its paramter is the key that the object was saved at through the call of putExtra() method
            String userInfo = intent.getStringExtra(MainActivity.USER_INFO_KEY);
            tvUserInfo.setText(userInfo);
        }

        if(intent.hasExtra(MainActivity.USER_NAME_KEY)) {
            String userName = intent.getStringExtra(MainActivity.USER_NAME_KEY);
            tvUserName.setText(userName);
        }

        if(intent.hasExtra(MainActivity.USER_AGE_KEY)) {
            String userAge = String.valueOf(intent.getIntExtra(MainActivity.USER_AGE_KEY, 0));
            tvUserAge.setText(userAge);
        }

        btnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(fragment1);
                sendDataBetweenActivityFragment(fragment1, "Send data from Activity", 55);
            }
        });

        btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(fragment2);
            }
        });
    }

    //new
    private void saveDataInSharedPref(){
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putFloat(RATING_KEY,rating); // v poate fi inlocuit cu rating + in float
                editor.apply();
            }
        });
    }
    //new
    private void readDataFromSharedPref(){
        float rating=sharedPreferences.getFloat(RATING_KEY,-1);
        if(rating>0){
            ratingBar.setRating(rating);
        }
    }
    private void initComponents() {
        tvUserInfo = findViewById(R.id.tvInfo);
        tvUserName = findViewById(R.id.tvUserName);
        tvUserAge = findViewById(R.id.tvUserAge);

        btnFragment1 = findViewById(R.id.btnFragment1);
        btnFragment2 = findViewById(R.id.btnFragment2);

        intent = getIntent();


        //new 12.12
        ratingBar=findViewById(R.id.ratingBarAbout);
        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
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

    private void sendDataBetweenActivityFragment(Fragment fragment, String data, int value) {
        //initialize a Bundle object
        Bundle bundle = new Bundle();
        //store a String to a certain key
        bundle.putString(KEY_ACTIVITY_TO_FRAGMENT, data);
        //store an int to a certain key
        bundle.putInt(KEY_ACTIVITY_TO_FRAGMENT_INT, value);
        //attach the Bundle to the fragment
        fragment.setArguments(bundle);
    }
}