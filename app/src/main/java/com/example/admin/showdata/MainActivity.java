package com.example.admin.showdata;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskListener {
    private ContentLoadingProgressBar contentLoadingProgressBar;
    private DrawerLayout drawerLayout;
    private  NavigationView navigationView;
    @Override
    public void onTaskStarted() {
        contentLoadingProgressBar = (ContentLoadingProgressBar) findViewById(R.id.loader);
        contentLoadingProgressBar.show();
    }

    @Override
    public void onTaskFinished(List<ContentModel> list) {
        if(contentLoadingProgressBar!=null)
            contentLoadingProgressBar.hide();
        Log.d("fetched data", "" + list);
        if(list !=null && !list.isEmpty()) {
            // setting Recycler View
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list, getApplication());
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // getting data from the api
        new DownloadTask(this).execute("http://www.mocky.io/v2/5a85f4a33100006800253180");

        // setting image from url in header
        navigationView = findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        ImageView imageView= (ImageView) header.findViewById(R.id.profile_image);
        new ImageLoadTask(imageView).execute("http://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/sample1_l.jpg");

        // get id of drawer layout
        drawerLayout = findViewById(R.id.drawer_layout);

        // action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setting menu button on action bar
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        // navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.getTitle();
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Toast.makeText(getApplicationContext(),item.getTitle()+" clicked",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
        );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

}
