package com.wolfram.aimquiz.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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

    /*@Query("SELECT * from teams")
    List<Team> loadAllTeams();*/
    /*@Query("SELECT * FROM players WHERE _id IN (:userIds)")
    List<Player> loadAllByUserId(int... userIds);*/
    @Insert
    void insertAll(Player... users);

    @Insert
    void insertPlayersList(List<Player> users);

    @Insert
    void insertPlayer(Player player);

    @Insert
    void insertTeam(Team team);

    @Delete
    void delete(Player user);

    @Insert
    void InsertTeamList(List<Team> teamList);
}