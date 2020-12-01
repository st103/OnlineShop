package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;

import java.util.ArrayList;

public class ListItems extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemListAdapter adapter;
    private ArrayList<ItemModel> items;
    private Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        db = new Database(ListItems.this);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.listItems_recyclerView);

        adapter = new ItemListAdapter(items, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListItems.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }

    void addData(){
        ItemModel[] item = db.getItemByCategory("baju");
        items = new ArrayList<>();

        for(int i = 0; i < item.length; i ++) {
            items.add(item[i]);
        }
    }

}