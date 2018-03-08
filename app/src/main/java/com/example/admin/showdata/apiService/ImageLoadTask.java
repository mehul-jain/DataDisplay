package com.example.admin.showdata.apiService;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 23-02-2018.
 */

public class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;
    private HttpURLConnection connection;
    private Context context;
    private ContentLoadingProgressBar contentLoadingProgressBar;

    public ImageLoadTask(ImageView imageView, Context context, View view) {
        this.imageView = imageView;
        this.context = context;
        this.contentLoadingProgressBar = (ContentLoadingProgressBar) view;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        contentLoadingProgressBar.show();
    }

    @Override
    protected Bitmap doInBackground(String... url) {
        try {
            URL urlConnection = new URL(url[0]);
            connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            try (InputStream input = connection.getInputStream()) {
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                return bitmap;
            }
        } catch (MalformedURLException e) {
            Log.e("URL_ERROR", "Exception while fetching data: ", e);
        } catch (IOException e) {
            Log.e("IO_ERROR", "Exception while fetching data: ", e);
        } finally {
            connection.disconnect();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        contentLoadingProgressBar.hide();
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), result);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);
    }
}
