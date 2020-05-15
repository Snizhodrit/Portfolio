
package nl.saxion.playground.template.bubbletea.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.PlayerData.Player;
import nl.saxion.playground.template.bubbletea.PlayerData.Players;
import nl.saxion.playground.template.bubbletea.RankingAdapter;

public class RankingActivity extends AppCompatActivity {

    ArrayList<Player> playerList = Players.getPlayers();
    private RankingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        adapter = new RankingAdapter(this, playerList);
        ListView listView = findViewById(R.id.lvRanking);
        listView.setAdapter(adapter);

        Button buttonPlayAgain = findViewById(R.id.buttonPlayAgain);
        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Players.getCurrentPlayer() != null) {
                    Intent intent = new Intent(RankingActivity.this, StartGame.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(RankingActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button buttonHome = findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Players.setCurrentPlayer(null);
                Intent intent = new Intent(RankingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
