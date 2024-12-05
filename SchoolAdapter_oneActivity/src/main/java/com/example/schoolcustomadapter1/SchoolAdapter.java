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

import java.util.List;

public class SchoolAdapter extends ArrayAdapter<School> {
    private int resource;
    private List<School> schoolList;
    private LayoutInflater layoutInflater;
    private TextView tvMaterie,tvData,tvNota;
    private RadioGroup rg;
    private RadioButton rbDa, rbNu;

    public SchoolAdapter(@NonNull Context context, int resource, List<School> objects, LayoutInflater layoutInflater) {
        super(context, resource,objects);
        this.resource=resource;
        this.schoolList=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;

        // Reuse the view if it exists; otherwise, inflate a new one
        if (row == null) {
            row = layoutInflater.inflate(this.resource, parent, false);
        }

        // Initialize the views
        TextView tvMaterie = row.findViewById(R.id.tvMaterie);
        TextView tvData = row.findViewById(R.id.tvDataTest);
        TextView tvNota = row.findViewById(R.id.tvNota);
        RadioGroup rg = row.findViewById(R.id.radioGroup);
        RadioButton rbDa = row.findViewById(R.id.rbDA);
        RadioButton rbNu = row.findViewById(R.id.rbNu);

        // Get the current school object
        School school = schoolList.get(position);

        // Populate the views
        tvMaterie.setText(school.getMaterie());
        tvData.setText(DateConverter.fromDate(school.getDataTest()));
        tvNota.setText(String.valueOf(school.getGrade()));
        if (school.isAbsolvent()) {
            rbDa.setChecked(true);
        } else {
            rbNu.setChecked(true);
        }

        return row;
    }
}
//    private void initializeVisualComponents(View row){
//        tvMaterie=row.findViewById(R.id.tvMaterie);
//        tvData=row.findViewById(R.id.tvDataTest);
//        tvNota=row.findViewById(R.id.tvNota);
//        rg=row.findViewById(R.id.radioGroup);
//        rbDa=row.findViewById(R.id.rbDA);
//        rbNu=row.findViewById(R.id.rbNu);
//    }
//    private void populateVisualComponents(School school){
//        tvMaterie.setText(school.getMaterie());
//        tvData.setText(DateConverter.fromDate(school.getDataTest()));
//        tvNota.setText(String.valueOf(school.getGrade()));
//        if (school.isAbsolvent()) {
//            rbDa.setChecked(true);
//        } else {
//            rbNu.setChecked(true);
//        }

