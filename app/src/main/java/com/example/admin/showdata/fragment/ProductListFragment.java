package com.example.admin.showdata.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.showdata.apiService.DownloadTask;
import com.example.admin.showdata.R;
import com.example.admin.showdata.recyclerview.RecyclerViewAdapter;
import com.example.admin.showdata.apiService.callback.TaskListener;
import com.example.admin.showdata.model.ContentModel;

import java.util.List;

/**
 * Created by admin on 01-03-2018.
 */

public class ProductListFragment extends Fragment implements TaskListener {
    private ContentLoadingProgressBar contentLoadingProgressBar;
    private DownloadTask downloadTask;
    private View fragmentView;

    @Override
    public void onTaskStarted() {
        contentLoadingProgressBar = (ContentLoadingProgressBar) fragmentView.findViewById(R.id.loader);
        contentLoadingProgressBar.show();
    }

    @Override
    public void onTaskFinished(List<ContentModel> list) {
        if (contentLoadingProgressBar != null)
            contentLoadingProgressBar.hide();
        Log.d("fetched data", "" + list);
        if (list != null && !list.isEmpty()) {
            // setting Recycler View
            RecyclerView recyclerView = (RecyclerView) fragmentView.findViewById(R.id.recycler_view);
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list, getActivity());
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.product_list_frag, container, false);
        // getting data from the api
        downloadTask = new DownloadTask(this);
        downloadTask.execute("http://www.mocky.io/v2/5a85f4a33100006800253180");
        return fragmentView;
    }


    // Define the events that the fragment will use to communicate
    public interface OnItemSelectedListener {
        void onItemSelected(ContentModel contentModel);
    }
}
