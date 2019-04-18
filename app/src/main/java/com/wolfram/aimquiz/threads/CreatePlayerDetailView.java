package com.wolfram.aimquiz.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.database.UserDao;

import androidx.room.Room;

/**
 * @author Wolfram
 * @date 2019-04-14
 */
public class CreatePlayerDetailView extends AsyncTask<Void, Void, Player> {
    private int player_id;
    private Context context;
    private ImageView player_view;
    private TextView nick;
    private TextView teamName;
    private ImageView team_view;
    private TextView mouse;
    private TextView dpi;
    private TextView sens;
    private TextView resolution;
    private String team_name;

    public CreatePlayerDetailView(int player_id, Context context, ImageView player_view, TextView nick, TextView teamName, ImageView team_view, TextView mouse, TextView dpi, TextView sens, TextView resolution) {
        this.player_id = player_id;
        this.context = context;
        this.player_view = player_view;
        this.nick = nick;
        this.teamName = teamName;
        this.team_view = team_view;
        this.mouse = mouse;
        this.dpi = dpi;
        this.sens = sens;
        this.resolution = resolution;
    }

    @Override
    protected Player doInBackground(Void... voids) {
        AppDatabase db = Room
                .databaseBuilder(context, AppDatabase.class, AppDatabase.APPDATABASE_NAME)
                .build();

        UserDao userDao = db.userDao();
        Player player = userDao.loadPlayer(player_id);
        Log.e("player id", ""+player.getId()+"");
        Team team = userDao.loadTeam(player.getTeam_id());
        team_name = team.getName();
        return player;
    }

    @Override
    protected void onPostExecute(Player player) {
        super.onPostExecute(player);
        player_view.setImageResource(context.getResources().getIdentifier(
                "player_"+player.getId(),
                "drawable",
                "com.wolfram.aimquiz"));
        nick.setText(player.getNick());
        teamName.setText(team_name);
        team_view.setImageResource(context.getResources().getIdentifier(
                "team_"+player.getTeam_id(),
                "drawable",
                "com.wolfram.aimquiz"));
        mouse.setText(player.getMouse());
        dpi.setText(player.getDPI());
        sens.setText(player.getSens());
        resolution.setText(player.getResolution());
    }
}
