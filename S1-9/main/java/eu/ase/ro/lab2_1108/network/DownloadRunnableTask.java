package eu.ase.ro.lab2_1108.network;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.io.IOException;

public class DownloadRunnableTask implements Runnable {
    private HttpConnection httpConnection;
    TextView tvResult;

    public DownloadRunnableTask(HttpConnection httpConnection, TextView tvResult) {
        this.httpConnection = httpConnection;
        this.tvResult = tvResult;
    }

    @Override
    public void run() {
        try {
            String result=httpConnection.readHttp();
            Handler handler=new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tvResult.setText(result);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
