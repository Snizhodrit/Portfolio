package nl.saxion.playground.template.bubbletea.PlayerData;

import java.util.ArrayList;

public class Players {
    private static ArrayList<Player> players = new ArrayList<>();
    private static Player currentPlayer;

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setCurrentPlayer(Player player) {
        currentPlayer = player;
        if (player != null) {
            players.add(player);
        }
    }
}
