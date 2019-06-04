package com.wolfram.aimquiz.activities.teams;

import android.app.Application;

import com.wolfram.aimquiz.database.AppDatabase;
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
public class TeamsViewModel extends AndroidViewModel {
    private LiveData<List<Team>> teamsList;
    private UserDao userDao;
    public TeamsViewModel(@NonNull Application application) {
        super(application);
        userDao = AppDatabase.getInstance(application.getApplicationContext()).getUserDao();
    }

    LiveData<List<Team>> getTeamsList() {
        if (teamsList == null) {
            teamsList = userDao.loadAllTeams();
        }
        return teamsList;
    }
}
