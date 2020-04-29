package com.example.w_mvvm_room_recyclerview;
/*
* //1.建構時取得1.userDatabase物建,userDao變形物件,和getAllUsers的LiveData
* //2.UserDao的insert方法
* //3.取得LiveData<List<User>> allUsers物件實體化方法
* //4.自己寫的背景類別,在背景執行中insert
* */
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private String TAG = "hank";
    private String TAG_MSG ="UserRepository";

    //1.建構時取得1.userDatabase物建,userDao變形物件,和getAllUsers的LiveData
    public UserRepository(Application application) {
        Log.v(TAG,TAG_MSG + "UserRepository()");
        UserDatabase userDatabase = UserDatabase.getInstance(application);
        userDao = userDatabase.userDao();
        allUsers = userDao.getAllUsers();

    }

    //2.UserDao的insert方法
    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new DeleteAllUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUsers(User user) {
        new DeleteAllUserAsyncTask(userDao).execute(user);
    }

    //3.取得LiveData<List<User>> allUsers物件實體化方法
    public LiveData<List<User>> getAllUsers() {
        Log.v(TAG,TAG_MSG + "getAllUsers()");
        return allUsers;
    }

    //4.自己寫的背景類別,在背景執行中insert
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        //A.建構時拿到userDao物件
        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            //B.在背景執行insert
            userDao.insert(users[0]);
            return null;
        }
    }

    //4.自己寫的背景類別,在背景執行中update
    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        //A.建構時拿到userDao物件
        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            //B.在背景執行insert
            userDao.update(users[0]);
            return null;
        }
    }

    //4.自己寫的背景類別,在背景執行中 Delete
    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        //A.建構時拿到userDao物件
        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            //B.在背景執行insert
            userDao.delete(users[0]);
            return null;
        }
    }
    //4.自己寫的背景類別,在背景執行中 DeleteAll
    private static class DeleteAllUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        //A.建構時拿到userDao物件
        private DeleteAllUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            //B.在背景執行insert
            userDao.deleteAllUsers();
            return null;
        }
    }
}
