package nl.saxion.playground.template.bubbletea.GameObjects;

import android.graphics.Bitmap;
import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.lib.GameView;

public class OrangeBubble extends Bubble {
    private Bitmap orangeBubble;
    private float oldYPosition = 0;

    public OrangeBubble(BubbleGame game) {
        super(game);
    }

    public void draw(GameView gv) {
        update();
        if (orangeBubble == null) {
            orangeBubble = gv.getBitmapFromResource(R.drawable.orangebubble);
        }

        if (yPosition != oldYPosition) {
            gv.drawBitmap(orangeBubble, xPosition, yPosition, 100, 100);
        }

        oldYPosition = yPosition;
    }


}
