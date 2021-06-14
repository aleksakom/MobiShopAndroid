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

import com.example.shopm.model.Product;
import com.example.shopm.viewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.example.shopm.model.Product;

public class AdminSeeAllActivity extends AppCompatActivity {

    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_see_all);

        ProductsRef= FirebaseDatabase.getInstance().getReference().child("Products");
        recyclerView=findViewById(R.id.adminSeeeverything_);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Product> options= new FirebaseRecyclerOptions.Builder<Product>().setQuery(ProductsRef, Product.class).build();

        FirebaseRecyclerAdapter<Product, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Product model) {
                holder.textProductName.setText(model.getPname());
                holder.txtProdDescription.setText(model.getDescription());
                holder.txtProdPrice.setText("Price = " + model.getPrice() + "$");
                Picasso.get().load(model.getImage()).into(holder.imgView);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        CharSequence options[]=new CharSequence[]
                                {
                                        "Go back",
                                        "Delete"
                                };
                        AlertDialog.Builder builder=new AlertDialog.Builder(AdminSeeAllActivity.this);
                        builder.setTitle("Product options");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0)
                                {
                                    Intent intent=new Intent(AdminSeeAllActivity.this, AdminCategoryActivity.class);
                                    startActivity(intent);
                                }
                                else if(which==1)
                                {
                                    ProductsRef.child(model.getPid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    Toast.makeText(AdminSeeAllActivity.this, "Item deleted!", Toast.LENGTH_SHORT).show();
                                                }
                                            Intent intent=new Intent(AdminSeeAllActivity.this, AdminSeeAllActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            }
                        });
                        builder.show();
                    }
                });
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.products_layout, parent, false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}