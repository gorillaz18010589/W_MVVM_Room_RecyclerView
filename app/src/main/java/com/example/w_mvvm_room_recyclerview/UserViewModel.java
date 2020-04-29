package com.example.w_mvvm_room_recyclerview;
//A.繼承ViewModelProvider.AndroidViewModelFactory
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
   private UserRepository userRepository;
   private  LiveData<List<User>> allUsers;


    //1.建構式
    public UserViewModel(@NonNull Application application) {
        super(application);
        //A.建構時拿到userRepository物件,並且取得裡面allUsers的LiveData
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
        Log.v("hank","UserViewModel =>" + "UserViewModel()");
    }



    //B.設定ViewModel增刪修茶方法
    public void insert(User user){
       userRepository.insert(user);
    }

    public void update(User user){
        userRepository.update(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public void deleteAllUsers(User user){
        userRepository.deleteAllUsers(user);
    }

    //C.取得LiveData物件
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
}
