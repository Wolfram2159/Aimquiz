package com.wolfram.aimquiz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.threads.CreatePlayerDetailView;

/**
 * @author Wolfram
 * @date 2019-04-01
 */

public class PlayerDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);
        int player_id = getIntent().getIntExtra("player_id",0);
        new CreatePlayerDetailView(player_id,this,
                (ImageView) findViewById(R.id.detailplayer_image_view),
                (TextView) findViewById(R.id.player_nick),
                (TextView) findViewById(R.id.player_team_name),
                (ImageView) findViewById(R.id.detailTeam_image_view),
                (TextView) findViewById(R.id.player_mouse),
                (TextView) findViewById(R.id.player_dpi),
                (TextView) findViewById(R.id.player_sens),
                (TextView) findViewById(R.id.player_resolution)).execute();
    }
}
