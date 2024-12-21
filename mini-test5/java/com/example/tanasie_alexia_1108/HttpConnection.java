package com.example.tanasie_alexia_1108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnection {

    private final String urlHttp;
    public HttpConnection(String urlHttp) {
        this.urlHttp = urlHttp;
    }

    public String readFromHttp() throws IOException {
        StringBuilder builder=new StringBuilder();
        URL url=new URL(urlHttp);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connection.disconnect();
        return builder.toString();
    }
}
