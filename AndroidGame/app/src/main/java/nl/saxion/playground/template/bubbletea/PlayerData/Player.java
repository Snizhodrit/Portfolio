package nl.saxion.playground.template.bubbletea.PlayerData;

public class Player {


    private String name;
    private int score = 0;

    //The player starts with score 0
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return getName();
    }




}
