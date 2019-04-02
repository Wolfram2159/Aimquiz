package com.wolfram.aimquiz.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

/**
 * @author Wolfram
 * @date 2019-03-14
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM players")
    List<Player> loadAllPlayers();

    @Query("SELECT * FROM teams")
    List<Team> loadAllTeams();

    @Insert
    void insertPlayer(Player player);

    @Insert
    void insertTeam(Team team);

    @Transaction
    @Insert
    void insertPlayers(List<Player> users);

    @Transaction
    @Insert
    void insertTeams(List<Team> teamList);
}