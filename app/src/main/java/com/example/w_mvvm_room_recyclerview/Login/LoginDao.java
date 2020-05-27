package com.example.w_mvvm_room_recyclerview.Login;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LoginDao {
    @Insert
    void insert(LoginResult loginResult);

    @Update
    void update(LoginResult user);

    @Delete
    void delete(LoginResult user);

    @Query("DELETE FROM login_table")
    void deleteAllLogin();

//    @Query("SELECT * FROM login_table ORDER ")
//    LiveData<List<LoginResult>> getAllLogin();
}
