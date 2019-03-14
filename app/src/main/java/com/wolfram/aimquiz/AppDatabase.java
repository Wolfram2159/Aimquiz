package com.wolfram.aimquiz;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * @author Wolfram
 * @date 2019-03-14
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
