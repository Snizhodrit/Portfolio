package com.example.trifonov463467.Model;


import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    private String name;
    private double length;
    private String albumName;

    public Song(String name, double length, String albumName) {
        this.name = name;
        this.length = length;
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setLength(double length) {
        this.length = length;
    }



    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", Album name='" + albumName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeDouble(this.length);
        dest.writeString(this.albumName);
    }

    private Song(Parcel in) {
        this.name = in.readString();
        this.length = in.readDouble();
        this.albumName = in.readString();
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}
