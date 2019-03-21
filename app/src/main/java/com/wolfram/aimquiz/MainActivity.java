package com.wolfram.aimquiz;

import android.content.res.AssetManager;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "aimquiz.db")
                .allowMainThreadQueries()
                .build();
        UserDao userDao = db.userDao();

        AssetManager assetManager = getApplicationContext().getAssets();
        InputStream inputStream = null;
        CSVReader csvReader;
        List<Team> teamList;
        List<Player> playersList;
        try {
            inputStream = assetManager.open("teams.csv");
            csvReader = new CSVReader(inputStream);
            teamList = csvReader.read();
            userDao.InsertTeamList(teamList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStream = assetManager.open("players.csv");
            csvReader = new CSVReader(inputStream);
            playersList = csvReader.read();
            userDao.insertPlayersList(playersList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
