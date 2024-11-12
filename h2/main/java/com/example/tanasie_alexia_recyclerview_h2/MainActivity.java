package com.example.tanasie_alexia_recyclerview_h2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CityAdapter adapter;
    private List<CityItem> shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shoppingList = new ArrayList<>();
        shoppingList.add(new CityItem("Rm Valcea"));
        shoppingList.add(new CityItem("Slanic"));
        shoppingList.add(new CityItem("Cluj-Napoca"));
        shoppingList.add(new CityItem("Craiova"));
        shoppingList.add(new CityItem("Tulcea"));
        shoppingList.add(new CityItem("Sibiu"));
        shoppingList.add(new CityItem("Iasi"));
        shoppingList.add(new CityItem("Oradea"));
        shoppingList.add(new CityItem("Sighisoara"));
        shoppingList.add(new CityItem("Reghin"));
        shoppingList.add(new CityItem("Turda"));
        shoppingList.add(new CityItem("Sebes"));
        shoppingList.add(new CityItem("Dragasani"));



        adapter = new CityAdapter(shoppingList);
        recyclerView.setAdapter(adapter);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                shoppingList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
