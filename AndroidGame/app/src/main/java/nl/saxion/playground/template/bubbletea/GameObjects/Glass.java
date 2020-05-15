package nl.saxion.playground.template.bubbletea.GameObjects;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;

public class Glass extends Entity {
    private float x;
    private float width;
    private float height;
    private float y;
    private Bitmap GlassImage;

    public Glass(BubbleGame game) {
        super(game);
        float screenWidth = game.getWidth();
        y = game.getHeight();
        x = screenWidth/2;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }


    public void draw(GameView view) {
        if(GlassImage == null) {
            GlassImage = view.getBitmapFromResource(R.drawable.catbutton);
        }

        width = (float) (GlassImage.getWidth()*0.7);
        height = (float) (GlassImage.getHeight()*0.7);
        float width1=(float) (GlassImage.getWidth() * 0.7*0.8);

        if ((x >= 0 + width / 3) && (x < view.getWidth() - width / 3)) {
            view.drawBitmap(GlassImage, x - width / 2, y - height, width, height);
        } else if (x <= 0 + width / 3) {
            view.drawBitmap(GlassImage, 0 - width / 6, y - height, width, height);
        } else if (x >= view.getWidth() - width / 3) {
            view.drawBitmap(GlassImage, view.getWidth() - width1, y - height, width, height);
        }
    }

    @Override
    public void handleTouch(BubbleGame.Touch touch, MotionEvent me) {
        if(me.getAction()==MotionEvent.ACTION_MOVE & me.getX()< x + width & me.getX() > x - width & me.getY() > y - height/2) {
            if(me.getX() < x + width/2) {
                x = x - width/6;
                x = x + width/4;
                x = x + width/2;
            }

            if(me.getX() > x - width/2) {
                x = x - width/6;
                x = x - width/4;
                x = x - width/2;
            }
            x = me.getX();
        }
    }
}
