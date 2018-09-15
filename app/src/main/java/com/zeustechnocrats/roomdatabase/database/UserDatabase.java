package com.zeustechnocrats.roomdatabase.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.zeustechnocrats.roomdatabase.Constants;
import com.zeustechnocrats.roomdatabase.dao.UserDao;
import com.zeustechnocrats.roomdatabase.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase UserDB;

    public abstract UserDao getUserDao();

    public static UserDatabase getInstance(Context context) {
        if (null == UserDB) {
            UserDB = buildDatabaseInstance(context);
        }
        return UserDB;
    }

    private static UserDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                UserDatabase.class,
                Constants.DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp() {
        UserDB = null;
    }

}
