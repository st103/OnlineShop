package com.example.onlineshop;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import java.lang.reflect.Field;

public class Item_Detail extends Activity {
    Database db;

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
        ItemModel item = db.getItemByID(1);

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

    }
}
