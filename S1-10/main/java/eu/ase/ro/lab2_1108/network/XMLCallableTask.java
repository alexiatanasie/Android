package eu.ase.ro.lab2_1108.network;

import java.io.BufferedReader;
import java.util.concurrent.Callable;

public class XMLCallableTask implements Callable<String> {

    private final HttpConnection connection;

    public XMLCallableTask(HttpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String call() throws Exception {
        BufferedReader reader = connection.getBufferedReader();
        if (reader == null) {
            throw new NullPointerException("invalid");
        }
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}

