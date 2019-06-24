/*
 * Developed by arieftb on 6/24/19 9:13 PM.
 * Last Modified 6/24/19 9:13 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private String title, genre, description, releaseDate;
    private Double rating;
    private int poster;

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public int getPoster() {
        return poster;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.genre);
        dest.writeString(this.description);
        dest.writeString(this.releaseDate);
        dest.writeValue(this.rating);
        dest.writeInt(this.poster);
    }

    public TvShow() {
    }

    protected TvShow(Parcel in) {
        this.title = in.readString();
        this.genre = in.readString();
        this.description = in.readString();
        this.releaseDate = in.readString();
        this.rating = (Double) in.readValue(Double.class.getClassLoader());
        this.poster = in.readInt();
    }

    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
