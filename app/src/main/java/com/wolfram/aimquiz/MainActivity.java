package com.wolfram.aimquiz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CreateDatabaseTask(getApplicationContext()).execute();
        //todo: how to create database only one time?
    }
}
