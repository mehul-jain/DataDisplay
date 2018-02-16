package com.example.admin.showdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting mock data for test
        List<Content_model> content=Content_model.fill_data();

        // setting Recycler View
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        Recycler_View_Adapter recycler_view_adapter= new Recycler_View_Adapter(content,getApplication());
        recyclerView.setAdapter(recycler_view_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
