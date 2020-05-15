package nl.saxion.playground.template.bubbletea.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.PlayerData.Player;
import nl.saxion.playground.template.bubbletea.PlayerData.Players;

/**
 * You can start the game from this activity
 */
public class MainActivity extends AppCompatActivity {

    private Player player;
    private EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bubbletea);

        ImageView backgroundView = findViewById(R.id.background);
        backgroundView.setImageResource(R.drawable.mainbackground);

        editName = findViewById(R.id.enterName);

        final ImageView playView = findViewById(R.id.play);
        playView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                if(name.trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter player name", Toast.LENGTH_SHORT).show();
                } else {
                    player = new Player(name);
                    Players.setCurrentPlayer(player);

                    Intent intent = new Intent(MainActivity.this, StartGame.class);
                    startActivity(intent);
                }
            }
        });
        ImageView ranking = findViewById(R.id.ibRanking);
        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intent);
            }
        });
    }
}
