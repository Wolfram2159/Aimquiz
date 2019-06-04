package com.wolfram.aimquiz.activities.players;

import android.content.Intent;
import android.os.Bundle;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.activities.playerdetail.PlayerDetailActivity;
import com.wolfram.aimquiz.adapters.PlayersViewAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Wolfram
 * @date 2019-04-05
 */
public class PlayersActivity extends AppCompatActivity {
    @BindView(R.id.players_recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        ButterKnife.bind(this);

        PlayersViewModel model = ViewModelProviders.of(this).get(PlayersViewModel.class);
        model.getPlayersList().observe(this, (players) -> {
            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.Adapter mAdapter = new PlayersViewAdapter(players, (view, position) -> {
                Intent intent = new Intent(this, PlayerDetailActivity.class);
                intent.putExtra("player_id", players.get(position).getId());
                startActivity(intent);
            }, this);
            recyclerView.setAdapter(mAdapter);
        });
    }
}