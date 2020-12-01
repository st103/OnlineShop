package com.example.onlineshop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import java.lang.reflect.Field;

public class Item_Detail extends Activity {
    Database db;

    protected void onCreate(Bundle savedInstanceState) {
        //get Item from database
        db =  new Database(this);

        int item_id = 1;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                item_id = 1;
            } else {
                item_id= extras.getInt("item_id");
            }
        } else {
            item_id= savedInstanceState.getInt("item_id");
        }

        ItemModel item = db.getItemByID(item_id);

        //set layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        //Set item image to display
        ImageView iv = (ImageView) findViewById(R.id.itemDetail_item_image);

        String uri = "@drawable/" + item.getItem_image();  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        iv.setImageDrawable(res);

        //write nama_item to item_detail layout
        TextView nama_item = (TextView) findViewById(R.id.itemDetail_textView_item_name);
        nama_item.setText(item.getItem_name());

        //write ukuran_item to item_detail layout
        TextView ukuran_item = (TextView) findViewById(R.id.itemDetail_textView_item_size_value);
        String string_ukuran_item = ": " + item.getItem_size();
        ukuran_item.setText(string_ukuran_item);

        //write bahan_item to item_detail layout
        TextView bahan_item = (TextView) findViewById(R.id.itemDetail_textView_item_material_value);
        String string_bahan = ": " + item.getItem_material();
        bahan_item.setText(string_bahan);

        //write warna to item_detail layout
        TextView warna_item = (TextView) findViewById(R.id.itemDetail_textView_item_color_value);
        String string_warna = ": " + item.getItem_color();
        warna_item.setText(string_warna);

        //Format int to currency
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        //write harga_item to item_detail layout
        TextView harga_item = (TextView) findViewById(R.id.itemDetail_textView_item_price);
        String string_harga = ": " + kursIndonesia.format(item.getItem_price());
        harga_item.setText(string_harga);

        Button back = (Button) findViewById(R.id.itemDetail_btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeItemDetail();
            }
        });
        setButtonInsertToCart(item.getItem_id());
    }

    private void closeItemDetail() {
        this.finish();
    }

    private void setButtonInsertToCart(int item_id) {
        Button insertToCart = (Button) findViewById(R.id.itemDetail_btn_insert_to_basket);
        insertToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ins(item_id);
            }
        });

    }

    void ins(int item_id) {
        if(db.insertItemToCart(1, item_id))  {
            Log.e("insertToCart", "success inserting item to cart");
            Intent intent= new Intent(this, ListItems.class);
            intent.putExtra("user_id", 1);
            startActivity(intent);
        }
        else Log.e("inInsertItemtoCart", "failed in setButtonInsertToCart()");
    }
}
