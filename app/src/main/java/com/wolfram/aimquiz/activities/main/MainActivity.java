package com.wolfram.aimquiz.activities.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.activities.players.PlayersActivity;
import com.wolfram.aimquiz.activities.teams.TeamsActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
ConstraintLayout
https://constraintlayout.com/basics/barriers.html
https://github.com/bumptech/glide
extends AppcompatAct.

https://developer.android.com/topic/libraries/architecture/livedata

https://github.com/JakeWharton/butterknife (pamietac ze nie moga byc prywatne)

todo : jesli chcemy wrzucic jakas klase do wnętrza innej tylko dlatego że tak jest wygodniej/czytelniej zamiast w osobnym pliku to ta klasa powinna być static (tak zwana inner static class)

ViewModel

https://developer.android.com/training/transitions/index.html

https://developer.android.com/training/animation/overview

todo : what is difference between scaleTypes on ImageView
*/
public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.btn_players) void playersClick(View view) {
        Intent intent = new Intent(this, PlayersActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @OnClick(R.id.btn_teams) void teamsClick(View view) {
        Intent intent = new Intent(this, TeamsActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
