package com.example.shopm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shopm.model.Product;

import com.example.shopm.model.Product;
import com.example.shopm.prevalent.Prevalent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class ProductDetailsActivity extends AppCompatActivity {


    private Button buyNow;
    private ImageView productImage;
    private TextView productPrice, prodDesc, prodName;
    private String productID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productImage=(ImageView) findViewById(R.id.product_image_details);
        productPrice=(TextView) findViewById(R.id.product_price_details);
        prodDesc=(TextView) findViewById(R.id.product_description_details);
        prodName=(TextView) findViewById(R.id.product_name_details_cart);
        buyNow=(Button) findViewById(R.id.buy_item);

        productID=getIntent().getStringExtra("pid");

        getProductDetail(productID);

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductDetailsActivity.this, ConfrimFinalOrder.class);

                startActivity(intent);
            }
        });
    }

//    private void addToCart(){
//        String saveCurrentTime, saveCurrentDate;
//        Calendar calForDate= Calendar.getInstance();
//        SimpleDateFormat currentDate= new SimpleDateFormat("MMM dd, yyyy");
//        saveCurrentDate= currentDate.format(calForDate.getTime());
//
//        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
//        saveCurrentTime= currentTime.format(calForDate.getTime());
//        final DatabaseReference cartRef=FirebaseDatabase.getInstance().getReference().child("Cart List");
//
//        final HashMap<String, Object> cartMap=new HashMap<>();
//        cartMap.put("pid", productID);
//        cartMap.put("pname",prodName.getText().toString());
//        cartMap.put("price", productPrice.getText().toString());
//        cartMap.put("date", saveCurrentDate);
//        cartMap.put("time", saveCurrentTime);
//
//        cartRef.child("User View").child("Products").child(productID).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    cartRef.child("Admin View").child("Products").child(productID).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful())
//                                Toast.makeText(ProductDetailsActivity.this, "Added to cart list!", Toast.LENGTH_SHORT).show();
//
//                            Intent intent=new Intent(ProductDetailsActivity.this, Home2Activity.class);
//                            startActivity(intent);
//                        }
//                    });
//                }
//                else
//                    Toast.makeText(ProductDetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
    private void getProductDetail(String productID){
        DatabaseReference productsRef= FirebaseDatabase.getInstance().getReference().child("Products");
        productsRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Product product =snapshot.getValue(Product.class);
                    prodName.setText(product.getPname());
                    productPrice.setText(product.getPrice());
                    prodDesc.setText(product.getDescription());
                    Picasso.get().load(product.getImage()).into(productImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}