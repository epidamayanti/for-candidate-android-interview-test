package com.tokopedia.testproject.problems.news.common;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tokopedia.testproject.R;
import com.tokopedia.testproject.problems.news.model.Article;

import java.util.ArrayList;
import java.util.List;


public class ImageAdapter extends PagerAdapter {


    private ArrayList<Article> imageModelArrayList;
    private LayoutInflater inflater;
    private Context context;

    public ImageAdapter(ArrayList<Article> articleList) {
        setArticleList(articleList);
    }

    void setArticleList(ArrayList<Article> articleList) {
        if (articleList == null) {
            this.imageModelArrayList = new ArrayList<>();
        } else {
            this.imageModelArrayList = articleList;
        }
    }

    public ImageAdapter(Context context, ArrayList<Article> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);

        Glide.with(imageView).load(imageModelArrayList.get(position).getUrlToImage()).into(imageView);
        //imageView.setImageResource(imageModelArrayList.get(position).getUrlToImage());

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}