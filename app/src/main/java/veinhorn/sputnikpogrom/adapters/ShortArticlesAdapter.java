package veinhorn.sputnikpogrom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.R;
import veinhorn.sputnikpogrom.entities.containers.ShortArticlesContainer;

/**
 * Created by veinhorn on 19.2.15.
 */
public class ShortArticlesAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ShortArticlesContainer shortArticlesContainer;

    static class ViewHolder {
        @InjectView(R.id.short_article_poster) ImageView poster;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public ShortArticlesAdapter(Context context, ShortArticlesContainer shortArticlesContainer) {
        this.context = context;
        this.shortArticlesContainer = shortArticlesContainer;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return shortArticlesContainer.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return shortArticlesContainer.getShortArticle(position);
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
        String posterUrl = shortArticlesContainer.getShortArticle(position).getPosterUrl();
        Picasso.with(context).load(posterUrl).into(viewHolder.poster);
        return convertView;
    }
}