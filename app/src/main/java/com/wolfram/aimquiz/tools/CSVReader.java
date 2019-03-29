package com.wolfram.aimquiz.tools;

import android.content.Context;
import android.content.res.AssetManager;

import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.Team;

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
    private String fileName;
    private Context context;

    public CSVReader(String fileName, Context context) {
        this.fileName = fileName;
        this.context = context;
    }

    public List read() {
        Team team;
        Player player;
        List<Team> resultListTeam = new ArrayList<>();
        List<Player> resultListPlayer = new ArrayList<>();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(";");
                if (fileName == Team.fileName) {
                    team = new Team(Integer.valueOf(row[0]), row[1]);
                    resultListTeam.add(team);
                } else if (fileName == Player.fileName) {
                    int id = Integer.valueOf(row[0]);
                    String nick = row[1];
                    int team_id = Integer.valueOf(row[2]);
                    String mouse = row[3];
                    String DPI = row[4];
                    String sens = row[5];
                    String resolution = row[6];
                    player = new Player(id, team_id, nick, mouse, DPI, sens, resolution);
                    resultListPlayer.add(player);
                }
                //Log.d("VariableTag", row[0].toString());
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        if (fileName == Team.fileName) {
            return resultListTeam;
        } else if (fileName == Player.fileName) {
            return resultListPlayer;
        } else {
            return null;
        }
    }
}
