package com.wolfram.aimquiz.threads;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

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
 * @date 2019-04-05
 */

public class CreatePlayersView extends AsyncTask<Void, Void, List<Player>> {
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    public CreatePlayersView(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected List<Player> doInBackground(Void... voids) {
        AppDatabase db = Room
                .databaseBuilder(context, AppDatabase.class, AppDatabase.APPDATABASE_NAME)
                .build();

        UserDao userDao = db.userDao();
        List<Player> playerList = userDao.loadAllPlayers();
        return playerList;
    }

    @Override
    protected void onPostExecute(List<Player> playerList) {
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new PlayersViewAdapter(playerList, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.w("" + (position), "msg");
                Intent intent = new Intent(context, PlayerDetailActivity.class);
                intent.putExtra("player_id",mAdapter.getItemId(position));
                context.startActivity(intent);
            }
        },context);
        recyclerView.setAdapter(mAdapter);
    }
}
