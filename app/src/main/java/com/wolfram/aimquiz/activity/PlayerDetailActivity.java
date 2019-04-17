package com.wolfram.aimquiz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.wolfram.aimquiz.R;

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
    }
}
