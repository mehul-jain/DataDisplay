package com.example.admin.showdata;

import android.os.AsyncTask;
import java.util.List;

/**
 * Created by admin on 22-02-2018.
 */

public class DownloadTask extends AsyncTask<String, Integer, List<ContentModel>> {

    private final TaskListener taskListener;

    public DownloadTask(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    @Override
    protected void onPreExecute() {
        taskListener.onTaskStarted();
    }

    @Override
    protected void onPostExecute(List<ContentModel> content_models) {
        taskListener.onTaskFinished(content_models);
    }

    @Override
    protected List<ContentModel> doInBackground(String... url) {
        DownloadData downloadData = new DownloadData();
        List<ContentModel> data = downloadData.getDataFromUrl(url[0]);
        return data;
    }
}
