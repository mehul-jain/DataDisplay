package com.example.admin.showdata;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 15-02-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    List<ContentModel> content_list = Collections.emptyList();
    private Context context;
    private ProductListFragment.OnItemSelectedListener onItemSelectedListener;

    public RecyclerViewAdapter(List<ContentModel> content_list, Context context) {
        this.content_list = content_list;
        this.context = context;
        this.onItemSelectedListener = (ProductListFragment.OnItemSelectedListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // populate content
        holder.title.setText(content_list.get(position).getTitle());
        holder.description.setText(content_list.get(position).getDescription());
        holder.imageView.setImageResource(content_list.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return content_list.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView description;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.title = itemView.findViewById(R.id.title);
            this.description = itemView.findViewById(R.id.description);

            // setting on click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    // going to the activity to load the details fragment
                    // communicating with the activity using listener
                    onItemSelectedListener.onItemSelected(content_list.get(pos));
                }
            });
        }
    }
}
