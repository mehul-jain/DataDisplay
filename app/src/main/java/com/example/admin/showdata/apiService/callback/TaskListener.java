package com.example.admin.showdata.apiService.callback;

import com.example.admin.showdata.model.ContentModel;

import java.util.List;

/**
 * Created by admin on 22-02-2018.
 */

public interface TaskListener {
    void onTaskStarted();
    void onTaskFinished(List<ContentModel> list);
}
