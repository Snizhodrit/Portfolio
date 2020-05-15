package com.example.trifonov463467.Model;

import java.util.ArrayList;

public class Admin {
    private static ArrayList<Album> albums;

    public Admin() {
        albums = new ArrayList<>();
    }

    public static void generateData() {
        albums.add(new Album("Odyssey to the Gallows", "Slice The Cake", "gallows.jpg"));
        albums.get(0).addSong(new Song("Odyssey to the Gallows", 28.03, albums.get(0).getName()));
        albums.add(new Album("You.Me.Hell soundtrack", "biscuit placebo", "you.me.hell.jpg"));
        albums.get(1).addSong(new Song("We are all in hell now", 2.43, albums.get(1).getName()));
        albums.get(1).addSong(new Song("You and me and TV", 2.49, albums.get(1).getName()));
        albums.get(1).addSong(new Song("Why dolls are not allowed in hell", 0.54, albums.get(1).getName()));
        albums.get(1).addSong(new Song("The evil twins", 2.34, albums.get(1).getName()));
        albums.get(1).addSong(new Song("The father in law", 1.27, albums.get(1).getName()));
        albums.get(1).addSong(new Song("No, \"I\" am your father in law!", 2.04, albums.get(1).getName()));
        albums.get(1).addSong(new Song("Singular Matters", 3.51, albums.get(1).getName()));
        albums.get(1).addSong(new Song("The party", 1.32, albums.get(1).getName()));
        albums.add(new Album("Some Album", "Some artist", "image1.jpg"));
        albums.get(2).addSong(new Song("Some song1", 1, albums.get(2).getName()));
        albums.get(2).addSong(new Song("Some song2", 1,albums.get(2).getName()));
        albums.get(2).addSong(new Song("Some song3", 1,albums.get(2).getName()));
        albums.get(2).addSong(new Song("Some song4", 1,albums.get(2).getName()));
        albums.add(new Album("Album", "Artist", "image2.jpg"));
        albums.get(3).addSong(new Song("song1", 2, albums.get(3).getName()));
        albums.get(3).addSong(new Song("song2", 2,albums.get(3).getName()));
        albums.get(3).addSong(new Song("song3", 2,albums.get(3).getName()));
        albums.add(new Album("Another Album", "Another Artist", "image3.jpg"));
        albums.get(4).addSong(new Song("Another song1", 3, albums.get(4).getName()));
        albums.get(4).addSong(new Song("Another song2", 3,albums.get(4).getName()));
        albums.get(4).addSong(new Song("Another song3", 3, albums.get(4).getName()));
        albums.get(4).addSong(new Song("Another song4", 3,albums.get(4).getName()));
        albums.add(new Album("One more album", "One more Artist", "image4.png"));
        albums.get(5).addSong(new Song("One more song1", 4.01, albums.get(5).getName()));
        albums.get(5).addSong(new Song("One more song2", 4.02,albums.get(5).getName()));
        albums.get(5).addSong(new Song("One more song3", 4.55, albums.get(5).getName()));
        albums.get(5).addSong(new Song("One more song4", 4.34, albums.get(5).getName()));
        albums.get(5).addSong(new Song("One more song5", 4.23, albums.get(5).getName()));
        albums.get(5).addSong(new Song("One more song6", 4.66, albums.get(5).getName()));
        albums.add(new Album("My album", "Me", "me.png"));
        albums.get(6).addSong(new Song("My song", 1.11, albums.get(6).getName()));
        albums.get(6).addSong(new Song("Another one of my songs", 2.22, albums.get(6).getName()));
        albums.get(6).addSong(new Song("My last song", 3.31, albums.get(6).getName()));
        albums.get(6).addSong(new Song("This is not really a song", 6.66, albums.get(6).getName()));
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void remove(Album album) {
        albums.remove(album);
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public Album findByName(String name) {
        for(Album album: albums) {
            if(album.getName().equalsIgnoreCase(name)) {
                return album;
            }
        }
        return null;
    }

    public void removeSong(Song songToDelete) {
        for(Album album: albums) {
            if(album.getSongs().contains(songToDelete)) {
                album.removeSong(songToDelete);
            }
        }

    }
}
