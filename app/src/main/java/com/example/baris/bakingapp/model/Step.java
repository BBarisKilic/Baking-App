package com.example.baris.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Step implements Parcelable {

    @SerializedName("id")
    private int ID;
    @SerializedName("shortDescription")
    private String shortDescription;
    @SerializedName("description")
    private String description;
    @SerializedName("thumbnailURL")
    private String thumbnailURL;
    @SerializedName("videoURL")
    private String videoURL;

    public Step() {
    }

    public Step(int ID, String shortDescription, String description, String videoUrl, String thumbnailUrl) {
        this.ID = ID;
        this.shortDescription = shortDescription;
        this.description = description;
        this.thumbnailURL = thumbnailUrl;
        this.videoURL = videoUrl;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        public Step[] newArray(int size) {
            return new Step[size];
        }
        public Step createFromParcel(Parcel parcel) {
            return new Step(parcel);
        }
    };

    private Step(Parcel parcel){
        ID = parcel.readInt();
        shortDescription = parcel.readString();
        description =  parcel.readString();
        thumbnailURL = parcel.readString();
        videoURL = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(ID);
        parcel.writeValue(shortDescription);
        parcel.writeValue(description);
        parcel.writeValue(thumbnailURL);
        parcel.writeValue(videoURL);

    }
}
