package com.example.onlineshop;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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
        db =  new Database(this);

        ItemModel item = new ItemModel();

        item = db.getItemByID(1);

        Log.e("item detail", "item name = " + item.getItem_image());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        ImageView iv = (ImageView) findViewById(R.id.itemDetail_item_image);

        String uri = "@drawable/" + item.getItem_image();  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        iv.setImageDrawable(res);

        TextView nama_item = (TextView) findViewById(R.id.itemDetail_textView_item_name);
        String string_nama_item = ": " + item.getItem_name();
        nama_item.setText(string_nama_item);

        TextView ukuran_item = (TextView) findViewById(R.id.itemDetail_textView_item_size_value);
        String string_ukuran_item = ": " + item.getItem_size();
        ukuran_item.setText(string_ukuran_item);

        TextView bahan_item = (TextView) findViewById(R.id.itemDetail_textView_item_material_value);
        String string_bahan = ": " + item.getItem_material();
        bahan_item.setText(string_bahan);

        TextView warna_item = (TextView) findViewById(R.id.itemDetail_textView_item_color_value);
        String string_warna = ": " + item.getItem_color();
        warna_item.setText(string_warna);

        TextView harga_item = (TextView) findViewById(R.id.itemDetail_textView_item_price);
        String string_harga = ": " + String.valueOf(item.getItem_price());
        harga_item.setText(string_harga);

    }
}
