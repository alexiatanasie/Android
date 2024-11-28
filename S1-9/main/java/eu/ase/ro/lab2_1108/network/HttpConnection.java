package eu.ase.ro.lab2_1108.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnection {
    private final String urlString;
    HttpURLConnection httpURLConnection;
    InputStream inputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;

    public HttpConnection(String urlString) {
        this.urlString = urlString;
    }
    public String readHttp() throws IOException {
        StringBuilder result=new StringBuilder();
        openConnection();
        String line;
        while((line=bufferedReader.readLine())!=null){
            result.append(line);
        }
        closeConnection();
        return result.toString();


    }
    void openConnection() throws IOException {
        URL url=new URL(urlString);
        httpURLConnection=(HttpURLConnection) url.openConnection();
        inputStream=httpURLConnection.getInputStream();
        inputStreamReader=new InputStreamReader(inputStream);

        bufferedReader=new BufferedReader(inputStreamReader);

    }
    void closeConnection() throws IOException {
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
    }
}
