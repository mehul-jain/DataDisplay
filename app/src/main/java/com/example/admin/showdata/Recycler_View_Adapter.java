package com.example.admin.showdata;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 15-02-2018.
 */

public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> {
    List<Content_model>  content_list= Collections.emptyList();
    Context context;

    public Recycler_View_Adapter(List<Content_model> content_list, Context context) {
        this.content_list = content_list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        View_Holder holder= new View_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        // populate content
        holder.title.setText(content_list.get(position).getTitle());
        holder.description.setText(content_list.get(position).getDescription());
        holder.imageView.setImageResource(content_list.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return content_list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void insert(int position,Content_model content)
    {
        content_list.add(position,content);
        notifyItemInserted(position);
    }

}
