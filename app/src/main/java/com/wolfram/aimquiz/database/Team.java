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
    private int teamHLTV;
    @Ignore
    public final static String fileName = "teams.csv";
    @Ignore
    public final static String HLTV_URL = "https://static.hltv.org/images/team/logo/";
    @Ignore
    public final static String HLTV_URL_UNKNOWN = "https://static.hltv.org/images/team/logo/10044";

    public Team() {
    }

    public Team(int id, String name, int teamHLTV) {
        this.id = id;
        this.name = name;
        this.teamHLTV = teamHLTV;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamHLTV=" + teamHLTV +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamHLTV() {
        return teamHLTV;
    }

    public void setTeamHLTV(int teamHLTV) {
        this.teamHLTV = teamHLTV;
    }
}
