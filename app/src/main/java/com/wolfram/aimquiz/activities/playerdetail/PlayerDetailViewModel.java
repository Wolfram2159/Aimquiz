package com.wolfram.aimquiz.activities.playerdetail;

import android.app.Application;

import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.database.UserDao;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * @author Wolfram
 * @date 2019-06-04
 */
public class PlayerDetailViewModel extends AndroidViewModel {
    //todo: make fragments, and download data from one viewModel about specific player
    private LiveData<Player> player;
    private LiveData<Team> team;
    private UserDao userDao;

    public PlayerDetailViewModel(@NonNull Application application) {
        super(application);
        userDao = AppDatabase.getInstance(application.getApplicationContext()).getUserDao();
    }

    LiveData<Player> getPlayer(int id) {
        if (player == null) {
            player = userDao.loadPlayer(id);
        }
        return player;
    }

    LiveData<Team> getTeam(int id) {
        if (team == null) {
            team = userDao.loadTeamByPlayerId(id);
        }
        return team;
    }
}
