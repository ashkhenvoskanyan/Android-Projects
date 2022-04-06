package com.example.mvvmproject;


import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {User.class, LogInUser.class}, version = 2, exportSchema = false)
abstract class AppDatabase extends RoomDatabase {



    public abstract UserDao userDao();

    public abstract LogInUserDao logInUserDao();

}