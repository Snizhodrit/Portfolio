package com.example.trifonov463467.PopupWindows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trifonov463467.Model.Album;
import com.example.trifonov463467.Model.Song;
import com.example.trifonov463467.R;
import static com.example.trifonov463467.MainActivity.admin;
import static com.example.trifonov463467.displaySongs.EXTRA_MESSAGE_SONG;

public class AddSong extends AppCompatActivity {
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width*0.8), (int) (height*0.6));
        album = admin.findByName(getIntent().getExtras().getString(EXTRA_MESSAGE_SONG));
    }

    public void addSong(View view) {
        EditText enterSong = findViewById(R.id.enterSongName);
        EditText enterSongLength = findViewById(R.id.enterSongLength);
        Double length = 0.1;
        boolean invalidSongLength = false;

        try {
            length = Double.parseDouble(enterSongLength.getText().toString());
        } catch (Exception e) {
            invalidSongLength = true;
        }
        if(enterSong == null || enterSong.getText().toString().trim().equals("") || enterSongLength.getText().toString().equals("")) {
            String message = getString(R.string.fill_fields);
            Toast.makeText(AddSong.this, message, Toast.LENGTH_LONG).show();
        } else if(!invalidSongLength){
            if(album.findByName(enterSong.getText().toString().trim()) != null) {
                String message = getString(R.string.song_with_name_exists);
                Toast.makeText(AddSong.this, message, Toast.LENGTH_LONG).show();
            } else {
                Song song = new Song(enterSong.getText().toString().trim(), length, album.getName());
                album.addSong(song);
                finish();
            }
        } else {
            String message = getString(R.string.enter_number);
            Toast.makeText(AddSong.this, message, Toast.LENGTH_LONG).show();
        }
    }
}
