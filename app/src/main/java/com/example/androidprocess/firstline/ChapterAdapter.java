package com.example.androidprocess.firstline;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidprocess.R;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private List<Chapter> mChapterList;

    public ChapterAdapter(List<Chapter> chapterList) {
        this.mChapterList = chapterList;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chapter chapter = mChapterList.get(position);
        holder.button.setText(chapter.getName());

        holder.button.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), chapter.getCls());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mChapterList.size();
    }

    static class ChapterViewHolder extends RecyclerView.ViewHolder {

        Button button;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.bt_chapter);
        }
    }
}
