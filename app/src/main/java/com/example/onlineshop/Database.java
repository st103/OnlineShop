package com.example.onlineshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(username text primary key, password text, phone number)");
        db.execSQL("Create table item(item_id INTEGER primary key autoincrement, item_name text, " +
                "item_price int, item_size text, item_material text, item_desc text, item_gender_category int, " +
                "item_category String, item_color String, Item_image String)");
        Log.e("onCreate", "finished onCreate() db func");
        insert_items(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db =  this.getReadableDatabase();
        db.execSQL("drop table if exists user");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.e("In DatabaseOnOpen", "start opening daaatabase");
        super.onOpen(db);
    }

    //inserting in database
    public boolean insert( String username,String password,String phone) {
        SQLiteDatabase sdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("phone",phone);
        long ins = sdb.insert("user",null,contentValues);
        if(ins==-1) {
            return false;
        } else {
            return true;
        }
    }

    private boolean insert_items_to_db(SQLiteDatabase sdb, ItemModel[] items) {
        Log.e("in insertitemtodb", "start sqliitedatabase  sdb");
        Log.e("in insertitemtodb", "finish sqliitedatabase  sdb");
        boolean success_status = false;
        for(int i = 0; i < items.length; i ++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("item_name", items[i].getItem_name());
            contentValues.put("item_price", items[i].getItem_price());
            contentValues.put("item_size", items[i].getItem_size());
            contentValues.put("item_material", items[i].getItem_material());
            contentValues.put("item_desc", items[i].getItem_desc());
            contentValues.put("item_gender_category", items[i].getItem_gender_category());
            contentValues.put("item_category", items[i].getItem_category());
            contentValues.put("item_color", items[i].getItem_color());
            contentValues.put("Item_image", items[i].getItem_image());
            long ins = sdb.insert("item", null, contentValues);
            if (ins == -1) success_status = false;
            else success_status = true;
        }

        return success_status;
    }

    private void insert_items(SQLiteDatabase db) {
        ItemModel[] items = new ItemModel[1];
        for(int i = 0; i < items.length; i++) {
            items[i] = new ItemModel();
        }
        items[0].setItem_name("Camo Sunflower Tee");
        Log.e("in insert_items()", "starting creaating items");
        items[0].setItem_desc("");
        items[0].setItem_category("baju");
        items[0].setItem_color("Blue, White, Loreng");
        items[0].setItem_gender_category(0);
        items[0].setItem_material("100% cotton");
        items[0].setItem_price(210000);
        items[0].setItem_size("S, M, L, XL");
        items[0].setItem_image("baju_1");
        Log.e("in insert_items()", "starting getItem_name()");
        Log.e("in insert_items()", "set item name = " + items[0].getItem_name());
        Log.e("in insert_items()", "set caategory = " + items[0].getItem_category());
        if(!insert_items_to_db(db, items)) Log.e("insert error", "Data input error");
    }

    public ItemModel getItemByID(int id) {
        SQLiteDatabase sdb = this.getReadableDatabase();
        ItemModel item = new ItemModel();

        Cursor cursor = sdb.rawQuery("Select * from item where item_id=?", new String[]{String.valueOf(id)} );
        cursor.moveToFirst();
        item.setItem_id(cursor.getInt(0));
        item.setItem_name(cursor.getString(1));
        item.setItem_price(cursor.getInt(2));
        item.setItem_size(cursor.getString(3));
        item.setItem_material(cursor.getString(4));
        item.setItem_desc(cursor.getString(5));
        item.setItem_gender_category(cursor.getInt(6));
        item.setItem_category(cursor.getString(7));
        item.setItem_color(cursor.getString(8));
        item.setItem_image(cursor.getString(9));

        cursor.close();
        return item;
    }

    public ItemModel[] getItem_kategoriBaju() {
        //get total row
        String baju = "baju";
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT  * FROM item where item_category like ?", new String[]{"%" + baju + "%"});
        int count = cursor.getCount();
        cursor.moveToFirst();
        Log.e("insert", "finished counting," + cursor.getString(7));
        cursor.close();

        //initialize items object
        ItemModel[] items = new ItemModel[count];
        for(int i =  0; i < count; i++) {
            items[i] = new ItemModel();
        }

        cursor = sdb.rawQuery("SELECT  * FROM item where item_category like 'baju'", null);
        cursor.moveToFirst();
        items[0].setItem_id(cursor.getInt(0));
        items[0].setItem_name(cursor.getString(1));
        items[0].setItem_price(cursor.getInt(2));
        items[0].setItem_size(cursor.getString(3));
        items[0].setItem_material(cursor.getString(4));
        items[0].setItem_desc(cursor.getString(5));
        items[0].setItem_gender_category(cursor.getInt(6));
        items[0].setItem_category(cursor.getString(7));
        items[0].setItem_color(cursor.getString(8));
        items[0].setItem_image(cursor.getString(9));
        int i = 1;
        while(cursor.moveToNext()) {
            items[i].setItem_id(cursor.getInt(0));
            items[i].setItem_name(cursor.getString(1));
            items[i].setItem_price(cursor.getInt(2));
            items[i].setItem_size(cursor.getString(3));
            items[i].setItem_material(cursor.getString(4));
            items[i].setItem_desc(cursor.getString(5));
            items[i].setItem_gender_category(cursor.getInt(6));
            items[i].setItem_category(cursor.getString(7));
            items[i].setItem_color(cursor.getString(8));
            items[0].setItem_image(cursor.getString(9));
            i++;
        }
        cursor.close();
        return items;
    }



    //checking if email exists;
    public Boolean checkusername( String username){
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("Select * from user where username=?",new String[]{username});
        if(cursor.getCount()>0) {
            return false;
        }
        else {
            return true;
        }
    }
    //checking the email and password;
    public Boolean usernamepassword (String username,String password) {
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("select * from user where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }
}
