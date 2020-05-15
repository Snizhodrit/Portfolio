package com.example.trifonov463467;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomDrawing extends View {

    private boolean isSong = false;
    private Double albumLength;
    private Double songLength;
    private  boolean emptyAlbum = true;

    public CustomDrawing(Context context) {
        super(context);
        init();
    }

    public CustomDrawing(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomDrawing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setIsSong(Boolean bool, Double albumLength, Double songLength) {
        isSong = bool;
        this.albumLength = albumLength;
        this.songLength = songLength;
    }

    public void setEmptyAlbum(boolean bool) {
        this.emptyAlbum = bool;
    }

    public void init() {

    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!emptyAlbum) {
            int width = canvas.getWidth();
            int height = canvas.getHeight() / 2;

            Paint paintBlue = new Paint();
            paintBlue.setColor(Color.BLUE);

            canvas.drawRect(0, 0, width, height, paintBlue);

            if (isSong) {
                Paint paintBlack = new Paint();
                paintBlack.setColor(Color.BLACK);
                Double percentage = (songLength / albumLength) * 100;
                Double percentageOfWidth = (width * percentage) / 100;
                //shows what percentage of the whole album is the song
                canvas.drawRect(0, 0, (int) (width - (width - percentageOfWidth)), height, paintBlack);
            }
        }
    }
}
