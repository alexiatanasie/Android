package com.example.ticket3_booking_json;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.jar.JarException;

public class MainActivity extends AppCompatActivity {

    public static final String bookURL = "https://pastebin.com/raw/QVX2pZqQ";

    Button btnSave, btnSync, btnView;

    List<Booking> bookings = new ArrayList<>();

    private static final String KEY_JSON = "bookings";
    private static final String KEY_CUSTOMER_CODE = "customerCode";
    private static final String KEY_STARTDATE = "startDate";
    private static final String KEY_PAYINGMETHOD = "payingMethod";
    private static final String KEY_PAYEDSUM = "payedSum";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        // 4
        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService threadpool = Executors.newCachedThreadPool();
                Future<List<Booking>> future = threadpool.submit(new Callable<List<Booking>>() {
                    @Override
                    public List<Booking> call() throws Exception {
                        List<Booking> bookings = new ArrayList<>();
                        StringBuilder json = new StringBuilder();
                        HttpURLConnection connection = (HttpURLConnection) new URL(bookURL).openConnection();
                        try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    json.append(line);
                                }
                        }
                        connection.disconnect();

                        if (json!=null) {
                            try {
                                JSONObject object = new JSONObject(json.toString());
                                JSONArray array = object.getJSONArray(KEY_JSON);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jsonObject = array.getJSONObject(i);
                                    Long customerCode = jsonObject.getLong(KEY_CUSTOMER_CODE);
                                    Date startDate = DateConverter.fromString(jsonObject.getString(KEY_STARTDATE));
                                    String payingMethod = jsonObject.getString(KEY_PAYINGMETHOD);
                                    Float payedSum = (float)jsonObject.getDouble(KEY_PAYEDSUM);

                                    Booking booking = new Booking(customerCode, startDate, payingMethod, payedSum);
                                    bookings.add(booking);
                                }

                            } catch (JSONException e) {
                                    e.printStackTrace();
                            }
                        }
                        return bookings;
                    }
                });

                //ex 5

                try {
                    threadpool.shutdown();

                    bookings=future.get();
                    if(bookings.size()==4){
                        Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"FAILURE",Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        // 7
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DatabaseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initComponents() {
        btnSave = findViewById(R.id.btnSave);
        btnSync = findViewById(R.id.btnSync);
        btnView = findViewById(R.id.btnView);
    }
}
