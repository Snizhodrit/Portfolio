package nl.saxion.playground.template.bubbletea.GameObjects;

import android.graphics.Bitmap;
import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.lib.GameView;

public class StarBubble extends Bubble {
    private Bitmap starBubble;
    private float oldYPosition = 0;


    public StarBubble(BubbleGame game) {
        super(game);
    }


    public void draw(GameView gv) {
        update();
        if (starBubble ==null) {
            starBubble = gv.getBitmapFromResource(R.drawable.starbubble);
        }
        if (yPosition != oldYPosition) {
            gv.drawBitmap(starBubble, xPosition, yPosition, 100, 100);
        }
        oldYPosition = yPosition;
    }


}
