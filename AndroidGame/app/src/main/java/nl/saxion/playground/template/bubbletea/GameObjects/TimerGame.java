package nl.saxion.playground.template.bubbletea.GameObjects;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.bubbletea.PlayerData.Players;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;

public class TimerGame extends Entity {

    public static int count;
    private int nextCount =1;
    private float scale= Resources.getSystem().getDisplayMetrics().widthPixels;
    private static int score;


    public TimerGame(BubbleGame game) {
        super(game);
        score = 0;
        count = 0;
    }

    public int getScore() {
        return score;
    }

    /**
     * this method draws the game time to @param v
     */
    public void draw(final GameView v){
        final Paint paintText=new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(65);
        v.drawText((count)+"s",(int)scale*950/1100,250,paintText);
    }

    @Override
    public void tick(){
        if(nextCount %180==0){
            count++;
        }
        //The score is determined by how fast all bubbles are collected
        nextCount++;
        score=120-count;
        if (count == 90) {
            getGame().addEntity(new GameOver(getGame(),false));
            //If the player fails to collect the bubbles in the change the score to 0
            Players.getCurrentPlayer().setScore(0);
            getGame().addEntity(new GameOver(getGame(),false));
        }
    }
}
