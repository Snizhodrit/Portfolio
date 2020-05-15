package nl.saxion.playground.template.bubbletea.GameObjects;

import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.bubbletea.PlayerData.Players;
import nl.saxion.playground.template.lib.Entity;

public class Bubble extends Entity {
    float xPosition;
    float yPosition;
    private int ySpeed;


    public Bubble(BubbleGame game) {
        super(game);
        xPosition = (float) Math.random() * game.getWidth()*3/4;
        yPosition = game.getHeight()/8;
        ySpeed = 30;
    }

    @Override
    public void tick(){

        Glass glass = getGame().getEntity(Glass.class);
        Recipe recipe = getGame().getEntity(Recipe.class);
        float height = getGame().getHeight();

        //If a bubble collides with the glass do something according to it's type
        if (yPosition>=glass.getY()-500&yPosition<=glass.getY()-400&xPosition >glass.getX()-glass.getWidth()/2.5&xPosition<glass.getX()+glass.getWidth()/4) {
            if (this instanceof OrangeBubble) {
                if (recipe.getOrangeCollected() < recipe.getOrangeBubbleRequired()) {
                    recipe.addOrangeCollected();
                }
                getGame().removeEntity(this);
            } else if (this instanceof StarBubble) {
                TimerGame.count = TimerGame.count-5;
                getGame().removeEntity(this);
            } else if (this instanceof StrawberryBubble) {
                if(recipe.getRedCollected() < recipe.getRedBubbleRequired()) {
                    recipe.addRedCollected();
                }
                getGame().removeEntity(this);
            } else if (this instanceof ClassicBubble) {
                if (recipe.getClassicCollected() < recipe.getClassicBubbleRequired()) {
                    recipe.addClassicCollected();
                }
                getGame().removeEntity(this);
            } else if (this instanceof DirtyBubble) {
                getGame().removeEntity(this);
                Players.getCurrentPlayer().setScore(0);
                getGame().addEntity(new GameOver(getGame(),false));
            }
        }
        //Remove the bubble when it reaches the bottom of the screen
        if(yPosition >= height - glass.getHeight()*1/4) {
            getGame().removeEntity(this);
        }
    }

    public void update() {
        yPosition += ySpeed;
    }

}
