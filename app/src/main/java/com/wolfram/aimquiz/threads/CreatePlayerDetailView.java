package com.wolfram.aimquiz.threads;

import android.content.Context;
import android.os.AsyncTask;

import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.UserDao;

import androidx.room.Room;

/**
 * @author Wolfram
 * @date 2019-04-14
 */
public class CreatePlayerDetailView extends AsyncTask<Void, Void, Player> {
    private int player_id;
    private Context context;

    public CreatePlayerDetailView(int player_id, Context context) {
        this.player_id = player_id;
        this.context = context;
    }

    @Override
    protected Player doInBackground(Void... voids) {
        AppDatabase db = Room
                .databaseBuilder(context, AppDatabase.class, AppDatabase.APPDATABASE_NAME)
                .build();

        UserDao userDao = db.userDao();
        Player player = userDao.loadPlayer(player_id);
        return null;
    }

    @Override
    protected void onPostExecute(Player player) {
        super.onPostExecute(player);
    }
}
