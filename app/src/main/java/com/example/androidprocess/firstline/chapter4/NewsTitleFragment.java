package com.example.androidprocess.firstline.chapter4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidprocess.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsTitleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsTitleFragment extends Fragment {

    private int newsType;
    private List<News> mNewsList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsTitleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsTitleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsTitleFragment newInstance(String param1, String param2) {
        NewsTitleFragment fragment = new NewsTitleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        mNewsList = getNews();
        RecyclerView rcvTitle = view.findViewById(R.id.rcv_news_title);
        rcvTitle.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvTitle.setAdapter(new NewsTitleAdapter(mNewsList));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //活动创建完毕说明NewsContentFragment加载完成，初始显示数据
        showNewsContent(mNewsList.get(0));
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            News news = new News();
            news.setTitle("This is Title " + i);
            news.setContent(randomLengthContent("News Content Number " + i));
            newsList.add(news);
        }
        return newsList;
    }

    private String randomLengthContent(String content) {
        Random random = new Random();
        int t = random.nextInt(20);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            sb.append(content);
        }
        return sb.toString();
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    private void showNewsContent(News news) {
        FragmentManager fm = getFragmentManager();
        if (fm != null) {
            NewsContentFragment contentFragment = (NewsContentFragment) fm.findFragmentById(R.id.fm_activity_news_content);
            if (contentFragment != null) {
                contentFragment.refresh(news.getTitle(), news.getContent());
            }
        }
    }

    class NewsTitleAdapter extends RecyclerView.Adapter<NewsTitleAdapter.ViewHolder> {

        private List<News> newsList;

        public NewsTitleAdapter(List<News> newsList) {
            this.newsList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_title, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = newsList.get(position);
            holder.tvTitle.setText(news.getTitle());

            holder.tvTitle.setOnClickListener(v -> {
                if (newsType == 1) {
                    NewsContentActivity.actionStart(holder.tvTitle.getContext(), news.getTitle(), news.getContent());
                } else if(newsType == 2) {
                    showNewsContent(news);
                }
            });
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tv_item_title);
            }
        }
    }

}