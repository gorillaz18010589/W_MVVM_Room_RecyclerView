package com.example.w_mvvm_room_recyclerview;
//https://medium.com/@arthastseng/%E5%AF%A6%E4%BD%9Cmvvm-pattern-livedata-%E4%BB%A5search-github-repositories-%E7%82%BA%E4%BE%8B-5a45afac66c3
//參考Sqlte語法:https://www.sqlite.org/c3ref/bind_blob.html
//1.加入API
//2.建立@Entity表:設定資料庫欄位
//3.建立@Dao interface:定義語法操作
//4.建立@Database 繼承RoomDatabase :創建數據庫,裡面包含DAO,Entity
//5.創建UserRepository區,裡面放LiveData:保存數據,可以備感知,當數據變動會呼叫,減少內存外洩
//6.ViewModel:好處當選轉時Acitivty restart,ViewModel仍然存活著
//7.回到MainActivity userViewModel物件實體化,取得LiveData建立Obseve
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.w_mvvm_room_recyclerview.Login.LoginDao;
import com.example.w_mvvm_room_recyclerview.Login.LoginDataBase;
import com.example.w_mvvm_room_recyclerview.Login.LoginResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private String TAG ="hank";
    private String url ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        loginApi();

        //2.init RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final UserRecyclerViewAdapter userRecyclerViewAdapter = new UserRecyclerViewAdapter();
        recyclerView.setAdapter(userRecyclerViewAdapter);


        //1.init ViewModel
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //3.將這個接收到的新資料,更新再Adapter
                userRecyclerViewAdapter.setUsers(users);
            }
        });




    }

    private void loginApi() {
        Map<String,String> map = new HashMap<>();
        map.put("module", "app");
        map.put("action", "login");
        map.put("app", "login");
        map.put("phone", "13232236359");
        map.put("password", "aaaa1111");
        map.put("cart_id", "");
        MyOkHttpApi.instance().doPostFormBody(url, map, new MyOkHttpApi.OkHttpCallBack() {
            @Override
            public void onFailure(IOException e) {

            }

            @Override
            public void onSuccess(Response response) {
                if(response.isSuccessful()){
                    try {
                        String body = response.body().string();
                        Log.v(TAG,"MainActivity:isSuccessful:" + "/body:" + body);
                        Gson gson = new Gson();
                        LoginResult loginResult =  gson.fromJson(body, LoginResult.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
