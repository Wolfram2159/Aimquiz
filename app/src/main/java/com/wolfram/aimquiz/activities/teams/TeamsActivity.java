package com.wolfram.aimquiz.activities.teams;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.widget.ImageView;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.activities.teamdetail.TeamDetailActivity;
import com.wolfram.aimquiz.adapters.TeamsViewAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Wolfram
 * @date 2019-04-05
 */
public class TeamsActivity extends AppCompatActivity {
    @BindView(R.id.teams_recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        ButterKnife.bind(this);

        TeamsViewModel model = ViewModelProviders.of(this).get(TeamsViewModel.class);

        model.getTeamsList().observe(this, (teams) -> {
            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.Adapter adapter = new TeamsViewAdapter(teams, (view, position) -> {
                Intent intent = new Intent(this, TeamDetailActivity.class);
                intent.putExtra("team_id", teams.get(position).getId());

                getWindow().setEnterTransition(new Fade(Fade.IN));
                getWindow().setEnterTransition(new Fade(Fade.OUT));

                ImageView iv = view.findViewById(R.id.team_image_view);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        new Pair<>(iv, ViewCompat.getTransitionName(iv))
                );
                startActivity(intent, options.toBundle());
            }, this);
            recyclerView.setAdapter(adapter);
        });
    }
}
