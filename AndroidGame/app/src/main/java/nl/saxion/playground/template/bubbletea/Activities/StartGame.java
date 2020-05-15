package nl.saxion.playground.template.bubbletea.Activities;

import android.app.Activity;
import android.os.Bundle;

import nl.saxion.playground.template.bubbletea.BubbleGame;
import nl.saxion.playground.template.bubbletea.Game;
import nl.saxion.playground.template.lib.GameView;


public class StartGame extends Activity {
    private static BubbleGame bubbleGame;
    private GameView gameView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        setContentView(gameView);

        if (savedInstanceState!=null && savedInstanceState.containsKey("game") && bubbleGame != null) {
            bubbleGame = (BubbleGame)savedInstanceState.getSerializable("game");
        } else {
            bubbleGame = Game.getInstance(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.setGame(bubbleGame);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.setGame(null);
    }
}
