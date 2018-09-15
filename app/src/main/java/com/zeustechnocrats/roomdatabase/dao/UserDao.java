package com.zeustechnocrats.roomdatabase.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.zeustechnocrats.roomdatabase.Constants;
import com.zeustechnocrats.roomdatabase.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM "+ Constants.TABLE_NAME_USERS)
    List<User> getAll();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User... user);

}
