package com.wolfram.aimquiz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.adapters.TeamsViewAdapter;
import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.database.UserDao;
import com.wolfram.aimquiz.tools.ItemClickListener;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class TeamsActivity extends Activity implements ItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        AppDatabase db = Room
                .databaseBuilder(this, AppDatabase.class, AppDatabase.APPDATABASE_NAME)
                .allowMainThreadQueries()
                .build();

        UserDao userDao = db.userDao();
        List<Team> playerList = userDao.loadAllTeams();
        recyclerView = findViewById(R.id.teams_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new TeamsViewAdapter(playerList, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItemId(position), Toast.LENGTH_SHORT).show();
    }
}
