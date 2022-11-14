package com.example.recipebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<FoodViewHolder>{

    private Context mContext;
    private List<FoodData> myFoodList;

    public MyAdapter(Context mContext, List<FoodData> myFoodList) {
        this.mContext = mContext;
        this.myFoodList = myFoodList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);

        return new FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.imageView.setImageResource(myFoodList.get(position).getImage());
        holder.mTitle.setText(myFoodList.get(position).getItemName());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ListDescription.class);
                intent.putExtra("Image", myFoodList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Title", myFoodList.get(holder.getAdapterPosition()).getItemName());
                intent.putExtra("Time", myFoodList.get(holder.getAdapterPosition()).getTimeTaken());
//                intent.putExtra("Ing", myFoodList.get(holder.getAdapterPosition()).getIngredients());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }
}


class FoodViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle;
    CardView mCardView;

    public FoodViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.cardView);

    }
}
