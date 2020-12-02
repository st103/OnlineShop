package com.example.onlineshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Main_Menu extends AppCompatActivity {
    Database db;
    Button LihatDetail;
    int user_id = 1;

    private int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        //get Item from database
        db =  new Database(this);
        ItemModel item = db.getItemByID(7);
        ItemModel item_rekomendasi_1 = db.getItemByID(17);
        ItemModel item_rekomendasi_2 = db.getItemByID(25);
        ItemModel item_rekomendasi_3 = db.getItemByID(28);
        ItemModel item_rekomendasi_4 = db.getItemByID(33);
        ItemModel item_rekomendasi_5 = db.getItemByID(2);
        ItemModel item_rekomendasi_6 = db.getItemByID(1);



        //set layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);

        //Set item image to display
        ImageView iv = (ImageView) findViewById(R.id.main_menu_gambar);
        ImageView rekomendasi_1 = (ImageView) findViewById(R.id.rekom_1);
        ImageView rekomendasi_2 = (ImageView) findViewById(R.id.rekom_2);
        ImageView rekomendasi_3 = (ImageView) findViewById(R.id.rekom_3);
        ImageView rekomendasi_4 = (ImageView) findViewById(R.id.rekom_4);
        ImageView rekomendasi_5 = (ImageView) findViewById(R.id.produk_lain_1);
        ImageView rekomendasi_6 = (ImageView) findViewById(R.id.produk_lain_2);

        String uri = "@drawable/" + item.getItem_image();  // where myresource (without the extension) is the file
        String url1 = "@drawable/" + item_rekomendasi_1.getItem_image();
        String url2 = "@drawable/" + item_rekomendasi_2.getItem_image();
        String url3 = "@drawable/" + item_rekomendasi_3.getItem_image();
        String url4 = "@drawable/" + item_rekomendasi_4.getItem_image();
        String url5 = "@drawable/" + item_rekomendasi_5.getItem_image();
        String url6 = "@drawable/" + item_rekomendasi_6.getItem_image();

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        iv.setImageDrawable(res);

        imageResource = getResources().getIdentifier(url1, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        rekomendasi_1.setImageDrawable(res);

        imageResource = getResources().getIdentifier(url2, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        rekomendasi_2.setImageDrawable(res);

        imageResource = getResources().getIdentifier(url3, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        rekomendasi_3.setImageDrawable(res);

        imageResource = getResources().getIdentifier(url4, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        rekomendasi_4.setImageDrawable(res);

        imageResource = getResources().getIdentifier(url5, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        rekomendasi_5.setImageDrawable(res);

        imageResource = getResources().getIdentifier(url6, null, getPackageName());
        res = getResources().getDrawable(imageResource);
        rekomendasi_6.setImageDrawable(res);

        //write nama_item to item_detail layout
        TextView nama_item = (TextView) findViewById(R.id.main_menu_gambar_nama);
        nama_item.setText(item.getItem_name());

        //Format int to currency
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        //write harga_item to item_detail layout
        TextView harga_item = (TextView) findViewById(R.id.main_menu_gambar_harga);
        String string_harga = ": " + kursIndonesia.format(item.getItem_price());
        harga_item.setText(string_harga);

        //write nama_item to item_detail layout
        TextView nama_item_1 = (TextView) findViewById(R.id.nama_1);
        nama_item_1.setText(item.getItem_name());

        //write warna to item_detail layout
        TextView warna_item_1 = (TextView) findViewById(R.id.nama_1_1);
        String string_warna_1 = ": " + item.getItem_color();
        warna_item_1.setText(string_warna_1);

        //write harga_item to item_detail layout
        TextView harga_item_1 = (TextView) findViewById(R.id.nama_1_2);
        String string_harga_1 = ": " + kursIndonesia.format(item.getItem_price());
        harga_item_1.setText(string_harga_1);

        //write nama_item to item_detail layout
        TextView nama_item_2 = (TextView) findViewById(R.id.nama_2);
        nama_item_2.setText(item.getItem_name());

        //write warna to item_detail layout
        TextView warna_item_2 = (TextView) findViewById(R.id.nama_2_1);
        String string_warna_2 = ": " + item.getItem_color();
        warna_item_2.setText(string_warna_2);

        //write harga_item to item_detail layout
        TextView harga_item_2 = (TextView) findViewById(R.id.nama_2_2);
        String string_harga_2 = ": " + kursIndonesia.format(item.getItem_price());
        harga_item_2.setText(string_harga_2);

        //write nama_item to item_detail layout
        TextView nama_item_3 = (TextView) findViewById(R.id.nama_3);
        nama_item_3.setText(item.getItem_name());

        //write warna to item_detail layout
        TextView warna_item_3 = (TextView) findViewById(R.id.nama_3_1);
        String string_warna_3 = ": " + item.getItem_color();
        warna_item_3.setText(string_warna_3);

        //write harga_item to item_detail layout
        TextView harga_item_3 = (TextView) findViewById(R.id.nama_3_2);
        String string_harga_3 = ": " + kursIndonesia.format(item.getItem_price());
        harga_item_3.setText(string_harga_3);

        //write nama_item to item_detail layout
        TextView nama_item_4 = (TextView) findViewById(R.id.nama_4);
        nama_item_4.setText(item.getItem_name());

        //write warna to item_detail layout
        TextView warna_item_4 = (TextView) findViewById(R.id.nama_4_1);
        String string_warna_4 = ": " + item.getItem_color();
        warna_item_4.setText(string_warna_4);

        //write harga_item to item_detail layout
        TextView harga_item_4 = (TextView) findViewById(R.id.nama_4_2);
        String string_harga_4 = ": " + kursIndonesia.format(item.getItem_price());
        harga_item_4.setText(string_harga_4);

        //write nama_item to item_detail layout
        TextView nama_item_5 = (TextView) findViewById(R.id.nama_5);
        nama_item_5.setText(item.getItem_name());

        //write warna to item_detail layout
        TextView warna_item_5 = (TextView) findViewById(R.id.nama_5_1);
        String string_warna_5 = ": " + item.getItem_color();
        warna_item_5.setText(string_warna_5);

        //write harga_item to item_detail layout
        TextView harga_item_5 = (TextView) findViewById(R.id.nama_5_2);
        String string_harga_5 = ": " + kursIndonesia.format(item.getItem_price());
        harga_item_5.setText(string_harga_5);

        //write nama_item to item_detail layout
        TextView nama_item_6 = (TextView) findViewById(R.id.nama_6);
        nama_item_6.setText(item.getItem_name());

        //write warna to item_detail layout
        TextView warna_item_6 = (TextView) findViewById(R.id.nama_6_1);
        String string_warna_6 = ": " + item.getItem_color();
        warna_item_6.setText(string_warna_6);

        //write harga_item to item_detail layout
        TextView harga_item_6 = (TextView) findViewById(R.id.nama_6_2);
        String string_harga_6 = ": " + kursIndonesia.format(item.getItem_price());
        harga_item_6.setText(string_harga_6);

        LinearLayout kategori = (LinearLayout) findViewById(R.id.kategori_1);
        kategori.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Main_Menu.this, ListItems.class);
                intent.putExtra("category", "baju");
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
        kategori = (LinearLayout) findViewById(R.id.kategori_2);
        kategori.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Main_Menu.this, ListItems.class);
                intent.putExtra("category", "celana");
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
        kategori = (LinearLayout) findViewById(R.id.kategori_3);
        kategori.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Main_Menu.this, ListItems.class);
                intent.putExtra("category", "sepatu");
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
        kategori = (LinearLayout) findViewById(R.id.kategori_4);
        kategori.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Main_Menu.this, ListItems.class);
                intent.putExtra("category", "aksesoris");
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
        LihatDetail = (Button)findViewById(R.id.lihat_detail);
        LihatDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Main_Menu.this, Item_Detail.class);
                int user_id = 1;
                intent.putExtra("user_id", user_id);
                intent.putExtra("item_id", item.getItem_id());
                startActivity(intent);
            }
        });

        setItemsButton(item_rekomendasi_1.getItem_id(), item_rekomendasi_2.getItem_id(), item_rekomendasi_3.getItem_id(), item_rekomendasi_4.getItem_id(), item_rekomendasi_5.getItem_id(), item_rekomendasi_6.getItem_id());
        setCartBtnListener();

    }

    void setCartBtnListener() {
        Button btn = (Button) findViewById(R.id.mainMenu_btn_cart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.cartIsEmpty(user_id)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main_Menu.this);
                    builder.setTitle("No Items in Cart");
                    builder.setMessage("please browse items and put them into cart");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    Intent intent = new Intent(Main_Menu.this, ListCartItems.class);
                    intent.putExtra("user_id", user_id);
                    startActivity(intent);
                }
            }
        });
    }

    void setItemsButton(int item_id1, int item_id2, int item_id3, int item_id4, int item_id5, int item_id6) {
        LinearLayout kategori = (LinearLayout) findViewById(R.id.mainMenu_linear_rek_1);
        kategori.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Menu.this, Item_Detail.class);
                intent.putExtra("item_id", item_id1);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }


        });

        kategori = (LinearLayout) findViewById(R.id.mainMenu_linear_rek_2);
        kategori.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Menu.this, Item_Detail.class);
                intent.putExtra("item_id", item_id2);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        kategori = (LinearLayout) findViewById(R.id.mainMenu_linear_rek_3);
        kategori.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Menu.this, Item_Detail.class);
                intent.putExtra("item_id", item_id3);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        kategori = (LinearLayout) findViewById(R.id.mainMenu_linear_rek_4);
        kategori.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Menu.this, Item_Detail.class);
                intent.putExtra("item_id", item_id4);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        ImageView iv = (ImageView) findViewById(R.id.produk_lain_1);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Menu.this, Item_Detail.class);
                intent.putExtra("item_id", item_id5);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        iv = (ImageView) findViewById(R.id.produk_lain_2);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Menu.this, Item_Detail.class);
                intent.putExtra("item_id", item_id6);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
    }
}