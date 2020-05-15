package nl.saxion.playground.template.bubbletea.GameObjects;

import android.graphics.Bitmap;
import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.lib.GameView;

public class ClassicBubble extends Bubble {
    private Bitmap classicBubble;
    private float oldYPosition = 0;

    public ClassicBubble(BubbleGame game) {
        super(game);
    }

    public void draw(GameView gv) {
        update();
        if (classicBubble ==null) {
            classicBubble = gv.getBitmapFromResource(R.drawable.classicbubble);
        }

        if (yPosition != oldYPosition) {
            gv.drawBitmap(classicBubble, xPosition, yPosition, 100, 100);
        }
        oldYPosition = yPosition;
    }

}
