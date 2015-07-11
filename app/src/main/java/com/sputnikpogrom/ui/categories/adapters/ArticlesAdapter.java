package com.sputnikpogrom.ui.categories.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sputnikpogrom.entities.Article;
import com.sputnikpogrom.entities.containers.ArticlesContainer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 5.7.15.
 */
public class ArticlesAdapter extends BaseAdapter {
    private Context context;
    private ArticlesContainer articles;
    private LayoutInflater layoutInflater;


    static class ViewHolder {
        @InjectView(R.id.article_poster) ImageView poster;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public ArticlesAdapter(Context context, ArticlesContainer articles) {
        this.context = context;
        this.articles = articles;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.article_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.poster.setImageDrawable(context.getResources().getDrawable(R.drawable.testimage));

        return convertView;
    }

    @Override
    public int getCount() {
        return articles.getSize();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return articles.getArticle(position);
    }
}
