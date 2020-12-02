package com.example.onlineshop;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListAdapterViewHolder> {
    private Context ctx;
    private int user_id;

    private int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }

    private ArrayList<ItemModel> dataList;

    public ItemListAdapter(ArrayList<ItemModel> dataList, Context ctx, int user_id) {
        this.dataList = dataList;
        this.ctx = ctx;
        this.user_id = user_id;
    }

    @Override
    public ItemListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new ItemListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemListAdapterViewHolder holder, int position) {
        holder.txtName.setText(dataList.get(position).getItem_name());
        holder.txtColor.setText(dataList.get(position).getItem_color());

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        holder.txtPrice.setText(kursIndonesia.format(dataList.get(position).getItem_price()));


        String url = "@drawable/" + dataList.get(position).getItem_image();  // where myresource (without the extension) is the file
        int imageResource = ctx.getResources().getIdentifier(url, null, ctx.getPackageName());
//        Log.e("InItemListAdapter", "imageResource = " + String.valueOf(imageResource));
        Drawable res = ctx.getResources().getDrawable(imageResource);
        holder.img.setImageDrawable(res);

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ctx, Item_Detail.class);
                intent.putExtra("item_id", dataList.get(position).getItem_id());
                intent.putExtra("user_id", user_id);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ItemListAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName, txtColor, txtPrice;
        private ImageView img;
        private Button btnDetail;

        public ItemListAdapterViewHolder(View itemView) {
            super(itemView);
            btnDetail = (Button) itemView.findViewById(R.id.cart_Item_card_btn_ItemDetail);
            img = (ImageView) itemView.findViewById(R.id.cart_item_card_itemImage);
            txtColor = (TextView) itemView.findViewById(R.id.cart_item_card_txt_ItemColor);
            txtName = (TextView) itemView.findViewById(R.id.cart_item_card_txt_itemName);
            txtPrice = (TextView) itemView.findViewById(R.id.cart_item_card_txt_itemPrice);
        }
    }
}
