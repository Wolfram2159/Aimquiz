package com.wolfram.aimquiz.activities.teamdetail;

import android.app.Application;

import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.database.UserDao;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * @author Wolfram
 * @date 2019-06-05
 */
public class TeamDetailViewModel extends AndroidViewModel {
    private LiveData<List<Player>> teamPlayers;
    private LiveData<Team> team;
    private UserDao userDao;

    public TeamDetailViewModel(@NonNull Application application) {
        super(application);
        userDao = AppDatabase.getInstance(application.getApplicationContext()).getUserDao();
    }

    LiveData<List<Player>> getTeamPlayers(int id) {
        if (teamPlayers == null) {
            teamPlayers = userDao.loadPlayersFromTeam(id);
        }
        return teamPlayers;
    }

    LiveData<Team> getTeam(int id) {
        if (team == null) {
            team = userDao.loadTeam(id);
        }
        return team;
    }
}
