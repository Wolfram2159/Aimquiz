package com.wolfram.aimquiz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    private int uid;
    private String name;
    @ColumnInfo(name = "last_name")
    private String lastName;
    // getters and setters are ignored for brevity but they are required for Room to work.
}