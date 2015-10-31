package com.sputnikpogrom.ui.categories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sputnikpogrom.R;
import com.sputnikpogrom.entities.containers.ArticlesContainer;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.HashSet;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by veinhorn on 5.7.15.
 */
public class ArticlesAdapter extends BaseAdapter {
    private WindowManager windowManager;
    private Context context;
    private ArticlesContainer articles;
    private LayoutInflater layoutInflater;
    private Set<Target> targets;

    static class ViewHolder {
        @Bind(R.id.article_poster) ImageView poster;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public ArticlesAdapter(Context context, ArticlesContainer articles) {
        windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        this.context = context;
        this.articles = articles;
        layoutInflater = LayoutInflater.from(context);
        targets = new HashSet<>();
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.article_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                viewHolder.poster.setMinimumWidth(bitmap.getWidth());
                viewHolder.poster.setMinimumHeight(bitmap.getHeight());

                Point point = new Point();
                Display display = windowManager.getDefaultDisplay();
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                    display.getSize(point);
                } else {
                    point.set(display.getWidth(), display.getHeight());
                }
                if(point.x == 480 || point.x == 540) {
                    parent.setPadding(20, 0, 20, 0);
                    viewHolder.poster.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }

                viewHolder.poster.setImageBitmap(bitmap);
                targets.remove(this);
            }

            @Override public void onBitmapFailed(Drawable errorDrawable) {}

            @Override public void onPrepareLoad(Drawable placeHolderDrawable) {}
        };

        targets.add(target);
        String posterUrl = articles.getArticle(position).getPosterUrl();
        if(!"".equals(posterUrl)) Picasso.with(context).load(posterUrl).into(target);
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
