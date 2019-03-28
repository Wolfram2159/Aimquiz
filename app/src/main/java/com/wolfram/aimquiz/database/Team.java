package com.wolfram.aimquiz.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author Wolfram
 * @date 2019-03-17
 */
@Entity(tableName = "teams")
public class Team {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private int id;
    private String name;
    @Ignore
    public final static String fileName = "teams.csv";

    @Override
    public String toString() {
        return name;
    }

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int team_id) {
        this.id = team_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
