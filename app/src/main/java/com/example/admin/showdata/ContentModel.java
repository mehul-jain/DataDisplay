package com.example.admin.showdata;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 15-02-2018.
 */

public class ContentModel implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ContentModel createFromParcel(Parcel in) {
            return new ContentModel(in);
        }

        public ContentModel[] newArray(int size) {
            return new ContentModel[size];
        }
    };

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

    public ContentModel(Parcel parcel) {
        title = parcel.readString();
        description = parcel.readString();
        imageId = parcel.readInt();
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.imageId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "ContentModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
