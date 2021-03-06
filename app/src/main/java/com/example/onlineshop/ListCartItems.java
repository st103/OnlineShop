package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class ListCartItems extends AppCompatActivity {
    private int user_id;
    private RecyclerView recyclerView;
    private ItemListAdapter adapter;
    private CartModel[] itemsInCart;
    private ArrayList<ItemModel> items;
    private Database db;
    private int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cart_items);
        db = new Database(ListCartItems.this);
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

        if(!db.cartIsEmpty(user_id)) {
            addData();
            inflateListView();
        } else {
            Toast.makeText(getApplicationContext(),"Cart is Empty, please insert item to cart", Toast.LENGTH_LONG).show();
            openHome();
        }

        changeTotalPrice();
        setCheckOutButton();
    }

    void setCheckOutButton() {
        Button checkOutBtn = (Button) findViewById(R.id.cart_button_checkout);
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPembayaran();
            }
        });
    }

    void openPembayaran() {
        Intent intent= new Intent(ListCartItems.this, ActivityPembayaraan.class);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }

    void changeTotalPrice() {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        TextView totalPrice = (TextView) findViewById(R.id.cart_txt_totalPrice);
        totalPrice.setText(kursIndonesia.format(total));
    }

    void addTotal(int pricePerItem) {
        total += pricePerItem;
    }

    void inflateListView() {
        recyclerView = (RecyclerView) findViewById(R.id.listCartItems_recyclerView);

        adapter = new ItemListAdapter(items, this, user_id);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListCartItems.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }

    void openHome() {
        Intent intent= new Intent(ListCartItems.this, Main_Menu.class);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }

    void addData() {
        itemsInCart = db.getUnpaidCartByUserId(user_id);
        items = new ArrayList<>();
        Log.e("inAddData", "after get cart by user id and status with cart = " + String.valueOf(itemsInCart.length));
        for(int i = 0; i < itemsInCart.length; i ++) {
            int item_id = itemsInCart[i].getItem_id();
            String s_item_id = String.valueOf(item_id);
            Log.e("addData", "item id = " + s_item_id);
            items.add(db.getItemByID(itemsInCart[i].getItem_id()));
            addTotal(db.getItemByID(itemsInCart[i].getItem_id()).getItem_price());
        }
    }
}