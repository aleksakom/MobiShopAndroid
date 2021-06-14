package com.example.shopm.viewHolder;

import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopm.ItemClickListener;
import com.example.shopm.R;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shopm.ItemClickListener;
import com.example.shopm.R;

import java.util.Objects;

public class OrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtSName, txtSaddress, txtSPhone, txtSCity, txtSPostalCode;
    public ItemClickListener listener;

    public OrdersViewHolder(@NonNull View itemView) {
        super(itemView);

        txtSName=(TextView) itemView.findViewById(R.id.shoper_name);
        txtSaddress=(TextView) itemView.findViewById(R.id.shoper_address);
        txtSPhone=(TextView) itemView.findViewById(R.id.shoper_phone);
        txtSCity=(TextView) itemView.findViewById(R.id.shoper_city);
        txtSPostalCode=(TextView) itemView.findViewById(R.id.shoper_postalcode);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(), false);
    }
}
