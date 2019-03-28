package com.wolfram.aimquiz.database;

import androidx.room.Database;
import androidx.room.Ignore;
import androidx.room.RoomDatabase;

/**
 * @author Wolfram
 * @date 2019-03-14
 */
@Database(entities = {Player.class, Team.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    @Ignore
    public final static String APPDATABASE_NAME = "aimquiz.db";

    public abstract UserDao userDao();
}
