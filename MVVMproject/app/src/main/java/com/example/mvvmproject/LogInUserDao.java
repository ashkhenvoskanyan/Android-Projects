package com.example.mvvmproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogInUserDao {


    @Query("SELECT * FROM logIn_table")
    LiveData<List<LogInUser>> getAllLogedUsers();

//    @Query("SELECT * FROM logIn_table WHERE username LIKE :username AND password LIKE :password")
//    LiveData<List<LogInUser>> logedUser(String username, String password);

    @Insert
    void insert(LogInUser logInUser);

    @Delete
    void delete(LogInUser logInUser);
}
