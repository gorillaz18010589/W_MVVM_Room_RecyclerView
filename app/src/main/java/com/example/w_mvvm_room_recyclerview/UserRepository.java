package com.example.w_mvvm_room_recyclerview;
/*
* //1.建構時取得1.userDatabase物建,userDao變形物件,和getAllUsers的LiveData
* //2.UserDao的insert方法
* //3.取得LiveData<List<User>> allUsers物件實體化方法
* //4.自己寫的背景類別,在背景執行中insert
* */
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    //1.建構時取得1.userDatabase物建,userDao變形物件,和getAllUsers的LiveData
    public UserRepository(Context context) {
        UserDatabase userDatabase = UserDatabase.getInstance(context);
        userDao = userDatabase.userDao();
        allUsers = userDao.getAllUsers();
    }

    //2.UserDao的insert方法
    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUsers(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    //3.取得LiveData<List<User>> allUsers物件實體化方法
    public LiveData<List<User>> getAllUsers() {
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
