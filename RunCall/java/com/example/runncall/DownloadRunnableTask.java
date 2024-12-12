package com.example.runncall;


import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

public class DownloadRunnableTask implements Runnable {
    private final TextView tvResult;
    private final HttpConnection httpConnection;

    public DownloadRunnableTask(TextView tvResult, HttpConnection httpConnection) {
        this.tvResult = tvResult;
        this.httpConnection = httpConnection;
    }

    @Override
    public void run() {
        try {
            String result = httpConnection.fetchData();
            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(() -> tvResult.setText(result));
        } catch (Exception e) {
            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(() -> tvResult.setText("Error: " + e.getMessage()));
        }
    }
}
