package com.example.shopm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.shopm.model.Product;

import io.paperdb.Paper;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView sphones, headPhones, laptops;
    private Button lgAdmin, orderAdm, seeAllAdm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        sphones=(ImageView) findViewById(R.id.phones);
        headPhones=(ImageView) findViewById(R.id.headphone);
        laptops=(ImageView) findViewById(R.id.laptops);
        lgAdmin=(Button)findViewById(R.id.admin_lg_btn_home);
        orderAdm=(Button)findViewById(R.id.admin_orders_btn);
        seeAllAdm=(Button)findViewById(R.id.admin_seeall_btn);


        Paper.init(this);


        sphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Phones");
                startActivity(intent);
            }
        });
        headPhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Headphones");
                startActivity(intent);
            }
        });
        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Laptops");
                startActivity(intent);
            }
        });
        lgAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                Intent intent=new Intent(AdminCategoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        orderAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AdminOrdersActivity.class);
                startActivity(intent);
            }
        });
        seeAllAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AdminSeeAllActivity.class);
                startActivity(intent);
            }
        });

    }
}