//package com.example.shopm;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.shopm.model.Cart;
//import com.example.shopm.prevalent.Prevalent;
//import com.example.shopm.viewHolder.CartViewHolder;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.squareup.picasso.Picasso;
//
//public class Cart2Activity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
//    private Button nextProcessBtn;
//    private TextView txtTotalPrice;
//    private String productID="";
//    //private String id=Prevalent.currentOnlineUser.getPhone();
//
//    private int totalPriceItems=0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cart2);
//
//        recyclerView=findViewById(R.id.cart_listtt);
//        recyclerView.setHasFixedSize(true);
//        layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        nextProcessBtn=(Button) findViewById(R.id.next_process_btn);
//        txtTotalPrice=(TextView) findViewById(R.id.totalprice);
//
//        productID=getIntent().getStringExtra("pid");
//
//
//        nextProcessBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                txtTotalPrice.setText("Total price " + String.valueOf(totalPriceItems));
//                Intent intent=new Intent(Cart2Activity.this, ConfrimFinalOrder.class);
//                intent.putExtra("Total Price", String.valueOf(totalPriceItems));
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");
//
//        FirebaseRecyclerOptions<Cart> options=new FirebaseRecyclerOptions.Builder<Cart>().setQuery(cartListRef.child("User View").child("Products").child(productID), Cart.class).build();
//
//        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter=new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model) {
//                holder.txtProductName.setText("Product name " + model.getPname());
//                holder.txtProductPrice.setText("Product price " + model.getPrice());
//
//                int valueOfOneItem=((Integer.valueOf(model.getPrice())));
//                totalPriceItems =totalPriceItems+valueOfOneItem;
//
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        CharSequence options[]=new CharSequence[] {
//                                "Edit",
//                                "Delete"
//                        };
//                        AlertDialog.Builder builder=new AlertDialog.Builder(Cart2Activity.this);
//                        builder.setTitle("Cart Options");
//                        builder.setItems(options, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if(which==0)
//                                {
//                                    Intent intent=new Intent(Cart2Activity.this, ProductDetailsActivity.class);
//                                    intent.putExtra("pid", model.getPid());
//                                    startActivity(intent);
//                                }
//                                if(which==1)
//                                {
//                                    cartListRef.child("User View").child("Products").child(productID).child(model.getPid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if(task.isSuccessful())
//                                            {
//                                                Toast.makeText(Cart2Activity.this, "Item removed!", Toast.LENGTH_SHORT).show();
//                                                Intent intent=new Intent(Cart2Activity.this, Home2Activity.class);
//
//                                                startActivity(intent);
//                                            }
//                                        }
//                                    });
//                                }
//                            }
//                        });
//                        builder.show();
//                    }
//                });
//            }
//
//            @NonNull
//            @Override
//            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
//                CartViewHolder holder= new CartViewHolder(view);
//                return holder;
//            }
//        };
//        recyclerView.setAdapter(adapter);
//        adapter.startListening();
//    }
//}