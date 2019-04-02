package com.wolfram.aimquiz.database;

import android.content.Context;
import android.os.AsyncTask;

import com.wolfram.aimquiz.tools.CSVReader;

import java.util.List;

import androidx.room.Room;

/**
 * @author Wolfram
 * @date 2019-03-22
 */
public class CreateDatabaseTask extends AsyncTask<Void, Void, Void> {
    private Context context;
    private CSVReader csvReader;
    private List<Team> teamList;
    private List<Player> playersList;

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