package nl.saxion.playground.template.bubbletea.GameObjects;

import android.graphics.Bitmap;
import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;

public class Background extends Entity {
    private Bitmap BackgroundImage;

    public Background(BubbleGame game) {
        super(game);
    }

    public void draw(GameView view) {
        if(BackgroundImage == null) {
            BackgroundImage = view.getBitmapFromResource(R.drawable.pinkbackground);
        }

        view.drawBitmap(BackgroundImage, 0, 0);
    }
}
