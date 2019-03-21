package com.wolfram.aimquiz;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "players" /*, foreignKeys = @ForeignKey(entity = Team.class, parentColumns = "_id",childColumns = "team_id")*/)
public class Player {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;
    private String player;
    private int team_id;
    private String mouse;
    private String DPI;
    private String sens;
    private String resolution;

    public Player(int id, int team_id, String player, String mouse, String DPI, String sens, String resolution) {
        this.id = id;
        this.player = player;
        this.team_id = team_id;
        this.mouse = mouse;
        this.DPI = DPI;
        this.sens = sens;
        this.resolution = resolution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getSens() {
        return sens;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}