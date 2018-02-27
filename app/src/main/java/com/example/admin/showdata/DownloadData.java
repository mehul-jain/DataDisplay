package com.example.admin.showdata;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15-02-2018.
 */

public class DownloadData {
    HttpURLConnection connection;
    List<ContentModel> dataList;


    public List<ContentModel> getDataFromUrl(String passed_url) {
        StringBuilder result = new StringBuilder();
        try {
            // getting url
            URL url = new URL(passed_url);
            // setting connection
            connection = (HttpURLConnection) url.openConnection();
            // setting stream

            try (
                    InputStream in = new BufferedInputStream(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }

            // convert whole response to string
            String resultStr = result.toString();
            try {
                // getting array from string
                JSONArray jsonArray = new JSONArray(resultStr);
                dataList = new ArrayList<>();
                // parse array
                for (int i = 0; i < jsonArray.length(); i++) {
                    ContentModel content_model = new ContentModel();
                    // getting  individual object
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    content_model.setTitle(jsonObject.getString("title"));
                    content_model.setDescription(jsonObject.getString("description"));
                    content_model.setImageId(R.drawable.movie_night);
                    dataList.add(content_model);
                }

            } catch (JSONException e) {
                Log.e("JSON_ERROR", "Exception while fetching data: ", e);
            }
        } catch (MalformedURLException e) {
            Log.e("URL_ERROR", "Exception while fetching data: ", e);
        } catch (IOException e) {
            Log.e("IO_ERROR", "Exception while fetching data: ", e);
        } finally {
            connection.disconnect();
        }

        return dataList;
    }
}
