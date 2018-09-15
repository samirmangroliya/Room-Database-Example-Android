package com.zeustechnocrats.roomdatabase.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.zeustechnocrats.roomdatabase.Constants;

import java.util.Objects;

@Entity(tableName = Constants.TABLE_NAME_USERS)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int userId;

    @ColumnInfo(name="fullname")
    private String name;

    @ColumnInfo(name="email")
    private String email;



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getName(), getEmail());
    }
}
