package com.example.admin.showdata;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15-02-2018.
 */

public class DownloadData {
    HttpURLConnection connection;
    List<ContentModel> data_list;


    public List<ContentModel> getdatafromurl(String passed_url){
        StringBuilder result = new StringBuilder();
        try {
            // getting url
            URL url = new URL(passed_url);
            // setting connection
            connection = (HttpURLConnection) url.openConnection();
            // setting stream
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            // convert whole response to string
            String resultStr=result.toString();
            Log.d("resultStr",resultStr);
            try {
                // getting array from string
                JSONArray jsonArray= new JSONArray(resultStr);
                data_list= new ArrayList<>();
                // parse array
                for (int i=0; i< jsonArray.length();i++)
                {
                    ContentModel content_model = new ContentModel();
                    // getting  individual object
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    content_model.setTitle(jsonObject.getString("title"));
                    content_model.setDescription(jsonObject.getString("description"));
                    content_model.setImageId(R.drawable.movie_night);
                    data_list.add(content_model);
                }

            } catch (Throwable t) {
                Log.d("Result String", "Could not parse malformed JSON: \"" + resultStr + "\"");
            }
        }catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
        }

             return data_list;
    }
}
