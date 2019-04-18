package com.wolfram.aimquiz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.threads.CreateTeamDetailView;

import androidx.recyclerview.widget.RecyclerView;

public class TeamDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        RecyclerView recyclerView = findViewById(R.id.playersFromTeamRecyclerView);
        ImageView imageView = findViewById(R.id.teamDeatil_image_view);
        int team_id = getIntent().getIntExtra("team_id",1);
        new CreateTeamDetailView(this,team_id,recyclerView,imageView).execute();
    }
}
