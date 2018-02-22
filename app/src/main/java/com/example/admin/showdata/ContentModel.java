package com.example.admin.showdata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15-02-2018.
 */

public class ContentModel {
    
    private String title;
    private String description;
    public int imageId;

    public ContentModel() {
    }

    public ContentModel(String title, String description, int imageId) {
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
        this.description = description;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
