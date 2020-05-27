package com.example.w_mvvm_room_recyclerview.Login;
// 1、創建繼承RoomDatabase的抽像類。
// 2、在繼承的類前使用註解@Database。
// 3申明數據庫結構的Entity，並且設置數據庫的版本號。
// 4.宣告抽象類別UserDao
// 5.創建UserDatabase物件實體
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.w_mvvm_room_recyclerview.MyOkHttpApi;
import com.example.w_mvvm_room_recyclerview.User;
import com.example.w_mvvm_room_recyclerview.UserDao;
import com.example.w_mvvm_room_recyclerview.UserDatabase;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

@Database(entities = {LoginResult.class} , version = 1)
public abstract class LoginDataBase extends RoomDatabase {
    private static LoginDataBase instance;
    private static String TAG = "hank";
    private static String TAG_MSG ="UserDatabase";

    //4.宣告抽象類別UserDao,讓UserRepository玩
    public abstract LoginDao loginDao();

    //5.創建UserDatabase物件實體
    public static synchronized LoginDataBase getInstance(Context context) {
        if (instance == null) {
            Log.v(TAG, TAG_MSG + "getInstance()");
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), LoginDataBase.class, "login_database")
                    .fallbackToDestructiveMigration()//如果未找到舊有的db,table就允許破壞性的重創Table
                    //9.加入自己寫的callback
                    .addCallback(roomCallbcak)//新增一個回呼事件(RoomDatabase.的CallBack事件)
                    .build();//創建一個db並且序列化

        }
        Log.v(TAG, TAG_MSG + "成功創建DB:" + instance.toString());
        return instance;
    }

    //6.自己寫RoomDatabase.Callback,複寫當DB創建時
    private static RoomDatabase.Callback roomCallbcak = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.v(TAG,TAG_MSG +"onCreate()");
            //8.創建時,PopulateDbAsyncTask的自動產生資料

        }
    };


}
