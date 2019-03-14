package com.wolfram.aimquiz;

import android.arch.persistence.room.Room;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHelper myDbHelper = new DataBaseHelper(MainActivity.this);
        myDbHelper = new DataBaseHelper(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {
            Log.e("asd","asd");
            throw new Error("Unable to create database");
        }

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }
        AppDatabase db = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "aimquiz")
                .build();
    }
}
