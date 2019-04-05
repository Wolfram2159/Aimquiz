package com.wolfram.aimquiz.activity;

import android.app.Activity;
import android.os.Bundle;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.threads.CreateTeamsView;

import androidx.recyclerview.widget.RecyclerView;
/**
 * @author Wolfram
 * @date 2019-04-05
 */
public class TeamsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        RecyclerView recyclerView = findViewById(R.id.teams_recycler_view);
        new CreateTeamsView(this, recyclerView).execute();
    }
}
