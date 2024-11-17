package com.example.passsecuritys.database;


import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.passsecuritys.Converter;
import com.example.passsecuritys.User;
import com.example.passsecuritys.database.dao.dao;

@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})

public abstract class Databse extends RoomDatabase {
    public abstract dao Userdao();

}
