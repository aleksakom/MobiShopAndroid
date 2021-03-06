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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputName, InputPhoneNumber, InputPassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccountButton= (Button) findViewById(R.id.register_btn);
        InputName= (EditText) findViewById(R.id.register_name_input);
        InputPhoneNumber= (EditText) findViewById(R.id.register_phone_number_input);
        InputPassword= (EditText) findViewById(R.id.register_password_input);
        loadingBar=new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateAccount ();
            }
        });
    }
    private void CreateAccount()
    {
        String name= InputName.getText().toString();
        String phone= InputPhoneNumber.getText().toString();
        String password= InputPassword.getText().toString();

        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Write your name!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Write your phone number!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Write your password!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Create account...");
            loadingBar.setMessage("Please wait.");
            loadingBar.setCanceledOnTouchOutside(false); //ako klikne negdje nece izaci dok ne popuni sve
            loadingBar.show();

            ValidatePhoneNumber(name, phone, password);
        }

    }
    private void ValidatePhoneNumber(String name, String phone, String password)
    {
        final DatabaseReference RootRef;

        RootRef= FirebaseDatabase.getInstance().getReference(); //referenca na rut

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String, Object> userDataMap= new HashMap<>();  //hesmapa za unos u bazu
                    userDataMap.put("phone", phone);
                    userDataMap.put("password", password);
                    userDataMap.put("name", name);

                    RootRef.child("Users").child(phone).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "Congrats! Your account has been created.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent=new Intent(RegisterActivity.this, LoginActivity.class); //nakon sto se registruje ide da se prijavi
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Network error...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "This" + phone + " alredy exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Use another phone number", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}