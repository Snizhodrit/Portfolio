package nl.saxion.playground.template.bubbletea;

import android.content.Context;

public class Game {
    private static BubbleGame bubbleGame;


    public static BubbleGame getInstance(Context context) {
        if (bubbleGame == null) {
            bubbleGame = new BubbleGame(context);
        }
        return bubbleGame;
    }

    public static void endGame() {
        bubbleGame=null;
    }
}
