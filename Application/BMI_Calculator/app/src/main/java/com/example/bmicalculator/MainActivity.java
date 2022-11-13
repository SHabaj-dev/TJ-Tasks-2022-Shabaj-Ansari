package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final String[] gender = {"Male",
                            "Female",
                            "Transgender"};
    String gndr;
    private FirebaseAuth mFirebaseAuth;
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.white));


//        final EditText name = findViewById(R.id.mainName);
//        final EditText age = findViewById(R.id.mainAge);
        final EditText height = findViewById(R.id.mainHeight);
        final EditText weight = findViewById(R.id.mainWeight);
        Spinner spinner = findViewById(R.id.genderSpinner);
        final AppCompatButton calculate = findViewById(R.id.calculateBtn);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mProgressbar = findViewById(R.id.mainProgressbar);

        spinner.setOnItemSelectedListener(this);


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_list, gender);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        spinner.setAdapter(arrayAdapter);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressbar.setVisibility(View.VISIBLE);
//                final String sName = name.getText().toString();
//                final String sAge = age.getText().toString();
                final String sHeight = height.getText().toString();
                final String sWeight = weight.getText().toString();
//                Toast.makeText(MainActivity.this,gndr, Toast.LENGTH_SHORT).show();

                if(sHeight.isEmpty() || sWeight.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Fill All the Credentials", Toast.LENGTH_SHORT).show();
                    mProgressbar.setVisibility(View.GONE);
                }else{
                    Intent intent = new Intent(MainActivity.this, Result.class);
//                    intent.putExtra("name", sName);
//                    intent.putExtra("age", sAge);
                    intent.putExtra("height", sHeight);
                    intent.putExtra("weight", sWeight);
                    intent.putExtra("gender", gndr);
                    mProgressbar.setVisibility(View.GONE);
                    startActivity(intent);
                }

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser != null){

        }else{
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        gndr = gender[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}