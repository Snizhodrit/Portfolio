package nl.saxion.playground.template.bubbletea;


import android.content.Context;

import nl.saxion.playground.template.bubbletea.GameObjects.Background;
import nl.saxion.playground.template.bubbletea.GameObjects.BubbleGenerator;
import nl.saxion.playground.template.bubbletea.GameObjects.Glass;
import nl.saxion.playground.template.bubbletea.GameObjects.Recipe;
import nl.saxion.playground.template.bubbletea.GameObjects.TimerGame;
import nl.saxion.playground.template.lib.GameModel;

public class BubbleGame extends GameModel {
    private Context context;

    @Override
    public float getWidth() {
        return actualWidth;
    }


    @Override
    public float getHeight() {
        return actualHeight;
    }

    public Context getContext() {
        return context;
    }

    public BubbleGame(Context context) {
        this.context = context;
    }

    /**
     * starts the game
     */
    public void start() {
        addEntity(new Background(this));
        addEntity(new Glass(this));
        addEntity(new Recipe(this));
        addEntity(new BubbleGenerator(this));
        addEntity(new TimerGame(this));
    }
}
