package com.wolfram.aimquiz.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.adapters.PlayersViewAdapter;
import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.glide.RequestBuilderFactory;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamDetailActivity extends AppCompatActivity {
    @BindView(R.id.playersFromTeamRecyclerView) RecyclerView recyclerView;
    @BindView(R.id.team_detail_image_view) ImageView teamLogo;
    @BindView(R.id.team_detail_progress_bar) ProgressBar teamProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        ButterKnife.bind(this);
        int team_id = getIntent().getIntExtra("team_id", 1);


        LiveData<List<Player>> teamPlayers = AppDatabase.getInstance(this).getUserDao().loadPlayersFromTeam(team_id);
        LiveData<Team> team = AppDatabase.getInstance(this).getUserDao().loadTeam(team_id);

        teamPlayers.observe(this, (players) -> {
            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.Adapter adapter = new PlayersViewAdapter(players, (view, position) -> {
                Intent intent = new Intent(this, PlayerDetailActivity.class);
                intent.putExtra("player_id", players.get(position).getId());
                startActivity(intent);
            }, this);
            recyclerView.setAdapter(adapter);
        });

        RequestBuilderFactory requestFactory = new RequestBuilderFactory(this);
        teamProgressBar.setVisibility(View.VISIBLE);

        team.observe(this, (thisTeam) -> {
            RequestBuilder requestBuilder = requestFactory.getTeamRequestBuilder(thisTeam.getTeamHLTV(), new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    teamProgressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    teamProgressBar.setVisibility(View.GONE);
                    return false;
                }
            });
            requestBuilder.into(teamLogo);
        });
    }
}
