package com.example.shopm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopm.model.Users;
import com.example.shopm.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import com.example.shopm.model.Product;

public class ConfrimFinalOrder extends AppCompatActivity {

    private EditText nameEdit, phoneEdit, adresEdit, cityEdit, postalcodeEdit;
    private String nameo, phoneo, adresao, cityo, postalcodeo, saveCurrentTime, saveCurrentDate;
    private Button confirmOrderAndBuy;
    private String productRandomKey;
    private DatabaseReference cartRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrim_final_order);


        confirmOrderAndBuy=(Button)findViewById(R.id.confirm_orderandbuy);
        nameEdit=(EditText)findViewById(R.id.shimpnet_name);
        phoneEdit=(EditText)findViewById(R.id.shimpnet_phone);
        adresEdit=(EditText)findViewById(R.id.shimpnet_address);
        cityEdit=(EditText)findViewById(R.id.shimpnet_city);
        postalcodeEdit=(EditText)findViewById(R.id.shimpnet_postalcode);
        cartRef= FirebaseDatabase.getInstance().getReference().child("Bought Items");

        confirmOrderAndBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });
    }
    private void ValidateProductData()
    {
        nameo=nameEdit.getText().toString();
        phoneo=phoneEdit.getText().toString();
        adresao=adresEdit.getText().toString();
        cityo=cityEdit.getText().toString();
        postalcodeo=postalcodeEdit.getText().toString();

        Calendar calForDate= Calendar.getInstance();
        SimpleDateFormat currentDate= new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate= currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime= currentTime.format(calForDate.getTime());

        productRandomKey= saveCurrentDate+saveCurrentTime;

        if(TextUtils.isEmpty(nameo))
        {
            Toast.makeText(this, "Add name!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phoneo))
        {
            Toast.makeText(this, "Add phone!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(adresao))
        {
            Toast.makeText(this, "Add address!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cityo))
        {
            Toast.makeText(this, "Add city!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(postalcodeo))
        {
            Toast.makeText(this, "Add postal code!", Toast.LENGTH_SHORT).show();
        }
        else
        {
                StoreProductInformation();
        }
    }
    public void StoreProductInformation(){

//        Calendar calForDate= Calendar.getInstance();
//        SimpleDateFormat currentDate= new SimpleDateFormat("MMM dd, yyyy");
//        saveCurrentDate= currentDate.format(calForDate.getTime());
//
//        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
//        saveCurrentTime= currentTime.format(calForDate.getTime());
//        final DatabaseReference cartRef= FirebaseDatabase.getInstance().getReference().child("Bought Items");

  //      productRandomKey= saveCurrentDate+saveCurrentTime;

        HashMap<String, Object> cartMap=new HashMap<>();
        cartMap.put("opid", productRandomKey);
        cartMap.put("nameo",nameo);
        cartMap.put("phoneo",phoneo);
        cartMap.put("adresao",adresao);
        cartMap.put("cityo", cityo);
        cartMap.put("postalcodeo", postalcodeo);
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);

        cartRef.child(productRandomKey).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {

                    Intent intent=new Intent(ConfrimFinalOrder.this, Home2Activity.class);
                    startActivity(intent);

                    Toast.makeText(ConfrimFinalOrder.this,"You bought Product successfully!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String message=task.getException().toString();
                    Toast.makeText(ConfrimFinalOrder.this,"Error:" + message, Toast.LENGTH_SHORT).show();
                }
            }
        });

//        cartRef.child("Products").child("Orders").updateChildren(cartMap);
//
//        Toast.makeText(ConfrimFinalOrder.this, "Congrats! You bought an item!", Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(ConfrimFinalOrder.this, Home2Activity.class);
//            startActivity(intent);



        //.addOnCompleteListener(new OnCompleteListener<Void>() {

//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    cartRef.child("Admin View").updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful())
//                                Toast.makeText(ConfrimFinalOrder.this, "Congrats! You bought an item!", Toast.LENGTH_SHORT).show();
//
//                            Intent intent=new Intent(ConfrimFinalOrder.this, Home2Activity.class);
//                            startActivity(intent);
//                        }
//                    });
//                }
//
//            }
//        });
    }
}