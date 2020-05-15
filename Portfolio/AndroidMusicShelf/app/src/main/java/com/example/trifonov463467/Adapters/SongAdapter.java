package com.example.trifonov463467.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.trifonov463467.Model.Album;
import com.example.trifonov463467.Model.Song;
import com.example.trifonov463467.R;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.trifonov463467.MainActivity.admin;

public class SongAdapter extends ArrayAdapter {
    private ArrayList<Song> songs;
    private LayoutInflater layoutInflater;

    public SongAdapter(Context context, ArrayList songs) {
        super(context, R.layout.song_list, songs);
        this.songs = songs;
        layoutInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.song_list,parent,false);
        }

        TextView songName = convertView.findViewById(R.id.songName);
        ImageView albumPhoto = convertView.findViewById(R.id.albumPhoto);

        Song song = songs.get(position);
        songName.setText(song.getName());

        Album album = admin.findByName(song.getAlbumName());
        if(album.getImage() != null) {
            InputStream inputStream;
            try {
                inputStream = getContext().getAssets().open(album.getImage());
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                albumPhoto.setImageDrawable(drawable);
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            albumPhoto.setImageBitmap(album.getBitmap());
        }

        return convertView;
    }
}
