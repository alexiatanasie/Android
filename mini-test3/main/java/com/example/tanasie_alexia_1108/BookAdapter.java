package com.example.tanasie_alexia_1108;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class BookAdapter extends ArrayAdapter<Book> {
    private TextView tvTitle,tvAuthor,tvPrice,tvReleaseDate;
    private int resource;
    private List<Book> books;
    private LayoutInflater inflater;

    public BookAdapter(@NonNull Context context, int resource,List<Book>books, LayoutInflater inflater) {
        super(context, resource,books);
        this.resource = resource;
        this.inflater = inflater;
        this.books=books;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = inflater.inflate(resource, parent, false);
        initVisualComponents(row);
        Book book = books.get(position);
        populateVisualComponents(book);
        return row;
    }

    private void initVisualComponents(View row) {
        tvTitle = row.findViewById(R.id.tvTitle_tanasie);
        tvAuthor = row.findViewById(R.id.tvAuthor_tan);
        tvPrice = row.findViewById(R.id.tvPrice);
        tvReleaseDate = row.findViewById(R.id.tvReleaseDate);
    }

    private void populateVisualComponents(Book book) {
        tvTitle.setText(book.getBookTitle());
        tvAuthor.setText(book.getBookAuthor());
        tvPrice.setText(String.format(Locale.getDefault(), "%.2f", book.getBookPrice()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        tvReleaseDate.setText(dateFormat.format(book.getBookReleaseDate()));
    }
}



