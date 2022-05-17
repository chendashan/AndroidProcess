package com.example.androidprocess.firstline.chapter3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidprocess.R;

import java.util.List;

/**
 * @author chendashan
 * @date 2022/5/17
 */
public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int mResource;

    public FruitAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> objects) {
        super(context, resource, objects);
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder= new ViewHolder();
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(mResource, parent, false);
            viewHolder.ivPhoto = view.findViewById(R.id.iv_fruit);
            viewHolder.tvName = view.findViewById(R.id.tv_fruit_name);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.ivPhoto.setImageResource(fruit.getRecId());
        viewHolder.tvName.setText(fruit.getName());
        return view;
    }

    class ViewHolder {
        TextView tvName;
        ImageView ivPhoto;
    }
}
