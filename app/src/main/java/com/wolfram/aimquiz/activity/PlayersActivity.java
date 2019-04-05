package com.wolfram.aimquiz.activity;

import android.app.Activity;
import android.os.Bundle;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.threads.CreatePlayersView;

import androidx.recyclerview.widget.RecyclerView;
/**
 * @author Wolfram
 * @date 2019-04-05
 */
public class PlayersActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        RecyclerView recyclerView = findViewById(R.id.players_recycler_view);
        new CreatePlayersView(this, recyclerView).execute();
    }
}