package nl.saxion.playground.template.bubbletea.GameObjects;

import android.graphics.Bitmap;
import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.lib.GameView;

public class DirtyBubble extends Bubble {
    private float oldYPosition = 0;
    private Bitmap dirtyBubble;

    public DirtyBubble(BubbleGame game) {
        super(game);
    }

    @Override
    public void draw(GameView gv) {
        update();
        if (dirtyBubble ==null) {
            dirtyBubble = gv.getBitmapFromResource(R.drawable.thedirtybubble);
        }

        if (yPosition != oldYPosition) {
            gv.drawBitmap(dirtyBubble, xPosition, yPosition, 100, 100);
        }
        oldYPosition = yPosition;
    }
}
