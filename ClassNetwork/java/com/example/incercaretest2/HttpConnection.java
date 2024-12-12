package com.example.incercaretest2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpConnection {
    private final String urlString; // URL to connect to
    private HttpURLConnection httpURLConnection; // HTTP connection
    private InputStream inputStream; // Input stream for reading data
    private InputStreamReader inputStreamReader; // Reads input stream data
    private BufferedReader bufferedReader; // Buffers data for reading lines

    public HttpConnection(String url) {
        this.urlString = url;
    }

    // Method to fetch raw data from the HTTP connection
    public String readFromHttp() throws IOException {
        StringBuilder result = new StringBuilder();
        openConnection();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        closeConnection();
        return result.toString();
    }

    // Open the HTTP connection
    private void openConnection() throws IOException {
        URL url = new URL(urlString); // Create URL object
        httpURLConnection = (HttpURLConnection) url.openConnection(); // Open connection

        // Reading the data
        inputStream = httpURLConnection.getInputStream(); // Get the input stream
        inputStreamReader = new InputStreamReader(inputStream); // Wrap in InputStreamReader
        bufferedReader = new BufferedReader(inputStreamReader); // Wrap in BufferedReader
    }

    // Close the HTTP connection
    private void closeConnection() throws IOException {
        if (bufferedReader != null) bufferedReader.close();
        if (inputStreamReader != null) inputStreamReader.close();
        if (inputStream != null) inputStream.close();
        if (httpURLConnection != null) httpURLConnection.disconnect();
    }
}

