package com.wolfram.aimquiz;

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
    InputStream inputStream;

    public CSVReader(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List read(){
        Team team;
        Player player;
        List<Team> resultListTeam = new ArrayList<>();
        List<Player> resultListPlayer = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        boolean returnValue = true;
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(";");
                if(row.length==2) {
                    team = new Team(Integer.valueOf(row[0]), row[1]);
                    resultListTeam.add(team);
                }else{
                    returnValue=false;
                    int id = Integer.valueOf(row[0]);
                    String name = row[1];
                    int team_id = Integer.valueOf(row[2]);
                    String mouse = row[3];
                    String DPI = row[4];
                    String sens = row[5];
                    String resolution = row[6];
                    player = new Player(id,team_id,name,mouse,DPI,sens,resolution);
                    resultListPlayer.add(player);
                }
                //Log.d("VariableTag", row[0].toString());
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        if(returnValue) {return resultListTeam;}
        else {return resultListPlayer;}
    }
}
