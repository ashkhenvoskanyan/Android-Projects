package com.example.mvvmproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel  extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<List<User>> allUsers;
    private LiveData<List<LogInUser>> allLogedUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
        allLogedUsers = repository.getAllLogedUsers();
    }

    public void insert(User user){
        repository.insert(user);
    }

    public void insert(LogInUser logInUser){
        repository.insert(logInUser);
    }

    public void update(User user){
        repository.update(user);
    }

    public void delete(User user) { repository.delete(user); }

    public void delete(LogInUser logInUser){
        repository.delete(logInUser);
    }

//    public void deleteAllUsers(){
//        repository.deleteAll();
//    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<LogInUser>> getAllLogedUsers(){
        return allLogedUsers;
    }

}
