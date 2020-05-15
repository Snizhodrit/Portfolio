package com.example.trifonov463467;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.trifonov463467.Model.Album;
import com.example.trifonov463467.Model.Song;
import static com.example.trifonov463467.MainActivity.admin;
import static com.example.trifonov463467.displaySongs.EXTRA_MESSAGE_SONG;

public class SongDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);
        Song song = getIntent().getExtras().getParcelable(EXTRA_MESSAGE_SONG);

        double albumLength = 0.0;
        Album album = admin.findByName(song.getAlbumName());
        for(Song element: album.getSongs()) {
            albumLength += element.getLength();
        }

        albumLength = Math.round(albumLength * 100.0) / 100.0;
        CompoundSongDetails compoundSongDetails = findViewById(R.id.compoundSongDetails);
        compoundSongDetails.fill(song.getName(), song.getLength(), albumLength);
    }
}
