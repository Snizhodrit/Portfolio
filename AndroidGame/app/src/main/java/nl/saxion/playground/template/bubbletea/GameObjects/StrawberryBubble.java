package nl.saxion.playground.template.bubbletea.GameObjects;

import android.graphics.Bitmap;
import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.lib.GameView;

public class StrawberryBubble extends Bubble {
    private float oldY = 0;
    private Bitmap strawberryBubble;

    public StrawberryBubble(BubbleGame game) {
        super(game);

    }

    public void draw(GameView gv) {
        update();
        if (strawberryBubble ==null) {
            strawberryBubble = gv.getBitmapFromResource(R.drawable.redbubble);
        }
        if (yPosition != oldY ) {
            gv.drawBitmap(strawberryBubble, xPosition, yPosition, 100, 100);
        }
        oldY = yPosition;
    }
}
