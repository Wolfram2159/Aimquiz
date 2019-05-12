package com.wolfram.aimquiz.activity;

import android.content.Intent;
import android.os.Bundle;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.adapters.TeamsViewAdapter;
import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Team;

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
public class TeamsActivity extends AppCompatActivity {
    @BindView(R.id.teams_recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        ButterKnife.bind(this);

        LiveData<List<Team>> teamList = AppDatabase.getInstance(this).getUserDao().loadAllTeams();
        teamList.observe(this, (teams) -> {
            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.Adapter adapter = new TeamsViewAdapter(teams, (view, position) -> {
                Intent intent = new Intent(this, TeamDetailActivity.class);
                intent.putExtra("team_id", teams.get(position).getId());
                startActivity(intent);
            }, this);
            recyclerView.setAdapter(adapter);
        });
    }
}
