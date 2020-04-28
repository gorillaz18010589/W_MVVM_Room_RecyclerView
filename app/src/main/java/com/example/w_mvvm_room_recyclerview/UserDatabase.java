package com.example.w_mvvm_room_recyclerview;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
//創建Room database包括三個步驟：
// 1、創建繼承RoomDatabase的抽像類。
// 2、在繼承的類前使用註解@Database。
// 3申明數據庫結構的Entity，並且設置數據庫的版本號。
// 4.宣告抽象類別UserDao
// 5.創建UserDatabase物件實體
/*
*@Database(entities = {User.class} , version = 1) //@Database(1.entities:要加入到資料庫的Entity表, 2.version:版本數)
* */

//2、在繼承的類前使用註解@Database。
@Database(entities = {User.class}, version = 1) //@Database(1.entities:要加入到資料庫的bean, 2.version:版本數)\
public abstract class UserDatabase extends RoomDatabase {//1、創建繼承RoomDatabase的抽像類。
    public static UserDatabase instance;

    //4.宣告抽象類別UserDao,讓UserRepository玩
    public abstract UserDao userDao();

    //5.創建UserDatabase物件實體
    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    //9.加入自己寫的callback
                    .addCallback(roomCallbcak)
                    .build();
        }
        return instance;
    }

    //6.自己寫RoomDatabase.Callback,複寫當DB創建時
    private static RoomDatabase.Callback roomCallbcak = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //8.創建時,PopulateDbAsyncTask的自動產生資料
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    //7.新增資料任務
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private UserDao userDao;

        private PopulateDbAsyncTask(UserDatabase userDatabase){
            userDao = userDatabase.userDao();
            userDao.insert(new User("1","自動產生1",1));
            userDao.insert(new User("1","自動產生2",1));
            userDao.insert(new User("1","自動產生3",1));
            userDao.insert(new User("1","自動產生4",1));
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
