package eu.ase.ro.lab2_1108.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {
    private final String urlString;
    HttpURLConnection httpURLConnection;
    InputStream inputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader,bufferedReader1;

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
    public String readXML() throws IOException {

        StringBuilder result1=new StringBuilder();
        openConnection();
        String line1;
        while((line1=bufferedReader1.readLine())!=null){
            result1.append(line1);
        }
        closeConnection();
        return result1.toString();


    }
    public BufferedReader getBufferedReader() {
        try {
            URL urlObject = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            InputStream inputStream = connection.getInputStream();
            return new BufferedReader(new InputStreamReader(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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