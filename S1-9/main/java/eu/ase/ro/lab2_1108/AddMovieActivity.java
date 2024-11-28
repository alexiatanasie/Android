package eu.ase.ro.lab2_1108;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

import eu.ase.ro.lab2_1108.util.DateConverter;
import eu.ase.ro.lab2_1108.util.Movie;

public class AddMovieActivity extends AppCompatActivity {
    public static final String MOVIE_KEY = "movieKey";
    EditText etTitle, etDate, etProfit;
    RadioGroup rgPlatform;
    Button btnSave;
    //declare a Spinner object
    Spinner spinnerGenre;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //method that associates the .xml file from layout with the correspondent .java file
        setContentView(R.layout.activity_add_movie);

        initComponents();
        intent = getIntent();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()) {
                    Movie movie = buildMovieFromComponents();
                    intent.putExtra(MOVIE_KEY, movie);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });
    }

    private boolean isValid() {
        if(etTitle.getText() == null || etTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.errorValidation, Toast.LENGTH_LONG).show();
            return false;
        }
        if(etDate.getText() == null || etDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.errorValidation, Toast.LENGTH_LONG).show();
            return false;
        }
        if(etProfit.getText() == null || etProfit.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.errorValidation, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void initComponents() {
        etTitle = findViewById(R.id.etMovieTitle);
        etDate = findViewById(R.id.etDate);
        etProfit = findViewById(R.id.etProfit);

        spinnerGenre = findViewById(R.id.spinnerMovieGenre);

        //create an adapter
        ArrayAdapter<CharSequence> adapterMovieGenre = ArrayAdapter.createFromResource(getApplicationContext(),R.array.movie_genre,
                android.R.layout.simple_spinner_item);
        //attach an adapter to a spinner
        //creating the adapter is not enough; it is mandatory to attach the adapter to the spinner so that the data will be available on the interface
        spinnerGenre.setAdapter(adapterMovieGenre);

        rgPlatform = findViewById(R.id.rgPlatform);
        btnSave = findViewById(R.id.btnSave);
    }

    private Movie buildMovieFromComponents() {
        String title = etTitle.getText().toString();

        String dateString = etDate.getText().toString();
        Date releaseDate = DateConverter.fromString(dateString);

        String profitString = etProfit.getText().toString();
        int profit = Integer.parseInt(profitString);

        String movieGenre = spinnerGenre.getSelectedItem().toString();

        RadioButton checkedButton =
                findViewById(rgPlatform.getCheckedRadioButtonId());
        String platform = checkedButton.getText().toString();

        return new Movie(title, releaseDate, profit, movieGenre, platform);
    }
}