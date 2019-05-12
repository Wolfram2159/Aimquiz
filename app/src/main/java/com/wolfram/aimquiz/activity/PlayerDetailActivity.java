package com.wolfram.aimquiz.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.Team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Wolfram
 * @date 2019-04-01
 */

public class PlayerDetailActivity extends AppCompatActivity {

    @BindView(R.id.detailplayer_image_view) ImageView playerImage;
    @BindView(R.id.player_nick) TextView nick;
    @BindView(R.id.player_team_name) TextView teamName;
    @BindView(R.id.detailTeam_image_view) ImageView teamImage;
    @BindView(R.id.player_mouse) TextView mouse;
    @BindView(R.id.player_dpi) TextView dpi;
    @BindView(R.id.player_sens) TextView sens;
    @BindView(R.id.player_resolution) TextView resolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);
        ButterKnife.bind(this);
        int player_id = getIntent().getIntExtra("player_id",1);
        LiveData<Player> player = AppDatabase.getInstance(this).getUserDao().loadPlayer(player_id);
        player.observe(this, (thisPlayer) -> {
            playerImage.setImageResource(this.getResources().getIdentifier(
                    "player_"+thisPlayer.getId(),
                    "drawable",
                    "com.wolfram.aimquiz"));
            nick.setText(thisPlayer.getNick());
            mouse.setText(thisPlayer.getMouse());
            dpi.setText(thisPlayer.getDPI());
            sens.setText(thisPlayer.getSens());
            resolution.setText(thisPlayer.getResolution());
        });
        LiveData<Team> team = AppDatabase.getInstance(this).getUserDao().loadTeamByPlayerId(player_id);
        team.observe(this, (thisTeam) -> {
            teamName.setText(thisTeam.getName());
            teamImage.setImageResource(this.getResources().getIdentifier(
                    "team_"+thisTeam.getId(),
                    "drawable",
                    "com.wolfram.aimquiz"));
        });
    }
}
