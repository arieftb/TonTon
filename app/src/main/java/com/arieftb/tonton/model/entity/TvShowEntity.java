package com.arieftb.tonton.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowEntity implements Parcelable {
    private int id;
    private String title;
    private String lang;
    private double voteAverage;
    private String poster;
    private String releaseDate;
    private String overview;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public TvShowEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.lang);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.poster);
        dest.writeString(this.releaseDate);
        dest.writeString(this.overview);
    }

    private TvShowEntity(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.lang = in.readString();
        this.voteAverage = in.readDouble();
        this.poster = in.readString();
        this.releaseDate = in.readString();
        this.overview = in.readString();
    }

    public static final Parcelable.Creator<TvShowEntity> CREATOR = new Parcelable.Creator<TvShowEntity>() {
        @Override
        public TvShowEntity createFromParcel(Parcel source) {
            return new TvShowEntity(source);
        }

        @Override
        public TvShowEntity[] newArray(int size) {
            return new TvShowEntity[size];
        }
    };
}
