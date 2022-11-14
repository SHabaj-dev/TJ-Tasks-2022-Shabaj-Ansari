package com.example.recipebook;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<FoodData> mFoodList;
    private FoodData mFoodData;
    private FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mFoodList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        MyAdapter myAdapter = new MyAdapter(MainActivity.this, mFoodList);
        mRecyclerView.setAdapter(myAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

//        db.collection("recipies")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                String name =  document.getData().get("Title").toString();
//                                List<String> Ingredients = (List<String>) document.getData().get("Ingredients");
//                                String imageUrl = document.getData().get("Image").toString();
//                                addData(name, Ingredients, imageUrl);
////                                Toast.makeText(MainActivity.this, Ingredients.toString() , Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });


        List<String> dummy = new ArrayList<>();
        dummy.add("abc");
        dummy.add("def");

        mFoodData = new FoodData("Biryani", "50 min", dummy, R.drawable.biryani);
        mFoodList.add(mFoodData);
        mFoodData = new FoodData("Fish Curry", "30 min", dummy, R.drawable.fish);
        mFoodList.add(mFoodData);
        mFoodData = new FoodData("Tea", "05 min", dummy, R.drawable.tea);
        mFoodList.add(mFoodData);


    }

    private void addData(String name, List<String> Ingredients, String imageUrl){
////        String[] dummyIng = {"a", "b"};
//        mFoodData = new FoodData(name, "50 min", Ingredients, R.drawable.);
//        mFoodList.add(mFoodData);
    }

}