package com.example.admin.showdata;

import android.content.Intent;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskListener {
    private ContentLoadingProgressBar contentLoadingProgressBar;

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

    }
    public void sendMessage(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

}
