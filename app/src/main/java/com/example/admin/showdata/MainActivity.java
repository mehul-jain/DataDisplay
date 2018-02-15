package com.example.admin.showdata;

import android.os.AsyncTask;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting data from the api
        new DownloadTask().execute("http://www.mocky.io/v2/5a85f4a33100006800253180");
    }

    private class DownloadTask extends AsyncTask<String,Integer,List<ContentModel>>
    {
        private ContentLoadingProgressBar contentLoadingProgressBar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            contentLoadingProgressBar=  (ContentLoadingProgressBar) findViewById(R.id.loader);
               contentLoadingProgressBar.show();
        }

        @Override
        protected void onPostExecute(List<ContentModel> content_models) {
            contentLoadingProgressBar.hide();
            super.onPostExecute(content_models);
            Log.d("fetched data",""+content_models);

            // setting Recycler View
            RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
            RecyclerViewAdapter recycler_view_adapter= new RecyclerViewAdapter(content_models,getApplication());
            recyclerView.setAdapter(recycler_view_adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }

        @Override
        protected List<ContentModel> doInBackground(String... url) {
            DownloadData downloadData = new DownloadData();
            List<ContentModel> data= downloadData.getdatafromurl(url[0]);
            return data;
        }
    }

}
