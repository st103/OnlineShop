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

public class CartItemListAdapter extends RecyclerView.Adapter<CartItemListAdapter.CartItemListAdapterViewHolder> {

    private ArrayList<ItemModel> dataList;

    public CartItemListAdapter(ArrayList<ItemModel> dataList, Context ctx) {
        this.dataList = dataList;
    }

    @Override
    public CartItemListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new CartItemListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartItemListAdapterViewHolder holder, int position) {
        holder.txtName.setText(dataList.get(position).getItem_name());
        holder.txtColor.setText(dataList.get(position).getItem_color());
        holder.txtDesc.setText(dataList.get(position).getItem_desc());
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        holder.txtPrice.setText(kursIndonesia.format(dataList.get(position).getItem_price()));


    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class CartItemListAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName, txtColor, txtPrice, txtDesc;
        private ImageView img;

        public CartItemListAdapterViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.cart_item_card_itemImage);
            txtDesc = (TextView) itemView.findViewById(R.id.cart_item_card_txt_ItemDesc);
            txtColor = (TextView) itemView.findViewById(R.id.cart_item_card_txt_ItemColor);
            txtName = (TextView) itemView.findViewById(R.id.cart_item_card_txt_itemName);
            txtPrice = (TextView) itemView.findViewById(R.id.cart_item_card_txt_itemPrice);
        }
    }
}
