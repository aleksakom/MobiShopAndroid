//package com.example.shopm.viewHolder;
//
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.shopm.ItemClickListener;
//import com.example.shopm.R;
//
//public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//    public TextView txtProductName, txtProductPrice;
//    private ItemClickListener itemClickListener;
//
//    public CartViewHolder(View itemView){
//        super(itemView);
//        txtProductName=itemView.findViewById(R.id.cart_product_name);
//        txtProductPrice=itemView.findViewById(R.id.product_price);
//    }
//
//    @Override
//    public void onClick(View v) {
//
//        itemClickListener.onClick(v, getAdapterPosition(), false);
//    }
//
//    public void setItemClickListener(ItemClickListener itemClickListener){
//        this.itemClickListener=itemClickListener;
//    }
//}
