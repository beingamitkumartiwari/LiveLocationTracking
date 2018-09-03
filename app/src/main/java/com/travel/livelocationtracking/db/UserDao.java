package com.travel.livelocationtracking.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Query("SELECT * from user_table ORDER BY user_name ASC")
    LiveData<List<UserEntity>> getAllUser();

    @Query("SELECT * FROM user_table WHERE user_email LIKE :email AND "
            + "user_password LIKE :password LIMIT 1")
    UserEntity userLogin(String email, String password);

    @Insert(onConflict = REPLACE)
    void insertAll(UserEntity... userEntities);

}
