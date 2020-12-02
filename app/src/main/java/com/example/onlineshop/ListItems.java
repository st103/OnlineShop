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
    private String category = "baju";
    private int user_id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        db = new Database(ListItems.this);

        getUserandCategory(savedInstanceState);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.listItems_recyclerView);

        adapter = new ItemListAdapter(items, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListItems.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }

    void addData(){
        ItemModel[] item = db.getItemByCategory(category);
        items = new ArrayList<>();

        for(int i = 0; i < item.length; i ++) {
            items.add(item[i]);
        }
    }

    void getUserandCategory(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                user_id = 1;
            } else {
                user_id= extras.getInt("user_id");
            }
        } else {
            user_id= savedInstanceState.getInt("item_id");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                category = "baju";
            } else {
                category = extras.getString("category");
            }
        } else {
            category= savedInstanceState.getString("category");
        }
    }

}