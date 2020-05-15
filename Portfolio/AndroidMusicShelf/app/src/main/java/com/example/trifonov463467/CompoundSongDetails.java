package com.example.trifonov463467;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CompoundSongDetails extends ConstraintLayout {
    private LinearLayout linearLayout;
    private TextView textView;
    private TextView textView2;
    private LinearLayout linearLayout2;
    private TextView songName;
    private TextView songLength;
    private TextView albumLength;
    private CustomDrawing customDrawing;

    public CompoundSongDetails(Context context) {
        super(context);
        init();
    }

    public CompoundSongDetails(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CompoundSongDetails(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.compound_song_details, this);
        linearLayout = findViewById(R.id.linearLayout3);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        linearLayout2 = findViewById(R.id.linearLayout5);
        songName = findViewById(R.id.nameOfSong);
        songLength = findViewById(R.id.songLength);
        albumLength = findViewById(R.id.albumLengthDetails);
        customDrawing = findViewById(R.id.customDrawingDetails);
    }

    public void  fill(String songName, Double songLength, Double albumLength) {
        this.songName.setText(songName);
        this.songLength.setText(Double.toString(songLength));
        this.albumLength.setText(Double.toString(albumLength));
        customDrawing.setIsSong(true, albumLength, songLength);
        customDrawing.setEmptyAlbum(false);
    }
}
