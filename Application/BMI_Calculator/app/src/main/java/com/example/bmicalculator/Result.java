package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DecimalFormat;

public class Result extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser user;
    private LinearLayout linearLayout, headerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final TextView hiName = findViewById(R.id.hiName);
        final TextView bmi = findViewById(R.id.bmi);
        final TextView category = findViewById(R.id.category);
        linearLayout = findViewById(R.id.l1V1);
        final ImageView backBtn = findViewById(R.id.backBtn);
        final AppCompatButton logoutBtn = findViewById(R.id.logoutBtn);
        headerLayout = findViewById(R.id.topBar);
        mFirebaseAuth = FirebaseAuth.getInstance();


        Intent intent = getIntent();
        final String height = intent.getStringExtra("height");
        final String weight = intent.getStringExtra("weight");
        final String gender = intent.getStringExtra("gender");
        String name = getUserName();



        Float floatHeight = Float.parseFloat(height);
        Float floatWeight = Float.parseFloat(weight);

        floatHeight /= 100;
        Float numBMI = floatWeight / (floatHeight * floatHeight);
        final DecimalFormat df = new DecimalFormat("0.00");
        String BMI = df.format(numBMI).toString();

        hiName.setText(name);
        bmi.setText(BMI);
        if(numBMI < 16.0f){
            category.setText("Severe Thinness");
            category.setTextColor(Color.parseColor("#ff0505"));
        }else if(numBMI < 16.9 && numBMI > 16){
            category.setText("Moderate Thinness");
            category.setTextColor(Color.parseColor("#1ced43"));

        }else if(numBMI < 18.4 && numBMI > 17){
            category.setText("Mild Thinness");
            category.setTextColor(Color.parseColor("#07f033"));

        }else if(numBMI < 25 && numBMI > 18.4){
            category.setText("Normal");
            category.setTextColor(Color.parseColor("#07b327"));
        }else if(numBMI < 29.4 && numBMI > 25){
            category.setText("OverWeight");
            category.setTextColor(Color.parseColor("#ff0505"));
        }else{
            category.setText("Obese Class 1");

        }
        setBackgroundColor(gender);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut();
                startActivity(new Intent(Result.this, Login.class));
                finish();
            }
        });


    }

    private void setBackgroundColor(String gender) {
        if(gender.equals("Male")){

            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.male));
            linearLayout.setBackgroundResource(R.color.male);
            headerLayout.setBackgroundResource(R.color.male);
        }else if(gender.equals("Female")){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.female));
            linearLayout.setBackgroundResource(R.color.female);
            headerLayout.setBackgroundResource(R.color.female);
        }else{
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.male));
            linearLayout.setBackgroundResource(R.drawable.other_flag);
            headerLayout.setBackgroundResource(R.color.male);
        }
    }

    private String getUserName() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            return user.getDisplayName();
        }

        return " ";
    }
}