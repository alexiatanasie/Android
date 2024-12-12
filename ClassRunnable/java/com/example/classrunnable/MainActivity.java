package com.example.classrunnable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    Button btnRunnable, btnCallable;
    ListView lvPayments;
    ArrayAdapter<String> adapter;
    List<String> paymentsDisplayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRunnable = findViewById(R.id.btnRunnable);
        btnCallable = findViewById(R.id.btnCallable);
        lvPayments = findViewById(R.id.lvPayments);

        paymentsDisplayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, paymentsDisplayList);
        lvPayments.setAdapter(adapter);

        btnRunnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        List<Payment> payments = new ArrayList<>();
                        payments.add(new Payment(101, "Credit Card", 150.50f, new Date()));
                        payments.add(new Payment(102, "PayPal", 200.75f, new Date()));
                        payments.add(new Payment(103, "Bank Transfer", 320.00f, new Date()));

                        paymentsDisplayList.clear();
                        for (Payment payment : payments) {
                            paymentsDisplayList.add(payment.toString());
                        }

                        runOnUiThread(() -> adapter.notifyDataSetChanged());
                    }
                };

                new Thread(task).start();
            }
        });

        btnCallable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Callable<List<String>> task = new Callable<List<String>>() {
                    @Override
                    public List<String> call() {
                        List<Payment> payments = new ArrayList<>();
                        payments.add(new Payment(201, "Debit Card", 250.50f, new Date()));
                        payments.add(new Payment(202, "Cash", 300.75f, new Date()));
                        payments.add(new Payment(203, "Crypto", 500.00f, new Date()));

                        List<String> displayList = new ArrayList<>();
                        for (Payment payment : payments) {
                            displayList.add(payment.toString());
                        }
                        return displayList;
                    }
                };

                ExecutorService executor = Executors.newSingleThreadExecutor();
                Future<List<String>> future = executor.submit(task);

                try {
                    List<String> result = future.get();
                    paymentsDisplayList.clear();
                    paymentsDisplayList.addAll(result);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    paymentsDisplayList.clear();
                    paymentsDisplayList.add("Error: " + e.getMessage());
                    adapter.notifyDataSetChanged();
                } finally {
                    executor.shutdown();
                }
            }
        });
    }
}
