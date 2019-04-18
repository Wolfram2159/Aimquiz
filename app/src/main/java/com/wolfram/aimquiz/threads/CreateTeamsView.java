package com.wolfram.aimquiz.threads;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.wolfram.aimquiz.activity.TeamDetailActivity;
import com.wolfram.aimquiz.adapters.TeamsViewAdapter;
import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.database.Team;
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
public class CreateTeamsView extends AsyncTask<Void, Void, List<Team>> {
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public CreateTeamsView(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected List<Team> doInBackground(Void... voids) {
        AppDatabase db = Room
                .databaseBuilder(context, AppDatabase.class, AppDatabase.APPDATABASE_NAME)
                .build();

        UserDao userDao = db.userDao();
        List<Team> teamList = userDao.loadAllTeams();
        return teamList;
    }

    @Override
    protected void onPostExecute(List<Team> teams) {
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TeamsViewAdapter(teams, new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.w("" + adapter.getItemId(position), "msg");
                Intent intent = new Intent(context, TeamDetailActivity.class);
                intent.putExtra("team_id",position+1);
                context.startActivity(intent);
            }
        },context);
        recyclerView.setAdapter(adapter);
    }
}
