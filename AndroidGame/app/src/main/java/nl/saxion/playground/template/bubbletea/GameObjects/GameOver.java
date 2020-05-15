package nl.saxion.playground.template.bubbletea.GameObjects;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;

public class GameOver extends Entity {
    private float xPosition;
    private float yPosition;
    private boolean win;
    private GameView gameView;

    /**
     * @param win - true if the game is won, false if the game is lost
     */
    public GameOver(BubbleGame game, boolean win) {
        super(game);
        float screenWidth = game.getWidth();
        float screenHeight = game.getHeight();
        this.win = win;
        yPosition = screenHeight/2;
        xPosition = screenWidth/3;
    }

    public void draw(GameView view) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(65);
        if (win) {
            view.drawText("YOU WON", xPosition, yPosition, paint);
        } else {
            view.drawText("YOU FAILED", xPosition, yPosition, paint);
        }
        view.drawText("(Click to continue)", xPosition-100, yPosition+100, paint);
        gameView = view;
        //Stops the game after you won/you failed is displayed
        view.setPaused(true);
    }

    /**
     * End the game when the screen is touched
     */
    @Override
    public void handleTouch(BubbleGame.Touch touch, MotionEvent me) {
        if (gameView != null) {
            gameView.setPaused(false);
            endGame();
        }
    }

}
