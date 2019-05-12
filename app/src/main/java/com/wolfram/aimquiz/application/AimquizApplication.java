package com.wolfram.aimquiz.application;

import android.app.Application;

import com.wolfram.aimquiz.database.AppDatabase;

/**
 * @author Wolfram
 * @date 2019-05-11
 */
public class AimquizApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase.getInstance(getApplicationContext());
    }
}
