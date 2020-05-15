package nl.saxion.playground.template.bubbletea.GameObjects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.bubbletea.PlayerData.Players;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;

public class Recipe extends Entity {

    private int classicBubbleRequired;
    private int orangeBubbleRequired;
    private int redBubbleRequired;
    private float scale= Resources.getSystem().getDisplayMetrics().widthPixels;
    private int redCollected, orangeCollected, classicCollected;
    private boolean scoreIsSet;

    public Recipe(BubbleGame game) {
        super(game);
        redCollected = 0;
        orangeCollected = 0;
        classicCollected = 0;
        redCollected = orangeCollected = classicCollected = 0;
        scoreIsSet = false;
        this.classicBubbleRequired = ((int) (Math.random()*5)+1)+10;
        this.orangeBubbleRequired = ((int) (Math.random()*5)+1)+5;
        this.redBubbleRequired = ((int) (Math.random()*5)+1)+5;
    }

    public int getClassicBubbleRequired() {
        return classicBubbleRequired;
    }

    public int getOrangeBubbleRequired() {
        return orangeBubbleRequired;
    }

    public int getRedBubbleRequired() {
        return redBubbleRequired;
    }

    public int getRedCollected() {
        return redCollected;
    }

    public int getOrangeCollected() {
        return orangeCollected;
    }

    public int getClassicCollected() {
        return classicCollected;
    }

    public void addRedCollected() {
        redCollected++;
    }

    public void addOrangeCollected() {
        orangeCollected++;
    }

    public void addClassicCollected() {
        classicCollected++;
    }

    public void draw(GameView v) {

        Bitmap classicBubble = v.getBitmapFromResource(R.drawable.classicbubble);
        Bitmap orangeBubble = v.getBitmapFromResource(R.drawable.orangebubble);
        Bitmap redBubble = v.getBitmapFromResource(R.drawable.redbubble);

        Rect rectangle1=new Rect((int)scale*25/1100,25,(int)scale*125/1100,125);//right-left,bottom-top
        Rect rectangle2=new Rect((int)scale*380/1100,25,(int)scale*480/1100,125);
        Rect rectangle3=new Rect((int)scale*750/1100,25,(int)scale*850/1100,125);
        Rect recipeBar=new Rect(0,0,(int)scale,150);

        Paint paintText=new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(65);

        Paint paintRect=new Paint();
        paintRect.setColor(Color.WHITE);

        v.drawRect(recipeBar,paintRect);

        v.drawBitmap(classicBubble, rectangle1);
        v.drawBitmap(orangeBubble, rectangle2);
        v.drawBitmap(redBubble,rectangle3);


        v.drawText(classicCollected +"/"+(classicBubbleRequired),(int)scale*150/1100, 100,paintText);
        v.drawText(orangeCollected +"/"+(orangeBubbleRequired),(int)scale*505/1100,100,paintText);
        v.drawText(redCollected +"/"+(redBubbleRequired),(int)scale*875/1100,100,paintText);
    }

    /**
     * If all required bubbles are collected add to the player score
     */
    public void tick() {
        if(classicCollected==classicBubbleRequired & redCollected==redBubbleRequired & orangeCollected==orangeBubbleRequired) {
            TimerGame timerGame = getGame().getEntity(TimerGame.class);
            if(!scoreIsSet) {
                int score = Players.getCurrentPlayer().getScore() + timerGame.getScore();
                Players.getCurrentPlayer().setScore(score);
                scoreIsSet = true;
            }
            getGame().addEntity(new GameOver(getGame(),true));
        }
    }
}
