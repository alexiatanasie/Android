package com.example.schoolcustomadapter1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
public class SchoolAdapter extends ArrayAdapter<School> {
    private int resource;
    private List<School> objects;
    private LayoutInflater inflater;
    // Visual components
    private TextView tvMaterie, tvDataTest, tvGrade;
    private RadioGroup radioGroupAbsolvent;
    private RadioButton radioYes, radioNo;
    public SchoolAdapter(@NonNull Context context, int resource, @NonNull List<School> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.resource = resource;
        this.objects = objects;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = inflater.inflate(this.resource, parent, false);
        initVisualComponents(row);
        School school = objects.get(position);
        populateVisualComponents(school);
        return row;
    }
    private void initVisualComponents(View row) {
        tvMaterie = row.findViewById(R.id.tvMaterie);
        tvDataTest = row.findViewById(R.id.tvDataTest);
        tvGrade = row.findViewById(R.id.tvGrade);
        radioGroupAbsolvent = row.findViewById(R.id.radioGroupAbsolvent);
        radioYes = row.findViewById(R.id.radioYes);
        radioNo = row.findViewById(R.id.radioNo);
    }
    private void populateVisualComponents(School school) {
        tvMaterie.setText(school.getMaterie());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        tvDataTest.setText(dateFormat.format(school.getDataTest()));
        tvGrade.setText(String.valueOf(school.getGrade()));
        if (school.isAbsolvent()) {
            radioYes.setChecked(true);
        } else {
            radioNo.setChecked(true);
        }
    }
}
