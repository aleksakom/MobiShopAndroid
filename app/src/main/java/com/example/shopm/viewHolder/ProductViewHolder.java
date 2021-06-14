package com.example.shopm.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopm.ItemClickListener;
import com.example.shopm.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textProductName, txtProdDescription, txtProdPrice;
    public ImageView imgView;
    public ItemClickListener listener;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        imgView=(ImageView) itemView.findViewById(R.id.product_image);
        textProductName=(TextView) itemView.findViewById(R.id.product_name);
        txtProdDescription=(TextView) itemView.findViewById(R.id.product_description);
        txtProdPrice=(TextView) itemView.findViewById(R.id.product_price);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
            listener.onClick(v, getAdapterPosition(), false);
    }
}
