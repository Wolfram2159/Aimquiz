package com.wolfram.aimquiz.activity;

import android.content.Intent;
import android.os.Bundle;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.adapters.PlayersViewAdapter;
import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
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

        LiveData<List<Player>> playerList = AppDatabase.getInstance(this).getUserDao().loadAllPlayers();
        playerList.observe(this, (players) -> {
                    recyclerView.setHasFixedSize(true);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                    recyclerView.setLayoutManager(layoutManager);

                    RecyclerView.Adapter mAdapter = new PlayersViewAdapter(players, (view, position) -> {
                        Intent intent = new Intent(this, PlayerDetailActivity.class);
                        intent.putExtra("player_id", players.get(position).getId());
                        startActivity(intent);
                    }, this);
                    recyclerView.setAdapter(mAdapter);
                }
        );
    }
}