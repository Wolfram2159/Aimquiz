package com.wolfram.aimquiz.activities.playerdetail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.glide.RequestBuilderFactory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
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
    @BindView(R.id.player_detail_player_progress_bar) ProgressBar playerProgressBar;
    @BindView(R.id.player_detail_team_progress_bar) ProgressBar teamProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);
        ButterKnife.bind(this);

        int player_id = getIntent().getIntExtra("player_id",1);

        PlayerDetailViewModel model = ViewModelProviders.of(this).get(PlayerDetailViewModel.class);

        RequestBuilderFactory requestFactory = new RequestBuilderFactory(this);
        playerProgressBar.setVisibility(View.VISIBLE);

        model.getPlayer(player_id).observe(this, (thisPlayer) -> {
            RequestBuilder requestBuilder = requestFactory.getPlayerRequestBuilder(thisPlayer.getPlayerHLTV(), new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    playerProgressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    playerProgressBar.setVisibility(View.GONE);
                    return false;
                }
            });
            requestBuilder.into(playerImage);
            nick.setText(thisPlayer.getNick());
            mouse.setText(thisPlayer.getMouse());
            dpi.setText(thisPlayer.getDPI());
            sens.setText(thisPlayer.getSens());
            resolution.setText(thisPlayer.getResolution());
        });

        model.getTeam(player_id).observe(this, (thisTeam) -> {
            teamProgressBar.setVisibility(View.VISIBLE);
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
            requestBuilder.into(teamImage);
            teamName.setText(thisTeam.getName());
        });
    }
}
