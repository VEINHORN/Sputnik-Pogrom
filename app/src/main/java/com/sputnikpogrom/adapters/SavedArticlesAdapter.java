package com.sputnikpogrom.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sputnikpogrom.entities.containers.SavedArticlesContainer;
import com.sputnikpogrom.entities.containers.ShortArticlesContainer;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 25.3.15.
 */
public class SavedArticlesAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private SavedArticlesContainer savedArticlesContainer;

    static class ViewHolder {
        @InjectView(R.id.short_article_poster) ImageView poster;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public SavedArticlesAdapter(Context context, SavedArticlesContainer savedArticlesContainer) {
        this.context = context;
        this.savedArticlesContainer = savedArticlesContainer;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return savedArticlesContainer.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return savedArticlesContainer.getSavedArticle(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.short_article_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Picasso.with(context).load(savedArticlesContainer.getSavedArticle(position).getPoster()).into(viewHolder.poster);
        return convertView;
    }
}