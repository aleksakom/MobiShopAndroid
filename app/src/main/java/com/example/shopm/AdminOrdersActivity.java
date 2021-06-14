package com.example.shopm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shopm.model.Orders;
import com.example.shopm.model.Product;
import com.example.shopm.viewHolder.ProductViewHolder;
import com.example.shopm.viewHolder.OrdersViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AdminOrdersActivity extends AppCompatActivity {

    private DatabaseReference OrdersRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders);

        OrdersRef= FirebaseDatabase.getInstance().getReference().child("Bought Items");
        recyclerView=findViewById(R.id.adminSeeeOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Orders> options= new FirebaseRecyclerOptions.Builder<Orders>().setQuery(OrdersRef, Orders.class).build();

        FirebaseRecyclerAdapter<Orders, OrdersViewHolder> adapter = new FirebaseRecyclerAdapter<Orders, OrdersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull OrdersViewHolder holder, int position, @NonNull Orders model) {
                holder.txtSName.setText(model.getNameo());
                holder.txtSaddress.setText(model.getAdresao());
                holder.txtSCity.setText(model.getCityo());
                holder.txtSPostalCode.setText(model.getPostalcodeo());
                holder.txtSPhone.setText(model.getPhoneo());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        CharSequence options[]=new CharSequence[]
                                {
                                        "Send order",
                                        "Delete"
                                };
                        AlertDialog.Builder builder=new AlertDialog.Builder(AdminOrdersActivity.this);
                        builder.setTitle("Orders Option");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0)
                                {
                                    OrdersRef.child(model.getOpid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(AdminOrdersActivity.this, "Product is sent!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                                else if(which==1)
                                {
                                    OrdersRef.child(model.getOpid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(AdminOrdersActivity.this, "Order deleted!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        builder.show();
                    }
                });

//              holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent=new Intent(AdminOrdersActivity.this, AdminSeeAllActivity.class);
//                        startActivity(intent);
//                   }
//                });
            }

            @NonNull
           @Override
            public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
                OrdersViewHolder holder = new OrdersViewHolder(view);
                return holder;
            }
       };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}