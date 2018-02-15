package com.example.admin.showdata;

/**
 * Created by admin on 15-02-2018.
 */

public class content_model {
    private String title;
    private String Description;
    public int imageId;

    public content_model(String title, String description, int imageId) {
        this.title = title;
        Description = description;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
