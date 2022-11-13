package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.white));

        final EditText fullName = findViewById(R.id.fullName);
        final EditText age = findViewById(R.id.regAge);
        final EditText pass = findViewById(R.id.regPass);
        final EditText confPass = findViewById(R.id.confPass);
        final EditText email = findViewById(R.id.emailReg);

        final AppCompatButton signUp = findViewById(R.id.signUp);
        final TextView signIn = findViewById(R.id.signIn);
        mAuth = FirebaseAuth.getInstance();
        mProgressbar = findViewById(R.id.regProgressbar);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressbar.setVisibility(View.VISIBLE);
                final String fName = fullName.getText().toString();
                final String mAge = age.getText().toString();
                final String pswd = pass.getText().toString();
                final String e_mail = email.getText().toString();
                final String cPswd = confPass.getText().toString();

                if(fName.isEmpty() || mAge.isEmpty() || pswd.isEmpty() || cPswd.isEmpty() || e_mail.isEmpty()){
                    mProgressbar.setVisibility(View.GONE);
                    Toast.makeText(Register.this, "Please Fill the Credential Carefully", Toast.LENGTH_SHORT).show();
                }else if(!pswd.equals(cPswd)){
                    mProgressbar.setVisibility(View.GONE);
                    Toast.makeText(Register.this, "Password didn't Match", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(e_mail, pswd, fName, mAge);

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

//    Method to register the user and save it to the Firebase.
    private void registerUser(String e_mail, String pswd, String fName, String age) {
        mAuth.createUserWithEmailAndPassword(e_mail, pswd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            saveUserInfo(fName, age);
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            mProgressbar.setVisibility(View.GONE);
                            startActivity(intent);
                        }else{
                            mProgressbar.setVisibility(View.GONE);
                            Toast.makeText(Register.this, "Registration Failed!! Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


//    Method to save the userName of the user to the
    private void saveUserInfo(String fName, String age){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(fName)
                .setPhotoUri(Uri.parse(age))
                .build();
        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d("PRINT", "User Updated");
                }
            }
        });
    }
}