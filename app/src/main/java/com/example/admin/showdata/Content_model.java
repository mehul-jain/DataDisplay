package com.example.admin.showdata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15-02-2018.
 */

public class Content_model {
    private String title;
    private String description;
    public int imageId;

    public Content_model(String title, String description, int imageId) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description= description;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    // mock function to test
    public static List<Content_model> fill_data(){
        List<Content_model> list=new ArrayList<>();
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        list.add(new Content_model("Sample Title here ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut tortor quis dolor ornare  ", R.drawable.movie_night));
        return list;
    }
}
