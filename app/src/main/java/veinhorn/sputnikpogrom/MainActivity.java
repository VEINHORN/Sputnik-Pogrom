package veinhorn.sputnikpogrom;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.adapters.ShortArticlesAdapter;
import veinhorn.sputnikpogrom.entities.containers.ShortArticlesContainer;
import veinhorn.sputnikpogrom.loaders.ArticlesLoader;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.short_articles_list_view)
    GridView shortArticlesGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        ShortArticlesContainer shortArticlesContainer = new ShortArticlesContainer();
        ShortArticlesAdapter shortArticlesAdapter = new ShortArticlesAdapter(this, shortArticlesContainer);
        shortArticlesGridView.setAdapter(shortArticlesAdapter);

        ArticlesLoader articlesLoader = new ArticlesLoader(shortArticlesAdapter, shortArticlesContainer);
        articlesLoader.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}