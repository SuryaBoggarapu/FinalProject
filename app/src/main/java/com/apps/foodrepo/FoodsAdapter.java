package com.apps.foodrepo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.MyViewHolder> {

    List<Food> foods = new ArrayList<>();
    Context context;

    public FoodsAdapter(List<Food> foods, Context context) {
        this.foods = foods;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_food,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(foods.get(position).getImg()).into(holder.img);
        holder.name.setText(foods.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_food);
            img = itemView.findViewById(R.id.img_food);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context,DetailsActivity.class);
                    i.putExtra("data",foods.get(getAdapterPosition()));
                    context.startActivity(i);
                }
            });

        }
    }
}
