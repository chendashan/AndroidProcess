package com.example.androidprocess.firstline.chapter3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidprocess.R;

import java.util.List;

/**
 * @author chendashan
 * @date 2022/5/17
 */
public class FruitRecyclerAdapter extends RecyclerView.Adapter<FruitRecyclerAdapter.FruitViewHolder> {

    private final List<Fruit> mFruitList;

    public FruitRecyclerAdapter(List<Fruit> mFruitList) {
        this.mFruitList = mFruitList;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit_recycle, parent, false);
        return new FruitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.textView.setText(fruit.getName());
        holder.imageView.setImageResource(fruit.getRecId());

        holder.fruitView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "FruitView  " + fruit.getName(), Toast.LENGTH_SHORT).show();
        });
        holder.imageView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "ImageView  " + fruit.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    static class FruitViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView imageView;
        TextView textView;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitView = itemView;
            imageView = itemView.findViewById(R.id.iv_fruit);
            textView = itemView.findViewById(R.id.tv_fruit_name);
        }
    }
}
