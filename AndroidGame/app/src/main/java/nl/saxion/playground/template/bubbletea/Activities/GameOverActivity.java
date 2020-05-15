package nl.saxion.playground.template.bubbletea.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.PlayerData.Players;


public class GameOverActivity extends AppCompatActivity {

    private TextView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        scoreView=findViewById(R.id.tvActualScore);

        int score = Players.getCurrentPlayer().getScore();
        scoreView.setText("" + score);
    }

    public void getHome(View view) {
        Players.setCurrentPlayer(null);
        Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void getRanking(View view) {
        Intent intent = new Intent(GameOverActivity.this, RankingActivity.class);
        startActivity(intent);
    }

    /**
     * Start the game as the same player in @param view
     */
    public void playAgain(View view) {
        Intent intent = new Intent(GameOverActivity.this, StartGame.class);
        startActivity(intent);
    }
}
