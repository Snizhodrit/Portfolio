package com.example.trifonov463467.Model;

import android.graphics.Bitmap;
import java.util.ArrayList;

public class Album{
    private String name;
    private int size;
    private String artist;
    private ArrayList<Song> songs;
    private Bitmap bitmap;
    private String image;

    public Album(String name, String artist, Bitmap bitmap) {
        songs = new ArrayList<>();
        this.name = name;
        this.size = songs.size();
        this.artist = artist;
        this.bitmap = bitmap;
        image = null;
    }

    public Album(String name, String artist, String image) {
        songs = new ArrayList<>();
        this.name = name;
        this.artist = artist;
        this.image = image;
    }

    public Song findByName(String name) {
        for (Song song: songs) {
            if(song.getName().equalsIgnoreCase(name)) {
                return song;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getArtist() {
        return artist;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public String getImage() {
        return image;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void addSong(Song song) {
        songs.add(song);
        this.size = songs.size();
    }

    public void removeSong(Song song) {
        songs.remove(song);
        size = songs.size();
    }


    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", artist='" + artist + '\'' +
                ", songs=" + songs +
                '}';
    }
}
