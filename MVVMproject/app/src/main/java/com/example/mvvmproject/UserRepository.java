package com.example.mvvmproject;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;


import java.util.List;

public class UserRepository {

    private String DB_NAME = "USER_DB";
    private AppDatabase appDatabase;


    public UserRepository(Application application) {
        appDatabase = Room.databaseBuilder(application, AppDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public void insert(final User user) {
        new Thread(() -> {
            appDatabase.userDao().insert(user);
        }).start();
    }

    public void insert(final LogInUser logInUser){
        new Thread(() ->{
            appDatabase.logInUserDao().insert(logInUser);
        }).start();
    }

    public void update(final User user) {
        new Thread(() -> {
            appDatabase.userDao().update(user);
        }).start();
    }

    public void delete(final int id) {
        final LiveData<User> task = getUser(id);
        if (task != null) {
            new Thread(() -> {
                appDatabase.userDao().delete(task.getValue());
            }).start();
        }
    }

    public void delete(final User user) {
        new Thread(() -> {
            appDatabase.userDao().delete(user);
        }).start();
    }

    public void delete(final LogInUser logInUser){
        new Thread(() -> {
            appDatabase.logInUserDao().delete(logInUser);
        }).start();
    }
//    public void deleteAll(){
//        new DeleteAllUserAsyncTask(userDao).execute();
//    }

    public LiveData<User> getUser(int id) {
        return appDatabase.userDao().getUser(id);
    }

    public LiveData<List<User>> getAllUsers() {
        return appDatabase.userDao().getAllUsers();
    }

    public LiveData<List<LogInUser>> getAllLogedUsers(){
        return appDatabase.logInUserDao().getAllLogedUsers();
    }
}