package com.wolfram.aimquiz.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.database.AppDatabase;
import com.wolfram.aimquiz.threads.CreateDatabaseTask;

import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File dbFile = getApplicationContext().getDatabasePath(AppDatabase.APPDATABASE_NAME);
        if (!dbFile.exists()) {
            new CreateDatabaseTask(getApplicationContext()).execute();
        }
    }

    public void playersClick(View view) {
        Intent intent = new Intent(this, PlayersActivity.class);
        startActivity(intent);
    }

    public void teamsClick(View view) {
        Intent intent = new Intent(this, TeamsActivity.class);
        startActivity(intent);
    }
}
