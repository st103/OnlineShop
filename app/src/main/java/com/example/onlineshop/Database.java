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
        db.execSQL("Create table cart(cart_id INTEGER primary key autoincrement, item_id int, " +
                "user_id int, buy_status int," +
                "FOREIGN KEY(item_id) REFERENCES item(item_id)," +
                "FOREIGN KEY(user_id) REFERENCES user(user_id))");
//        Log.e("onCreate", "finished onCreate() db func");
        insert_items(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db =  this.getReadableDatabase();
        db.execSQL("drop table if exists user");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
//        Log.e("In DatabaseOnOpen", "start opening daaatabase");
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

    public boolean insertItemToCart(int user_id, int item_id){
        boolean success_status = false;
        SQLiteDatabase sdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item_id", item_id);
        contentValues.put("user_id", user_id);
        contentValues.put("buy_status", 0);
        long ins = sdb.insert("cart", null, contentValues);
        if (ins == -1) success_status = false;
        else success_status = true;

        return success_status;
    }

    private boolean insert_items_to_db(SQLiteDatabase sdb, ItemModel[] items) {
//        Log.e("in insertitemtodb", "start sqliitedatabase  sdb");
//        Log.e("in insertitemtodb", "finish sqliitedatabase  sdb");
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
        ItemModel[] items = new ItemModel[34];
        for(int i = 0; i < items.length; i++) {
            items[i] = new ItemModel();
        }

        //Insert items
        //----------------------------------------------------------
        items[0].setItem_name("Camo Sunflower Tee");
        items[0].setItem_desc("");
        items[0].setItem_category("baju");
        items[0].setItem_color("Blue, White, Loreng");
        items[0].setItem_gender_category(0);
        items[0].setItem_material("100% cotton");
        items[0].setItem_price(210000);
        items[0].setItem_size("S, M, L, XL");
        items[0].setItem_image("baju_1");
        //----------------------------------------------------------
        items[1].setItem_name("Heather Rose Helen Hoodie");
        items[1].setItem_desc("");
        items[1].setItem_category("jaket");
        items[1].setItem_color("Pink, Grey");
        items[1].setItem_gender_category(0);
        items[1].setItem_material("50% polyester / 38% cotton / 12% rayon");
        items[1].setItem_price(353000);
        items[1].setItem_size("S, M, L, XL");
        items[1].setItem_image("jaket_1");
        //----------------------------------------------------------
        items[2].setItem_name("Tie-Dye Three-Quarter Sleeve Empire-Waist Top");
        items[2].setItem_desc("");
        items[2].setItem_category("baju");
        items[2].setItem_color("BLUE");
        items[2].setItem_gender_category(0);
        items[2].setItem_material("100% polyester");
        items[2].setItem_price(240000);
        items[2].setItem_size("27, 29, 30,31");
        items[2].setItem_image("baju_2");
        //----------------------------------------------------------
        items[3].setItem_name("Vintage Modern High-Rise Dungaree Ankle Jeans");
        items[3].setItem_desc("");
        items[3].setItem_category("celana");
        items[3].setItem_color("Dessert brown, Blue sky");
        items[3].setItem_gender_category(0);
        items[3].setItem_material("100% cotton");
        items[3].setItem_price(475000);
        items[3].setItem_size("27, 29, 30,31");
        items[3].setItem_image("celana_1");
        //----------------------------------------------------------
        items[4].setItem_name("Vintage Modern High-Rise Dungaree Denim Shorts");
        items[4].setItem_desc("");
        items[4].setItem_category("celana");
        items[4].setItem_color("Dessert brown, Blue sky");
        items[4].setItem_gender_category(0);
        items[4].setItem_material("100% cotton");
        items[4].setItem_price(375000);
        items[4].setItem_size("27, 29, 30,31");
        items[4].setItem_image("celana_2");
        //----------------------------------------------------------
        items[5].setItem_name("Ronda Jeanne Button-Up Denim Skirt");
        items[5].setItem_desc("");
        items[5].setItem_category("Rok");
        items[5].setItem_color("Dessert brown, Blue sky");
        items[5].setItem_gender_category(0);
        items[5].setItem_material("81% cotton / 19% rayon");
        items[5].setItem_price(489000);
        items[5].setItem_size("S, M, L, XL");
        items[5].setItem_image("rok_1");
        //----------------------------------------------------------
        items[6].setItem_name("Lafley Alaine Leather Sandal");
        items[6].setItem_desc("2.75 inch heel, Hook and loop closure, Textile lining");
        items[6].setItem_category("sepatu");
        items[6].setItem_color("Black");
        items[6].setItem_gender_category(0);
        items[6].setItem_material("Leather upper, Rubber sole");
        items[6].setItem_price(489000);
        items[6].setItem_size("36, 37, 38, 39, 40, 41");
        items[6].setItem_image("sepatu_1");
        //----------------------------------------------------------
        items[7].setItem_name("Laina Violet Leather Bootie");
        items[7].setItem_desc("3.35 inch heel, Textile lining");
        items[7].setItem_category("sepatu");
        items[7].setItem_color("Black");
        items[7].setItem_gender_category(0);
        items[7].setItem_material("Leather upper, Rubber sole");
        items[7].setItem_price(489000);
        items[7].setItem_size("36, 37, 38, 39, 40, 41");
        items[7].setItem_image("sepatu_2");
        //----------------------------------------------------------
        items[8].setItem_name("Vibe Flip-Flop");
        items[8].setItem_desc("1.1 inch heel, Textile lining");
        items[8].setItem_category("sandal");
        items[8].setItem_color("Rose Gold Brio");
        items[8].setItem_gender_category(0);
        items[8].setItem_material("Man-made upper, TPR sole");
        items[8].setItem_price(234000);
        items[8].setItem_size("36, 37, 38, 39, 40, 41");
        items[8].setItem_image("sandal_1");
        //----------------------------------------------------------
        items[9].setItem_name("Denim Button-Up Maxi Dress");
        items[9].setItem_desc("52.36 inch long from high point of shoulder to hem");
        items[9].setItem_category("baju");
        items[9].setItem_color("BLUE");
        items[9].setItem_gender_category(0);
        items[9].setItem_material("35% cotton / 65% polyester");
        items[9].setItem_price(449000);
        items[9].setItem_size("S");
        items[9].setItem_image("baju_3");
        //----------------------------------------------------------
        items[10].setItem_name("Sherpa-Lined Full-Zip Hoodie");
        items[10].setItem_desc("Zip closure, Drawstring hood, Rib knit cuffs and waistband, Sherpa-lined");
        items[10].setItem_category("jaket");
        items[10].setItem_color("Blue navy,  green, dark grey, grey");
        items[10].setItem_gender_category(1);
        items[10].setItem_material("100% polyester");
        items[10].setItem_price(439000);
        items[10].setItem_size("M, L, XL, 2XL, 3XL");
        items[10].setItem_image("jaket_2");
        //----------------------------------------------------------
        items[11].setItem_name("Waffle-Knit Thermal Shirt Set");
        items[11].setItem_desc("");
        items[11].setItem_category("baju");
        items[11].setItem_color("black, heather navy and white");
        items[11].setItem_gender_category(1);
        items[11].setItem_material("60% cotton / 40% polyester");
        items[11].setItem_price(319000);
        items[11].setItem_size("M, L, XL, 2XL, 3XL");
        items[11].setItem_image("baju_4");
        //----------------------------------------------------------
        items[12].setItem_name("Since 1952 Anaconda Tee");
        items[12].setItem_desc("");
        items[12].setItem_category("baju");
        items[12].setItem_color("Red, White, Navy, Black");
        items[12].setItem_gender_category(1);
        items[12].setItem_material("100% cotton");
        items[12].setItem_price(299000);
        items[12].setItem_size("S M, L, XL");
        items[12].setItem_image("baju_5");
        //----------------------------------------------------------
        items[13].setItem_name("Pocket Track Pants");
        items[13].setItem_desc("");
        items[13].setItem_category("celana");
        items[13].setItem_color("blue, black");
        items[13].setItem_gender_category(1);
        items[13].setItem_material("100% polyester");
        items[13].setItem_price(479000);
        items[13].setItem_size("S M, L, XL");
        items[13].setItem_image("celana_3");
        //----------------------------------------------------------
        items[14].setItem_name("Rocco Moto Relaxed Straight-Leg Jeans");
        items[14].setItem_desc("");
        items[14].setItem_category("celana");
        items[14].setItem_color("light blue");
        items[14].setItem_gender_category(1);
        items[14].setItem_material("99% cotton / 1% elastane");
        items[14].setItem_price(519000);
        items[14].setItem_size("S M, L, XL");
        items[14].setItem_image("celana_4");
        //----------------------------------------------------------
        items[15].setItem_name("Rocco Moto Relaxed Straight-Leg Jeans");
        items[15].setItem_desc("");
        items[15].setItem_category("baju");
        items[15].setItem_color("aqua, green,  orange, red, black");
        items[15].setItem_gender_category(1);
        items[15].setItem_material("100% cotton");
        items[15].setItem_price(349000);
        items[15].setItem_size("S M, L, XL");
        items[15].setItem_image("baju_6");
        //----------------------------------------------------------
        items[16].setItem_name("Gridway Canvas & Leather Sneaker");
        items[16].setItem_desc("Textile lining");
        items[16].setItem_category("sepatu");
        items[16].setItem_color("black, light blue, olive, white, grey");
        items[16].setItem_gender_category(1);
        items[16].setItem_material("Canvas / leather upper, Rubber sole");
        items[16].setItem_price(449000);
        items[16].setItem_size("41, 42, 43, 44, 45, 46");
        items[16].setItem_image("sepatu_3");
        //----------------------------------------------------------
        items[17].setItem_name("Wp Leather Sneaker");
        items[17].setItem_desc("Textile lining");
        items[17].setItem_category("sepatu");
        items[17].setItem_color("black, light blue, olive, white, grey");
        items[17].setItem_gender_category(1);
        items[17].setItem_material("Nylon arch shank, Rubber storm welt, Leather upper");
        items[17].setItem_price(510000);
        items[17].setItem_size("41, 42, 43, 44, 45, 46");
        items[17].setItem_image("sepatu_4");
        //----------------------------------------------------------
        items[18].setItem_name("Benjamin Leather Oxford");
        items[18].setItem_desc("Lace-up closure, Leather lining");
        items[18].setItem_category("sepatu");
        items[18].setItem_color("brown");
        items[18].setItem_gender_category(1);
        items[18].setItem_material("Leather upper, , Rubber sole");
        items[18].setItem_price(539000);
        items[18].setItem_size("41, 42, 43, 44, 45, 46");
        items[18].setItem_image("sepatu_5");
        //----------------------------------------------------------
        items[19].setItem_name("Allgood Slip-On Sneake");
        items[19].setItem_desc("");
        items[19].setItem_category("sepatu");
        items[19].setItem_color("black");
        items[19].setItem_gender_category(1);
        items[19].setItem_material("Man-made upper, Man-made lining, Man-made sole");
        items[19].setItem_price(389000);
        items[19].setItem_size("41, 42, 43, 44, 45, 46");
        items[19].setItem_image("sepatu_6");
        //----------------------------------------------------------
        items[20].setItem_name("Gehry Sandal");
        items[20].setItem_desc("EVA sole");
        items[20].setItem_category("sandal");
        items[20].setItem_color("black");
        items[20].setItem_gender_category(1);
        items[20].setItem_material("Textile upper, Man-made lining");
        items[20].setItem_price(255000);
        items[20].setItem_size("41, 42, 43, 44, 45, 46");
        items[20].setItem_image("sandal_2");
        //----------------------------------------------------------
        items[21].setItem_name("Hi-Top Sneaker");
        items[21].setItem_desc("Lace-up");
        items[21].setItem_category("sepatu");
        items[21].setItem_color("blue");
        items[21].setItem_gender_category(2);
        items[21].setItem_material("Man-made upper, Man-made lining, Man-made sole");
        items[21].setItem_price(167000);
        items[21].setItem_size("28-34");
        items[21].setItem_image("sepatu_7");
        //----------------------------------------------------------
        items[22].setItem_name("Suede Moccasin");
        items[22].setItem_desc("");
        items[22].setItem_category("sepatu");
        items[22].setItem_color("brown");
        items[22].setItem_gender_category(2);
        items[22].setItem_material("Suede upper Faux, fur lining, Man-made sole");
        items[22].setItem_price(179000);
        items[22].setItem_size("28-31");
        items[22].setItem_image("sepatu_8");
        //----------------------------------------------------------
        items[22].setItem_name("Suede Moccasin");
        items[22].setItem_desc("");
        items[22].setItem_category("sepatu");
        items[22].setItem_color("brown");
        items[22].setItem_gender_category(2);
        items[22].setItem_material("Suede upper Faux, fur lining, Man-made sole");
        items[22].setItem_price(179000);
        items[22].setItem_size("28-31");
        items[22].setItem_image("sepatu_8");
        //----------------------------------------------------------
        items[23].setItem_name("Zip-Front Fleece Hooded Playsuit");
        items[23].setItem_desc("");
        items[23].setItem_category("baju");
        items[23].setItem_color("red, white");
        items[23].setItem_gender_category(2);
        items[23].setItem_material("100% polyester");
        items[23].setItem_price(260000);
        items[23].setItem_size("S, M, L, XL");
        items[23].setItem_image("baju_7");
        //----------------------------------------------------------
        items[24].setItem_name("Long-Sleeve Dress Set");
        items[24].setItem_desc("");
        items[24].setItem_category("baju");
        items[24].setItem_color("white rose, macan");
        items[24].setItem_gender_category(2);
        items[24].setItem_material("100% cotton");
        items[24].setItem_price(152000);
        items[24].setItem_size("S, M, L, XL");
        items[24].setItem_image("baju_8");
        //----------------------------------------------------------
        items[25].setItem_name("Belted Cargo Shorts");
        items[25].setItem_desc("");
        items[25].setItem_category("celana");
        items[25].setItem_color("white, Brown, black ,grey");
        items[25].setItem_gender_category(2);
        items[25].setItem_material("60% cotton / 40% polyester");
        items[25].setItem_price(152000);
        items[25].setItem_size("S, M, L, XL");
        items[25].setItem_image("celana_5");
        //----------------------------------------------------------
        items[26].setItem_name("Floral Pocket Joggers");
        items[26].setItem_desc("");
        items[26].setItem_category("celana");
        items[26].setItem_color("white. Brown, black ,grey");
        items[26].setItem_gender_category(2);
        items[26].setItem_material("95% polyester / 5% spandex");
        items[26].setItem_price(162000);
        items[26].setItem_size("S, M, L, XL");
        items[26].setItem_image("celana_6");
        //----------------------------------------------------------
        items[27].setItem_name("Merry & Bright Lightweight Hoodie");
        items[27].setItem_desc("Full graphic text: Merry and bright");
        items[27].setItem_category("jaket");
        items[27].setItem_color("red, blue, grey");
        items[27].setItem_gender_category(2);
        items[27].setItem_material("60% cotton / 40% polyester");
        items[27].setItem_price(184000);
        items[27].setItem_size("S, M, L, XL");
        items[27].setItem_image("jaket_3");
        //----------------------------------------------------------
        items[28].setItem_name("Stripe Bunny Appliqué T-Shirt Dress");
        items[28].setItem_desc("");
        items[28].setItem_category("baju");
        items[28].setItem_color("pink, blue");
        items[28].setItem_gender_category(2);
        items[28].setItem_material("100% cotton");
        items[28].setItem_price(199000);
        items[28].setItem_size("S, M, L, XL");
        items[28].setItem_image("baju_9");
        //----------------------------------------------------------
        items[29].setItem_name("Denim Button-Up");
        items[29].setItem_desc("");
        items[29].setItem_category("baju");
        items[29].setItem_color("black ");
        items[29].setItem_gender_category(2);
        items[29].setItem_material("100% cotton");
        items[29].setItem_price(150000);
        items[29].setItem_size("S, M, L, XL");
        items[29].setItem_image("baju_10");
        //----------------------------------------------------------
        items[30].setItem_name("Harrison Silk Tie");
        items[30].setItem_desc("");
        items[30].setItem_category("aksesoris");
        items[30].setItem_color("Navy & Red");
        items[30].setItem_gender_category(1);
        items[30].setItem_material("100% silk");
        items[30].setItem_price(159000);
        items[30].setItem_size("3 inch W x 60 inch L");
        items[30].setItem_image("aksesoris_1");
        //----------------------------------------------------------
        items[31].setItem_name("Lords Prayer Leather Crossbody Bag");
        items[31].setItem_desc("Zip closure, Adjustable strap, Graphic text: The Lords prayer");
        items[31].setItem_category("aksesoris");
        items[31].setItem_color("Black, Light brown");
        items[31].setItem_gender_category(3);
        items[31].setItem_material("Leather");
        items[31].setItem_price(159000);
        items[31].setItem_size("8 inch W x 10.5 inch H");
        items[31].setItem_image("aksesoris_2");
        //----------------------------------------------------------
        items[32].setItem_name("Stainless Steel Triangle-Accent Belt");
        items[32].setItem_desc("");
        items[32].setItem_category("aksesoris");
        items[32].setItem_color("Black, Light brown");
        items[32].setItem_gender_category(3);
        items[32].setItem_material("Man-made, Leather");
        items[32].setItem_price(342000);
        items[32].setItem_size("-");
        items[32].setItem_image("aksesoris_4");
        //----------------------------------------------------------
        items[33].setItem_name("Round Aviator Sunglasses");
        items[33].setItem_desc("");
        items[33].setItem_category("aksesoris");
        items[33].setItem_color("Gunmetal & Blue");
        items[33].setItem_gender_category(3);
        items[33].setItem_material("Metal");
        items[33].setItem_price(212000);
        items[33].setItem_size("Lens width: 58 mm, Bridge distance: 18 mm, Arm length: 145 mm");
        items[33].setItem_image("aksesoris_3");




        /*if(!*/insert_items_to_db(db, items);//) Log.e("insert error", "Data input error");
    }

    public ItemModel getItemByID(int id) {
//        Log.e("getItembyId", "start getting item by Id");
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

//        Log.e("getItembyId", "finished getting item by Id");
        return item;
    }

    public ItemModel[] getItemByCategoryAndGender(String category, int gender) {
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT  * FROM item where item_gender_category=? and item_category like ?", new String[]{String.valueOf(gender), "%" + category + "%"});
        int count = cursor.getCount();
        cursor.moveToFirst();
        cursor.close();

        ItemModel []items = new ItemModel[count];
        for(int i = 0; i < items.length; i++) {
            items[i] = new ItemModel();
        }

        cursor = sdb.rawQuery("SELECT  * FROM item where item_gender_category=? and item_category like ?", new String[]{String.valueOf(gender), "%" + category + "%"});
        cursor.moveToFirst();
        int i = 0;
        items[i].setItem_id(cursor.getInt(0));
        items[i].setItem_name(cursor.getString(1));
        items[i].setItem_price(cursor.getInt(2));
        items[i].setItem_size(cursor.getString(3));
        items[i].setItem_material(cursor.getString(4));
        items[i].setItem_desc(cursor.getString(5));
        items[i].setItem_gender_category(cursor.getInt(6));
        items[i].setItem_category(cursor.getString(7));
        items[i].setItem_color(cursor.getString(8));
        items[i].setItem_image(cursor.getString(9));
        while(cursor.moveToNext()) {
            i++;
            items[i].setItem_id(cursor.getInt(0));
            items[i].setItem_name(cursor.getString(1));
            items[i].setItem_price(cursor.getInt(2));
            items[i].setItem_size(cursor.getString(3));
            items[i].setItem_material(cursor.getString(4));
            items[i].setItem_desc(cursor.getString(5));
            items[i].setItem_gender_category(cursor.getInt(6));
            items[i].setItem_category(cursor.getString(7));
            items[i].setItem_color(cursor.getString(8));
            items[i].setItem_image(cursor.getString(9));
        }

        return items;
    }

    public ItemModel[] getItemByGender(int gender)  {
        //get total row
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT  * FROM item where item_gender_category=?", new String[]{String.valueOf(gender)});
        int count = cursor.getCount();
        cursor.moveToFirst();
//        Log.e("insert", "finished counting," + cursor.getString(7));
        cursor.close();

        ItemModel []items = new ItemModel[count];
        for(int i = 0; i < items.length; i++) {
            items[i] = new ItemModel();
        }

        cursor = sdb.rawQuery("SELECT  * FROM item where item_gender_category=?", new String[]{String.valueOf(gender)});
        cursor.moveToFirst();
        int i = 0;
        items[i].setItem_id(cursor.getInt(0));
        items[i].setItem_name(cursor.getString(1));
        items[i].setItem_price(cursor.getInt(2));
        items[i].setItem_size(cursor.getString(3));
        items[i].setItem_material(cursor.getString(4));
        items[i].setItem_desc(cursor.getString(5));
        items[i].setItem_gender_category(cursor.getInt(6));
        items[i].setItem_category(cursor.getString(7));
        items[i].setItem_color(cursor.getString(8));
        items[i].setItem_image(cursor.getString(9));
        while(cursor.moveToNext()) {
            i++;
            items[i].setItem_id(cursor.getInt(0));
            items[i].setItem_name(cursor.getString(1));
            items[i].setItem_price(cursor.getInt(2));
            items[i].setItem_size(cursor.getString(3));
            items[i].setItem_material(cursor.getString(4));
            items[i].setItem_desc(cursor.getString(5));
            items[i].setItem_gender_category(cursor.getInt(6));
            items[i].setItem_category(cursor.getString(7));
            items[i].setItem_color(cursor.getString(8));
            items[i].setItem_image(cursor.getString(9));
        }

        return items;
    }

    public ItemModel[] getItemByCategory(String category) {
        //get total row
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT  * FROM item where item_category like ?", new String[]{"%" + category + "%"});
        int count = cursor.getCount();
        cursor.moveToFirst();
//        Log.e("insert", "finished counting," + cursor.getString(7));
        cursor.close();

        //initialize items object
        ItemModel[] items = new ItemModel[count];
        for(int i =  0; i < count; i++) {
            items[i] = new ItemModel();
        }

        cursor = sdb.rawQuery("SELECT  * FROM item where item_category like ?", new String[]{"%" + category + "%"});
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
            items[i].setItem_image(cursor.getString(9));
            i++;
        }
        cursor.close();
        return items;
    }

    public CartModel[] getUnpaidCartByUserId(int userId) {
        //get total row
//        Log.e("getCartByUserId", "start get caart by user id");
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT  * FROM cart where user_id=? and buy_status=0", new String[]{String.valueOf(userId)});
        int count = cursor.getCount();
//        Log.e("cart row", "got " + count + " items");
        CartModel []emptyCart = new CartModel[0];
        if(count == 0) return emptyCart;
        cursor.moveToFirst();


        CartModel []cart = new CartModel[count];
        int i = 0;
        for(i = 0; i < cart.length; i++) {
            cart[i] = new CartModel();
        }

//        Log.e("init cart[]", "finished init caart[]");
        cursor.moveToFirst();
        i = 0;
        do {
            cart[i].setCart_id(cursor.getInt(0));
            cart[i].setItem_id(cursor.getInt(1));
            cart[i].setUser_id(cursor.getInt(2));
            cart[i].setBuy_status(cursor.getInt(3));
            i++;
        } while(cursor.moveToNext());

        cursor.close();

//        Log.e("getCartByUserId", "finished get caart by user id");
        return cart;
    }

    public void buyItem(int user_id) {

        SQLiteDatabase sdb = this.getWritableDatabase();

        CartModel [] cartModels = getUnpaidCartByUserId(user_id);

        for(int i = 0; i < cartModels.length; i++) {
            ContentValues values = new ContentValues();
            values.put("buy_status", "1");
            String selection = "cart_id=?";
            String[] selectionArgs = {String.valueOf(cartModels[i].getCart_id())};
            sdb.update("cart", values, selection,selectionArgs);
        }
    }

    //checking if user cart is empty;
    public Boolean cartIsEmpty( int userId){
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("Select * from cart where user_id=? and buy_status=0",new String[]{String.valueOf(userId)});
        if(cursor.getCount()==0) {
            return true;
        }
        else {
            return false;
        }
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
