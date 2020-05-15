package nl.saxion.playground.template.bubbletea;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.bubbletea.PlayerData.Player;

public class RankingAdapter extends ArrayAdapter {

    ArrayList<Player> mPlayer;
    LayoutInflater inflater;

    public RankingAdapter(Context context, ArrayList<Player> objects) {
        super(context, R.layout.player_list_item, objects);

        mPlayer= objects;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.player_list_item, parent, false);
        }

        TextView tvPlayerName = convertView.findViewById(R.id.tvPlayerName);

        TextView tvPlayerScore = convertView.findViewById(R.id.tvPlayerScore);

        Player player = mPlayer.get(position);
        tvPlayerName.setText(player.getName());
        tvPlayerScore.setText(player.getScore() + "");



        return convertView;
    }
}
