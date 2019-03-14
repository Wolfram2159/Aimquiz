package com.wolfram.aimquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * @author Wolfram
 * @date 2019-03-14
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> loadAll();
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByUserId(int... userIds);
    @Query("SELECT * FROM user where name LIKE :first AND last_name LIKE :last LIMIT 1")
    User loadOneByNameAndLastName(String first, String last);
    @Insert
    void insertAll(User... users);
    @Delete
    void delete(User user);
}
