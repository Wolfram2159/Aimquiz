package com.wolfram.aimquiz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.adapters.PlayersViewAdapter;
import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.UserDao;
import com.wolfram.aimquiz.tools.ItemClickListener;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class PlayersActivity extends Activity implements ItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        AppDatabase db = Room
                .databaseBuilder(this, AppDatabase.class, AppDatabase.APPDATABASE_NAME)
                .allowMainThreadQueries()
                .build();

        UserDao userDao = db.userDao();
        List<Player> playerList = userDao.loadAllPlayers();

        recyclerView = findViewById(R.id.players_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new PlayersViewAdapter(playerList, this);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItemId(position), Toast.LENGTH_SHORT).show();
    }
}