package com.example.admin.showdata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
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
    HttpURLConnection connection;

    public ImageLoadTask(ImageView imageView) {
        this.imageView = imageView;
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
        imageView.setImageBitmap(result);
    }
}