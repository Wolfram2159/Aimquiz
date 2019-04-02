package com.wolfram.aimquiz.tools;

import android.content.Context;
import android.content.res.AssetManager;

import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.database.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wolfram
 * @date 2019-03-21
 */

public class CSVReader {
    private AssetManager assetManager;
    private InputStream inputStream;
    private UserDao userDao;

    public CSVReader(Context context, UserDao userDao) {
        this.userDao = userDao;
        assetManager = context.getAssets();

    }

    public void readPlayers(String fileName) {
        List<Player> playerList = new ArrayList<>();
        try {
            inputStream = assetManager.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(";");
                int id = Integer.valueOf(row[0]);
                String nick = row[1];
                int team_id = Integer.valueOf(row[2]);
                String mouse = row[3];
                String DPI = row[4];
                String sens = row[5];
                String resolution = row[6];
                playerList.add(new Player(id, team_id, nick, mouse, DPI, sens, resolution));
            }
            reader.close();
            userDao.insertPlayers(playerList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readTeams(String fileName) {
        List<Team> teamList = new ArrayList<>();
        try {
            inputStream = assetManager.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(";");
                Integer id = Integer.valueOf(row[0]);
                String name = row[1];
                teamList.add(new Team(id, name));
            }
            reader.close();
            userDao.insertTeams(teamList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
