package com.example.mvvmproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    private String name_surname;

    private String phoneNumber;

    private String dateOfBirth;


    public User(String name_surname, String phoneNumber, String dateOfBirth) {
        this.name_surname = name_surname;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }



    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public String getName_surname() {
        return name_surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }


}