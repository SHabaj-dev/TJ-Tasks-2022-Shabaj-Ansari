package com.example.recipebook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class ListDescription extends AppCompatActivity {

    private TextView title, timeRequired;
    private ListView ingredients;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_description);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        title = findViewById(R.id.tvDesName);
//        ingredients = findViewById(R.id.lvIngredients);
        imageView = findViewById(R.id.ivDescription);
        timeRequired = findViewById(R.id.timeRequired);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            title.setText(bundle.getString("Title"));
            actionBar.setTitle(bundle.getString("Title"));
            imageView.setImageResource(bundle.getInt("Image"));
            timeRequired.setText(bundle.getString("Time"));
        }



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}