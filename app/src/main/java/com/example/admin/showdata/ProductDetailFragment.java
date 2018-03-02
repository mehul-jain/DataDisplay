package com.example.admin.showdata;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 02-03-2018.
 */

public class ProductDetailFragment extends Fragment {
    int position = 0;
    private TextView tvTitle;
    private TextView tvDescription;
    private ImageView imageView;
    private ContentModel contentModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            // get back arguments
            if (getArguments() != null) {
                Bundle bundle = this.getArguments();
                contentModel = bundle.getParcelable("Product");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_view_frag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //set values for views here
        tvTitle = (TextView) view.findViewById(R.id.view_frag_title);
        tvDescription = (TextView) view.findViewById(R.id.view_frag_description);
        imageView = (ImageView) view.findViewById(R.id.view_frag_image);

        // update view
        tvTitle.setText(contentModel.getTitle());
        tvDescription.setText(contentModel.getDescription());
        imageView.setImageResource(contentModel.getImageId());

    }
}
