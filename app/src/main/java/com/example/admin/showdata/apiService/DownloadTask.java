package com.example.admin.showdata.apiService;

import android.os.AsyncTask;

import com.example.admin.showdata.apiService.callback.TaskListener;
import com.example.admin.showdata.model.ContentModel;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by admin on 22-02-2018.
 */

public class DownloadTask extends AsyncTask<String, Integer, List<ContentModel>> {

    private WeakReference<TaskListener> taskListener;

    public DownloadTask(TaskListener taskListener) {

        this.taskListener = new WeakReference<TaskListener>(taskListener);
    }

    @Override
    protected void onPreExecute() {
            if (taskListener.get() != null)
            taskListener.get().onTaskStarted();
    }

    @Override
    protected void onPostExecute(List<ContentModel> content_models) {
        if (taskListener.get() != null)
            taskListener.get().onTaskFinished(content_models);
    }

    @Override
    protected List<ContentModel> doInBackground(String... url) {
        DownloadData downloadData = new DownloadData();
        List<ContentModel> data = downloadData.getDataFromUrl(url[0]);
        return data;
    }
}
