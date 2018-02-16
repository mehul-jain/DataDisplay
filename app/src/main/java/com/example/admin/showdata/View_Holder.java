package com.example.admin.showdata;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 15-02-2018.
 */

public class View_Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView description;

    public View_Holder(View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.imageView);
        this.title = itemView.findViewById(R.id.title);
        this.description = itemView.findViewById(R.id.description);
    }
}
