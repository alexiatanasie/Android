package com.example.incercaretest2;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private ListView listViewPayments;
    private final String urlPayments = "https://pastebin.com/raw/uvAniicc"; // Example URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPayments = findViewById(R.id.listViewPayments);

        // Fetch and display payment data
        fetchAndDisplayPayments();
    }

    private void fetchAndDisplayPayments() {
        new Thread(() -> {
            try {
                // Fetch payment data using HttpConnection
                HttpConnection httpConnection = new HttpConnection(urlPayments);
                String response = httpConnection.readFromHttp();

                // Simulate parsing the response into Payment objects
                List<Payment> payments = getSamplePayments(); // Replace with actual parsing logic if needed

                // Update UI with payment data
                runOnUiThread(() -> {
                    List<String> paymentDetails = new ArrayList<>();
                    for (Payment payment : payments) {
                        paymentDetails.add(payment.toString());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            paymentDetails
                    );
                    listViewPayments.setAdapter(adapter);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Simulated method to generate payment data
    private List<Payment> getSamplePayments() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(123456, "Credit Card", 100.5f, new Date()));
        payments.add(new Payment(789012, "Cash", 250.0f, new Date()));
        payments.add(new Payment(345678, "Bank Transfer", 500.75f, new Date()));
        return payments;
    }
}

