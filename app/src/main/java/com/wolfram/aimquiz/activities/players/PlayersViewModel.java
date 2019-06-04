package com.wolfram.aimquiz.activities.players;

import android.app.Application;

import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.UserDao;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * @author Wolfram
 * @date 2019-06-04
 */
public class PlayersViewModel extends AndroidViewModel {
    private LiveData<List<Player>> playersList;
    private UserDao userDao;

    public PlayersViewModel(@NonNull Application application) {
        super(application);
        this.userDao = AppDatabase.getInstance(application.getApplicationContext()).getUserDao();
    }

    LiveData<List<Player>> getPlayersList(){
        if (playersList == null){
            playersList = userDao.loadAllPlayers();
        }
        return playersList;
    }
}
