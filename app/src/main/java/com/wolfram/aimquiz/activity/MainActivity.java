package com.wolfram.aimquiz.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.database.UserDao;
import com.wolfram.aimquiz.tools.CSVReader;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

/*
todo:
ConstraintLayout
https://constraintlayout.com/basics/barriers.html
https://github.com/bumptech/glide
extends AppcompatAct.

https://developer.android.com/topic/libraries/architecture/livedata

https://github.com/JakeWharton/butterknife (pamietac ze nie moga byc prywatne)

jesli chcemy wrzucic jakas klase do wnętrza innej tylko dlatego że tak jest wygodniej/czytelniej zamiast w osobnym pliku to ta klasa powinna być static (tak zwana inner static class)

ModelView
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File dbFile = getApplicationContext().getDatabasePath(AppDatabase.APPDATABASE_NAME);
        if (!dbFile.exists()) {
            AsyncTask.execute(() -> {
                UserDao userDao = AppDatabase.getInstance(getApplicationContext()).getUserDao();

                CSVReader csvReader = new CSVReader(getApplicationContext(), userDao);

                csvReader.readTeams(Team.fileName);
                csvReader.readPlayers(Player.fileName);
            });
        }

    }

    public void playersClick(View view) {
        Intent intent = new Intent(this, PlayersActivity.class);
        startActivity(intent);
    }

    public void teamsClick(View view) {
        Intent intent = new Intent(this, TeamsActivity.class);
        startActivity(intent);
    }
}
