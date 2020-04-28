package com.example.w_mvvm_room_recyclerview;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/*
 *在DAO（數據訪問對象）中，可以使用SQL語句進行對數據庫的操作和將這些語句與Java中方法關聯，調用編譯器會檢查SQL語句並通過註解生成對應的查詢語句，例如@Insert。
 注意：
 1，DAO必現是抽像類或接口
 2，所有的查詢語句必須在單獨的線程裡面執行。
 *
 * */

/*
 * @Dao:
 * @Update:
 * @Delete:
 * @Query
 * */
@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_table")
    void deleteAllUsers();

    @Query("SELECT * FROM user_table ORDER by priority DESC")
    LiveData<List<User>> getAllUsers();
}
