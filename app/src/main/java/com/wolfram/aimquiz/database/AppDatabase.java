package com.wolfram.aimquiz.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Ignore;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author Wolfram
 * @date 2019-03-14
 */
@Database(entities = {Player.class, Team.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    @Ignore
    public final static String APPDATABASE_NAME = "aimquiz.db";

    private static AppDatabase appDatabase;

    public abstract UserDao getUserDao();

    public static synchronized AppDatabase getInstance(Context context){
        if (appDatabase == null) {
            appDatabase = Room
                    .databaseBuilder(context, AppDatabase.class, AppDatabase.APPDATABASE_NAME)
                    .build();
        }
        return appDatabase;
    }
}
