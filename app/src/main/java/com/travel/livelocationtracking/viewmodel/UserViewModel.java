package com.travel.livelocationtracking.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.travel.livelocationtracking.UserRepository;
import com.travel.livelocationtracking.db.UserEntity;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<UserEntity>> mAllUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        mAllUsers = userRepository.getAllUser();
    }

    public LiveData<List<UserEntity>> getAllUser() {
        return mAllUsers;
    }



    public void insert(UserEntity userEntity) {
        userRepository.insert(userEntity);
    }
}
