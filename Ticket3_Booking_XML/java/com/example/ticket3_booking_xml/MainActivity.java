package com.example.ticket3_booking_xml;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    public static final String bookingURL="https://pastebin.com/raw/XUNUjqM9";
    Button btnSave,btnView,btnSync;
    List<Booking> bookings=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService threadpool= Executors.newCachedThreadPool();
                Future<List<Booking>> future=threadpool.submit(new Callable<List<Booking>>() {
                    @Override
                    public List<Booking> call() throws Exception {
                        List<Booking>bookings=new ArrayList<>();
                        HttpURLConnection connection=null;
                        try{
                            connection=(HttpURLConnection) new URL(bookingURL).openConnection();
                            InputStream inputStream=connection.getInputStream();
                            XmlPullParser parser= Xml.newPullParser();
                            parser.setInput(inputStream,"UTF-8");
                            String text="";
                            Booking booking =null;


                            int eventType=parser.getEventType();
                            while(eventType!=XmlPullParser.END_DOCUMENT){
                                String tagName=parser.getName();
                                switch(eventType){
                                    case XmlPullParser.START_TAG:
                                        if("booking".equalsIgnoreCase(tagName)){
                                            booking=new Booking();
                                        }
                                        break;
                                    case XmlPullParser.TEXT:
                                        text=parser.getText();
                                        break;
                                    case XmlPullParser.END_TAG:
                                        if(booking==null)break;
                                        if("booking".equalsIgnoreCase(tagName)){
                                            bookings.add(booking);

                                        }
                                        else if("customerCode".equalsIgnoreCase(tagName)){
                                            booking.setCustomerCode(Long.parseLong(text));
                                        }
                                        else if("startDate".equalsIgnoreCase(tagName)){
                                            booking.setStartDate(DateConverter.fromString(text));
                                        }

                                        else if("payingMethod".equalsIgnoreCase(tagName)){
                                            booking.setPayingMethod(text);
                                        }
                                        else if("payedSum".equalsIgnoreCase(tagName)){
                                            booking.setPayedSum(Float.parseFloat(text));
                                        }
                                        break;


                                }
                                eventType=parser.next();
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }finally {
                            if(connection!=null){
                                connection.disconnect();
                            }
                        }
                        return bookings;
                    }
                });

                try{
                    bookings=future.get();
                    threadpool.shutdown();
                    if(bookings.size()==2){
                        Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "FAILURE", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //ex 7
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),DatabaseActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initComponents(){
        btnSave=findViewById(R.id.btnSave);
        btnSync=findViewById(R.id.btnSync);
        btnView=findViewById(R.id.btnView);
    }
}