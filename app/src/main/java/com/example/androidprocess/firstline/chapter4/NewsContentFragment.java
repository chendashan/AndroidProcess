package com.example.androidprocess.firstline.chapter4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidprocess.R;

/**
 * @author chendashan
 * @date 2022/5/18
 */
public class NewsContentFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag, container, false);
        return view;
    }

    public void refresh(String newsTitle, String newsContent) {
        View newsView  = view.findViewById(R.id.ll_news_view);
        newsView.setVisibility(View.VISIBLE);

        TextView tvTitle = view.findViewById(R.id.tv_news_title);
        TextView tvContent = view.findViewById(R.id.tV_news_content);

        tvTitle.setText(newsTitle);
        tvContent.setText(newsContent);
    }
}
