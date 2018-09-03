package com.travel.livelocationtracking;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.travel.livelocationtracking.db.UserDao;
import com.travel.livelocationtracking.db.UserDatabase;
import com.travel.livelocationtracking.db.UserEntity;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<UserEntity>> mAllUserEntity;
    UserDatabase db;

    public UserRepository(Application application) {
        db = UserDatabase.getUserDatabase(application);
        mUserDao = db.userDao();
        mAllUserEntity = mUserDao.getAllUser();
    }

    public LiveData<List<UserEntity>> getAllUser(){
        return mAllUserEntity;
    }


    public void insert(UserEntity userEntity){
        new insertAsyncTask(mUserDao).execute(userEntity);
    }

    private static class insertAsyncTask extends AsyncTask<UserEntity, Void, Void>{
        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            mAsyncTaskDao.insertAll(userEntities[0]);
            return null;
        }
    }

}
