package com.wolfram.aimquiz.threads;

import android.content.Context;
import android.os.AsyncTask;

import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.database.UserDao;
import com.wolfram.aimquiz.tools.CSVReader;

import androidx.room.Room;

/**
 * @author Wolfram
 * @date 2019-03-22
 */
public class CreateDatabaseTask extends AsyncTask<Void, Void, Void> {
    private Context context;
    private CSVReader csvReader;

    public CreateDatabaseTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... contexts) {
        AppDatabase db = Room
                .databaseBuilder(context, AppDatabase.class, AppDatabase.APPDATABASE_NAME)
                .build();

        UserDao userDao = db.userDao();

        csvReader = new CSVReader(context, userDao);

        csvReader.readTeams(Team.fileName);
        csvReader.readPlayers(Player.fileName);

        return null;
    }
}