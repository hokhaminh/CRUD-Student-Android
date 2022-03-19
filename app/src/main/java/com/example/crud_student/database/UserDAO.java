package com.example.crud_student.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crud_student.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("select * from user where name =:username")
    User getUserbyId(String username);
    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user")
    List<User> getListUser();

    @Query("SELECT * FROM user where name=:username")
    List<User> checkUser(String username);
}
