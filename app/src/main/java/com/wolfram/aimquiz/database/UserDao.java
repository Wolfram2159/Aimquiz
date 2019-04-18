package com.wolfram.aimquiz.database;

import java.util.List;

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
    List<Player> loadAllPlayers();

    @Query("SELECT * FROM players WHERE _id=(:userId)")
    Player loadPlayer(int userId);

    @Transaction
    @Insert
    void insertPlayers(List<Player> users);

    @Query("SELECT * FROM players WHERE team_id=(:team_id)")
    List<Player> loadPlayersFromTeam(int team_id);
    //Teams

    @Query("SELECT * FROM teams")
    List<Team> loadAllTeams();

    @Query("SELECT * FROM teams WHERE _id=(:teamId)")
    Team loadTeam(int teamId);

    @Transaction
    @Insert
    void insertTeams(List<Team> teamList);
}