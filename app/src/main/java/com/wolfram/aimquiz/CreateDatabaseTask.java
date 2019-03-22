package com.wolfram.aimquiz;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import androidx.room.Room;

/**
 * @author Wolfram
 * @date 2019-03-22
 */
public class CreateDatabaseTask extends AsyncTask<Void,Void,Void> {
    private Context context;

    public CreateDatabaseTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... contexts) {
        AppDatabase db = Room
                .databaseBuilder(context, AppDatabase.class, "aimquiz.db")
                .build();
        UserDao userDao = db.userDao();

        AssetManager assetManager = context.getAssets();
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
        return null;
    }
}