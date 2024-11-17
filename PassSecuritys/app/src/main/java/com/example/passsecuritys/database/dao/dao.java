package com.example.passsecuritys.database.dao;

import androidx.room.*;

import com.example.passsecuritys.User;

import java.util.List;

@Dao
public interface dao {
    @Insert
    void inserir(User U);

    @Delete
    void delete(User U);

    @Update
    void update(User U);

    @Query("SELECT * FROM User WHERE email = :email")
    User getUserEmail(String email);

    @Query("SELECT * FROM User")
    List<User> getAll();
}
