package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements OnCompleteListener{
    private EditText username,password;
    private Button btnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.txt_UserName);
        password = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btn_Login);
        
        handleLogin();
    }

    private void handleLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String pass = password.getText().toString();

                if(email.isEmpty()){
                    username.setError("Không được để trống");
                    username.requestFocus();
                }

                if(pass.isEmpty()){
                    password.setError("Không được để trống");
                    password.requestFocus();
                }

                System.out.println(email);
                System.out.println(pass);

                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG);
                            System.out.println("dawng nhap that bai");
                        }else{
                            Intent intent = new Intent(MainActivity.this,List_user.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this,"Sucess",Toast.LENGTH_LONG);

                        }
                    }
                });
            }
        });
    }


    @Override
    public void onComplete(@NonNull Task task) {

    }
}