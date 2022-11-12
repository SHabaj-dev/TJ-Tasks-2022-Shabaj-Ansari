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

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        final EditText fullName = findViewById(R.id.fullName);
        final EditText phone = findViewById(R.id.regPhone);
        final EditText pass = findViewById(R.id.regPass);
        final EditText confPass = findViewById(R.id.confPass);
        final EditText email = findViewById(R.id.emailReg);

        final AppCompatButton signUp = findViewById(R.id.signUp);
        final TextView signIn = findViewById(R.id.signIn);
        mAuth = FirebaseAuth.getInstance();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fName = fullName.getText().toString();
                final String ph = phone.getText().toString();
                final String pswd = pass.getText().toString();
                final String e_mail = email.getText().toString();
                final String cPswd = confPass.getText().toString();

                if(fName.isEmpty() || ph.isEmpty() || pswd.isEmpty() || cPswd.isEmpty() || e_mail.isEmpty()){
                    Toast.makeText(Register.this, "Please Fill the Credential Carefully", Toast.LENGTH_SHORT).show();
                }else if(!pswd.equals(cPswd)){
                    Toast.makeText(Register.this, "Password didn't Match", Toast.LENGTH_SHORT).show();
                }else{

                    mAuth.createUserWithEmailAndPassword(e_mail, pswd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Register.this, MainActivity.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(Register.this, "Registration Failed!! Please Try Again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}