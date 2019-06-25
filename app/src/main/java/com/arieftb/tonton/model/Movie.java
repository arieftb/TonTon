/*
 * Developed by arieftb on 6/23/19 9:32 PM.
 * Last Modified 6/23/19 9:32 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String title, genre, description, releaseDate;
    private Double rating;
    private int poster, id;

    public Movie(int id, String title, String genre, String description, String releaseDate, Double rating, int poster) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    public Double getRating() {
        return rating;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.poster);
        dest.writeString(this.title);
        dest.writeString(this.genre);
        dest.writeString(this.description);
        dest.writeString(this.releaseDate);
        dest.writeValue(this.rating);
    }

    private Movie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.genre = in.readString();
        this.description = in.readString();
        this.releaseDate = in.readString();
        this.rating = (Double) in.readValue(Double.class.getClassLoader());
        this.poster = (int) in.readValue(int.class.getClassLoader());
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
