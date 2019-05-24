package com.wolfram.aimquiz.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

/**
 * @author Wolfram
 * @date 2019-03-14
 */
@Dao
public interface UserDao {

    //Players

    @Query("SELECT * FROM players")
    LiveData<List<Player>> loadAllPlayers();

    @Query("SELECT * FROM players WHERE _id=(:userId)")
    LiveData<Player> loadPlayer(int userId);

    @Transaction
    @Insert
    void insertPlayers(List<Player> users);

    @Query("SELECT * FROM players WHERE team_id=(:team_id)")
    LiveData<List<Player>> loadPlayersFromTeam(int team_id);

    //Teams

    @Query("SELECT * FROM teams")
    LiveData<List<Team>> loadAllTeams();

    @Query("SELECT teams._id, teams.name " +
            "FROM teams JOIN players " +
            "ON players.team_id=teams._id " +
            "WHERE players._id=(:playerId)")
    LiveData<Team> loadTeamByPlayerId(int playerId);

    @Transaction
    @Insert
    void insertTeams(List<Team> teamList);
}