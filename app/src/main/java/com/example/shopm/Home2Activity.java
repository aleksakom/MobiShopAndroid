package com.example.shopm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.paperdb.Paper;

public class Home2Activity extends AppCompatActivity {

    private Button category_homBtn, orders_homBtn, logout_homBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        Paper.init(this);
        category_homBtn=(Button)findViewById(R.id.user_category_btn);
        orders_homBtn=(Button)findViewById(R.id.user_orders_btn);
        logout_homBtn=(Button)findViewById(R.id.user_logout_btn_home);

        category_homBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent=new Intent(Home2Activity.this, Category2Activity.class);
                startActivity(intent);
            }
        });
        logout_homBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Paper.book().destroy();
                Intent intent=new Intent(Home2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        orders_homBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home2Activity.this, AbtUActivity.class);
                startActivity(intent);
            }
        });
    }
}