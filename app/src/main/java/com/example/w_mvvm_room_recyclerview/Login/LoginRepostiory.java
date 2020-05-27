package com.example.w_mvvm_room_recyclerview.Login;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LoginRepostiory {
    private LoginDao loginDao;
    private LiveData<List<LoginDao>> allLogins;
    private String TAG = "hank";
    private String TAG_MSG ="UserRepository";
}
