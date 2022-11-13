package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressBar mProgressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final AppCompatButton loginBtn = findViewById(R.id.loginBtn);
        final TextView registerBtn = findViewById(R.id.regNowBtn);
        mAuth = FirebaseAuth.getInstance();
        mProgressbar = findViewById(R.id.loginProgressbar);


        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.white));


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressbar.setVisibility(View.VISIBLE);
                final String e_mail = email.getText().toString();
                final String pass = password.getText().toString();

                if(e_mail.isEmpty()) {
                    mProgressbar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }
                if (pass.isEmpty()){
                    mProgressbar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Please Enter your Password!", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(e_mail, pass);
                }



            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }

//    Method to login the user with the given Credentials
    private void loginUser(String e_mail, String pass) {
        mAuth.signInWithEmailAndPassword(e_mail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            mProgressbar.setVisibility(View.GONE);
                            finish();
                        }else{
                            mProgressbar.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "Login Failed! Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}