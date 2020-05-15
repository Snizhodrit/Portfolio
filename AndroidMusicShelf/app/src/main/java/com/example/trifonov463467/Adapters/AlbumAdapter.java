package com.example.trifonov463467.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.trifonov463467.Model.*;
import com.example.trifonov463467.R;
import java.io.InputStream;
import java.util.ArrayList;

public class AlbumAdapter extends ArrayAdapter {
    private ArrayList<Album> albums;
    private LayoutInflater layoutInflater;

    public AlbumAdapter(Context context, ArrayList albums) {
        super(context, R.layout.album_list, albums);
        this.albums = albums;
        layoutInflater =LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.album_list, parent, false);
        }

        TextView artistName = convertView.findViewById(R.id.artistName);
        TextView albumName = convertView.findViewById(R.id.albumName);
        TextView size = convertView.findViewById(R.id.numberOfSongs);
        ImageView albumPhoto = convertView.findViewById(R.id.albumPhoto);

        Album album = albums.get(position);
        artistName.setText(album.getArtist());
        albumName.setText(album.getName());
        size.setText("size: " + album.getSize());

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
