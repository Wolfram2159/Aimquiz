package com.wolfram.aimquiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

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

public class TeamDetailActivity extends AppCompatActivity {
    @BindView(R.id.playersFromTeamRecyclerView) RecyclerView recyclerView;
    @BindView(R.id.teamDeatil_image_view) ImageView teamLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        ButterKnife.bind(this);
        int team_id = getIntent().getIntExtra("team_id", 1);
        LiveData<List<Player>> teamPlayers = AppDatabase.getInstance(this).getUserDao().loadPlayersFromTeam(team_id);
        teamPlayers.observe(this, (players) -> {
            recyclerView.setHasFixedSize(true);
            teamLogo.setImageResource(this.getResources().getIdentifier(
                    "team_" + team_id,
                    "drawable",
                    "com.wolfram.aimquiz"));
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.Adapter adapter = new PlayersViewAdapter(players, (view, position) -> {
                Intent intent = new Intent(this, PlayerDetailActivity.class);
                intent.putExtra("player_id", players.get(position).getId());
                startActivity(intent);
            }, this);
            recyclerView.setAdapter(adapter);
        });

    }
}
