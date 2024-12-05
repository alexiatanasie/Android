package eu.ase.ro.lab2_1108;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import eu.ase.ro.lab2_1108.network.DownloadCallableTask;
import eu.ase.ro.lab2_1108.network.DownloadRunnableTask;
import eu.ase.ro.lab2_1108.network.HttpConnection;

public class NetworkActivity extends AppCompatActivity {
    Button btnRunnable,btnCallable;
    TextView tvResult;
    String urlRunnable = "https://pastebin.com/raw/aPNZpzMB";
    String urlCallable = "https://pastebin.com/raw/uvAniicc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        initComponents();
        btnRunnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpConnection connection=new HttpConnection(urlRunnable);
                DownloadRunnableTask runnableTask=new DownloadRunnableTask(connection,tvResult);
                Thread thread =new Thread(runnableTask);
                thread.start();
            }
        });
        btnCallable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpConnection connection=new HttpConnection(urlCallable);
                DownloadCallableTask callableTask=new DownloadCallableTask(connection);
                ExecutorService executorService= Executors.newCachedThreadPool();
                Future<String> resultCallable=executorService.submit(callableTask);
                try {
                    String result=resultCallable.get();
                    tvResult.setText(result);
                } catch (ExecutionException e) {

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
    void initComponents(){
        btnRunnable=findViewById(R.id.btnRunnable);
        btnCallable=findViewById(R.id.btnCallable);
        tvResult=findViewById(R.id.tvResultNetwork);
    }
}