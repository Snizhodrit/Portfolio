package nl.saxion.playground.template.bubbletea.GameObjects;


import java.util.Random;
import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.lib.Entity;

public class BubbleGenerator extends Entity {
    private Random random = new Random();
    private long tickCount;
    private long nextCount;

    public BubbleGenerator(BubbleGame bubbleGame) {
        super(bubbleGame);
        calculate();
    }

    @Override
    public void tick() {
        ++tickCount;
        int type;
        random= new Random();
        type = random.nextInt((5 - 1) + 1) + 1;

        // Generate bubbles
        while (tickCount >= nextCount) {
            if (type==1) {
                getGame().addEntity(new OrangeBubble(getGame()));
            }else if (type == 2){
                getGame().addEntity(new ClassicBubble(getGame()));
            }else if (type == 3){
                getGame().addEntity(new StrawberryBubble(getGame()));
            }else if (type == 4){
                getGame().addEntity(new StarBubble(getGame()));
            }else if (type == 5){
                getGame().addEntity(new DirtyBubble(getGame()));
            }
            calculate();
        }
    }


    private void calculate() {
        float gameTime = (float) ++tickCount / getGame().ticksPerSecond();
        float avgTime = 2f * 120f / (120f + gameTime);
        float nextTime = avgTime * 3f * random.nextFloat();
        nextCount = tickCount + Math.round(nextTime * getGame().ticksPerSecond());
    }

}


