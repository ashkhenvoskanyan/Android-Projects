package com.example.mvvmproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "logIn_table")
public class LogInUser {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String username;

    private String password;

    public LogInUser(String username, String password){

        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
