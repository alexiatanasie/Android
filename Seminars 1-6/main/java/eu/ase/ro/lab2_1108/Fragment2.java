package eu.ase.ro.lab2_1108;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    TextView tvString,tvInt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_2, container, false);
        tvString=view.findViewById(R.id.tvFragmentString);
        tvInt=view.findViewById(R.id.tvFragmentInt);
        String stringValue=getArguments().getString(AboutActivity.STRING_KEY);
        tvString.setText(stringValue);
        int intValue=getArguments().getInt(AboutActivity.INT_KEY);
        tvInt.setText(String.valueOf(intValue));
        return view;

    }
}