package com.wolfram.aimquiz.application;

import android.app.Application;
import android.os.AsyncTask;

import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.database.UserDao;
import com.wolfram.aimquiz.tools.CSVReader;

import java.io.File;

/**
 * @author Wolfram
 * @date 2019-05-11
 */
public class AimquizApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());

        File dbFile = getApplicationContext().getDatabasePath(AppDatabase.APPDATABASE_NAME);
        if (!dbFile.exists()) {
            AsyncTask.execute(() -> {
                UserDao userDao = appDatabase.getUserDao();

                CSVReader csvReader = new CSVReader(getApplicationContext(), userDao);

                csvReader.readTeams(Team.fileName);
                csvReader.readPlayers(Player.fileName);
            });
        }
    }
}
