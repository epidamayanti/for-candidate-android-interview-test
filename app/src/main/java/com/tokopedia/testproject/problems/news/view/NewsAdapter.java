package com.tokopedia.testproject.problems.news.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tokopedia.testproject.R;
import com.tokopedia.testproject.problems.news.model.Article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> articleList;
    private List<Article> arraylist;
    private String tdc = "";
    private int count = 0;


    NewsAdapter(List<Article> articleList) {
        setArticleList(articleList);
    }

    void setArticleList(List<Article> articleList) {
        if (articleList == null) {
            this.articleList = new ArrayList<>();
            this.arraylist = new ArrayList<>();
        } else {
            this.articleList = articleList;
            Collections.sort(articleList, StringDescComparator);
            this.arraylist.addAll(articleList);
            this.tdc = date(articleList.get(0).getPublishedAt());
            this.count = 0;
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NewsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        newsViewHolder.bind(articleList.get(i));
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvDate;
        TextView tvDateCategory;


        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDateCategory = itemView.findViewById(R.id.tv_date_category);
        }

        void bind(Article article) {
            //String date = article.getPublishedAt().replaceAll("[TZ]","");
            if(count == 0){
                tvDateCategory.setVisibility(View.VISIBLE);
                tvDateCategory.setText(date(article.getPublishedAt()));
                count++;
                Log.d("count", ""+date(article.getPublishedAt()));
            }
            else if(tdc.equals(date(article.getPublishedAt())) && count > 1){
                tvDateCategory.setVisibility(View.GONE);
                count++;
                Log.d("date gone: ", ""+date(article.getPublishedAt()));
            }
            else if(!tdc.equals(date(article.getPublishedAt()))){
                count = 1 ;
                tvDateCategory.setVisibility(View.VISIBLE);
                tvDateCategory.setText(date(article.getPublishedAt()));
                tdc = date(article.getPublishedAt());
                Log.d("count", ""+date(article.getPublishedAt()));

                //tvDateCategory.setVisibility(View.VISIBLE);
            }

            Glide.with(itemView).load(article.getUrlToImage()).into(imageView);
            tvTitle.setText(article.getTitle());
            tvDescription.setText(article.getDescription());
            tvDate.setText(date(article.getPublishedAt()));

        }
    }

    public String date(String d){
        String date, month, year, dates;
        year = d.substring(0,4);
        month = d.substring(5,7);
        date = d.substring(8,10);

        String[] months = {"-", "January", "February", "March", "April", "May", "June", "July", "August","September", "November", "December"};

        dates = date+" "+months[Integer.parseInt(month)]+" "+year;
        return dates;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        articleList.clear();
        if (charText.length() == 0) {
            articleList.addAll(arraylist);
        } else {
            for (Article wp : arraylist) {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    articleList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static Comparator<Article> StringDescComparator = new Comparator<Article>() {

        public int compare(Article app1, Article app2) {

            String stringName1 = app1.getPublishedAt();
            String stringName2 = app2.getPublishedAt();

            return stringName2.compareToIgnoreCase(stringName1);
        }
    };


}
