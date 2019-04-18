package com.wolfram.aimquiz.threads;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.wolfram.aimquiz.activity.PlayerDetailActivity;
import com.wolfram.aimquiz.adapters.PlayersViewAdapter;
import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.UserDao;
import com.wolfram.aimquiz.tools.ItemClickListener;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

/**
 * @author Wolfram
 * @date 2019-04-18
 */
public class CreateTeamDetailView extends AsyncTask<Void, Void, List<Player>> {
    private Context context;
    private int team_id;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ImageView imageView;

    public CreateTeamDetailView(Context context, int team_id, RecyclerView recyclerView, ImageView imageView) {
        this.context = context;
        this.team_id = team_id;
        this.recyclerView = recyclerView;
        this.imageView = imageView;
    }

    @Override
    protected List<Player> doInBackground(Void... voids) {
        AppDatabase db = Room
                .databaseBuilder(context, AppDatabase.class, AppDatabase.APPDATABASE_NAME)
                .build();

        UserDao userDao = db.userDao();
        List<Player> playerList = userDao.loadPlayersFromTeam(team_id);
        return playerList;
    }

    @Override
    protected void onPostExecute(final List<Player> players) {
        recyclerView.setHasFixedSize(true);
        imageView.setImageResource(context.getResources().getIdentifier(
                "team_"+team_id,
                "drawable",
                "com.wolfram.aimquiz"));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new PlayersViewAdapter(players, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.w("" + players.get(position).getId(), "msg");
                Intent intent = new Intent(context, PlayerDetailActivity.class);
                intent.putExtra("player_id",players.get(position).getId());
                context.startActivity(intent);
            }
        },context);
        recyclerView.setAdapter(mAdapter);
    }
}
