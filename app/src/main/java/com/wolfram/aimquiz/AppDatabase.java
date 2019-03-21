package com.wolfram.aimquiz;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author Wolfram
 * @date 2019-03-14
 */
@Database(entities = {Player.class, Team.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
