package ma.tp_celebrity_list;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ma.tp_celebrity_list.adapter.StarAdapter;
import ma.tp_celebrity_list.beans.Star;
import ma.tp_celebrity_list.service.StarService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ShareCompat;
import android.view.MenuItem;
import android.widget.Toast;


public class ListActivity extends AppCompatActivity {

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;
    private static final String TAG = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Stars");
        TextView toolbarTitle = (TextView) toolbar.getChildAt(0);
        toolbarTitle.setTypeface(toolbarTitle.getTypeface(), Typeface.BOLD);
        Drawable overflowIcon = toolbar.getOverflowIcon();
        if (overflowIcon != null) {
            overflowIcon.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        }

        stars = new ArrayList<>();
        service = StarService.getInstance();

        init();

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        starAdapter = new StarAdapter(this, stars);
        recyclerView.setAdapter(starAdapter);
    }
    public void init() {

        service.create(new Star("Han SukKyu", "https://i.mydramalist.com/B0e75c.jpg", 4.5f));
        service.create(new Star("Park ShinHye", "https://i.mydramalist.com/Q3Nz7_5c.jpg", 5.0f));
        service.create(new Star("Ji ChangWook", "https://i.mydramalist.com/ZyyEJ_5c.jpg", 4.0f));
        service.create(new Star("Hyun Bin", "https://i.mydramalist.com/qAkLdc.jpg", 5.0f));
        service.create(new Star("IU", "https://i.mydramalist.com/W2mRD_5c.jpg", 5.0f));
        service.create(new Star("Song HyeKyo", "https://i.mydramalist.com/Bd1kDb_5c.jpg", 3.8f));
        service.create(new Star("Ma DongSeok", "https://i.mydramalist.com/pekDn_5c.jpg", 5.0f));
        service.create(new Star("Song KangJoon", "https://i.mydramalist.com/rbPe2c.jpg", 4.0f));
        service.create(new Star("Song JoongKi", "https://i.mydramalist.com/1kymd_5c.jpg", 4.7f));

        stars.addAll(service.findAll());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share) {
            String txt = "Check out my great Stars app!";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Share this app via:")
                    .setText(txt)
                    .startChooser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
